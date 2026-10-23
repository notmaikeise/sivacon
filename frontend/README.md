# SIVACon Frontend

Frontend React/Vite do primeiro fluxo de catálogo e estoque. Ele consulta a API
real para catálogo, cadastro e publicação de unidades; não há dados mockados.

```powershell
npm install
npm run dev
```

Para apontar para outra API:

```powershell
$env:VITE_API_URL = "http://localhost:8080"
npm run dev
```

O build de produção é validado com `npm run build`.
