# DOCKER FOR SPRINGBOOT REST API APPLICATION , REACT APPLICATION , REACT AND SPRINGBOOT(FULL STACK APPLICATION), SPRING MICROSERVICES

Before you start find your docker basic commands here


DockerFile commands

|  Command |  Purpose |
|---|---|
| FROM <image> | Refers to base image  |
| ARG var=value |  Variable during build time |
| EXPOSE <port_number> |  Port in which the container is exposed not host |
| COPY <$source> <$destination> | Copy a folder or file  |
| ADD <$source> <$destination>  | Create or Copy a file or folder or Download from URL and copy  |
| CMD <["",""]>  | Command to be executed <br/> Overridden by args passed|
| ENTRYPOINT <["",""]>  | First command to be executed when the container starts <br/> Not overriden by args passed |
|   |   |


## Sample Applications

1. [DOCKER FOR SPRINGBOOT REST API APPLICATION](https://github.com/Shanthi-rajendran-22/Dockerizing-FullStack-SpringBoot-React-Application-Microservice/tree/master/Hello-World)
2. [DOCKER FOR SPRINGBOOT REST API WITH MYSQL DB APPLICATION](https://github.com/Shanthi-rajendran-22/Dockerizing-FullStack-SpringBoot-React-Application-Microservice/tree/master/hello-world-mysql)
3. [DOCKER FOR REACT APPLICATION](https://github.com/Shanthi-rajendran-22/Dockerizing-FullStack-SpringBoot-React-Application-Microservice/tree/master/hello-world-fe)
4. [DOCKER FOR REACT AND SPRINGBOOT(FULL STACK APPLICATION) - combining project 2 & 3 using docker-compose](https://github.com/Shanthi-rajendran-22/Dockerizing-FullStack-SpringBoot-React-Application-Microservice/blob/master/docker-compose.yml)
5. [DOCKER FOR SPRINGBOOT MICROSERVICES](https://github.com/Shanthi-rajendran-22/Dockerizing-FullStack-SpringBoot-React-Application-Microservice/tree/master/CSS-UNIT-CONVERTOR-Microservice) 


### DOCKER FOR REACT AND SPRINGBOOT(FULL STACK APPLICATION) - combining project 2 & 3 using docker-compose
- Create a docker-compose file in the root folder of the 2 project folder
- Create 3 different services - springboot app , mysql , react app and connect to the common network
- use "docker-compose up" command to start the application

Docker-compose file 
