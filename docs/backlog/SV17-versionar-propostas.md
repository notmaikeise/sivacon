# [SV17] Criar e versionar propostas comerciais

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

Preservar condições negociadas e disponibilizá-las ao comprador correto.

## Rastreabilidade

RF06, RF09; HU05; RN02, RN05, RN08; CT05, CT07

## Entregas

- [ ] Proposta com comprador, unidade, preço, desconto, validade e pagamento.
- [ ] Histórico de versões e estados aprovados.
- [ ] Consulta da proposta no CRM e na área do titular.

## Critérios de aceite

- [ ] Proposta possui exatamente um comprador e uma unidade.
- [ ] Alteração de condição preserva a versão anterior.
- [ ] Preço atualizado no catálogo não reescreve negociação histórica.
- [ ] Proposta vencida exige nova versão para fechamento.
- [ ] Outro comprador não consegue consultar a proposta.

## Dependências

SV04, SV12, SV16

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e cenários de consulta, versionamento e validade.
