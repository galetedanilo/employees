# Employees

# Sobre o projeto

Employees é uma aplicação back end desenvolvida com Spring Boot para implementar um CRUD completo de web services REST para acessar os recursos de Employees, Departments e Users.
Neste sistema, todas as rotas são protegidas. Usuários ADMIN podem ler e alterar recursos, enquanto que usuários OPERATOR podem apenas ler(GET) os recursos e foram utilizados
recursos para validação das informações com Spring Validation.
 

# Modelo Conceitual

![Modelo](https://github.com/galetedanilo/employees/blob/master/assets/modelo.png)

# Padrão Camada

![Padrao](https://github.com/galetedanilo/employees/blob/master/assets/camadas.png)

# Tecnologias Utilizadas

## Back end

- Java
- Spring Boot
- Spring Security
- Spring Validation
- Spring Hateoas
- OAuth 2.0
- Token JWT
- JPA / Hibernate
- Lombok
- JUnit
- Mockito
- Maven

# Como Executar o Projeto

## Back end:

Pré-requisito: Java 17

```bash
# clonar repositório
git clone https://github.com/galetedanilo/employees.git

# entrar na pasta do back end
cd backend

# executar projeto
./mvnw spring-boot:run
```

# Agradecimentos

-  Equipe DevSuperior
-  Prof. Nélio Alves

# Autor

Danilo Fernandes Galete

https://www.linkedin.com/in/galetedanilo/

