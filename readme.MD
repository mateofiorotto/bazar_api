# Bazaar API / Bazar API

## Used Technologies / Tecnologias Utilizadas

<p class="technologies">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,hibernate,maven,postman,idea" />
  </a>
</p>

- Java, JDK 21 (Programming Language + Version)
- Spring Boot (Framework and Dependencies)
  - Lombok
  - Validation
  - MySQL Driver
  - Spring DevTools
  - Spring Data JPA
  - Spring Web
- Hibernate + JPA (DB Persistence)
- Maven (Dependencies)
- Postman (Test API)
- JavaDoc (Methods docs)
- Swagger, SpringDoc OpenAPI (API Basic Endpoints Documentation)
- XAMPP (Development Environment, Apache, phpmyadmin)
- IntelliJ Idea (IDE)


- [Go to documentation in English](#ENG)
- [Ir a documentación en Español](#ESP)

---

## ENG

---

This project is a final project for the "APIs in Java with Spring Boot" course of the TodoCode educational platform. The objective of this project is to validate the practical and technical knowledge related to the development of APIs in the Java programming language using Spring Boot.

### Project Scenario
One bazaar has increased in sales. Given this and that it is almost impossible to register the same and manage the stock of its products manually, it needs the development of an application that allows this.

### Models
- Product --> product_code, name, brand, cost, stock
- Sale --> sale_code, date, total, products (1:n), client (1:n)
- Client --> client_id, name, surname
- SaleProduct --> id, sale_product_code, quantity, total

### Obligatory Requirements
- CRUD for products
- CRUD for sales
- CRUD for clients
- Get all products that have a stock less than 5
- Get the list of products of a sale
- Get the sum of the amount and the total number of a sold products in a day
- Get the sale of a product with the highest amount of total and the client who made it

### Optional Requirements (not necessary)
- Additional endpoints (SaleProduct Endpoints)
- Additional models (SaleProduct)
- DTOs and Mappers (Convert models to DTOs and vice versa, hide data from the user)
- Validations (@Valid, Annotations in models)
- Custom Exceptions (ApiException, GlobalExceptionHandler), custom error messages
- Use ResponseEntity<?>
- Javadoc and Swagger (With Springdoc)

### How to use
1. Clone repository

  ```
  git clone https://github.com/mateofiorotto/bazar_api
  cd bazar_api
  ```

2. Check Dependencies
- JDK 21
- Maven
- IDE of your preference (I used IntelliJ IDEA)

3. Database Configuration
- Open XAMPP and start Apache and MySQL
- Create a database with the name of your preference, for default in application.properties is "api_bazar"

4. application.properties

Here is an example of the configuration for local environment:

```
spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Feel free to adjust any settings to fit your local environment.

- database-name --> is the database that you created in xampp
- username --> username of your local environment or server db, for default in xampp is root
- password --> password of your local environment or server db, for default in xampp is empty password

5. Run the app

Run ```./mvnw spring-boot:run``` or go to the "run button" of your IDE while you are targeting the "BazarApiApplication" class. (Main class with the @SpringBootApplication annotation)

6. Test with Postman or other

By default in Spring Boot, the localhost is in 8080 port.

You can check all the endpoints here: ```http://localhost:8080/swagger-ui/index.html#/```

## ESP

---

Este proyecto es un proyecto final para el curso "APIs con Spring Boot" de la plataforma de educación TodoCode. El objetivo de este proyecto es validar los conocimientos prácticos y técnicos referidos al desarrollo de APIs en el lenguaje de programación Java mediante Spring Boot.

### Escenario del Proyecto
Un bazar ha incrementado en gran medida sus ventas. Dado esto y que le está siendo casi
imposible registrar las mismas y manejar el stock de sus productos de forma manual, necesita
del desarrollo de una aplicación que le permita realizar esta tarea.

### Modelos
- Producto --> codigo_producto, nombre, marca, costo, cantidad_disponible
- Venta --> codigo_venta, fecha, productos (1:n), clientes (1:n)
- Cliente --> id_cliente, nombre, apellido
- VentaProducto --> id, codigo_venta_producto, cantidad, total

### Requerimientos Obligatorios
- CRUD para productos
- CRUD para ventas
- CRUD para clientes
- Obtener todos los productos con stock menor a 5
- Obtener la lista de productos de una venta
- Obtener la sumatoria del monto y tambien la cantidad total de productos vendidos en un dia
- Obtener la venta de un producto con el total mas alto y el cliente que la hizo

### Requerimientos Opcionales (no necesarios)
- Endpoints adicionales (Endpoints de VentaProducto)
- Modelos adicionales (VentaProducto)
- DTOs y Mappers (Convertir modelos a DTOs y viceversa, ocultar datos del usuario)
- Validaciones (@Valid, Anotaciones en modelos)
- Excepciones personalizadas (ApiException, GlobalExceptionHandler), mensajes de error personalizados
- Usar ResponseEntity<?>
- Javadoc y Swagger (con Springdoc)

### Como usarlo
1. Clone repository

  ```
  git clone https://github.com/mateofiorotto/bazar_api
  cd bazar_api
  ```

2. Revisar dependencias
- JDK 21
- Maven
- IDE de tu preferencia (yo usé IntelliJ IDEA)

3. Configuración de la base de datos
- Abre XAMPP e inicia Apache y MySQL
- Crea una base de datos con el nombre de tu preferencia, por defecto en application.properties es "api_bazar"

4. application.properties

Ejemplo de la configuración del entorno local:

```
spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Ajusta a gusto las configuraciones que quieras para adaptarlo a tu entorno local.

- database-name --> es la base de datos que creaste en xampp
- username --> usuario de tu entorno local, en xampp por defecto es root
- password --> contraseña de tu entorno local, en xampp por defecto esta vacia

5. Ejecutar la app

Ejecuta ```./mvnw spring-boot:run``` o presiona el boton de iniciar en tu IDE mientras estas dentro de la clase "BazarApiApplication". (Clase principal con la anotación @SpringBootApplication)

6. Probar con Postman u otro

Por defecto en Spring Boot, el localhost esta en el puerto 8080.

Proba los endpoints acá: ```http://localhost:8080/swagger-ui/index.html#/```