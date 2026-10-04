# [SV21] Administrar loja, usuários e resumo de vendas

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P1 |
| Etapa | E3 |
| Frente | Fullstack |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:feature`, `area:backend`, `area:frontend` |

## Objetivo

Completar a administração mínima prevista no MVP.

## Rastreabilidade

RF10; RNF01, RNF03; CT09 proposto

## Entregas

- [ ] Identidade, contatos e parâmetros comerciais por configuração.
- [ ] Gestão de usuários e perfis conforme matriz aprovada.
- [ ] Resumo básico de vendas fictícias.

## Critérios de aceite

- [ ] Configuração persiste e aparece nas áreas pertinentes.
- [ ] Usuário sem permissão não altera loja, políticas ou perfis pela API.
- [ ] Resumo usa registros de venda e não inclui fechamentos rejeitados.
- [ ] Alterar política não muda propostas históricas.
- [ ] Não há configuração de cliente feita por cópia divergente do código.

## Dependências

SV11, SV20

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e CT09, sujeito à aprovação dos critérios adicionais.
