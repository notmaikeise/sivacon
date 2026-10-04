# [SV29] Avaliar CI opcional para os pull requests

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P2 |
| Etapa | E1 |
| Frente | Infraestrutura |
| Escopo | Opcional |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `area:infra` |

## Objetivo

Apoiar a revisão automática quando houver comandos locais de verificação.

## Rastreabilidade

RNF03; C02

## Entregas

- [ ] Avaliar GitHub Actions dentro das condições gratuitas disponíveis.
- [ ] Se adotado, executar apenas verificações relevantes do backend/frontend.
- [ ] Manter comandos locais como caminho principal.

## Critérios de aceite

- [ ] Equipe registra se o apoio será adotado.
- [ ] Se adotado, workflow executa comandos existentes e verificáveis.
- [ ] Não há habilitação de gasto pago ou dependência para operação offline.
- [ ] Se adiado, motivo é registrado e o card não é tratado como funcionalidade implementada.

## Dependências

SV09, SV10

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

Decisão registrada e, se adotado, PR e execução do workflow.
