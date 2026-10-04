# Organização no GitHub Projects

**Configuração proposta.** Os campos e cards abaixo serão criados no GitHub pela equipe. Os arquivos deste repositório não configuram o Project automaticamente.

## Estrutura de colaboração

Um repositório `sivacon` e um Project `SIVACon | Planejamento e Desenvolvimento`. Para começar, ambos podem pertencer à conta pessoal que coordena o trabalho. Uma organização é uma alternativa se a equipe quiser administração compartilhada.

Um repositório pessoal tem proprietário e colaboradores. Permissões mais detalhadas de equipe pertencem ao modelo de organizações; não orientar integrantes a procurar um papel “Maintain” no repositório pessoal.

Recomenda-se iniciar privado durante a revisão e decidir publicação/licença com a equipe. Hospedagem local do sistema e visibilidade do repositório são escolhas diferentes.

## Criar e vincular o Project

No perfil: `Projects → New project → Board`. Nomear o quadro e manter a visibilidade combinada. No repositório: `Projects → Link a project` e selecionar o Project do mesmo proprietário.

No Project: `… → Settings → Manage access`, adicionar integrantes confirmados com `Write`. Acesso ao quadro não concede acesso ao repositório; conferir as duas permissões. Pode haver uma segunda pessoa com `Admin` no Project se a equipe decidir.

## Campos

Usar uma vista `Table` para criar campos pelo `+` no cabeçalho à direita e `New field`. Os campos de escolha usam `Single select`.

| Campo | Tipo | Valores |
| --- | --- | --- |
| Status | Single select existente | Backlog, Pronto, Em andamento, Em revisão, Concluído |
| Prioridade | Single select | P0, P1, P2, P3 |
| Etapa | Single select | E0, E1, E2, E3, E4, E5 |
| Frente | Single select | Gestão, Modelagem, Backend, Frontend, Fullstack, Qualidade, Infraestrutura, Documentação |
| Escopo | Single select | MVP, Opcional, Evolução |
| Assignees | Campo do GitHub | Uma pessoa responsável por card, definida com a equipe. |
| Labels | Campo do GitHub | Labels reais da issue no repositório. |

Assignees e Labels já existem; mostrá-los na vista, sem criar cópias como campos de texto. Dependências ficam no corpo da issue com links reais.

## Significado de Status

| Valor | Quando usar |
| --- | --- |
| Backlog | Proposto, ainda não selecionado ou refinado. |
| Pronto | Critérios definidos, responsável e dependências resolvidos. |
| Em andamento | Trabalho iniciado. |
| Em revisão | PR ou entrega esperando revisão. |
| Concluído | Critérios verificados, revisão feita e entrega integrada. |

Definir `Backlog` como valor padrão do Status se disponível. Nenhum card começa concluído. Registrar bloqueios no corpo da issue e usar a label `bloqueado`.

## Prioridade

| Valor | Uso |
| --- | --- |
| P0 | Bloqueia o planejamento, a jornada ou a entrega. |
| P1 | Necessário no MVP, com sequência posterior às dependências críticas. |
| P2 | Apoio opcional ou evolução depois do MVP. |
| P3 | Ideia de evolução a refinar. |

Prioridade não é estado nem medida de esforço. Os valores iniciais são sugestões; a equipe pode revisá-los.

## Etapas

| Valor | Marco |
| --- | --- |
| E0 | Alinhamento e elicitação. |
| E1 | Prototipação, projeto e fluxo mínimo integrado. |
| E2 | Catálogo e acesso. |
| E3 | Jornada comercial e revisão funcional. |
| E4 | Entrega acadêmica e operação local. |
| E5 | Evolução. |

Não criar datas de entrega antes de SV02. Milestones são opcionais; não são necessários para duplicar o campo Etapa.

## Labels sugeridas

Criar em `Issues → Labels → New label`. As cores são sugestões sem função de prioridade.

