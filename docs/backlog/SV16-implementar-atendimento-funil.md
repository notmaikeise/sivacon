# [SV16] Implementar atendimento, histórico e funil

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E3 |
| Frente | Fullstack |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:feature`, `area:backend`, `area:frontend` |

## Objetivo

Organizar a atividade do vendedor com histórico rastreável.

## Rastreabilidade

RF04, RF05; HU04; RNF01; CT04

## Entregas

- [ ] Cadastros comerciais e atendimentos recebidos do site ou registrados pelo vendedor.
- [ ] Contatos com autor/data e responsável.
- [ ] Funil com transições aprovadas.

## Critérios de aceite

- [ ] Contato preserva autor, data e conteúdo.
- [ ] Transição permitida altera etapa e registra histórico.
- [ ] Transição inválida não modifica o atendimento.
- [ ] Ações respeitam o perfil e a política de responsabilidade aprovados.

## Dependências

SV04, SV14

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e CT04 cobrindo contatos e transições.
