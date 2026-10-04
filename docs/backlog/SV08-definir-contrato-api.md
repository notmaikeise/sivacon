# [SV08] Definir contrato de API com o frontend

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E1 |
| Frente | Fullstack |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:documentacao`, `area:backend`, `area:frontend` |

## Objetivo

Permitir implementação integrada com payloads, permissões e erros compartilhados.

## Rastreabilidade

RF01-RF10; RNF01, RNF03; D06

## Entregas

- [ ] Contrato OpenAPI ou especificação equivalente versionada.
- [ ] Exemplos fictícios de sucesso, validação e conflito.
- [ ] Formato de valores/datas, paginação, sessão e titularidade.

## Critérios de aceite

- [ ] Cada operação tem método, rota, campos, perfil e respostas definidos.
- [ ] Frontend e backend revisam o primeiro fluxo em conjunto.
- [ ] Fechamento concorrente e acesso indevido têm respostas previstas.
- [ ] O caminho do contrato concreto é registrado em docs/08-api-e-integracao.md.

## Dependências

SV05, SV06, SV07

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR do contrato, exemplos e confirmação de revisão das duas frentes.
