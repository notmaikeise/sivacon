# [SV18] Implementar simulações de pagamento

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

Mostrar valores e condições com cálculo acadêmico verificável.

## Rastreabilidade

RF07; HU05; RN09-RN11; CT05

## Entregas

- [ ] À vista, Pix, Pix agendado e parcelamento conforme política.
- [ ] Entrada, saldo, taxa, períodos, cronograma e total.
- [ ] Persistência dos parâmetros e resultados da simulação.
- [ ] Tratamento de taxa zero, entradas inválidas e centavos.

## Critérios de aceite

- [ ] Tela identifica a operação como simulação sem dinheiro real.
- [ ] Método e unidade de taxa/prazo estão explícitos.
- [ ] Resultados conferidos com referência independente.
- [ ] Soma do cronograma arredondado coincide com o total exibido.
- [ ] Encargos não são contados duas vezes.

## Dependências

SV04, SV17

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR, CT05 e registro da referência de cálculo e arredondamento.
