# SIVACon Backend

API Spring Boot do SIVACon. O backend usa PostgreSQL como fonte de dados e Flyway
para aplicar migrações versionadas.

## Organização do código

O backend segue a separação:

```text
src/main/java/br/com/sivacon/
├── catalog/       # controllers e contratos HTTP do catálogo
├── interest/      # controllers e contratos HTTP de interesse
├── service/       # regras de negócio, transações e orquestração
└── dao/           # consultas SQL e persistência PostgreSQL
```

Controllers não acessam o banco diretamente. Services coordenam o caso de uso
e DAOs concentram SQL e mapeamento de resultados. Novos módulos devem seguir o
mesmo padrão, mantendo seus controllers finos.

## Pré-requisitos

- Java 21
- Docker Desktop com Compose

## Subir PostgreSQL e pgAdmin

Na raiz do repositório:

```powershell
docker compose -f infra/docker-compose.yml up -d postgres pgadmin
```

Serviços locais:

| Serviço | Endereço | Credencial de exemplo |
| --- | --- | --- |
| PostgreSQL | `localhost:5432` | `sivacon` / `sivacon_dev` |
| pgAdmin | http://localhost:5050 | `admin@example.com` / `admin_dev` |

No pgAdmin, registre o servidor usando `postgres` como host (não `localhost`,
porque o pgAdmin roda dentro do Compose), porta `5432`, banco `sivacon`,
usuário `sivacon` e senha `sivacon_dev`.

## Executar a API

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Verificação:

```powershell
Invoke-WebRequest http://localhost:8080/actuator/health
```

As migrações são executadas automaticamente na inicialização. Para parar os
serviços sem remover os dados:

```powershell
docker compose -f infra/docker-compose.yml down
```

Para remover também o volume local do PostgreSQL, use `down -v` somente em
ambiente de desenvolvimento.
