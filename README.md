# SalesCrud

![Java 21](https://img.shields.io/badge/Java-21-f5a524?logo=openjdk&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?logo=springboot&logoColor=white)

API REST de ventas para un bazar: clientes, productos y ventas que relacionan a un cliente con los productos que compró. Es un proyecto de práctica para trabajar una API en capas con Spring Boot.

## Qué tiene

- **Capas separadas:** controladores, servicios detrás de interfaces y repositorios de Spring Data JPA.
- **DTOs de entrada y de salida**, para no exponer las entidades.
- **Validación** de lo que llega con Bean Validation (`@Valid`).
- **Manejo global de errores** con `@ControllerAdvice`: un recurso que no existe o que ya existe devuelve un error con formato propio.

## Endpoints

| Recurso | Crear | Listar | Ver uno | Modificar | Borrar |
|---|---|---|---|---|---|
| Clientes | `POST /clients/create` | `GET /clients/get_all` | `GET /clients/get/{id}` | `PUT /clients/update/{id}` | `DELETE /clients/delete/{id}` |
| Productos | `POST /products/create` | `GET /products/get_all` | `GET /products/get/{code}` | `PUT /products/update/{code}` | `DELETE /products/delete/{code}` |
| Ventas | `POST /sales/create` | `GET /sales/get_all` | `GET /sales/get/{code}` | `PUT /sales/update/{code}` | `DELETE /sales/delete/{code}` |

## Stack

Java 21 · Spring Boot 3.4 · Spring Web · Spring Data JPA · Bean Validation · Lombok · H2 · MySQL

## Correrlo

```bash
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`. Sin configuración arranca con una base H2 en memoria. Para usar MySQL, poné los datos de conexión (`spring.datasource.*`) en `src/main/resources/application.properties`, que no está versionado.
