# Employee Service

API REST para la gestión de empleados desarrollada con Spring Boot, Spring Security y JWT.

## Descripción

Esta aplicación permite:

- Registrar, consultar, actualizar y eliminar empleados
- Buscar empleados por nombre
- Autenticación basada en JWT
- Documentación automática con Swagger/OpenAPI
- Persistencia con base de datos H2 en memoria
- Validación de datos de entrada

## Tecnologías

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (Auth0)
- Hibernate / JPA
- H2 Database
- Springdoc OpenAPI
- Maven

## Requisitos previos

- JDK 21 o superior
- Maven 3.9+
- Git

## Clonar el proyecto

```bash
git clone <url-del-repositorio>
cd employ-service
```


## Configuración
La aplicación requiere algunas variables de entorno para seguridad y JWT.
Ejemplo:
export SPRING_SECURITY_USER_NAME=admin
export SPRING_SECURITY_USER_PASSWORD=admin123
export JWT_SECRET=miClaveSuperSecreta123
export JWT_EXPIRATION=3600000

## Ejecutar la aplicacion
Con Maven Wrapper: 
./mvnw clean install
./mvnw spring-boot:run

## Documentacion Swagger
La API queda documentada con OpenAPI/Swagger UI:

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

## Base de Datos
Se usa H2 en memoria por defecto
URL de acceso
http://localhost:8080/h2-console
Configuración típica:
- JDBC URL: jdbc:h2:mem:testdb
- Usuario: sa
- Contraseña: (vacía)

## Endpoints principales
### Autenticación
 #### Login
POST /auth/login
Content-Type: application/json
Body ejemplo:
```
{
  "username": "admin",
  "password": "admin123"
}
```
Respuesta:
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```
### Empleados
 #### Listar empleados
GET /employees
Authorization: Bearer <token>
 Obtener empleado por ID
GET /employees/{id}
Authorization: Bearer <token>
 
#### Crear empleados
POST /employees
Authorization: Bearer <token>
Content-Type: application/json
Body ejemplo:
```
[
  {
    "firstName": "Juan",
    "middleName": "Carlos",
    "lastName": "Pérez",
    "secondLastName": "López",
    "age": 28,
    "gender": "M",
    "birthDate": "15-06-1996",
    "active": true
  }
]
```
El formato de fecha usado es dd-MM-yyyy.

 #### Actualizar empleado
PUT /employees/{id}
Authorization: Bearer <token>
Content-Type: application/json
#### Eliminar empleado
DELETE /employees/{id}
Authorization: Bearer <token>
 
#### Buscar empleados por nombre
GET /employees/search?name=juan
Authorization: Bearer <token>
 ## Estructura del proyecto
``` employ-service/
├── src/
│   ├── main/
│   │   ├── java/com/invex/employservice/
│   │   │   ├── config/
│   │   │   ├── controllers/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── services/
│   │   │   └── EmployServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```
 Seguridad
La aplicación usa Spring Security con protección por JWT. Las rutas públicas son:
- /auth/
- /swagger-ui/
- /v3/api-docs/
- /actuator/health

### El resto de rutas requiere autenticación.
 Buenas prácticas
- Mantén tus credenciales fuera del repositorio
- Usa variables de entorno para secretos y tokens
- Asegura la rotación de la clave JWT en entornos reales
- Usa Swagger para probar la API sin necesidad de Postman

### Licencia
Este proyecto está bajo la licencia establecida por el propietario del repositorio.