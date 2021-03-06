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
`curl --request GET 
  --url http://localhost:8080/`
  
## Create a Category
`curl --request POST 
  --url http://localhost:8080/category/ 
  --header 'content-type: application/json' 
  --data '{"name": "Cloth", "description": "Wear"}'`
  
NOTE:- After Category is Created, Please save the categoryId that you received in response for Creating Category Attributes and for linking product to category

## Create Category Attributes
`curl -X POST 
  http://localhost:8080/category/attribute 
  -H 'content-type: application/json' 
  -d '{
	"name": "Color",
	"description": "Color of Cloth",
	"categoryId": 711577
}'`

## Get Category Attributes By Category ID
`curl -X GET 
  http://localhost:8080/category/711577/attribute`
  
## Create a Product by linking it to a Category and its attributes
`curl -X POST 
  http://localhost:8080/product 
  -H 'content-type: application/json' 
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

NOTE:- After Product is Created, Please save the productId that you received in response for fetching productInfo by productId

## Get Product By ID
`curl -X GET 
  http://localhost:8080/product/92403996`
