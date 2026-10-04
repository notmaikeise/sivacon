# [SV09] Inicializar backend, PostgreSQL e migrações

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E1 |
| Frente | Backend |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `area:backend`, `area:infra` |

## Objetivo

Criar uma base executável e reproduzível para o backend.

## Rastreabilidade

RF01-RF10; RNF03, RNF06-RNF07

## Entregas

- [ ] Projeto Java/Spring Boot com Maven Wrapper e módulos iniciais.
- [ ] Banco local de desenvolvimento e migrações versionadas.
- [ ] Configuração de exemplo sem segredos e dados fictícios.
- [ ] Comandos reais de início e verificação no README do backend.

## Critérios de aceite

- [ ] Outra pessoa compila e inicia com as instruções.
- [ ] Migrações criam o banco e funcionam no reinício sem inconsistência.
- [ ] Maven Wrapper e arquivos necessários estão no Git.
- [ ] Credenciais e dados locais não entram no commit.

## Dependências

SV05, SV07

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e execução do ambiente por outro integrante.
