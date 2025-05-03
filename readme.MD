# Bazaar API / Bazar API

## Used Technologies / Tecnologias Utilizadas

<p class="technologies">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,hibernate,maven,postman,idea" />
  </a>
</p>

- Java, JDK 21 (Programming Language + Versión)
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
- XAMPP (Development Environment, Apache, phpmyadmin)
- IntelliJ Idea (IDE)

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
- Get the sum of the amount and the total number of sales of a day
- Get the sale with the highest amount of total and the client who made it

### Optional Requirements (not necessary)
- Additional endpoints
- Additional models
- DTOs and Mappers (Convert models to DTOs and vice versa, hide data from the user)
- Validations (@Valid, Annotations in models)
- Custom Exceptions (ApiException, GlobalExceptionHandler), custom error messages
- Use ResponseEntity<?>

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
- Obtener la sumatoria del monto y tambien la cantidad total de ventas de un dia
- Obtener la venta con el total mas alto y el cliente que la hizo

### Requerimientos Opcionales (no necesarios)
- Endpoints adicionales
- Modelos adicionales
- DTOs y Mappers (Convertir modelos a DTOs y vice versa, ocultar datos del usuario)
- Validaciones (@Valid, Anotaciones en modelos)
- Excepciones personalizadas (ApiException, GlobalExceptionHandler), mensajes de error personalizados
- Usar ResponseEntity<?>
