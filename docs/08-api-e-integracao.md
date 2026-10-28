# API e integração

**Estado:** contrato incremental. Catálogo, interesses, estoque,
autenticação e área do comprador estão implementados até E2; CRM, propostas,
simulação e venda continuam planejados para E3.

## Famílias previstas

| Contexto | Operações a detalhar | Requisitos |
| --- | --- | --- |
| Catálogo | Listar, filtrar e consultar detalhes públicos. | RF02 |
| Conta / sessão | Cadastro de comprador, login, logout e usuário atual. | RF03 |
| Estoque | Modelo, unidade, fotos e disponibilidade. | RF01 |
| Atendimento | Interesse, contatos, responsável e funil. | RF04, RF05 |
| Proposta / simulação | Versões, condições e cronograma fictício. | RF06, RF07 |
| Aprovação / venda | Decisões e fechamento transacional. | RF08 |
| Minha área | Atendimentos, propostas e compra do titular. | RF09 |
| Administração | Usuários, perfis, configuração e resumo. | RF10 |

Nomes de rotas e payloads serão definidos no contrato aprovado. A listagem acima não deve ser copiada como se fosse uma API pronta.

## Primeiro contrato implementado

`GET /api/v1/catalog?brand={marca}&maxPrice={valor}`

Retorna unidades publicadas e disponíveis, ordenadas por marca, modelo e preço.
Os filtros são opcionais. A resposta é uma lista de objetos com `id`, `brand`,
`name`, `price`, `condition`, `status` e `description`. O detalhe público usa
`GET /api/v1/catalog/{id}` e retorna `404` quando a unidade não existe ou não
está publicada.

`POST /api/v1/interests` registra um comprador, uma oportunidade no estágio
`NEW` e a primeira interação do formulário em uma única transação. O payload
exige `vehicleId`, `name`, `email` e `message`, com `phone` opcional. A API
retorna `409` quando a unidade não está publicada ou disponível.

## Estoque (E2)

- `GET /api/v1/inventory` lista unidades e seus estados para a área interna.
- `POST /api/v1/inventory` cadastra modelo/unidade como `DRAFT`.
- `PATCH /api/v1/inventory/{id}/publication` publica ou retira uma unidade do
  catálogo. A publicação muda o estado para `AVAILABLE`; retirar muda para
  `DRAFT`.

O endpoint de estoque exige autenticação e os perfis `ADMIN`, `MANAGER` ou
`STOCK`. Ele continua destinado ao ambiente local até a revisão da matriz final
de permissões.

## Autenticação (E2)

`POST /api/v1/auth/register` cria uma conta de comprador (`fullName`, `email`,
`password` e `phone` opcional) e já inicia a sessão. `POST /api/v1/auth/login` recebe `email` e `password` e retorna um token de
sessão com validade de oito horas, além do perfil do usuário. Envie o token em
`Authorization: Bearer <token>` para acessar o estoque. O catálogo e o registro
de interesse continuam públicos. O usuário `admin@example.com` com senha
`password` existe apenas para desenvolvimento local.

Contas autenticadas de comprador acessam `GET /api/v1/me`, que retorna o perfil,
atendimentos, propostas e compras somente do titular. Também existem as consultas
independentes `/api/v1/me/interests`, `/api/v1/me/proposals` e
`/api/v1/me/purchases`; todas exigem o papel `BUYER` e filtram pelo usuário da
sessão, nunca por um identificador recebido pela URL.

O catálogo aceita também `minPrice`, `condition` e `category`. A listagem mantém
somente unidades publicadas e disponíveis; o detalhe de uma unidade publicada
retorna o campo `available`, inclusive quando ela está temporariamente
indisponível.

## Conteúdo de cada operação

Método e caminho; finalidade; perfil autorizado; campos e validações; respostas e erros; paginação quando necessária; exemplos fictícios; RF/RN relacionados; propriedade do recurso; efeitos transacionais.

Definir também formato de dinheiro, datas/fuso, identificadores, mensagens e versão do contrato. HTTP 401, 403, 404 e conflitos devem ter significado consistente conforme a política aprovada.

## Integração cedo

1. Backend e frontend revisam juntos os contratos do primeiro fluxo.
2. Criam exemplos fictícios compartilhados, coerentes com o modelo.
3. Conectam uma tela a uma API e persistência reais em SV10.
4. Implementam cada história como entrega integrada.
5. Registram alterações de contrato na issue e no PR.

Mocks permitem antecipar telas, mas não comprovam persistência, autorização ou integração.

## Autorização

O servidor valida autenticação, perfil e titularidade. Ocultar botão ou proteger rota React não basta. Consultas da área do comprador devem impedir acesso a registros de outro comprador, inclusive por ID direto.

## Arquivos do contrato

O caminho concreto do OpenAPI e das coleções locais será registrado quando
criado em SV08. Não há arquivos OpenAPI nem Swagger funcionando neste pacote.
