# ADR 0001 - Arquitetura local para o piloto

| Campo | Registro |
| --- | --- |
| Estado | Proposto; aguarda SV07. |
| Data da proposta documental | 04/10/2026 |
| Aprovação | Não realizada. |
| Vínculos | C01-C03; RNF01-RNF03, RNF06; SV05, SV07-SV10, SV24. |

## Contexto

Projeto acadêmico com três áreas conectadas, cenário fictício de motos, políticas ainda em revisão e entrega local sem contratação de serviços. A equipe informou preferência Java/React.

## Decisão proposta

Monólito modular Java/Spring Boot; frontend React/TypeScript; PostgreSQL e fotos locais; identidade e políticas por configuração; uma instalação e armazenamento próprios por concessionária. Maven Wrapper, migrações e lockfile padronizam o ambiente.

## Alternativas consideradas

| Alternativa | Avaliação inicial |
| --- | --- |
| Monólito modular | Favorece transações, integração e operação pela equipe; exige limites entre módulos. |
| Microsserviços | Mais serviços e coordenação operacional para uma jornada ainda em definição. |
| Serviço de nuvem contratado | Não atende à restrição confirmada de hospedagem final local sem gasto. |
| Banco compartilhado entre lojas | Exige isolamento adicional; não é a proposta inicial do piloto. |

## Consequências

Uma base de código; transação local para fechamento; capacidade limitada ao equipamento disponível; atualizações e backups por instalação; segunda loja somente após estabilizar o MVP.

## Aprovação pendente

Registrar versões exatas, hardware, sistema operacional, autenticação, limites de módulos e estratégia localhost/LAN. Preencher revisores, data e justificativa antes de mudar o estado para aprovado.
