# API e integração

**Estado:** contrato incremental. O primeiro endpoint público de catálogo está
implementado; as demais famílias continuam sendo adicionadas por fatias.

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

## Estoque (checkpoint E2)

- `GET /api/v1/inventory` lista unidades e seus estados para a área interna.
- `POST /api/v1/inventory` cadastra modelo/unidade como `DRAFT`.
- `PATCH /api/v1/inventory/{id}/publication` publica ou retira uma unidade do
  catálogo. A publicação muda o estado para `AVAILABLE`; retirar muda para
  `DRAFT`.

O endpoint de estoque ainda é uma superfície local de desenvolvimento. A
autenticação e a matriz de perfis devem ser implementadas antes de expor essa
rota fora do ambiente de teste.

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

O caminho concreto do OpenAPI e das coleções locais será registrado quando criado em SV08. Não há arquivos OpenAPI nem Swagger funcionando neste pacote.
