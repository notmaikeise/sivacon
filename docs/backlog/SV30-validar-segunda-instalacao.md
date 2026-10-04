# [SV30] Validar personalização em segunda instalação

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P2 |
| Etapa | E5 |
| Frente | Infraestrutura |
| Escopo | Evolução |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `tipo:teste`, `area:infra` |

## Objetivo

Demonstrar reuso com identidade e dados independentes após estabilizar o MVP.

## Rastreabilidade

RNF01, RNF03; visão de adaptação; C01-C02

## Entregas

- [ ] Segunda loja fictícia configurada a partir da mesma versão.
- [ ] Banco, fotos, usuários e parâmetros separados.
- [ ] Roteiro de isolamento e atualização.

## Critérios de aceite

- [ ] Troca de identidade não exige duplicar o código do produto.
- [ ] Instalação A não acessa dados/arquivos/usuários de B.
- [ ] Migrações e versão são documentadas em ambas.
- [ ] Não há nova contratação para provar a adaptação.

## Dependências

SV28

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

Demonstração das duas instalações e resultado de isolamento.
