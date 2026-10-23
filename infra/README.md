# Infraestrutura local

O Compose deste diretório fornece o PostgreSQL persistente e o pgAdmin para
inspeção gráfica do banco durante o desenvolvimento.

```powershell
docker compose -f infra/docker-compose.yml up -d
docker compose -f infra/docker-compose.yml ps
docker compose -f infra/docker-compose.yml logs -f postgres
```

Os dados ficam no volume Docker `sivacon-postgres-data`. Credenciais reais não
devem ser commitadas; os valores do arquivo Compose são apenas para o ambiente
local de desenvolvimento.