| Label | Cor | Uso |
| --- | --- | --- |
| tipo:planejamento | #D4A72C | Escopo, organização ou decisão. |
| tipo:documentacao | #0075CA | Documento ou modelo. |
| tipo:feature | #2DA44E | Funcionalidade. |
| tipo:teste | #8250DF | Verificação e evidência. |
| tipo:infra | #57606A | Ambiente e operação. |
| tipo:bug | #CF222E | Defeito observado. |
| area:gestao | #D4C5F9 | Organização e entrega. |
| area:negocio | #F9D0C4 | Modelagem e política. |
| area:backend | #BFD4F2 | API, regras e banco. |
| area:frontend | #C2E0C6 | Interface. |
| area:infra | #E4E669 | Execução e operação. |
| area:qualidade | #D4C5F9 | Qualidade. |
| bloqueado | #B60205 | Dependência ou decisão impede avanço. |

Os templates não aplicam labels por padrão. Escolher as labels indicadas em cada card depois de criá-las. Prioridade, Etapa, Frente e Escopo são campos do Project, não labels adicionais.

## Vistas

| Vista | Layout | Configuração |
| --- | --- | --- |
| Backlog | Table | Mostrar título, Status, Prioridade, Etapa, Frente, Escopo, Assignees e Labels. |
| Quadro do MVP | Board | Agrupar por Status, filtrar `Escopo:MVP`. |
| Etapas | Table | Agrupar por Etapa. |
| Evoluções | Table ou Board | Filtrar `Escopo:Evolução`. |

Salvar cada vista após configurar. Cards sem Escopo ainda preenchido não aparecerão no filtro do MVP.

## Criar issues a partir dos cards

1. Abrir [backlog/README.md](backlog/README.md) e escolher o card.
2. Em `Issues → New issue`, usar o template adequado.
3. Copiar título sem o `#` Markdown e substituir o corpo do template pelo corpo completo do card.
4. Definir labels e assignee com base na equipe.
5. Vincular ao Project pela seção `Projects` da issue.
6. Preencher os campos do Project e atualizar dependências com números/links reais.

Manter `[SV01]` no título facilita a busca. Esse ID não é o número da issue.

Começar com SV01-SV04. As demais tarefas podem ser cadastradas gradualmente. Um rascunho digitado apenas no Project pode não ser uma issue do repositório; converter em issue antes de usar no fluxo de código.

## Adição e sincronização

O caminho manual é suficiente: `Projects` na lateral da issue. Também é possível selecionar várias issues na lista e adicioná-las pelo menu `Projects`.

Para apoio automático: `… → Workflows → Auto-add to project → Edit`; selecionar `sivacon` e filtrar `is:issue is:open`; salvar e habilitar. O GitHub Free permite um workflow de auto-add.

Essa automação não importa retroativamente todas as issues existentes. Vincular as anteriores manualmente. Se uma issue estiver ausente, verificar vínculo, filtro, workflow e acesso em vez de presumir que esperar resolverá.

Revisar os workflows de item fechado e PR integrado para usar `Concluído` como Status. Mover para `Em revisão` quando abrir o PR. Uma issue fechada sem implementação deve manter motivo explícito; conferir o Status automático antes de tratá-la como entrega concluída.

## Rotina da equipe

Antes de começar, conferir critérios e dependências. Durante o trabalho, registrar bloqueios. Na revisão, incluir evidências. Fechar a issue apenas após a definição de concluído em [CONTRIBUTING.md](../CONTRIBUTING.md).

## Proteção de main

Se o plano oferecer proteção de branch para a visibilidade escolhida, configurar revisão antes do merge e resolução de conversas. Verificações obrigatórias entram depois que existirem verificações funcionais.

Em repositório privado, a disponibilidade de proteção depende do plano. Se o recurso não estiver disponível, manter o acordo de branches e PRs revisados; não contratar plano apenas para iniciar o trabalho.

Referências oficiais e data de consulta: [14-referencias.md](14-referencias.md).
