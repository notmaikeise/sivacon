# Como trabalhar no SIVACon

Estas são propostas de organização para a equipe acadêmica. Revisar no card SV01 e registrar os ajustes aprovados.

## Fluxo de uma tarefa

1. Escolher uma issue pronta para começar, conferir dependências e assumir a responsabilidade.
2. Mover o card para `Pronto` quando estiver definido e para `Em andamento` ao iniciar.
3. Atualizar a `main` local e abrir uma branch específica.
4. Implementar ou documentar o que foi combinado, com commits pequenos e claros.
5. Conferir os critérios de aceite e anexar evidências proporcionais à mudança.
6. Abrir um pull request para `main` e mover o card para `Em revisão`.
7. Outra pessoa revisa; o autor resolve comentários antes do merge.
8. Integrar, conferir o resultado e fechar a issue como concluída.

Não criar uma branch `develop` por padrão: `main` mais branches por tarefa é suficiente para o início.

## Branches e commits

| Tipo | Branch de exemplo | Commit de exemplo |
| --- | --- | --- |
| Documentação | `docs/12-validar-requisitos` | `docs: revisar requisitos do catálogo` |
| Funcionalidade | `feat/18-catalogo` | `feat: adicionar filtros ao catálogo` |
| Correção | `fix/25-acesso-proposta` | `fix: validar dono da proposta` |
| Ambiente | `chore/9-ambiente-local` | `chore: configurar banco de desenvolvimento` |
| Testes | `test/30-fechamento-concorrente` | `test: verificar venda concorrente` |

Os números acima são exemplos de **números reais de issues**. `SV01` a `SV32` são identificadores do planejamento e não equivalem automaticamente a `#1` a `#32`.

## Comandos de uma tarefa

Na pasta de um clone já configurado, substitua `NUMERO` e `assunto` pelos dados da issue:

```bash
git switch main
git pull --ff-only origin main
git switch -c docs/NUMERO-assunto
```

Após trabalhar, confira o que será enviado:

```bash
git status
git diff
git add CAMINHO_DO_ARQUIVO
git diff --cached
git commit -m "docs: descrever a mudança realizada"
git push -u origin docs/NUMERO-assunto
```

`CAMINHO_DO_ARQUIVO` é um exemplo a substituir. Se houver alterações locais pendentes, finalize ou guarde seu trabalho antes de atualizar a `main`.

## Revisão

- Descrever o problema resolvido, o resultado e a verificação feita.
- Usar `Closes #NUMERO_REAL` quando o PR concluir a issue inteira.
- Registrar RF, RN, RNF, HU e CT afetados.
- Atualizar regras e decisões antes de implementar uma mudança de escopo.
- Documentação pode ser verificada pela leitura, links e critérios de coerência. Mudanças de código precisam das verificações relevantes ao comportamento alterado.

## Pronto para começar

A tarefa tem objetivo, escopo, responsável, critérios de aceite e dependências resolvidas. Regras que afetam a implementação foram aprovadas ou estão explicitamente fora do escopo.

## Definição de concluído

- Critérios de aceite verificados e evidência registrada.
- Revisão de outra pessoa concluída e mudanças integradas.
- Migrações, configuração e documentação atualizadas quando afetadas.
- Incremento demonstrável localmente, quando houver código.
- Nenhum defeito conhecido impede a jornada entregue.

## Cuidados do repositório

Versionar apenas exemplos fictícios e configurações de exemplo. Senhas, tokens, certificados privados, backups, fotos de pessoas reais e dados do banco ficam fora do Git.

## Uso de IA

Registrar no PR a finalidade do apoio quando relevante: estruturação de requisitos, sugestões de código, revisão ou testes. O autor confere regras, fontes, cálculos, segurança e resultado. Não atribuir à IA uma validação que a equipe ainda não fez.
