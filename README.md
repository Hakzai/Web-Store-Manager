# WebStore Manager App

This is a webstore manager service to receive, process and manage orders. It can be integrated with external systems. It was proposed as a technical challenge from Guarani Sistemas talent acquisition team.

## Purpose 

Develop a RESTful API for an ordering system from a web store. The system must allow creation, reading, updating and deleting (CRUD) orders, and besides it should have functions like price management, status management, and support to different incoming payment ways.

The orders and its products can be integrated t be available to be retrieved and consulted by another external system (Plus functionality).

## Requirements:

1. Order management:
- Complete CRUD operations for ordering.
- Listing and filter by status, creation date and value amount.
- Detailed query result for a given order including products, quantity, values and status.

2. Products management:
- Complete CRUD operations for the products.
- Listing and filter by name, category and prices.
- Detailed query result for given products with price, description and category infos.

3. Payment/Incoming Integration:
- Manage different payment details and ways (credit and debit cards, direct billing, bank transfer, PIX).

4. Authentication with Spring Security.
- Create different users and roles (Admin, Customer, Operator/CSR).
- Access control based on permissions/roles (e.g: only admins can delete orders).

5. Price calculating:
- Implement ordering total amount based on added products, applying discounts and taxes (ex: courier charge).
- Calculate new order value when changing the items quantity.

6. Documentation:
- Create automatic API documentation using Swagger/OpenAPI.

**Extra-Points Requirements:**
1. Tests:
- Unit Testing for each component using JUnit and Mockito.
- Integration Testing to ensure proper functionalities between different system modules.
- Performance and Load/Overload Testing.

2. Reports (Optional)
- Create selling reports (monthly, daily or custom date).
- Create status ordering reports (finished, pending, canceled).   

3. CI/CD Process Implementation
- Complete automated deployment process

## Technology Requirements (Summary)

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

## Project Roadmap

* [X]  Code with Java 17+
* [ ]  Create the API using Spring Boot Framework
    * [X]  Order Management
    * [X]  Product Management
    * [X]  MVC Layer Architecture
    * [ ]  Payment Integrations
    * [ ]  Pricing Calculating after creation/updating
* [X]  Use a relational persistance database 
    * [X]  MySQL running on WAMPP server
* [ ]  Security layers
    * [X]  Implement Spring Security framework
    * [ ]  Create Users and Roles (Admin, Customer, Operator/CSR).
* [ ]  API Documentation 
    * [ ]  With OpenAPI
* [X]  Version Control on Git
    * [X]  Published on GitHub

**Extras**
* [ ]  App Testing and Automation
    * [ ]  Unit Testing components using JUnit/Mockito.
    * [ ]  Integration Testing between different system modules.
    * [ ]  Performance and Load/Overload Testing.
* [ ]  App Monitoring
    * [ ]  With Actuator
* [ ]  Reporting/Summary Mechanism
* [ ]  CI/CD Automated Processes
* [ ]  Messaging Handling with Kafka/RabbitMQ

## Deliverables
* [X] Complete Source code at GitHub/GitLab
* [ ] API Technical documents
* [ ] Unit tests Reports
* [X] Installing and integration instructions
* [ ] CI/CD Implementation

## Installing

To build and run Project please refer to **[INSTALL-GUIDE](https://github.com/Hakzai/Web-Store-Manager/blob/master/INSTALL-GUIDE.md)**

### Project Technologies Signature
- OpenJDK 23.0.1
- Spring Boot 3.3.4
- Spring Security Web 6.3.3
- JUnit 5.10.3
- Open API 2.3.0
- Apache Maven 3.9.9
- Apache Tomcat Embed 10.1.30
- Apache Kafka 3.9.0
- Docker version 27.5.1
- MySQL 8.0.18
- Git 2.47

### Personal Tools Setup:
- Spring Tool Suite 4.25.0
- WAMP Server 3.2.2.2
- Postman Portable Version 8.5.1

## Author

Project done by _Isaac Alencar (Akeir Technology)_