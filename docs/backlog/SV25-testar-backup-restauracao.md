# [SV25] Implementar e testar backup e restauração

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E4 |
| Frente | Infraestrutura |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `tipo:teste`, `area:infra` |

## Objetivo

Recuperar dados e fotos de demonstração com um procedimento reproduzível.

## Rastreabilidade

RNF06; R01; CT08

## Entregas

- [ ] Backup consistente do banco e dos arquivos.
- [ ] Política e destino em outro dispositivo disponível.
- [ ] Restauração em ambiente de teste separado.
- [ ] Registro da versão da aplicação e dados usados.

## Critérios de aceite

- [ ] Backup possui os dados e fotos necessários.
- [ ] Restauração recupera registros e arquivos verificáveis.
- [ ] Jornada funciona no ambiente restaurado.
- [ ] Procedimento não depende só de manter o volume ou cópia no mesmo disco.
- [ ] Backup e segredos não entram no repositório.

## Dependências

SV24

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

Relatório de restauração e instruções reais sem dados sensíveis.
