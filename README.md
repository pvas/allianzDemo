# Mapfre Professional Selection

# Description

- RESTful server application which provides this backend service to store CO2 emission from sensor 

### Tech

The application is built using Spring Boot framework (2.3.1.RELEASE) with the following libraries:

*	[Spring JPA 2.3.1.RELEASE]: to interact with the database
*	[Spring Web 2.3.1.RELEASE]: In order to create endpoint available to the final user
*	[H2 1.4.200]: to create an H2 database in memory
*	[Lombok 1.18.12]: to avoid the boiler plate when we are creating the setter and getters method
*	[Jacksondatabind 2.11.0]: Convert that data from json format to java objects format
*	[javax-validator 2.0.1.Final]: With this library we validate the input DTO’s object that the application receives in a clean ordered and scalable matter 
*	[Junit: 5.6.2]: framework that was used to create unit test for the application 
*	[Mockito 3.3.3]: library that allows us to mock and validate some methods call in unit tests
*	[springfox-swagger2 3.0.0-SNAPSHOT]: simplify API development with the Swagger open source and professional toolset.

Everything managed with Maven, using Java 8.

### Installation

Compile it using Maven

```sh
$ cd coreader
$ mvn clean install
$ java -jar target/coreader-1.0.0-SNAPSHOT.jar
```

### Test

Send a post rest call to the follow URL: http://localhost:8080/sensor with the format specified on tecnical design.


