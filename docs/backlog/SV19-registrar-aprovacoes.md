# [SV19] Registrar aprovações e estados financeiros fictícios

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

Controlar exceções de desconto e crédito e distinguir previsão de confirmação.

## Rastreabilidade

RF06-RF08; HU05; RN07, RN12, RN13; CT06, CT07

## Entregas

- [ ] Aprovação gerencial de desconto e decisão de crédito fictícia.
- [ ] Autor, data, decisão e versão da proposta associados.
- [ ] Estado previsto do Pix agendado e confirmação fictícia conforme política.

## Critérios de aceite

- [ ] Desconto acima do limite exige aprovação autorizada.
- [ ] Proposta financiada sem crédito aprovado permanece impedida de fechar.
- [ ] Data de Pix agendado não é tratada como pagamento confirmado.
- [ ] Mudança de condições segue a política de versão e nova aprovação.
- [ ] Comprador acompanha os estados pertinentes sem acesso à administração.

## Dependências

SV04, SV17, SV18

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e CT07, com cenários de crédito pendente de CT06.
