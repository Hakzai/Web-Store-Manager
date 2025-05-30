# WebStore Manager App

## Install Guide

Welcome to the install guide.

Please be attempt to Technologies used on this project.

### Technology Stack versions:
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

**To build and run make sure you're using same versions** (or higher, with compatibility).

## How to Start

- Download Java OpenJDK 23.x and set your machine variables for JAVA_HOME
- Download Apache Maven 3.9.x 
- Run 'mvn eclipse:eclipse' command at the project root folder to download dependency libs
- Download and install WAMP Server (or other MySQL supported server) and set it to port 3308
- Run the server and create database named 'webstore' on it
- After all setup you just need to create the jar to run the app. Create it using 'mvn clean install' command at project root folder
    - When the build finishes, open the target folder and start the app with command 'java -jar webstore-x.x.x.jar' (being 'x' the number related to version)
    - If you want it, it is possible to run through IDEs like STS (which is mentioned on personal tools)
- When server and start are up you can refer to [Open-API Doc](http://localhost:8080/swagger-ui/index.html) for APIs details or try the api-docs files at ~/docs/ path.
    - Initial standard user to access it is: docreader | docreader
    - To use external apps calling you can use default SA user: sysadm | sysadm
    - As an example, you can use this validated payload as your first order placement 
        _{"customerName": "Joao da Mercearia", "items": [{"name": "Papel Higienico", "unitPrice": 15.0, "orderedQuantity": 15, "description": "Papel Higienico da marca Neve", "category": "Higiene" }], registerDate": "2025-05-01T04:57:59", "status": "PENDING" }_
- After creating the first order, try using other APIs to check functionalities.
