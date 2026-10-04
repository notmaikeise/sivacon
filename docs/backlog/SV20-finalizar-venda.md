# [SV20] Finalizar venda fictícia com controle de concorrência

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

Concluir a negociação sem duplicar a venda ou deixar estoque inconsistente.

## Rastreabilidade

RF08, RF09; HU06; RN03, RN05, RN06, RN08, RN13; RNF02; CT06

## Entregas

- [ ] Fechamento transacional e controle de concorrência.
- [ ] Validação de aprovação, validade e disponibilidade no servidor.
- [ ] Atualização do catálogo, CRM e acompanhamento do comprador.
- [ ] Resposta clara para conflito ou falha.

## Critérios de aceite

- [ ] Venda e alteração de estoque são gravadas juntas.
- [ ] Duas tentativas simultâneas geram só uma venda válida.
- [ ] Proposta vencida, não aprovada, unidade indisponível e crédito pendente são rejeitados.
- [ ] Falha durante fechamento não deixa gravação parcial.
- [ ] As três áreas refletem o estado final coerente.

## Dependências

SV12, SV17, SV19

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e CT06 com PostgreSQL, concorrência e rollback.
