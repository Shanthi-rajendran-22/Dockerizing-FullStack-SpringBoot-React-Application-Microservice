# Hello-world with axios - Containerization with Docker

### Application
Hello-world project is a single page react application that displays the value of a state variable.
Axios method is used to fetch value from the rest endpoint and update the value of the state.

### Steps required to create a image of the application
1. Use Nodejs as base image
2. Copy the dependency to the image and start installing using npm install 
3. Copy the src code to the nodejs image 
4. use npm run build to create a production ready code in .gzip format
5. Use a nginx image to run the package

Dockerfile
```
## Stage 1 - Lets build .gzip
FROM node:13.12.0-alpine as frontend-build
WORKDIR /hello-world/frontend

# Step 1 - Download dependency
COPY package*.json ./
RUN npm install

# Step 2 - Copy all source and run build
# untill you add an extra dependency and make only code modification docker build starts only for this step
COPY . ./
RUN npm run build

## Stage 2 - Let's build a minimal image with .gzip
FROM nginx:1.12-alpine
COPY --from=frontend-build /hello-world/frontend/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```
Use 
```sudo docker build .``` to build the image for the application

> Sending build context to Docker daemon  **256.4MB**

To reduce the size of build context create a .dockerignore file and add "node_modules" to the file

Build again
> Sending build context to Docker daemon    **895kB**

Docker compose file is not required since there is a single service but the sample docker-compose file is given below

```
version: '3.7'
services:
  hello-world-frontend:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "4200:80"
    restart: always
    depends_on:
      - hello-world-api 
    networks:
      - hello-world-springboot

 
  
# Networks to be created to facilitate communication between containers
networks:
  hello-world-springboot:
```


Run command "sudo docker-compose up" to start the application and you can find the application running in **"http://0.0.0.0:4200/"**