# Operação local

**Estado:** manual de instalação e execução local do incremento E2. Backup,
restauração e instalação em outra máquina continuam pendentes para E4.

## Diretriz

Executar com equipamento, rede, energia e armazenamento já disponíveis. Não contratar hospedagem, domínio, banco em nuvem ou API paga. Instalação e atualização podem precisar de internet; a jornada principal funcionará offline depois de preparar dependências e imagens.

## Ambientes

| Ambiente | Uso e acesso |
| --- | --- |
| Desenvolvimento | Cada integrante executa o projeto localmente com dados fictícios. |
| Demonstração na mesma máquina | Navegador acessa o servidor por localhost. |
| Demonstração na LAN | Navegador usa o endereço do servidor, com configuração documentada. |

`localhost` em outro computador aponta para esse outro computador. Para LAN, definir HTTPS confiável nos dispositivos e firewall. Não expor PostgreSQL ou API diretamente como requisito da demonstração.

## Pacote disponível no incremento E2

Frontend compilável; backend; configuração local; PostgreSQL e pgAdmin em
volumes persistentes; dados fictícios; versões das ferramentas e comandos de
inicialização. Fotos, proxy e procedimentos offline completos ainda serão
adicionados.

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

## Instalação, execução e testes

Na raiz do repositório:

```powershell
docker compose -f infra/docker-compose.yml up -d postgres pgadmin
cd backend
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Em outro terminal:

```powershell
cd frontend
npm install
npm run build
npm run dev
```

Acesse `http://localhost:5173`. A API fica em `http://localhost:8080` e o
health check está em `/actuator/health`. Para parar os serviços:

```powershell
docker compose -f infra/docker-compose.yml down
```

O banco e o pgAdmin usam volumes persistentes. Não use `down -v` se quiser
preservar os dados locais.

## Verificação do incremento E2

Já verificado:

- `docker compose -f infra/docker-compose.yml up -d`;
- PostgreSQL saudável em `localhost:5432`;
- pgAdmin acessível em `http://localhost:5050`;
- migrações Flyway aplicadas;
- `.\mvnw.cmd test` com Java 21 (7 testes aprovados);
- `npm run build`;
- catálogo e registro de interesse via API.
- autenticação local e proteção do estoque por perfil;
- área do comprador filtrada pela identidade da sessão.

## Verificação futura

SV25 comprova backup/restauração. SV27 revisa e amplia este roteiro com as
instruções da máquina de entrega. CT08 exige instalação por outra pessoa com o
manual, dados após reinício e restauração de teste. Não marcar esses itens como
concluídos apenas por criar os documentos.
