# [SV05] Modelar dados e regras de integridade

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E1 |
| Frente | Modelagem |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:documentacao`, `area:negocio`, `area:backend` |

## Objetivo

Produzir o modelo lógico que sustenta estoque, negociação e fechamento.

## Rastreabilidade

RF01, RF04, RF06-RF08; RN01-RN03, RN09-RN10; RNF02-RNF03; D05

## Entregas

- [ ] Diagrama editável e dicionário de dados com campos e cardinalidades.
- [ ] Chaves, índices, unicidade, estados e precisão monetária.
- [ ] Persistência de versões, cronogramas e aprovações.
- [ ] Estratégia de integridade e transação do fechamento.

## Critérios de aceite

- [ ] Modelo distingue modelo comercial de unidade física.
- [ ] Chassi único e uma venda ativa por unidade têm mecanismos de integridade previstos.
- [ ] Proposta preserva valores/políticas da negociação.
- [ ] Decisão de concorrência não depende só de consultar disponibilidade antes de gravar.

## Dependências

SV03, SV04

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

Modelo e dicionário revisados pelo backend e por representante da equipe.
