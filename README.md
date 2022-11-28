# Virtual Store  

A RESTful API for managing an online store.

## Prerequisites

Before you begin, ensure you have met the following requirements:

* Java 8 (JavaSE-1.8)
* Spring 2.5.2
* Docker 19+
* GNU Make 4.3+

## Running Virtual store API

To run this project simply execute this commands inside the project path

```shell script
./mvn clean package
docker build -t joao/virtual-store-api:latest . 
docker run joao/virtual-store-api:latest
```
or if you just want to view the documentation you can try: 

## Link for swagger documentation

* https://virtual-store-api.herokuapp.com/swagger-ui.html#/
