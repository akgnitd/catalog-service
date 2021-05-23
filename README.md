# catalog-service

This is a Java MicroService on Spring Boot.

## Setting up development environment

Requirements: JDK 11, Maven, MySQL with DDL Access.

Run the Database Scripts present in /scripts folder.

## Configuring the Service
  Open the Application.yaml file and edit the MySQL connection details.

## Building the Service
  Open any terminal or command prompt and type:
  `mvn clean install`

## Running the Service
Once this command execution is completed, please go inside the /target folder and run the generated jar (catalog-service-0.0.1.jar) using this command:
  `java -jar catalog-service-0.0.1.jar &`
  
# API Documentation - provided by Swagger2
Once Application is running, hit below url
`http://localhost:8080/swagger-ui/#/product-controller`
