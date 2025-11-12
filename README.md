# FitStore

A e-commerce API devoloped in Quarkus, for educational purposes.

This project uses Quarkus, the Supersonic Subatomic Java Framework. If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

You can use IntelliJ, or any other tool to explore this project.

## Architecture
A simple layered applicatcion, with:
### model
Entity classes with jakarta JPA annotations and PanacheEntity (extends) for Active Record pattern.
### service
Business Rules and Transactional
### controller
REST controllers / endpoints

## Technical details
- Data Persistence with Panache
- Active Record pattern
- JPA specification
- Database migrations with Flyway
- PostgreSQL as Database
- JWT Security (basic) using Smallrye
- HTTP-only cookies to prevent auth token local storage
- Server-side pagination

In develop:
- Event-driven architecture with Kafka
- In memory cache with Caffeine and Redis
- Object Storage (minio/AWS S3) product pictures
- Deploy using docker

## How to create a key-pair for Smallrye
You can use the Git Bash utility and run this commands

```shell script

openssl genrsa -out rsaPrivateKey.pem 2048
openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
 
```
[Security JWT Guide](https://pt.quarkus.io/guides/security-jwt)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/fitstore-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
