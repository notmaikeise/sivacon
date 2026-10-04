# [SV12] Cadastrar modelos e unidades de moto

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

Manter estoque com unidades físicas, preço, estado e fotos locais.

## Rastreabilidade

RF01, RF02; HU03; RN01; RNF01; CT03

## Entregas

- [ ] Tela e API de modelos/unidades com campos aprovados.
- [ ] Validações e unicidade de chassi no banco.
- [ ] Fotos locais e estado de publicação/disponibilidade.

## Critérios de aceite

- [ ] Perfil autorizado salva e altera uma unidade válida.
- [ ] Chassi duplicado é rejeitado sem criar outro registro.
- [ ] Campos inválidos geram mensagem clara e preservam formulário.
- [ ] Estado e fotos persistem e ficam coerentes no catálogo.

## Dependências

SV05, SV08, SV10, SV11

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

PR, CT03 e demonstração de cadastro/alteração.
