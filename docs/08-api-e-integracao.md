# API e integração

**Estado:** roteiro do contrato, sem endpoints implementados. Produzir o contrato concreto em SV08.

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
