# Web application for food delivery K-Curry Jib

### 1. Clone the repository

### 2. Launch jar archive

###### The driver for the *MySQL* database must be installed on the computer. For example *Workbench*. When you first launch the application, a database will be created, and *Liquibase* will create all the necessary tables for the application to work properly.

*At the root of the project*

```
java -jar kcj-customer-be.jar
```

---

### [3. The docker way](https://hub.docker.com/repository/docker/ikarzindo/k-curry-jib-customer-app/general)

###### At the 1st launch, a DB will be created.

*At the root of the project*

```
docker-compose build
docker-compose up
```

---

### [4. View](http://localhost:8889)

*Copy to browser address bar*

```
http://localhost:8889
```

## Customer part web application

### Used technology stack in my web application:

- Java 17
- Spring Framework
- Spring Security
- MySQL
- JPA
- Mapstruct

---

- Jupiter - Testing
- Maven
- SLF4J

---

- WebMVC

### 5. Authorization data 

```bash

# username:pass
echo -n "maria@mail.com:1qaz" | base64
echo -n "thomas@mail.com:1qaz" | base64
echo -n "christina@mail.com@mail.com:1qaz" | base64
echo -n "elizabeth@mail.com@mail.com:1qaz" | base64
echo -n "frederique@mail.com@mail.com:1qaz" | base64

```