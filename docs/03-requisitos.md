# Requisitos

**Estado:** detalhamento proposto para validação. IDs preservados do plano local. A execução local e a diretriz de evitar gastos são restrições confirmadas; os critérios abaixo ainda precisam de aprovação.

## Requisitos funcionais

| ID | Resultado observável | Escopo |
| --- | --- | --- |
| RF01 | Cadastrar e alterar modelos e unidades de moto, com identificador, preço, condição, estado e fotos locais. | MVP |
| RF02 | Publicar catálogo sem login, com filtros e detalhes; indicar claramente indisponibilidade. | MVP |
| RF03 | Criar conta do comprador, autenticar funcionários, encerrar sessão e controlar perfis. | MVP |
| RF04 | Registrar interesse, comprador/prospect e atendimento com origem, responsável e histórico. | MVP |
| RF05 | Exibir funil e aplicar somente transições de etapa permitidas. | MVP |
| RF06 | Criar e versionar proposta ligada a comprador e unidade, com preço, desconto, validade e pagamento. | MVP |
| RF07 | Simular à vista, Pix, Pix agendado e parcelamento, exibindo entrada, taxa, períodos, parcelas e total. | MVP |
| RF08 | Registrar aprovações e concluir venda fictícia com validação de disponibilidade e consistência. | MVP |
| RF09 | Comprador consulta apenas seus atendimentos, propostas e estado da compra. | MVP |
| RF10 | Exibir resumo de vendas, configurar a loja e controlar usuários e permissões. | MVP |
| RF11 | Buscar/recomendar por orçamento, uso, preferências e catálogo disponível. | Evolução |
| RF12 | Estender pós-venda, comissões, indicadores e condições para outros cenários validados. | Evolução |

Campos, obrigatoriedade, mensagens de erro e perfis devem ser detalhados antes de implementar cada RF. Não interpretar “cadastro” como um contrato completo.

## Requisitos não funcionais

| ID | Atributo | Critério proposto |
| --- | --- | --- |
| RNF01 | Proteção | Validar perfil e propriedade no servidor; barrar visitante no CRM e comprador A nos dados de B; persistir senhas como hash adequado. |
| RNF02 | Confiança | Duas tentativas simultâneas de vender a mesma unidade produzem uma única venda válida; falha não deixa gravações parciais. |
| RNF03 | Manutenibilidade | Módulos separados, migrações, contrato, manual e decisões versionados; verificar regras alteradas. |
| RNF04 | Eficiência | Meta a validar: primeira página do catálogo em até 2 s em 95 de 100 medições na LAN, com 500 unidades e 5 usuários simultâneos. Registrar hardware e método. |
| RNF05 | Aceitabilidade | Validar a jornada com participantes; verificar teclado, rótulos, contraste e mensagens. |
| RNF06 | Operação local | Jornada sem internet após preparo; dados após reinício; instalação por manual e restauração de backup de teste. |
| RNF07 | Privacidade | Dados fictícios; sem dados pessoais em logs/repositório/relatórios públicos; qualquer piloto real exige planejamento específico. |

A meta RNF04 veio do plano como proposta, não como medição ou garantia. Revisá-la em SV07 conforme a máquina e em SV26 conforme as evidências.

## Validação e rastreabilidade

Para cada requisito: registrar estado, revisor, data, motivo de mudança, histórias, regras, cards e testes afetados. Os vínculos iniciais estão em [testes e rastreabilidade](09-testes-e-rastreabilidade.md).
