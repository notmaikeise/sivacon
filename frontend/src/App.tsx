import { FormEvent, useEffect, useState } from 'react'

type Session = { token: string; role: string; displayName: string; email: string }
type Vehicle = {
  id: string; brand: string; name: string; engine?: string; category?: string
  price: number; condition: string; status: string; description?: string
  published: boolean; available: boolean
}
type InventoryItem = Vehicle & { chassis: string; modelId?: string }
type BuyerArea = {
  profile: { fullName: string; email?: string; phone?: string }
  interests: Array<{ id: string; stage: string; brand?: string; name?: string; createdAt?: string }>
  proposals: Array<{ id: string; status: string; brand?: string; name?: string; negotiatedPrice?: number }>
  purchases: Array<{ id: string; status: string; brand?: string; name?: string }>
}

const apiBase = import.meta.env.VITE_API_URL ?? 'http://localhost:8080'
const SESSION_KEY = 'sivacon_session'

function readSession(): Session | null {
  try { return JSON.parse(localStorage.getItem(SESSION_KEY) ?? 'null') as Session | null } catch { return null }
}
function money(value: number) {
  return Number(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })
}

export function App() {
  const [session, setSession] = useState<Session | null>(() => readSession())
  const [view, setView] = useState<'catalog' | 'admin' | 'buyer'>('catalog')
  const [vehicles, setVehicles] = useState<Vehicle[]>([])
  const [filters, setFilters] = useState({ brand: '', category: '', condition: '', minPrice: '', maxPrice: '' })
  const [catalogState, setCatalogState] = useState<'idle' | 'loading' | 'error'>('loading')
  const [catalogError, setCatalogError] = useState('')
  const [notice, setNotice] = useState<{ type: 'success' | 'error'; text: string } | null>(null)
  const [selectedVehicle, setSelectedVehicle] = useState<Vehicle | null>(null)
  const [interestOpen, setInterestOpen] = useState(false)
  const [adminLoginError, setAdminLoginError] = useState('')
  const [buyerMode, setBuyerMode] = useState<'login' | 'register'>('login')
  const [buyerError, setBuyerError] = useState('')
  const [buyerArea, setBuyerArea] = useState<BuyerArea | null>(null)
  const [buyerLoading, setBuyerLoading] = useState(false)
  const [inventory, setInventory] = useState<InventoryItem[]>([])
  const [inventoryLoading, setInventoryLoading] = useState(false)
  const [inventorySaving, setInventorySaving] = useState(false)
  const [inventoryError, setInventoryError] = useState('')

  async function request<T>(path: string, init?: RequestInit): Promise<T> {
    const response = await fetch(`${apiBase}${path}`, init)
    const text = await response.text()
    let body: unknown = null
    try { body = text ? JSON.parse(text) : null } catch { body = text }
    if (!response.ok) {
      const message = typeof body === 'object' && body !== null
        ? String((body as { detail?: string; message?: string }).detail ?? (body as { message?: string }).message ?? '')
        : ''
      throw new Error(message || `Não foi possível concluir a operação (${response.status}).`)
    }
    return body as T
  }
  const authHeaders = (token = session?.token): Record<string, string> =>
    token ? { Authorization: `Bearer ${token}` } : {}

  async function loadCatalog(event?: FormEvent) {
    event?.preventDefault()
    setCatalogState('loading'); setCatalogError('')
    const query = new URLSearchParams()
    Object.entries(filters).forEach(([key, value]) => { if (value.trim()) query.set(key, value.trim()) })
    try {
      setVehicles(await request<Vehicle[]>(`/api/v1/catalog?${query.toString()}`))
      setCatalogState('idle')
    } catch (error) {
      setCatalogState('error'); setCatalogError(error instanceof Error ? error.message : 'Catálogo indisponível.')
    }
  }
  useEffect(() => { void loadCatalog() }, [])

  function saveSession(result: { token: string; role: string; displayName: string; email: string }) {
    const next = { token: result.token, role: result.role, displayName: result.displayName, email: result.email }
    localStorage.setItem(SESSION_KEY, JSON.stringify(next)); setSession(next)
    setAdminLoginError(''); setBuyerError('')
  }
  function logout() {
    localStorage.removeItem(SESSION_KEY); localStorage.removeItem('sivacon_token')
    setSession(null); setBuyerArea(null); setInventory([]); setView('catalog')
  }

  async function adminLogin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setAdminLoginError('')
    const data = new FormData(event.currentTarget)
    try {
      const result = await request<Session>('/api/v1/auth/login', {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: data.get('email'), password: data.get('password') }),
      })
      if (!['ADMIN', 'MANAGER', 'STOCK'].includes(result.role)) throw new Error('Esta conta não possui acesso ao estoque.')
      saveSession(result); setView('admin'); await loadInventory(result.token)
    } catch (error) { setAdminLoginError(error instanceof Error ? error.message : 'Falha ao entrar.') }
  }

  async function buyerAuth(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setBuyerError('')
    const data = new FormData(event.currentTarget)
    const password = String(data.get('password') ?? '')
    if (password.length < 8) { setBuyerError('A senha deve ter pelo menos 8 caracteres.'); return }
    const payload = buyerMode === 'register'
      ? { email: data.get('email'), password, fullName: data.get('fullName'), phone: data.get('phone') }
      : { email: data.get('email'), password }
    try {
      const result = await request<Session>(`/api/v1/auth/${buyerMode === 'register' ? 'register' : 'login'}`, {
        method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(payload),
      })
      if (result.role !== 'BUYER') throw new Error('Use a área de estoque para esta conta.')
      saveSession(result); await loadBuyerArea(result.token)
    } catch (error) { setBuyerError(error instanceof Error ? error.message : 'Falha ao autenticar.') }
  }

  async function loadBuyerArea(token = session?.token) {
    if (!token) return
    setBuyerLoading(true); setBuyerError('')
    try { setBuyerArea(await request<BuyerArea>('/api/v1/me', { headers: authHeaders(token) })) }
    catch (error) { setBuyerArea(null); setBuyerError(error instanceof Error ? error.message : 'Não foi possível carregar sua área.') }
    finally { setBuyerLoading(false) }
  }
  async function openBuyerArea() {
    setView('buyer')
    if (session?.role === 'BUYER') await loadBuyerArea()
  }

  async function loadInventory(token = session?.token) {
    if (!token) return
    setInventoryLoading(true); setInventoryError('')
    try { setInventory(await request<InventoryItem[]>('/api/v1/inventory', { headers: authHeaders(token) })) }
    catch (error) { setInventoryError(error instanceof Error ? error.message : 'Não foi possível carregar o estoque.') }
    finally { setInventoryLoading(false) }
  }
  async function createInventory(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setInventoryError('')
    const data = new FormData(event.currentTarget)
    const price = Number(data.get('price'))
    const brand = String(data.get('brand') ?? '').trim()
    const name = String(data.get('name') ?? '').trim()
    const chassis = String(data.get('chassis') ?? '').trim()
    if (!brand || !name || !chassis || !Number.isFinite(price) || price < 0) {
      setInventoryError('Preencha marca, modelo, chassi e um preço válido.')
      return
    }
    if (!session || !['ADMIN', 'MANAGER', 'STOCK'].includes(session.role)) {
      setInventoryError('Faça login com um perfil autorizado para cadastrar unidades.')
      return
    }
    setInventorySaving(true)
    try {
      await request('/api/v1/inventory', {
        method: 'POST', headers: { 'Content-Type': 'application/json', ...authHeaders() },
        body: JSON.stringify({
          brand,
          name,
          engine: String(data.get('engine') ?? '').trim() || null,
          category: String(data.get('category') ?? '').trim() || null,
          chassis,
          price,
          condition: String(data.get('condition') ?? 'NEW'),
          description: String(data.get('description') ?? '').trim() || null,
        }),
      })
      event.currentTarget.reset(); setNotice({ type: 'success', text: 'Unidade cadastrada como rascunho.' }); await loadInventory()
    } catch (error) { setInventoryError(error instanceof Error ? error.message : 'Não foi possível cadastrar a unidade.') }
    finally { setInventorySaving(false) }
  }
  async function togglePublication(item: InventoryItem) {
    try {
      await request(`/api/v1/inventory/${item.id}/publication`, {
        method: 'PATCH', headers: { 'Content-Type': 'application/json', ...authHeaders() },
        body: JSON.stringify({ published: !item.published }),
      })
      await Promise.all([loadInventory(), loadCatalog()]); setNotice({ type: 'success', text: item.published ? 'Unidade retirada do catálogo.' : 'Unidade publicada.' })
    } catch (error) { setInventoryError(error instanceof Error ? error.message : 'Não foi possível alterar a publicação.') }
  }
  async function submitInterest(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!selectedVehicle) return
    const data = new FormData(event.currentTarget)
    try {
      await request('/api/v1/interests', {
        method: 'POST', headers: { 'Content-Type': 'application/json', ...authHeaders() },
        body: JSON.stringify({ vehicleId: selectedVehicle.id, name: data.get('name'), email: data.get('email'), phone: data.get('phone'), message: data.get('message') }),
      })
      setSelectedVehicle(null); setInterestOpen(false); setNotice({ type: 'success', text: 'Interesse registrado. Nossa equipe entrará em contato.' })
      if (session?.role === 'BUYER') await loadBuyerArea()
    } catch (error) { setNotice({ type: 'error', text: error instanceof Error ? error.message : 'Não foi possível registrar o interesse.' }) }
  }

  function showVehicle(vehicle: Vehicle, interest = false) { setSelectedVehicle(vehicle); setInterestOpen(interest) }
  const authenticatedBuyer = session?.role === 'BUYER'

  return <main>
    <header className="hero">
      <span className="eyebrow">SIVACon · demonstração</span>
      <h1>Encontre sua próxima moto.</h1>
      <p>Catálogo conectado à API e ao PostgreSQL local.</p>
      <nav className="hero-actions" aria-label="Navegação">
        <button className="admin-toggle" type="button" onClick={() => { setView(view === 'catalog' ? 'admin' : 'catalog'); if (view === 'catalog' && session && !authenticatedBuyer) void loadInventory() }}>
          {view === 'admin' ? 'Voltar para vitrine' : 'Abrir estoque'}
        </button>
        <button className="admin-toggle" type="button" onClick={() => void openBuyerArea()}>{view === 'buyer' ? 'Voltar para vitrine' : 'Minha área'}</button>
        {session && <button className="logout" type="button" onClick={logout}>Sair ({session.displayName})</button>}
      </nav>
    </header>

    {notice && <p className={`notice ${notice.type}`} role="status">{notice.text}<button type="button" className="notice-close" onClick={() => setNotice(null)}>×</button></p>}

    {view === 'admin' ? <section className="admin-panel">
      {!session || authenticatedBuyer ? <><h2>Acesso ao estoque</h2>
        <form className="login-form" onSubmit={adminLogin}>
          <label>E-mail<input name="email" type="email" required autoComplete="email" defaultValue="admin@example.com" /></label>
          <label>Senha<input name="password" type="password" required autoComplete="current-password" defaultValue="password" /></label>
          <button type="submit">Entrar</button>
        </form>
        {adminLoginError && <p className="notice error">{adminLoginError}</p>}
      </> : <><div className="panel-heading"><div><h2>Estoque</h2><p className="muted">Sessão: {session.role}</p></div></div>
        <form className="inventory-form" onSubmit={createInventory}>
          <label>Marca<input name="brand" required maxLength={100} /></label><label>Modelo<input name="name" required maxLength={120} /></label>
          <label>Motorização<input name="engine" maxLength={120} /></label><label>Categoria<input name="category" maxLength={80} /></label>
          <label>Chassi<input name="chassis" required maxLength={80} /></label><label>Preço<input name="price" required type="number" min="0" step="0.01" /></label>
          <label>Condição<select name="condition" defaultValue="NEW"><option value="NEW">Nova</option><option value="USED">Usada</option></select></label>
          <label>Descrição<input name="description" maxLength={2000} /></label><button type="submit" disabled={inventorySaving}>{inventorySaving ? 'Cadastrando...' : 'Cadastrar unidade'}</button>
        </form>
        {inventoryError && <p className="notice error">{inventoryError}</p>}{inventoryLoading && <p className="notice">Carregando estoque...</p>}
        <div className="inventory-list">{inventory.map(item => <article className="inventory-row" key={item.id}><div><strong>{item.brand} {item.name}</strong><small>Chassi: {item.chassis} · {item.status}</small></div><button type="button" onClick={() => void togglePublication(item)}>{item.published ? 'Retirar do catálogo' : 'Publicar'}</button></article>)}</div>
      </>}
    </section> : view === 'buyer' ? <section className="admin-panel">
      {!session || !authenticatedBuyer ? <><h2>{buyerMode === 'register' ? 'Criar conta de comprador' : 'Entrar na minha área'}</h2>
        <form className="login-form" onSubmit={buyerAuth}>
          {buyerMode === 'register' && <label>Nome completo<input name="fullName" required maxLength={160} autoComplete="name" /></label>}
          <label>E-mail<input name="email" type="email" required maxLength={254} autoComplete="email" /></label><label>Senha<input name="password" type="password" required minLength={8} autoComplete={buyerMode === 'register' ? 'new-password' : 'current-password'} /></label>
          {buyerMode === 'register' && <label>Telefone<input name="phone" maxLength={32} autoComplete="tel" /></label>}<button type="submit">{buyerMode === 'register' ? 'Criar conta' : 'Entrar'}</button>
        </form><button type="button" className="secondary" onClick={() => { setBuyerMode(buyerMode === 'login' ? 'register' : 'login'); setBuyerError('') }}>{buyerMode === 'login' ? 'Ainda não tenho conta' : 'Já tenho conta'}</button>
        {buyerError && <p className="notice error">{buyerError}</p>}
      </> : buyerLoading ? <p className="notice">Carregando sua área...</p> : buyerArea && <><div className="panel-heading"><div><h2>Olá, {buyerArea.profile.fullName}</h2><p className="muted">{buyerArea.profile.email}</p></div></div>
        <h3>Atendimentos</h3>{buyerArea.interests.length ? <ul>{buyerArea.interests.map(item => <li key={item.id}>{item.brand} {item.name} · {item.stage}</li>)}</ul> : <p className="muted">Nenhum atendimento registrado.</p>}
        <h3>Propostas</h3>{buyerArea.proposals.length ? <ul>{buyerArea.proposals.map(item => <li key={item.id}>{item.brand} {item.name} · {item.status} {item.negotiatedPrice != null && money(item.negotiatedPrice)}</li>)}</ul> : <p className="muted">Nenhuma proposta disponível.</p>}
        <h3>Compras</h3>{buyerArea.purchases.length ? <ul>{buyerArea.purchases.map(item => <li key={item.id}>{item.brand} {item.name} · {item.status}</li>)}</ul> : <p className="muted">Nenhuma compra registrada.</p>}
      </>}
    </section> : <>
      <section className="filters" aria-label="Filtros do catálogo"><form onSubmit={loadCatalog}>
        <label>Marca<input value={filters.brand} onChange={e => setFilters({ ...filters, brand: e.target.value })} placeholder="Honda" /></label>
        <label>Categoria<input value={filters.category} onChange={e => setFilters({ ...filters, category: e.target.value })} placeholder="Street" /></label>
        <label>Condição<select value={filters.condition} onChange={e => setFilters({ ...filters, condition: e.target.value })}><option value="">Todas</option><option value="NEW">Nova</option><option value="USED">Usada</option></select></label>
        <label>Preço mínimo<input type="number" min="0" value={filters.minPrice} onChange={e => setFilters({ ...filters, minPrice: e.target.value })} /></label>
        <label>Preço máximo<input type="number" min="0" value={filters.maxPrice} onChange={e => setFilters({ ...filters, maxPrice: e.target.value })} /></label><button type="submit">Buscar motos</button>
      </form></section>
      {catalogState === 'error' && <p className="notice error">{catalogError}</p>}{catalogState === 'loading' && <p className="notice">Carregando catálogo...</p>}
      {catalogState !== 'loading' && <section className="catalog">{vehicles.length === 0 ? <p className="notice">Nenhuma moto encontrada para estes filtros.</p> : vehicles.map(vehicle => <article className="card" key={vehicle.id}>
        <div className="bike-placeholder" aria-hidden="true">🏍</div><div className="card-body"><span className="tag">{vehicle.condition === 'NEW' ? 'Nova' : 'Usada'}</span><h2>{vehicle.brand} {vehicle.name}</h2>
          {(vehicle.engine || vehicle.category) && <small>{vehicle.engine} {vehicle.category && `· ${vehicle.category}`}</small>}<p>{vehicle.description || 'Consulte a equipe para mais detalhes.'}</p><strong>{money(vehicle.price)}</strong>
          <span className={vehicle.available ? 'availability available' : 'availability'}>{vehicle.available ? 'Disponível' : 'Indisponível'}</span><button className="secondary" type="button" onClick={() => showVehicle(vehicle)}>Ver detalhes</button>
        </div></article>)}</section>}
    </>}

    {selectedVehicle && <div className="modal-backdrop" role="presentation" onMouseDown={e => { if (e.target === e.currentTarget) { setSelectedVehicle(null); setInterestOpen(false) } }}>
      {interestOpen ? <form className="interest-modal" onSubmit={submitInterest} onMouseDown={e => e.stopPropagation()}><button className="close" type="button" onClick={() => { setSelectedVehicle(null); setInterestOpen(false) }} aria-label="Fechar">×</button>
        <span className="tag">Interesse em {selectedVehicle.brand} {selectedVehicle.name}</span><h2>Como podemos falar com você?</h2><label>Nome<input name="name" required maxLength={160} /></label><label>E-mail<input name="email" type="email" required maxLength={254} /></label><label>Telefone<input name="phone" maxLength={32} /></label><label>Mensagem<textarea name="message" required maxLength={2000} defaultValue="Gostaria de saber mais sobre esta moto." /></label><button type="submit">Enviar interesse</button>
      </form> : <section className="interest-modal" onMouseDown={e => e.stopPropagation()}><button className="close" type="button" onClick={() => setSelectedVehicle(null)} aria-label="Fechar">×</button><span className="tag">{selectedVehicle.brand} {selectedVehicle.name}</span><h2>Detalhes da moto</h2><p>{selectedVehicle.description || 'Consulte a equipe para mais detalhes.'}</p><p><strong>{money(selectedVehicle.price)}</strong></p><p className={selectedVehicle.available ? 'availability available' : 'availability'}>{selectedVehicle.available ? 'Disponível para interesse' : 'Indisponível no momento'}</p>{selectedVehicle.available && <button type="button" onClick={() => setInterestOpen(true)}>Tenho interesse</button>}</section>}
    </div>}
  </main>
}
