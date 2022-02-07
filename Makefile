package:
	@ mvn clean package
	
docker-image-build: package
	@ docker build -t joao/virtual-store-api:latest . 

run: docker-image-build
	@ docker run joao/virtual-store-api:latest
	
stop:
	@ docker stop joao/virtual-store-api:latest
	
	
	
deploy: docker-image-build
	@ heroku container:login
	@ docker image tag joao/virtual-store-api:latest registry.heroku.com/joao-virtual-store-api-teste/web:1
	@ heroku container:push web
	@ heroku container:release web
	
	
	
	
	
	
	
	
