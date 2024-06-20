# Ejemplo de Spring Boot Refresh Token con JWT.
Consiste en sistema de Refresh de Token JWT en una aplicación Java Spring Boot.
Con este proyecto se muestra cómo expirar el JWT y renovar el Access Token con el Refresh Token. Acceso a api rest mediante JWT y Roles de usuarios.
## Configurar Spring Datasource, JPA, en el archivo `application.properties`
Open `src/main/resources/application.properties`

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 123456

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
security.app.jwtSecret= SecretKey
security.app.jwtExpirationMs= 3600000
security.app.jwtRefreshExpirationMs= 86400000
```

## Correr la aplicación Spring Boot
```
mvn spring-boot:run
```

## Correr el script para insertar los ROLES
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
