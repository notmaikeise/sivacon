# SIVACon

**Sistema Inteligente de Vendas Automotivas para Concessionárias**

Projeto acadêmico que conecta uma vitrine de veículos, a área do comprador e um CRM para funcionários. O primeiro cenário será uma concessionária fictícia de motos.

**Situação atual:** incremento E2 implementado e executável localmente. O
projeto já possui PostgreSQL, pgAdmin, migrações Flyway, catálogo público,
estoque protegido, autenticação, cadastro de compradores e área do comprador.

## Objetivo

Demonstrar uma jornada integrada: cadastrar uma moto, publicar no catálogo, receber um interesse, registrar atendimento, enviar uma proposta com simulação e concluir uma venda fictícia.

## Áreas do produto

| Área | Uso previsto |
| --- | --- |
| Vitrine | Catálogo, filtros, detalhes e início de interesse, sem login. |
| Comprador | Conta, atendimentos, propostas e andamento da compra. |
| CRM | Estoque, contatos, funil, negociação e fechamento. |
| Administração | Usuários, perfis, configuração da loja e resumo básico de vendas. |

## Escopo e limites

- Hospedagem final em computador existente, com acesso local ou pela rede local a confirmar.
- Ferramentas essenciais gratuitas, sem contratação de hospedagem, domínio, banco em nuvem ou API paga.
- Dados fictícios e pagamentos e crédito simulados.
- Uma base reutilizável, com personalização por configuração; isolamento por instalação ainda sujeito à aprovação técnica.
- Busca inteligente, segunda instalação e recursos adicionais em incrementos futuros.

“Vitrine pública” significa acesso sem autenticação no ambiente local. Acesso externo pela internet depende de novo planejamento.

## Tecnologias

| Finalidade | Proposta |
| --- | --- |
| Backend | Java 21, Spring Boot, Spring Web, Spring Data JPA/Hibernate e Spring Security |
| Build e migrações | Maven Wrapper e Flyway Community |
| Frontend | React, TypeScript, Vite, React Router, HTML e CSS/CSS Modules |
| Dados | PostgreSQL e arquivos locais para fotos |
| Instalação | Docker/Compose e servidor de arquivos/proxy local, como Nginx |
| Contratos e testes | OpenAPI, JUnit/Mockito, Vitest/Testing Library e Playwright, conforme cada incremento |
| Colaboração | Git, GitHub Issues, Projects e pull requests |

Java 21, Spring Boot 3.4, Maven Wrapper, Flyway 10, PostgreSQL 16,
React 18, TypeScript, Vite e Docker Compose estão configurados no incremento
atual. OpenAPI e as bibliotecas dos módulos comerciais entrarão nos próximos
incrementos após validação do contrato.

## Documentação

Comece pelo [índice dos documentos](docs/README.md).

- [Visão e escopo](docs/01-visao-e-escopo.md)
- [Requisitos](docs/03-requisitos.md) e [regras de negócio](docs/04-regras-de-negocio.md)
- [Histórias de usuário](docs/05-historias-de-usuario.md)
- [Arquitetura proposta](docs/06-arquitetura.md) e [modelo conceitual](docs/07-modelagem-de-dados.md)
- [Testes e rastreabilidade](docs/09-testes-e-rastreabilidade.md)
- [Planejamento e riscos](docs/10-planejamento-e-riscos.md)
- [Equipe e decisões](docs/11-equipe-e-decisoes.md)
- [Operação local](docs/12-operacao-local.md)
- [GitHub Projects](docs/13-github-projects.md) e o backlog descrito no planejamento

## Trabalho em equipe

Cada tarefa deve ter uma issue, uma pessoa responsável e critérios de aceite. As mudanças serão feitas em branches e revisadas por pull request antes de entrar na `main`. Consulte [CONTRIBUTING.md](CONTRIBUTING.md).

## Instalação e execução local

### Pré-requisitos

- Git
- Java 21
- Docker Desktop com Docker Compose
- Node.js e npm

Clone o repositório e entre na pasta do projeto:

```powershell
git clone https://github.com/notmaikeise/sivacon.git
cd sivacon
```

Instale as dependências do frontend:

```powershell
cd frontend
npm install
cd ..
```

1. Inicie o PostgreSQL e o pgAdmin:

   ```powershell
   docker compose -f infra/docker-compose.yml up -d
   ```

2. Inicie a API (Java 21):

   ```powershell
   cd backend
   .\mvnw.cmd spring-boot:run
   ```

3. Em outro terminal, inicie a interface:

   ```powershell
   cd frontend
   npm run dev
   ```

   Acesse http://localhost:5173. O pgAdmin fica em http://localhost:5050;
   credenciais e configuração estão em [backend/README.md](backend/README.md).

## Estado atual — 27/10/2026

O incremento E2 está implementado: catálogo público com filtros, registro de
interesse, estoque, autenticação por token, perfis e área autenticada do
comprador. A área do comprador consulta interesses, propostas e compras sempre
pela identidade da sessão. O CRM completo, propostas, simulação, aprovações e
venda continuam no incremento E3.

## Testes e validação

Backend:

```powershell
cd backend
.\mvnw.cmd test
```

Frontend:

```powershell
cd frontend
npm run build
```

O comando `npm test` está configurado, mas ainda não há testes frontend
versionados; por isso o Vitest encerra com `No test files found`.

Verificação opcional da API:

```powershell
Invoke-WebRequest http://localhost:8080/actuator/health
```

As entregas seguintes seguirão o planejamento em fatias integradas:

1. **E3 — Jornada comercial:** CRM/funil, propostas versionadas, simulação,
   aprovações e fechamento transacional.
2. **E4 — Entrega:** testes de jornada, backup/restauração, empacotamento local
   e manual de instalação.
3. **E5 — Evoluções:** segunda instalação, busca por regras e funcionalidades
   adicionais aprovadas.

As regras comerciais marcadas como propostas em
`docs/04-regras-de-negocio.md` serão parametrizadas após validação da equipe.

## Base acadêmica e autoria

Os documentos aplicam ao SIVACon os fundamentos de requisitos, processo incremental, prototipação, premissas, restrições e riscos sintetizados no plano de modelagem fornecido pela equipe.

A organização inicial foi preparada com auxílio de IA a partir dos dois PDFs de referência. Decisões, revisão e implementação cabem à equipe. Nomes, papéis e exigências formais da disciplina ainda serão confirmados.

Referências e precedência entre documentos: [docs/14-referencias.md](docs/14-referencias.md).

## Licença

A equipe ainda definirá as condições de distribuição do código. Este pacote não adiciona uma licença automaticamente.
