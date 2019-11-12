# User REST API using Spring Boot and PostgreSQL

Build a Restful CRUD API for a simple user application using Spring Boot and PostgreSQL. In the application, the postgres instance is run in a docker container.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Docker - 19.x.x

## Steps to Setup

**1. Clone the application**

```
git clone https://github.com/samarth18/spring-boot-user-api.git
```

**2. Run the PostgreSQL docker container**

```
docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
```

The container is now running

**3. Create a database called springdb**

```
docker exec -it postgres-spring /bin/bash
psql -U postgres
CREATE DATABASE springdb;
```

_docker exec_ is used to interact with a running container

**4. Run the app using maven**

```
mvn spring-boot:run
```

The app will start running at http://localhost:8080.

**5. REST APIs**

```
GET /api/v1/person
GET /api/v1/person/{personId}
POST /api/v1/person
PUT /api/v1/person/{personId}
DELETE /api/v1/person/{personId}
```

You can test them using postman or any other rest client.
