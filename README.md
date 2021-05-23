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
`http://localhost:8080/swagger-ui/#/`

In case, swagger doesn't open up, please use these curl requests:

## Health Check API
`curl -X GET \
  http://localhost:8080/ `
  
## Create a Category
`curl -X POST \
  http://localhost:8080/category/ \
  -H 'content-type: application/json' \
  -d '{
	"name": "Cloth",
	"description": "Wear"
}'`

## Create Category Attributes
`curl -X POST \
  http://localhost:8080/category/attribute \
  -H 'content-type: application/json' \
  -d '{
	"name": "Color",
	"description": "Color of Cloth",
	"categoryId": 711577
}'`

## Get Category Attributes By Category ID
`curl -X GET \
  http://localhost:8080/category/711577/attribute`
  
## Create a Product by linking it to a Category and its attributes
`curl -X POST \
  http://localhost:8080/product \
  -H 'content-type: application/json' \
  -d '{
  "name": "Wrangler Shirt",
  "description": "Clothing Item",
  "categoryId": 711577,
  "categoryAttributes": [
    {
      "categoryId": 711577,
      "attributeId": 145684,
      "attributeName": "Color",
      "attributeValue": "BLACK"
    }
  ]
}'`

## Get Product By ID
`curl -X GET \
  http://localhost:8080/product/92403996`
