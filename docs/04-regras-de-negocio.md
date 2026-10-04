# Regras de negócio

**Estado inicial de RN01-RN13: proposto.** Fonte: plano de modelagem local, páginas 9-10. Registrar aprovação, dono e versão antes de aplicar uma política.

## Regras propostas

| ID | Tipo | Política | Dono de negócio |
| --- | --- | --- | --- |
| RN01 | Estrutural | Chassi único por unidade dentro da instalação. | Estoque |
| RN02 | Estrutural | Proposta do piloto ligada a um comprador e uma unidade. | Vendas |
| RN03 | Restrição | Unidade vendida não pode gerar outra venda ativa. | Estoque / vendas |
| RN04 | Restrição | Comprador acessa apenas atendimentos, propostas e compras vinculados a ele. | Gestão |
| RN05 | Condição | Fechamento exige proposta aprovada e vigente. | Vendas |
| RN06 | Condição | Unidade deve estar disponível na confirmação. | Estoque |
| RN07 | Condição | Desconto acima do limite configurado exige aprovação gerencial registrada. | Gerência |
| RN08 | Restrição | Proposta vencida exige nova versão antes do fechamento. | Vendas |
| RN09 | Cálculo | Saldo financiado = preço negociado - entrada. | Financeiro |
| RN10 | Cálculo | Total = entrada + parcelas + encargos separados, sem dupla contagem. | Financeiro |
| RN11 | Restrição | Simulação exibe método, taxa, quantidade, entrada, vencimentos e total. | Financeiro |
| RN12 | Restrição | Data de Pix agendado não confirma pagamento. | Financeiro |
| RN13 | Condição | Fechamento financiado exige crédito fictício aprovado. | Financeiro |

## Parâmetros a validar em SV04

| Tema | Decisão pendente |
| --- | --- |
| Comprador | PF/PJ no piloto e campos obrigatórios. |
| Veículo | Campos, estados de disponibilidade, regras de publicação e fotos. |
| Funil | Etapas, transições, responsável e permissões. |
| Proposta | Validade, estados, versionamento, desconto máximo e aprovações. |
| Crédito fictício | Quem decide, critérios acadêmicos e estados. |
| Simulação | Método, taxa por período, número de parcelas, entrada, vencimentos, encargos e arredondamento. |
| Pix agendado | Estados e registro manual de confirmação fictícia. |
| Reserva | Se ficará fora do MVP; caso incluída por revisão, prazo e conflitos. |

Não adotar automaticamente 10% de desconto, 7 dias de validade, 48 parcelas ou prazo de reserva. Os exemplos acadêmicos não são políticas aprovadas do SIVACon.

## Cálculo acadêmico proposto

Usar precisão decimal para dinheiro. A escolha definitiva do método e o tratamento dos centavos dependem de aprovação.

- Capitalização sem pagamentos intermediários: `M = P × (1 + i)^n`.
- Prestações iguais, primeira após um período: `A = P × i / [1 - (1 + i)^(-n)]`.
- Taxa zero: `A = P / n`.
- `P` é o saldo, `i` a taxa decimal por período e `n` a quantidade de períodos. Prazo e taxa usam a mesma unidade.

Não calcular prestações amortizadas dividindo o montante capitalizado por `n`. Validar entrada, valores negativos, quantidade de parcelas e arredondamento; os limites aprovados compõem os testes.

O exemplo numérico da fonte é ilustrativo. A implementação deverá conferir resultados com referência independente e preservar parâmetros e cronograma utilizados em cada simulação.

## Mudança de regra

Registrar ID, enunciado, dono, fonte, estado, versão e data. Vincular ao requisito e teste. Mudanças comerciais não reescrevem propostas históricas; condições alteradas geram a versão prevista.
