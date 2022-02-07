FROM openjdk:8-jre-slim

WORKDIR /products-json


COPY target/*.jar /products-json/app.jar 


EXPOSE 8080 


CMD java -XX:+UseContainerSupport -Xmx512m -jar app.jar --server.port=$PORT
