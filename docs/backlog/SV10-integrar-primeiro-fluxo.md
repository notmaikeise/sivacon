# [SV10] Inicializar frontend e integrar um fluxo mínimo

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E1 |
| Frente | Fullstack |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `area:backend`, `area:frontend` |

## Objetivo

Comprovar cedo que navegador, API e banco funcionam juntos.

## Rastreabilidade

RF02; RNF03, RNF06; R05

## Entregas

- [ ] React/TypeScript/Vite com rotas e componentes iniciais.
- [ ] Lockfile e comandos de execução versionados.
- [ ] Uma tela mínima com dados fictícios lidos pela API e banco.
- [ ] Estados de carregamento, erro e vazio do fluxo.

## Critérios de aceite

- [ ] Tela consome a API real com persistência, sem depender apenas de mock.
- [ ] Contrato do fluxo corresponde a SV08.
- [ ] Outra pessoa consegue executar frontend e backend pelo manual.
- [ ] Ainda não há alegação de catálogo/MVP completo.

## Dependências

SV08, SV09

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e demonstração do fluxo mínimo integrado.
