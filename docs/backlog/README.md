# Backlog inicial

**32 cards propostos, todos inicialmente em Backlog.** Os arquivos abaixo não criam issues ou campos automaticamente no GitHub. Copiar título e corpo e preencher os campos no Project conforme [o guia](../13-github-projects.md).

SV01-SV04 iniciam o alinhamento. SV29 é opcional. SV30-SV32 são evoluções após o MVP. Não iniciar um card bloqueado por uma decisão apenas porque o texto já foi preparado.

| Card | Título | Etapa | Prioridade | Frente | Escopo | Dependências |
| --- | --- | --- | --- | --- | --- | --- |
| [SV01](SV01-organizar-repositorio.md) | Organizar o repositório e o Project compartilhados | E0 | P0 | Gestão | MVP | - |
| [SV02](SV02-confirmar-entrega-e-equipe.md) | Confirmar prazo, avaliação e papéis da equipe | E0 | P0 | Gestão | MVP | SV01 |
| [SV03](SV03-validar-cenario-e-mvp.md) | Validar cenário fictício, atores e escopo do MVP | E0 | P0 | Modelagem | MVP | SV02 |
| [SV04](SV04-aprovar-regras-comerciais.md) | Aprovar políticas comerciais e de simulação | E0 | P0 | Modelagem | MVP | SV03 |
| [SV05](SV05-modelar-dados.md) | Modelar dados e regras de integridade | E1 | P0 | Modelagem | MVP | SV03, SV04 |
| [SV06](SV06-prototipar-telas.md) | Prototipar a jornada das três áreas | E1 | P0 | Frontend | MVP | SV03, SV04 |
| [SV07](SV07-definir-arquitetura-e-ambiente.md) | Aprovar arquitetura, versões e ambiente local | E1 | P0 | Backend | MVP | SV02, SV03 |
| [SV08](SV08-definir-contrato-api.md) | Definir contrato de API com o frontend | E1 | P0 | Fullstack | MVP | SV05, SV06, SV07 |
| [SV09](SV09-iniciar-backend-e-banco.md) | Inicializar backend, PostgreSQL e migrações | E1 | P0 | Backend | MVP | SV05, SV07 |
| [SV10](SV10-integrar-primeiro-fluxo.md) | Inicializar frontend e integrar um fluxo mínimo | E1 | P0 | Fullstack | MVP | SV08, SV09 |
| [SV11](SV11-implementar-acesso.md) | Implementar login, perfis e autorização | E2 | P0 | Fullstack | MVP | SV08, SV09, SV10 |
| [SV12](SV12-cadastrar-estoque.md) | Cadastrar modelos e unidades de moto | E2 | P0 | Fullstack | MVP | SV05, SV08, SV10, SV11 |
| [SV13](SV13-publicar-catalogo.md) | Publicar catálogo, filtros e detalhe da moto | E2 | P0 | Fullstack | MVP | SV12 |
| [SV14](SV14-registrar-interesse.md) | Criar conta de comprador e registrar interesse | E2 | P0 | Fullstack | MVP | SV11, SV13 |
| [SV15](SV15-criar-area-comprador.md) | Implementar a área do comprador | E2 | P0 | Fullstack | MVP | SV14 |
| [SV16](SV16-implementar-atendimento-funil.md) | Implementar atendimento, histórico e funil | E3 | P0 | Fullstack | MVP | SV04, SV14 |
| [SV17](SV17-versionar-propostas.md) | Criar e versionar propostas comerciais | E3 | P0 | Fullstack | MVP | SV04, SV12, SV16 |
| [SV18](SV18-simular-pagamentos.md) | Implementar simulações de pagamento | E3 | P0 | Fullstack | MVP | SV04, SV17 |
| [SV19](SV19-registrar-aprovacoes.md) | Registrar aprovações e estados financeiros fictícios | E3 | P0 | Fullstack | MVP | SV04, SV17, SV18 |
| [SV20](SV20-finalizar-venda.md) | Finalizar venda fictícia com controle de concorrência | E3 | P0 | Fullstack | MVP | SV12, SV17, SV19 |
| [SV21](SV21-administrar-loja-resumo.md) | Administrar loja, usuários e resumo de vendas | E3 | P1 | Fullstack | MVP | SV11, SV20 |
| [SV22](SV22-verificar-jornada-integrada.md) | Verificar a jornada integrada e cenários críticos | E3 | P0 | Qualidade | MVP | SV15, SV16, SV18, SV20, SV21 |
| [SV23](SV23-validar-acessibilidade.md) | Validar usabilidade e acessibilidade das telas | E3 | P1 | Qualidade | MVP | SV06, SV22 |
| [SV24](SV24-empacotar-entrega-local.md) | Empacotar instalação e execução local offline | E4 | P0 | Infraestrutura | MVP | SV07, SV22 |
| [SV25](SV25-testar-backup-restauracao.md) | Implementar e testar backup e restauração | E4 | P0 | Infraestrutura | MVP | SV24 |
| [SV26](SV26-consolidar-qualidade.md) | Consolidar verificação de qualidade do MVP | E4 | P1 | Qualidade | MVP | SV22, SV23, SV24, SV25 |
| [SV27](SV27-finalizar-documentacao.md) | Finalizar documentação e manual local | E4 | P0 | Documentação | MVP | SV24, SV25, SV26 |
| [SV28](SV28-preparar-apresentacao.md) | Preparar e ensaiar a entrega acadêmica | E4 | P0 | Gestão | MVP | SV27 |
| [SV29](SV29-avaliar-ci-opcional.md) | Avaliar CI opcional para os pull requests | E1 | P2 | Infraestrutura | Opcional | SV09, SV10 |
| [SV30](SV30-validar-segunda-instalacao.md) | Validar personalização em segunda instalação | E5 | P2 | Infraestrutura | Evolução | SV28 |
| [SV31](SV31-busca-por-regras.md) | Planejar e implementar busca por regras | E5 | P2 | Fullstack | Evolução | SV13, SV28 |
| [SV32](SV32-priorizar-evolucoes.md) | Priorizar evoluções comerciais após o MVP | E5 | P3 | Modelagem | Evolução | SV28 |

## Uso dos IDs

Não usar `Closes #SV01` e não presumir que SV01 é a issue #1. O GitHub atribui os números reais ao criar cada issue; PRs também participam dessa numeração. Manter o ID SV no título e atualizar dependências com os links reais.
