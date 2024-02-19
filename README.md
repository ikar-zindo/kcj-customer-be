# Web application for food delivery K-Curry Jib

### Clone the repository

```
git clone git@github.com:ikar-zindo/k-curry-jib-customer.git
```

---

### Launch jar archive

###### You need to make sure that the DB exists and is connected correctly.

*At the root of the project*

```
java -jar k-curry-jib-customer-v1.1.0.jar
```

---

### [The docker way](https://hub.docker.com/repository/docker/ikarzindo/k-curry-jib-customer-app/general)

###### At the 1st launch, a DB will be created.

*At the root of the project*

```
docker-compose build
docker-compose up
```

---

### [View](http://localhost:8889)

*Copy to browser address bar*

```
http://localhost:8889
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
- Model Mapper

---

- Jupiter - Testing
- Maven
- SLF4J

---

- WebMVC
- Thymeleaf
- Bootstrap