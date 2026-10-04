# SIVACon

**Sistema Inteligente de Vendas Automotivas para Concessionárias**

Projeto acadêmico que conecta uma vitrine de veículos, a área do comprador e um CRM para funcionários. O primeiro cenário será uma concessionária fictícia de motos.

**Situação inicial:** planejamento e modelagem em revisão pela equipe. Este primeiro pacote contém documentação e organização do trabalho; a aplicação ainda será implementada.

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

## Tecnologias propostas

| Finalidade | Proposta |
| --- | --- |
| Backend | Java 21, Spring Boot, Spring Web, Spring Data JPA/Hibernate e Spring Security |
| Build e migrações | Maven Wrapper e Flyway Community |
| Frontend | React, TypeScript, Vite, React Router, HTML e CSS/CSS Modules |
| Dados | PostgreSQL e arquivos locais para fotos |
| Instalação | Docker/Compose e servidor de arquivos/proxy local, como Nginx |
| Contratos e testes | OpenAPI, JUnit/Mockito, Vitest/Testing Library e Playwright, conforme cada incremento |
| Colaboração | Git, GitHub Issues, Projects e pull requests |

As versões exatas e a estratégia de autenticação serão registradas na decisão técnica antes do bootstrap. GitHub Actions e benefícios estudantis são apoios opcionais.

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
- [GitHub Projects](docs/13-github-projects.md) e [cards do backlog](docs/backlog/README.md)

## Trabalho em equipe

Cada tarefa deve ter uma issue, uma pessoa responsável e critérios de aceite. As mudanças serão feitas em branches e revisadas por pull request antes de entrar na `main`. Consulte [CONTRIBUTING.md](CONTRIBUTING.md).

## Execução

Os comandos de instalação serão publicados quando backend, frontend e infraestrutura existirem. Hoje, `backend/`, `frontend/` e `infra/` possuem apenas orientações para a implementação.

## Base acadêmica e autoria

Os documentos aplicam ao SIVACon os fundamentos de requisitos, processo incremental, prototipação, premissas, restrições e riscos sintetizados no plano de modelagem fornecido pela equipe.

A organização inicial foi preparada com auxílio de IA a partir dos dois PDFs de referência. Decisões, revisão e implementação cabem à equipe. Nomes, papéis e exigências formais da disciplina ainda serão confirmados.

Referências e precedência entre documentos: [docs/14-referencias.md](docs/14-referencias.md).

## Licença

A equipe ainda definirá as condições de distribuição do código. Este pacote não adiciona uma licença automaticamente.
