# [SV11] Implementar login, perfis e autorização

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E2 |
| Frente | Fullstack |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:feature`, `area:backend`, `area:frontend` |

## Objetivo

Proteger as áreas internas e impedir acesso aos dados de outro comprador.

## Rastreabilidade

RF03, RF09; HU02; RN04; RNF01; CT02

## Entregas

- [ ] Login/logout e gestão da sessão conforme decisão aprovada.
- [ ] Perfis e matriz de permissão aplicados no backend.
- [ ] Hash de senhas e acesso às rotas da interface.
- [ ] Verificações de acesso por perfil e titularidade.

## Critérios de aceite

- [ ] Visitante é barrado nos recursos internos pela API.
- [ ] Perfil sem permissão não executa ação restrita via requisição direta.
- [ ] Comprador A não lê nem altera dados de B por identificador.
- [ ] Logout invalida/encerra a sessão conforme o contrato aprovado.

## Dependências

SV08, SV09, SV10

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR e resultados de CT02 nos recursos disponíveis; repetir em SV22.
