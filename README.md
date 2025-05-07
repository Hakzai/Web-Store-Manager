# WebStore Manager App

This is a webstore manager service to receive, process and manage orders. It can be integrated with external systems. It was proposed as a technical challenge from Guarani Sistemas talent acquisition team.

## Purpose 

Develop a RESTful API for an ordering system from a web store. The system must allow creation, reading, updating and deleting (CRUD) orders, and besides it should have functions like price management, status management, and support to different incoming payment ways.

The orders and its products can be integrated t be available to be retrieved and consulted by another external system (Plus functionality).

## Requirements:

Requisitos Funcionais
1. Gerenciamento de Pedidos:
   - CRUD completo para pedidos (criação, leitura, atualização, exclusão).
   - Listagem de pedidos com filtros por status, data de criação e valor.
   - Consulta detalhada de um pedido, incluindo produtos, quantidades, valores e status.

2. Gerenciamento de Produtos:
   - CRUD de produtos associados aos pedidos.
   - Consultas detalhadas dos produtos com informações como preço, descrição, e categoria.
   - Filtros de produtos por nome, categoria e preço.

3. Integração de Pagamentos:
   - Gerenciar diferentes formas de pagamento (cartão de crédito, boleto, transferência bancária).

4. Autenticação e Autautenticação via Spring Security.
   - Diferentes perfis de usuário (Admin, Cliente, Operador).
   - Controle de acessos baseado em permissões (ex: apenas admins podem excluir pedidos).- orização:
   - Implementar 

5. Cálculo de Preços:
   - Implementar cálculo de total do pedido com base nos produtos adicionados, aplicando descontos e taxas (ex: frete).
   - Recalcular o valor do pedido ao alterar a quantidade de itens.

6. Relatórios: (Opcional)
   - Gerar relatórios de vendas mensais, diários ou por período customizado.
   - Relatórios de pedidos por status (concluído, pendente, cancelado).

8. Documentação:
   - Gerar documentação automática da API utilizando Swagger/OpenAPI.

#### Requisitos Não-Funcionais
1. Segurança:
   - A aplicação deve ser usado o spring security.

2. Testes: (Opcional mas contará bastante)
   - Testes unitários para cada componente utilizando JUnit e Mockito.
   - Testes de integração para garantir o funcionamento correto entre os diferentes módulos do sistema.
   - Testes de carga e performance.

**Extra-Points Requirements:**
- Check for duplicated orders;
- Garantee the service availability even with high volume of orders (from 150k to 200k per day);
- Have data consistency and allow concurrency;
- Check if the high volume can cause database bottleneck and how to avoid it. 

## Technology Requirements

**Back-end**
- Write the required API with Java 17+;
- Use Spring Boot Framework (version 3.x);
- Database (to be chosen from MySQL or PostgreSQL);
- Implement Spring Security (with OAuth2 and JWT support);

**Documentation**
- Tests with JUnit 5 and/or Mockito;
- API Documentation: Swagger/OpenAPI;

**Messaging:**
- Messaging protocols: RabbitMQ or Kafka (if needed);

**Versioning:**
- Versioning: Git (GitHub/GitLab).


## Roadmap

* [X]  Create the API using Spring Boot Framework
* [X]  Layer Architecture (using MVC)
* [X]  Use a relational persistance database (using MySQL running on WAMPP server)
* [X]  API Documentation with Swagger
* []  App monitoring with Actuator
* [X]  Published on GitHub

## Deliverables
* [X] Complete Source code at GitHub/GitLab
* [] API Technical documents
* [] Unit tests Reports
* [X] Installing and integration instructions
* [] CI/CD Implementation (Extra Points for implementation)

## Installing

Project is managed with maven. POM file is inside project.

It was written using:
* [X] OpenJDK 23.0.1
* [X] Spring Boot 3.4.2
* [X] Apache Maven 3.9.9
* [X] Apache Tomcat 9.0.96
* [] Apache Kafka 3.9.0
* [] Docker version 27.5.1
* [X] MySQL 8.0.18
_Unmarked technologies are yet to be implemented_

To build and run make sure you're using same versions (or higher, with compatibility).

Personal tools setup:
- Spring Tool Suite 4.25.0
- WAMPP Server 3.2.2.2
- Postman Portable Version 8.5.1

## Author

Project done by Isaac Alencar (Akeir Technology)