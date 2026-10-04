# Histórias de usuário

**Estado:** propostas preservadas do plano de modelagem. Detalhar dados, erros e permissão na issue de implementação.

## HU01 - Encontrar uma moto

Como visitante, quero filtrar motos por preço, marca e condição, para encontrar uma opção compatível com meu orçamento.

- [ ] Dado um catálogo, quando aplico filtros, recebo apenas resultados compatíveis sem login.
- [ ] Sem resultados, vejo mensagem clara e posso remover filtros.
- [ ] O detalhe informa disponibilidade.

Vínculos: RF02; CT01; card SV13.

## HU02 - Acompanhar meus dados

Como comprador autenticado, quero ver meus atendimentos e propostas, para acompanhar a negociação.

- [ ] Após login, vejo somente registros vinculados a mim.
- [ ] Ao tentar ler ou alterar dados de outra pessoa por URL ou API, o servidor nega acesso.
- [ ] Visitante não acessa recursos internos do CRM.

Vínculos: RF03, RF09; RN04; RNF01; CT02; SV11 e SV15.

## HU03 - Cadastrar uma unidade

Como responsável pelo estoque, quero cadastrar uma moto e sua disponibilidade, para manter o catálogo coerente.

- [ ] Perfil autorizado salva unidade válida conforme seu estado.
- [ ] Chassi repetido é rejeitado sem duplicidade.
- [ ] Perfil sem permissão não altera estoque pela API.

Vínculos: RF01, RF02; RN01; CT03; SV12.

## HU04 - Registrar o atendimento

Como vendedor, quero registrar contatos e mover a oportunidade no funil, para preservar o histórico e organizar a próxima ação.

- [ ] Contato salva autor, data e conteúdo.
- [ ] Mudança de etapa respeita a transição aprovada e preserva histórico.
- [ ] Transição inválida não altera a oportunidade.

Vínculos: RF04, RF05; CT04; SV14 e SV16. Etapas a validar em SV04.

## HU05 - Negociar com transparência

Como comprador, quero consultar proposta e simulação, para compreender o valor e as condições.

- [ ] Parcelamento mostra taxa, entrada, quantidade, vencimentos e total, identificado como simulação.
- [ ] Desconto acima do limite exige aprovação gerencial.
- [ ] Alteração de condição gera nova versão sem apagar a anterior.
- [ ] Pix agendado continua previsto até confirmação fictícia.

Vínculos: RF06, RF07, RF09; RN07, RN09-RN12; CT05, CT07; SV17-SV19.

## HU06 - Fechar sem duplicar a venda

Como vendedor autorizado, quero confirmar uma venda aprovada, para concluir a negociação e atualizar o estoque.

- [ ] Proposta aprovada, vigente e unidade disponível geram venda e alteração de estoque juntas.
- [ ] Proposta financiada exige crédito fictício aprovado.
- [ ] Tentativas concorrentes produzem uma única venda válida.
- [ ] Proposta vencida, indisponibilidade ou falha não deixam fechamento parcial.

Vínculos: RF08; RN03, RN05, RN06, RN08, RN13; RNF02; CT06; SV20.

## Complementos a detalhar

RF10 precisa de critérios próprios para administração e resumo (SV21 e CT09 proposto). Instalação e operação são cobertas por RNF06 e CT08. Não criar novas histórias como se já tivessem sido aprovadas.
