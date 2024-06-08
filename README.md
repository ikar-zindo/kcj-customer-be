# Web application for food delivery K-Curry Jib

### 1. Clone the repository

```
git clone git@github.com:ikar-zindo/kcj-customer-be.git
```

---

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

### [4. View](https://localhost:8889)

*Copy to browser address bar*

```
https://localhost:8889
```

pass for all: `1qaz`

- `thomas@mail.com`, `antonio@mail.com`, `maria@mail.com`, `hanna@mail.com`, `laurence@mail.com`

---

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