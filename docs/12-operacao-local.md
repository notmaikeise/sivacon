# Operação local

**Estado:** requisitos do futuro manual. A aplicação, Compose e scripts operacionais ainda não existem neste pacote.

## Diretriz

Executar com equipamento, rede, energia e armazenamento já disponíveis. Não contratar hospedagem, domínio, banco em nuvem ou API paga. Instalação e atualização podem precisar de internet; a jornada principal funcionará offline depois de preparar dependências e imagens.

## Ambientes

| Ambiente | Uso e acesso |
| --- | --- |
| Desenvolvimento | Cada integrante executa o projeto localmente com dados fictícios. |
| Demonstração na mesma máquina | Navegador acessa o servidor por localhost. |
| Demonstração na LAN | Navegador usa o endereço do servidor, com configuração documentada. |

`localhost` em outro computador aponta para esse outro computador. Para LAN, definir HTTPS confiável nos dispositivos e firewall. Não expor PostgreSQL ou API diretamente como requisito da demonstração.

## Pacote a produzir em SV24

Frontend compilado; backend; configuração local; banco e volumes persistentes; fotos; dados fictícios; versões das ferramentas; procedimentos de preparo offline.

Publicar a entrada web e encaminhar API pela rede interna aprovada. Senhas, certificados privados e arquivos operacionais reais não entram no Git. Documentar apenas exemplo de configuração.

## Conteúdo obrigatório do manual final

| Procedimento | Evidência |
| --- | --- |
| Preparação | Pré-requisitos, versões e arquivos necessários disponíveis. |
| Configuração | Exemplo sem segredos, portas/endereço e armazenamento definidos. |
| Início e parada | Comandos reais verificados na máquina escolhida. |
| Reinício | Banco e fotos continuam disponíveis. |
| Uso offline | Jornada integrada sem internet após preparo. |
| Backup | Banco e fotos consistentes, com versão da aplicação e política registrada. |
| Restauração | Ambiente de teste recupera dados e arquivos e executa a jornada. |
| Atualização | Backup, migrações, validação e procedimento de retorno documentados. |

Guardar backup em outro dispositivo já disponível. Um volume persistente não é backup; cópia no mesmo disco não protege contra perda desse disco.

## Verificação

SV25 comprova backup/restauração. SV27 troca este roteiro por instruções reais e revisadas. CT08 exige instalação por outra pessoa com o manual, dados após reinício e restauração de teste. Não marcar esses itens como concluídos apenas por criar os documentos.
