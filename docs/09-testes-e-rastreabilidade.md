# Testes e rastreabilidade

**Estado dos cenários:** E2 possui testes unitários de autenticação, catálogo,
interesse e estoque. CT01, CT02 e CT03 têm cobertura inicial; os demais
continuam planejados. CT01-CT08 preservam a identificação da fonte. CT09 é um
complemento proposto para detalhar RF10.

## Cenários de aceitação

| Teste | Vínculos | Evidência esperada |
| --- | --- | --- |
| CT01 | RF02 / HU01 | Filtros corretos sem login, detalhe e estado sem resultados. |
| CT02 | RF03, RF09 / HU02 / RN04 / RNF01 | Visitante barrado no CRM; comprador A barrado ao ler ou alterar dados de B por API. |
| CT03 | RF01 / HU03 / RN01 | Unidade válida persistida e chassi repetido rejeitado. |
| CT04 | RF04, RF05 / HU04 | Contato e transição permitida com autoria/histórico; transição inválida rejeitada. |
| CT05 | RF06, RF07 / HU05 / RN09-RN11 | Cálculo conferido, taxa zero, entradas inválidas e arredondamento documentados. |
| CT06 | RF08 / HU06 / RN03, RN05, RN06, RN08, RN13 / RNF02 | Uma venda concorrente; proposta vencida e crédito pendente rejeitados; falha sem gravação parcial. |
| CT07 | RF06-RF08 / HU05 / RN07, RN12 | Desconto exige aprovação; Pix agendado não confirma pagamento. |
| CT08 | RF01-RF10 / RNF06 | Jornada local offline após preparo, persistência no reinício, instalação e restauração. |
| CT09 (novo, proposto) | RF10 / RNF01, RNF03 | Configuração persiste, alterações de usuário/perfil respeitam acesso e resumo coincide com vendas fictícias. |

Os testes unitários dos services cobrem autenticação, catálogo, interesse e
validações básicas do estoque. Testes HTTP, testes de titularidade com banco e
testes de jornada serão adicionados na E3/E4.

## Do requisito ao card

| Requisito | Cards principais | Testes |
| --- | --- | --- |
| RF01 | SV05, SV12 | CT03 |
| RF02 | SV12, SV13 | CT01 |
| RF03 | SV11, SV14 | CT02 |
| RF04 | SV14, SV16 | CT04 |
| RF05 | SV16 | CT04 |
| RF06 | SV17, SV19 | CT05, CT07 |
| RF07 | SV18, SV19 | CT05, CT07 |
| RF08 | SV19, SV20 | CT06, CT07 |
| RF09 | SV11, SV15, SV17, SV20 | CT02 e jornada integrada |
| RF10 | SV21 | CT09 e CT08 |
| RF11 | SV31 | Definir ao aprovar evolução. |
| RF12 | SV32 | Definir ao aprovar evolução. |

## Qualidade e verificação

| Requisito | Cards | Verificação |
| --- | --- | --- |
| RNF01 | SV11, SV22, SV26 | Matriz de perfis, titularidade, hash e negação pela API. |
| RNF02 | SV20, SV22 | Concorrência e rollback com PostgreSQL de teste. |
| RNF03 | SV05-SV10, SV27 | Módulos, migrações, contrato, decisões e manual consistentes. |
| RNF04 | SV07, SV26 | Meta aprovada, hardware e medições documentados. |
| RNF05 | SV06, SV23 | Jornada, teclado, rótulos, contraste e estados. |
| RNF06 | SV24-SV27 | Execução offline após preparo, reinício e restauração. |
| RNF07 | SV09, SV24, SV26 | Dados fictícios e inspeção de logs e arquivos. |

## Estratégia

Testar regras e cálculos no backend; persistência, transações e concorrência com PostgreSQL; interações relevantes na interface; jornada completa com Playwright ou roteiro manual reproduzível, conforme recursos.

Uma verificação do mesmo cálculo escrita com a mesma fórmula não é referência independente. Comparar valores esperados conferidos e cronograma arredondado. Capturas de tela não substituem o teste de concorrência ou a negação no servidor.

## Registro de evidência

Para cada cenário, anotar versão/commit, ambiente, dados fictícios, responsável, passos, resultado esperado, resultado observado e evidência. Estados: `Não executado`, `Aprovado`, `Falhou` ou `Bloqueado`.

Todos os estados começam em `Não executado`. O card SV22 reúne CT01-CT07 e CT09; SV24/SV25 cobrem CT08; SV26 consolida qualidade e resultados.

## Evidência do incremento E2

Além dos testes de catálogo, interesse e estoque, a autenticação local foi
validada com login válido (`admin@example.com` / `password`) e acesso autenticado
ao estoque. Sem token, o estoque responde `403`; com token Bearer válido,
responde `200`.

| Verificação | Resultado |
| --- | --- |
| Build backend com Java 21 | Aprovado |
| Build frontend com `npm run build` | Aprovado |
| PostgreSQL e pgAdmin via Compose | Aprovado |
| Migrações Flyway V1 a V5 | Aprovado |
| Catálogo sem filtros e com filtros | Aprovado manualmente |
| Registro de interesse persistido | Aprovado manualmente |
| Testes unitários backend | 7 testes aprovados com `.\mvnw.cmd test` |
| Testes frontend | Não executados: ainda não há arquivos de teste |
