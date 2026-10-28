# Planejamento, premissas, restrições e riscos

## Processo proposto

Incremental e iterativo: elicitar, analisar, modelar, implementar, verificar e implantar localmente em cada entrega. Revisar entendimento e protótipos conforme retorno. A proposta não exige adotar todo o RUP.

| Etapa | Insumo | Entrega e marco | Cards |
| --- | --- | --- | --- |
| E0 - Alinhamento | PDFs, contexto e exigências da disciplina. | Equipe aceita escopo, atores, papéis e regras necessárias. | SV01-SV04 |
| E1 - Projeto | Requisitos priorizados, riscos e políticas. | Telas, dados, arquitetura e contrato; fluxo mínimo integrado. | SV05-SV10; SV29 opcional |
| E2 - Catálogo e acesso | Modelos e ambiente. | Estoque, vitrine, contas, interesses e área do comprador; CT01-CT03. | SV11-SV15 |
| E3 - Jornada comercial | Catálogo, contas e regras comerciais. | Atendimento, proposta, simulação, aprovação e venda; CT04-CT07. | SV16-SV23 |
| E4 - Entrega | Incremento integrado e evidências. | Pacote local, manual, backup restaurado e demonstração. | SV24-SV28 |
| E5 - Evolução | MVP aceito e nova prioridade. | Segunda instalação, busca e extras com critérios próprios. | SV30-SV32 |

E2 está implementada na branch `feature/area-comprador`. Não há datas
inventadas; confirmar prazo e disponibilidade em SV02 antes de montar
calendário ou preencher milestones.

## Premissas da fonte

| ID | Hipótese a confirmar | Se não se confirmar |
| --- | --- | --- |
| P01 - Técnica | Máquina e armazenamento suficientes. | Reduzir recursos e confirmar equipamento disponível. |
| P02 - Organizacional | Disponibilidade dos quatro perfis informados. | Confirmar integrantes, redistribuir tarefas e rever escopo. |
| P03 - Negócio | Equipe consegue representar operação fictícia e validar políticas. | Resolver decisões com orientação antes do CRM. |
| P04 - Externa | Internet durante preparo e colaboração. | Manter cópia local e preparar dependências para entrega offline. |

O número de integrantes não está confirmado neste pacote. “Quatro perfis” registra a premissa do plano, sem atribuir nomes.

## Restrições

| ID | Limite |
| --- | --- |
| C01 | Hospedagem final local; não contratar infraestrutura ou API paga. |
| C02 | Ferramentas essenciais gratuitas; benefícios temporários opcionais. |
| C03 | Trabalho acadêmico com telas e histórias; preferência Java/React a consolidar tecnicamente. |
| C04 | Proteção e acesso aos dados; demonstração com dados fictícios. |
| C05 | Prazo oficial ainda pendente; torna-se limite quando confirmado. |

## Riscos iniciais

Escala hipotética 1-3 para probabilidade (P) e impacto (I); pontuação = P × I. Valores vêm do plano para priorização, sem representar estatísticas observadas.

| ID | Evento | P × I | Resposta | Papel responsável proposto |
| --- | --- | --- | --- | --- |
| R01 | Falha ou perda do computador servidor. | 2 × 3 = 6 | Backup em outro dispositivo, restauração e manual. | Implantação |
| R02 | Duas vendas da mesma unidade. | 2 × 3 = 6 | Transação, concorrência e CT06. | Backend |
| R03 | Acesso aos dados de outro comprador. | 2 × 3 = 6 | Autorização no servidor e CT02. | Backend + revisor |
| R04 | Política comercial não validada. | 3 × 2 = 6 | Aprovar parâmetros, versionar e testar. | Negócio + equipe |
| R05 | Integração tardia entre telas e API. | 2 × 3 = 6 | Contrato e incremento mínimo integrado. | Frontend + backend |
| R06 | Escopo maior que o prazo. | 3 × 3 = 9 | Priorizar MVP e adiar extras. | Equipe |
| R07 | Hardware insuficiente. | 2 × 2 = 4 | Medir cedo e começar busca por regras. | Equipe técnica |
| R08 | Benefício estudantil expira e bloqueia recurso. | 2 × 2 = 4 | Base local gratuita e alternativa documentada. | Ambiente |

## Controle

Revisar risco, evidência, responsável e pontuação a cada marco. Evitar, mitigar, transferir ou aceitar conforme contexto; aceitação exige justificativa e condição de revisão. Mudanças de escopo registram motivo, esforço e requisitos/modelos/testes afetados.
