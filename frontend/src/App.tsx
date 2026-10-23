import { FormEvent, useEffect, useState } from 'react'

type Vehicle = {
  id: string
  brand: string
  name: string
  price: number
  condition: string
  status: string
  description: string
}

type InventoryItem = Vehicle & {
  chassis: string
  published: boolean
}

const apiBase = import.meta.env.VITE_API_URL ?? 'http://localhost:8080'

export function App() {
  const [vehicles, setVehicles] = useState<Vehicle[]>([])
  const [brand, setBrand] = useState('')
  const [maxPrice, setMaxPrice] = useState('')
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [selectedVehicle, setSelectedVehicle] = useState<Vehicle | null>(null)
  const [submitted, setSubmitted] = useState('')
  const [admin, setAdmin] = useState(false)
  const [inventory, setInventory] = useState<InventoryItem[]>([])
  const [inventoryMessage, setInventoryMessage] = useState('')
  const [token, setToken] = useState(() => localStorage.getItem('sivacon_token') ?? '')
  const [loginError, setLoginError] = useState('')

  async function loadCatalog(event?: FormEvent) {
    event?.preventDefault()
    setLoading(true)
    setError('')
    const query = new URLSearchParams()
    if (brand) query.set('brand', brand)
    if (maxPrice) query.set('maxPrice', maxPrice)
    try {
      const response = await fetch(`${apiBase}/api/v1/catalog?${query}`)
      if (!response.ok) throw new Error('Não foi possível carregar o catálogo.')
      setVehicles(await response.json() as Vehicle[])
    } catch {
      setError('API indisponível. Suba o backend e o PostgreSQL para consultar dados reais.')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => { void loadCatalog() }, [])

  async function loadInventory(authToken = token) {
    const response = await fetch(`${apiBase}/api/v1/inventory`, {
      headers: { Authorization: `Bearer ${authToken}` },
    })
    if (response.ok) setInventory(await response.json() as InventoryItem[])
  }

  async function login(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    const form = new FormData(event.currentTarget)
    const response = await fetch(`${apiBase}/api/v1/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email: form.get('email'), password: form.get('password') }),
    })
    if (!response.ok) {
      setLoginError('E-mail ou senha inválidos.')
      return
    }
    const result = await response.json() as { token: string }
    localStorage.setItem('sivacon_token', result.token)
    setToken(result.token)
    setLoginError('')
    await loadInventory(result.token)
  }

  function logout() {
    localStorage.removeItem('sivacon_token')
    setToken('')
    setAdmin(false)
    setInventory([])
  }

  async function createInventory(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    const form = new FormData(event.currentTarget)
    const response = await fetch(`${apiBase}/api/v1/inventory`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
      body: JSON.stringify(Object.fromEntries(form.entries())),
    })
    if (!response.ok) {
      setInventoryMessage('Não foi possível cadastrar. Verifique os dados e o chassi.')
      return
    }
    event.currentTarget.reset()
    setInventoryMessage('Unidade cadastrada como rascunho.')
    await loadInventory()
  }

  async function togglePublication(item: InventoryItem) {
    await fetch(`${apiBase}/api/v1/inventory/${item.id}/publication`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
      body: JSON.stringify({ published: !item.published }),
    })
    await loadInventory()
    await loadCatalog()
  }

  async function submitInterest(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    if (!selectedVehicle) return
    const form = new FormData(event.currentTarget)
    const response = await fetch(`${apiBase}/api/v1/interests`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        vehicleId: selectedVehicle.id,
        name: form.get('name'),
        email: form.get('email'),
        phone: form.get('phone'),
        message: form.get('message'),
      }),
    })
    if (!response.ok) {
      setError('Não foi possível registrar o interesse. Tente novamente.')
      return
    }
    setSelectedVehicle(null)
    setSubmitted('Interesse registrado. Nossa equipe entrará em contato.')
  }

  return (
    <main>
      <header className="hero">
        <span className="eyebrow">SIVACon · demonstração</span>
        <h1>Encontre sua próxima moto.</h1>
        <p>Catálogo conectado à API e ao PostgreSQL local.</p>
        <button className="admin-toggle" type="button" onClick={() => { setAdmin(!admin); if (!admin) void loadInventory() }}>
          {admin ? 'Voltar para vitrine' : 'Abrir estoque'}
        </button>
      </header>
      {admin ? (
        <section className="admin-panel">
          {!token ? (
            <>
              <h2>Acesso ao estoque</h2>
              <form className="login-form" onSubmit={login}>
                <label>E-mail<input name="email" type="email" required defaultValue="admin@example.com" /></label>
                <label>Senha<input name="password" type="password" required defaultValue="password" /></label>
                <button type="submit">Entrar</button>
              </form>
              {loginError && <p className="notice error">{loginError}</p>}
              <p className="muted">Usuário local: admin@example.com / password</p>
            </>
          ) : (
            <>
          <div className="panel-heading"><div><h2>Estoque</h2><p className="muted">Sessão autenticada para desenvolvimento.</p></div><button className="logout" type="button" onClick={logout}>Sair</button></div>
          <form className="inventory-form" onSubmit={createInventory}>
            <input name="brand" required placeholder="Marca" />
            <input name="name" required placeholder="Modelo" />
            <input name="engine" placeholder="Motorização" />
            <input name="category" placeholder="Categoria" />
            <input name="chassis" required placeholder="Chassi" />
            <input name="price" required type="number" min="0" step="0.01" placeholder="Preço" />
            <select name="condition" defaultValue="NEW"><option value="NEW">Nova</option><option value="USED">Usada</option></select>
            <input name="description" placeholder="Descrição" />
            <button type="submit">Cadastrar unidade</button>
          </form>
          {inventoryMessage && <p className="notice success">{inventoryMessage}</p>}
          <div className="inventory-list">
            {inventory.map((item) => (
              <article className="inventory-row" key={item.id}>
                <div><strong>{item.brand} {item.name}</strong><small>Chassi: {item.chassis} · {item.status}</small></div>
                <button type="button" onClick={() => void togglePublication(item)}>
                  {item.published ? 'Retirar do catálogo' : 'Publicar'}
                </button>
              </article>
            ))}
          </div>
            </>
          )}
        </section>
      ) : (
        <>
      <section className="filters" aria-label="Filtros do catálogo">
        <form onSubmit={loadCatalog}>
          <label>Marca<input value={brand} onChange={(e) => setBrand(e.target.value)} placeholder="Honda" /></label>
          <label>Preço máximo<input type="number" min="0" value={maxPrice} onChange={(e) => setMaxPrice(e.target.value)} placeholder="30000" /></label>
          <button type="submit">Buscar motos</button>
        </form>
      </section>
      {error && <p className="notice error">{error}</p>}
      {submitted && <p className="notice success">{submitted}</p>}
      {loading ? <p className="notice">Carregando catálogo...</p> : (
        <section className="catalog">
          {vehicles.length === 0 ? <p className="notice">Nenhuma moto encontrada para estes filtros.</p> : vehicles.map((vehicle) => (
            <article className="card" key={vehicle.id}>
              <div className="bike-placeholder" aria-hidden="true">🏍</div>
              <div className="card-body">
                <span className="tag">{vehicle.condition === 'NEW' ? 'Nova' : 'Usada'}</span>
                <h2>{vehicle.brand} {vehicle.name}</h2>
                <p>{vehicle.description}</p>
                <strong>{vehicle.price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</strong>
                <button className="secondary" type="button" onClick={() => setSelectedVehicle(vehicle)}>Tenho interesse</button>
              </div>
            </article>
          ))}
        </section>
      )}
      {selectedVehicle && (
        <div className="modal-backdrop">
          <form className="interest-modal" onSubmit={submitInterest}>
            <button className="close" type="button" onClick={() => setSelectedVehicle(null)} aria-label="Fechar">×</button>
            <span className="tag">Interesse em {selectedVehicle.brand} {selectedVehicle.name}</span>
            <h2>Como podemos falar com você?</h2>
            <label>Nome<input name="name" required maxLength={160} /></label>
            <label>E-mail<input name="email" type="email" required maxLength={254} /></label>
            <label>Telefone<input name="phone" maxLength={32} /></label>
            <label>Mensagem<textarea name="message" required maxLength={2000} defaultValue="Gostaria de saber mais sobre esta moto." /></label>
            <button type="submit">Enviar interesse</button>
          </form>
        </div>
      )}
        </>
      )}
    </main>
  )
}
