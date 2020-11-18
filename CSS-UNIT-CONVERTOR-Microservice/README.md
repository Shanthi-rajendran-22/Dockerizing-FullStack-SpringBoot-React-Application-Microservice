# CSS UNIT CONVERTOR - Spring microservice - Containerization with Docker

### Application
CSS UNIT CONVERTOR is a springboot microservice application that has 2 services.
1. convertor service
2. conversion service 

Convertor service :

- has endpoint **/convertor/{from}/{to}** , {from}  - that receives the input (ex.pixel) and {to} - gets the value to which the value is suppose to be converted (ex.percentage,point,rem..etc)
- It communicates with the mysql database that store the value of convertion

conversion service :
- this service communicates has the endpoint that receives the 


