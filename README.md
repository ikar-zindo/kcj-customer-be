[//]: # (TODO it would be good to have more descriptive text here, e.g. what app can do. This page is quite representative and important: it is seen on github and when you open project, so everyone will see at. )

# Web application for food delivery K-Curry Jib

[//]: # (TODO If you are going to give installation instructions, then tell about it: How to install? To tell to clone repository looks redundant - we assume that your project will see developer. In general, I see your readmy as follows:1.Description)

[//]: # (2.Installation)

[//]: # (2.1 How to run normal mode)

[//]: # (2.2 Hot to run docker mode)

[//]: # (3. Technology Stack)

### Clone the repository

```
git clone git@github.com:ikar-zindo/k-curry-jib-customer.git
```

---

### Launch jar archive

[//]: # (// TODO how to make sure? Give instruction. I would not keep jat at root at all. Just give short instructions how to setup db and launch app ( if we are talking about normal mode)
###### You need to make sure that the DB exists and is connected correctly.

*At the root of the project*

```
java -jar k-curry-jib-customer-v1.1.1.jar
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
