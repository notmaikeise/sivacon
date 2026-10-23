# Arquitetura e estado da implementação

**Estado:** monólito modular em implementação incremental. A decisão
arquitetural continua sujeita à aprovação formal no [ADR 0001](decisoes/0001-arquitetura-local-proposta.md).

## Estrutura

| Parte | Organização proposta |
| --- | --- |
| Backend | Aplicação Java/Spring Boot com `controller`, `service` e `dao` por módulo. |
| Frontend | Projeto React/Vite com catálogo público de teste. |
| Banco | PostgreSQL 16 local com migrações Flyway 10. |
| Fotos | Arquivos/volume local, com metadados e controle de acesso adequados. |
| Entrada local | Servidor de arquivos React e proxy para API. |
| Isolamento | Banco, arquivos, usuários e configuração próprios por instalação. |

O monólito modular favorece transações e operação pela equipe. A separação por módulos evita espalhar regras entre controllers. Microsserviços não foram escolhidos para o piloto.

## Organização do backend

Módulos previstos: `veiculos`, `clientes`, `atendimento`, `propostas`, `vendas`, `financeiro` e `configuracao`. O código inicial usa `catalog`, `interest`, `service` e `dao`; os demais módulos serão adicionados conforme os casos de uso forem implementados.

Controllers tratam entrada/saída; serviços aplicam políticas e transações; repositórios persistem; DTOs definem o contrato. Entidades de persistência não serão expostas diretamente na API. Padrões adicionais entram quando resolverem uma necessidade concreta.

## Ferramentas e versões

| Ferramenta | Estado atual |
| --- | --- |
| Java 21 + Maven Wrapper | Configurado e compilado. |
| Spring Boot 3.4 / Web / Data JPA / JDBC | Configurado; Security será adicionado no módulo de acesso. |
| Flyway 10 + PostgreSQL 16 | Configurado; migrações V1 e V2 aplicadas. |
| React 18 + TypeScript + Vite | Configurado; catálogo de teste implementado. |
| HTML + CSS/CSS Modules | Proposta de interface. |
| Docker/Compose + Nginx ou equivalente | Escolha operacional depende do servidor disponível. |
| DBeaver Community, Bruno, IDE gratuita | Apoio local opcional. |

Não depender de créditos de nuvem ou licença temporária para executar o sistema. Maven Wrapper e lockfile do frontend entram no Git.

## Decisões que antecedem o código

- Estratégia de sessão, proteção das requisições e matriz de permissões. JWT não está pré-aprovado.
- Versões exatas, sistema operacional do servidor e capacidade da máquina.
- Estados, índices, integridade e estratégia de concorrência no fechamento.
- Tratamento de fotos, datas, dinheiro e erros de API.
- localhost ou LAN; para LAN, definir HTTPS confiável nos dispositivos.

## Organização do repositório

| Diretório | Uso |
| --- | --- |
| `backend/` | API, regras, migrações e testes Java. |
| `frontend/` | Interface, componentes e testes. |
| `infra/` | Configuração local e procedimentos de operação. |
| `docs/` | Requisitos, modelos, decisões, testes e backlog. |
| `.github/` | Templates de colaboração. |

## Personalização

Nome, cores, logo, contatos, catálogo e políticas por configuração. Atualizações usam a mesma base e migrações versionadas. Uma segunda instalação será testada em SV30, após o MVP.
