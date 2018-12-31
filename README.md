# CLIENTS MICROSERVICE - Event Driven with SpringBoot and RabbitMQ Producer

This is a simple example of microservice, we are using the event driven architecture. 

For more details of consumer see the example here: [https://github.com/felipegirotti/spring-boot-event-driven-consumer](https://github.com/felipegirotti/spring-boot-event-driven-consumer)

## API
We expose a simple CRUD HTTP API.   
- POST `/api/v1/client` Body:
  ```json
  {   
      "name": "Client Example"      
  }
  ```  
- GET `/api/v1/client/{id}`

- GET `/api/v1/clients?page=0`

- PUT `/api/v1/client/{id}` Body:
    ```json
    {   
         "name": "Client Example UP"
    }
    ```
- DELETE `/api/v1/client/{id}`


From POST/PUT we emit an event with routing key `clients.client.save`, for delete, we are using the same exchange and queue but the routing key is different `clients.client.delete`


## Run locally
We are using env variables to setup the database properties and others configs  
See the .env.example and export your variables.  
e.g:    
```bash
export $(cat .env.example | xargs)
mvn spring-boot:run
```

### Docker compose
There a few dependencies, Mysql and RabbitMQ, those are configured into the docker-compose.yaml.    
Run `docker-compose up -d`

### Build a image with docker
For build a image you should first create a jar file with this command below:   
```bash
mvn clean package
```

After that just build the image (pay attention on the file of jar):    
```bash
docker build . -t {{REPLACE_YOUR_DOCKER_HUB_NAME}}/place-java-example --build-arg JAR_FILE=target/place-0.0.1-SNAPSHOT.jar
```

And push the image to [DockerHub](https://hub.docker.com/)  
```bash
docker push {{REPLACE_YOUR_DOCKER_HUB_NAME}}/place-java-example
```
