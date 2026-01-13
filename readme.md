# User Service

User Service skrbi za upravljanje uporabnikov (users).
Implementirana je v Spring Boot in uporablja PostgreSQL podatkovno bazo.

## Tehnologije
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Maven

## Funkcionalnost
- ustvarjanje uporabnikov
- pridobivanje seznama uporabnikov
- pridobivanje uporabnika po ID
- osnovno upravljanje uporabniških podatkov

## Konfiguracija
User Service uporablja okoljske spremenljivke za povezavo do baze:

SPRING_DATASOURCE_URL  
SPRING_DATASOURCE_USERNAME  
SPRING_DATASOURCE_PASSWORD

Primer:
jdbc:postgresql://localhost:5432/users

## Zagon lokalno

```
mvn clean package  
java -jar target/user-service-0.0.1-SNAPSHOT.jar
```

Storitev teče na:
http://localhost:8080

## Swagger dokumentacija
Swagger (OpenAPI) dokumentacija je na voljo na:
http://localhost:8080/swagger-ui/index.html

## Opombe
- Storitev pričakuje, da je PostgreSQL baza že inicializirana