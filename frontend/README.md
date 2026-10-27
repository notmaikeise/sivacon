# SIVACon Frontend

Frontend React/Vite do incremento E2. Ele consulta a API real para catálogo,
cadastro e publicação de unidades, autenticação e área do comprador; não há
dados mockados.

```powershell
npm install
npm run dev
```

Para apontar para outra API:

```powershell
$env:VITE_API_URL = "http://localhost:8080"
npm run dev
```

O build de produção é validado com `npm run build`. O script `npm test` está
configurado para Vitest, mas ainda não há testes frontend versionados.
