# Web application for food delivery K-Curry Jib

## 1. Clone the repository

```
git clone git@github.com:ikar-zindo/kcj-customer-be.git
```

## 2. Launch jar archive

> [!IMPORTANT]
> The driver for the *MySQL* database must be installed on the computer.
> For example *Workbench*. Availability of a created database `kcj-db`.
> *Liquibase* will create all the necessary tables for the application to work properly.
> It is necessary to specify these `environment variables` to connect to the database
> - DATASOURCE_DATABASE_HOST=localhost
> - DATASOURCE_DATABASE_PORT=3306
> - DATASOURCE_DATABASE_NAME=kcj-db
> - DATASOURCE_DATABASE_USERNAME=root
> - DATASOURCE_DATABASE_PASSWORD=<YOUR_PASSWORD>
> - REST_API_URL=http://localhost:8890

*At the root of the project*

```
java -jar kcj-customer-be.jar
```

---

## [3. The docker way](https://hub.docker.com/repository/docker/ikarzindo/k-curry-jib-customer-app/general)

*At the root of the project*

```bash
# Collect images for all services
docker-compose -p kcj build

# Start all services
docker-compose -p kcj up -d

# Check the status of running containers
docker-compose -p kcj ps

# View logs (optional)
docker-compose -p kcj logs -f

# Stop all services
docker-compose -p kcj stop

# Start all services
docker-compose -p kcj start

# Deactivate all services (if necessary)
docker-compose -p kcj down
```

---

## [4. View](http://localhost:8890)

*Copy to browser address bar*

```
http://localhost:8890
```

## 5. Used technology stack in my web application:

## Customer part web application

- Java 17
- Maven
- Spring Framework
- Spring Security
- Nimbus JOSE + JWT
- MySQL
- JPA/JDBC
- Liquibase
- Mapstruct
- Lombok

---

- Swagger
- Logback/Logback DB
- SLF4J
- Springdoc
- Jacoco

---

- H2 (only for testing)

## 6. Authorization data

```shell
# username:password
echo -n "maria@mail.com:1qaz" | base64
echo -n "ultron@mail.com:1qaz" | base64
echo -n "santa@mail.com:1qaz" | base64
echo -n "luke@mail.com:1qaz" | base64
echo -n "frodo@mail.com:1qaz" | base64
```