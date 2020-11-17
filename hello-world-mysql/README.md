# Spring boot application with mysql

This application consist of the endpoint "/hello-world" that retrieve the message from the database table and returns to the client

#### Application details

```
Database driver : mysql
Database name : hello_world_poc
Database username : ****
Database password : ****

Table : messages
+----+---------------------------------+
| id | message                         |
+----+---------------------------------+
| 30 | Hello World Message From Server |
+----+---------------------------------+

*** Endpoint working ***
Endpoint : /hello-world
 
When the endpoint is hit for the first time the string "Hello World Message From Server" is inserted into the "messages" table.
In the successive hits the values are retrieved from the table andd displayed



```
## Multilayer docker file
Cache for image optimization.

But rebuilds from the step if a small modification is made in the docker file.

Dockerfile

```
# Base Image
FROM openjdk:8-jdk-alpine
# Arg - variable during build time
ARG DEPENDENCY=target/dependency
# Port in which the container is exposed not host
EXPOSE 8080
#Efficient caching
# copy dependency into the app
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
# copy classes into the application folder directly for execution
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT - first command to be executed when the container starts
ENTRYPOINT ["java","-cp","app:app/lib/*","com.docker.restapimysql.helloworldmysql.HelloWorldMysqlApplication"]

```

Using the above dockerfile a docker image is created.

```
shanthi/hello-world-mysql:0.0.1-SNAPSHOT
``` 

Now executing the image - (shanthi/hello-world-mysql:0.0.1-SNAPSHOT)

command : 
```
sudo docker run --name hello-world-container-1 shanthi/hello-world-mysql:0.0.1-SNAPSHOT
```
:heavy_exclamation_mark: Error

```
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure

The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
```
This implies that the database connectivity is not achieved.

Creating a mysql image and configuring the database required by the application.

```
sudo docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=hello_world_poc --name mysql_container -p 3307:3306 mysql:5.7

```

Now a container is created and executing

```
sudo docker container ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                               NAMES
6c44be164c16        mysql:5.7           "docker-entrypoint.s…"   44 seconds ago      Up 43 seconds       33060/tcp, 0.0.0.0:3307->3306/tcp   mysql_container
```
Verify the database creation at user@localhost:3306


Connecting Application and DB using network

Create a custom network

```
# creating a bridge network - manual-hello-world-be
sudo docker network create manual-hello-world-be

#verify the network
sudo docker network ls

```

Delete the already running container(mysql).Use the below command to add the container to the network along with the persistance storage for the database.

##### Mysql container in "manual-hello-world-be" custom network
```
sudo docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=hello_world_poc --name mysql_container -p 3307:3306 --network=manual-hello-world-be --volume mysql-hello-db-vol=/var/lib/mysql mysql:5.7
```
##### Application container in "manual-hello-world-be" custom network
```

```

####Creating a docker compose to automate

```
version: '3.7' #version of docker
services:
  hello-world-sb:
    build:
      context: . #use the current folder
      dockerfile: Dockerfile #Name of the docker file
    ports:
      - "8082:8082"
    restart: always
    depends_on: # Start the depends_on first
      - mysql
    environment:
      DATABASE_HOSTNAME: mysql  # Should be same as the service name
#      DATABASE_PORT: 3307
      DATABASE_NAME: hello_world_poc # database variable for application.properties
      DATABASE_USERNAME: user # database username variable for application.properties
      DATABASE_PASSWORD: password # database password variable for application.properties
    networks:
      - hello-world-springboot #Custom network

  mysql:
    image: mysql:5.7 #base image
#    ports:
#      - "3307:3306"
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_USER: user
      MYSQL_PASSWORD: password
      MYSQL_DATABASE: hello_world_poc
    volumes:
      - mysql-database-data-volume:/var/lib/mysql #mysql folder inside host that contains data
    networks:
      - hello-world-springboot #Custom network

# Volumes
volumes:
  mysql-database-data-volume:

#Custom network
networks:
  hello-world-springboot:
```


## Multistage docker compose file:

- More fexibility - Plat form independent file
- Initial build takes time
- Uses cache for speed

Dockerfile
```
# Stage - 1
FROM maven:3.6.1-jdk-8-alpine as backend-build
WORKDIR /hello_world/backend

### Step 1 - Copy pom.xml and download project dependencies

# Dividing copy into two steps to ensure that we download dependencies
# only when pom.xml changes
COPY pom.xml .
# dependency:go-offline - Goal that resolves all project dependencies,
# including plugins and reports and their dependencies. -B -> Batch mode
RUN mvn dependency:go-offline -B

### Step 2 - Copy source and build "deployable package"
COPY src src
RUN mvn install -DskipTests

# Unzip
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage - 2 Building minimal docker file
# Base Image
FROM openjdk:8-jdk-alpine
# Arg - variable during build time
ARG DEPENDENCY=target/dependency
# Port in which the container is exposed not host
EXPOSE 8080
#Efficient caching
# copy dependency into the app
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
# copy classes into the application folder directly for execution
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT - first command to be executed when the container starts
ENTRYPOINT ["java","-cp","app:app/lib/*","com.docker.restapimysql.helloworldmysql.HelloWorldMysqlApplication"]

```
