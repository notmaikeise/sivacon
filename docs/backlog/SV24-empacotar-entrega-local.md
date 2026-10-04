# [SV24] Empacotar instalação e execução local offline

## Campos da issue e do Project

| Campo | Valor sugerido |
| --- | --- |
| Status inicial | Backlog |
| Prioridade | P0 |
| Etapa | E4 |
| Frente | Infraestrutura |
| Escopo | MVP |
| Assignee | Definir com a equipe. |
| Labels | `tipo:infra`, `area:infra` |

## Objetivo

Preparar o sistema para funcionar no computador aprovado sem serviços pagos.

## Rastreabilidade

RNF06-RNF07; C01-C02; CT08

## Entregas

- [ ] Frontend compilado, API, banco e volumes/fotos.
- [ ] Configuração local, exemplo sem segredos e preparo de dependências/imagens.
- [ ] Acesso localhost ou LAN aprovado, com medidas previstas para LAN.
- [ ] Procedimentos reais de início, parada e reinício.

## Critérios de aceite

- [ ] Jornada funciona sem internet após preparo.
- [ ] Reinício preserva banco e fotos.
- [ ] Só a entrada local planejada precisa ficar acessível.
- [ ] Outra pessoa inicia o pacote na máquina escolhida.
- [ ] Não existe dependência essencial de nuvem ou API paga.

## Dependências

SV07, SV22

Os IDs SV são referências do planejamento. Após criar as issues, registrar os números/links reais nesta seção.

## Evidência de conclusão

Pacote e execução registrada de CT08, exceto restauração que será concluída em SV25.
