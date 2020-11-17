Building Image
- Build application jar (mvn clean package)
- Build openjdk:8-jdk-alpine image
- Add application jar to openjdk:8-jdk-alpine container
- Execute jar

Manual Steps to Build Image
- Build application jar (using mvn clean package)<br/>
  Output : target/hello-world-rest-api.jar
- Creating openjdk:8-jdk-alpine container<br/>
  `docker run -dit openjdk:8-jdk-alpine`          
  `sudo docker container ls` 
  check if the container is running
- Copy the application jar(target/hello-world-rest-api.jar) inside /tmp of the created container                                             
  `sudo docker container cp target/hello-world-rest-api.jar jolly_hoover:/tmp`
  `sudo docker exec jolly_hoover ls /tmp`       
  check if jar is copied inside the jdk container                                                                                                                                               
- Execute the jar file
  `sudo docker container commit --change='CMD ["java","-jar","/tmp/hello-world-rest-api.jar"]' jolly_hoover hello-world-rest-api:manual`
   jolly_hover - container name , hello-world-rest-api:manual - final_image_name:image_tag <br/>
   If any change in code , rebuild jar using mvn clean package and use the above comment to commit changes to the image<br/>
  `sudo docker images`
   check if the image is created 
  `sudo docker run -p 8083:8080 hello-world-rest-api:manual` <br/>
   Output: http://0.0.0.0:8083/hello-world <br/>
   > Hello World

Automating the above process using DockerFile

- Dockerfile
```
# Base Image
FROM openjdk:8-jdk-alpine
# Port in which the container is exposed not host
EXPOSE 8080
# Adding sorce code from target to image in the name hello-world-rest-api.jar
ADD target/hello-world-rest-api.jar hello-world-rest-api.jar
# ENTRYPOINT - first command to be executed when the container starts
ENTRYPOINT ["sh", "-c", "java -jar /hello-world-rest-api.jar"]
```     

- Building the docker image<br/>
`sudo docker build -t hello-world:dockerfile .
`
- Check if images is created
 `sudo docker build -t hello-world:dockerfile .` <br/>
   Output :
   > REPOSITORY                                           TAG                 IMAGE ID            CREATED             SIZE 
   > hello-world                                          dockerfile          05c4dad9715a        3 minutes ago       122M

   If any changes in the code - rebuild docker image on jar build (mvn clean package)

Auto Build Docker image using spotify plugin
`
<plugin>
   <groupId>com.spotify</groupId>
   <artifactId>dockerfile-maven-plugin</artifactId>
   <version>1.4.10</version>
   <executions>
      <execution>
         <id>default</id>
         <goals>
            <goal>build</goal>
         </goals>
      </execution>
   </executions>
   <configuration>
      <repository>shanthi/${project.name}</repository>
      <tag>${project.version}</tag>
      <skipDockerInfo>true</skipDockerInfo>
   </configuration>
</plugin>
`               
Repository name can also appended with the image if required for future push in docker hub
```
<repository>YOUR_REPOSITORY_NAME/${project.name}</repository>
<tag>${project.version}</tag>
```
> Add <goal>push</goal> to spotify to push the image to dockerhub on every build
Improve performance of image using layers 

1. JDK
2. Dependency
3. Classes

- Maven Dependency Plugin
```
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-dependency-plugin</artifactId>
   <executions>
      <execution>
         <id>unpack</id>
         <phase>package</phase>
         <goals>
            <goal>unpack</goal>
         </goals>
         <configuration>
            <artifactItems>
               <artifactItem>
                  <groupId>${project.groupId}</groupId>
                  <artifactId>${project.artifactId}</artifactId>
                  <version>${project.version}</version>
               </artifactItem>
            </artifactItems>
         </configuration>
      </execution>
   </executions>
</plugin>
```
The above maven dependency unpacks the library and the class file into different folders
target/dependency/BOOT_INF,target/dependency/META_INF.

So we can to containerize docker as different layers dependency,classess

Reusable Multilayer Dockerfile

- Update the Dockerfile
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
ENTRYPOINT ["java","-cp","app:app/lib/*","com.docker.restfulwebservices.Application"]
```

-----------------------------------------

Reusable Dockerfile with single layer

- Dockerfile
```
# Base Image
FROM openjdk:8-jdk-alpine
# Port in which the container is exposed not host
EXPOSE 8080
# Adding sorce code from target to image in the name app.jar
ADD target/*.jar app.jar
# ENTRYPOINT - first command to be executed when the container starts
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
```    
       
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             