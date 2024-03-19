# Desafio Técnico
- Sacar dinheiro de acordo com o valor que tem na empresa
- Depositar valores na empresa específica
- Verificação se o cliente tem cadastro na empresa para poder:
   - sacar
   - depositar
- Criação de clientes e empresas
- Tratamento de erros

# Rotas
| Rota                        |     Method    |   Descrição      |     
| ----------------------------| ------------- | ---------------- |
| api/client/add              | POST          | Criar um cliente     |
| api/company/add             | POST          | Criar uma empresa    |
| api/transaction/add         | POST          | Criar relação entre cliente e empresa |
| api/transaction/deposit     | PUT           | Depositar valor em um determinada empresa  que está cadastrada |
| api/transaction/withdraw    | PUT           | Sacar valor em uma determinada empresa que está cadastrada |

# Tecnologias
- Spring Boot
- Maven
- JPA
- Hibernate
- Postgres

# Arquitetura
- Foi escolhido MVC com desacoplamento de dependências e adicionado dto para transferir dados especifícos

# Desenvolvido por:
- Angélica Shigematsu
