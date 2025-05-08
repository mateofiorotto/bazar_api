package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.dto.ProductoEditDTO;
import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.ProductoSaveDTO;
import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.service.IProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador REST que gestiona las operaciones CRUD sobre los productos
 */
@RestController
public class ProductoController {

    private final IProductoService productoService;

    /*
    *
    * */
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return lista de productos o mensaje si la lista está vacía
     */
    @GetMapping("/productos")
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista con todos los productos registrados.")
    @ApiResponse(responseCode = "200", description = "Productos encontrados exitosamente")

    public ResponseEntity<?> getProductos() {
        List<ProductoGetDTO> listaProductos = productoService.getProductos();

        // ListaProductos = empty, entonces vacia pero retornar 200. La solicitud es valida
        if (listaProductos.isEmpty()) {
            return ResponseEntity.ok("Lista vacia");
        }

        return ResponseEntity.ok(listaProductos);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id_producto ID del producto a buscar
     * @return el producto correspondiente al ID
     */
    @GetMapping("productos/{id_producto}")
    @Operation(summary = "Buscar producto por ID", description = "Devuelve el producto correspondiente al ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<?> getProductoById(@PathVariable Long id_producto){
        ProductoGetDTO producto = productoService.getProductoById(id_producto);

        return ResponseEntity.ok(producto);
    }

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param producto DTO con los datos del nuevo producto
     * @return mensaje indicando que el producto fue creado
     */
    @PostMapping("/productos/crear")
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto y lo guarda en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el producto")
    })
    public ResponseEntity<?> saveProducto(@Valid @RequestBody ProductoSaveDTO producto){
        productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }

    /**
     * Edita un producto existente.
     *
     * @param producto     DTO con los nuevos datos del producto
     * @param id_producto  ID del producto a editar
     * @return mensaje indicando que el producto fue editado
     */
    @PutMapping("/productos/editar/{id_producto}")
    @Operation(summary = "Editar un producto", description = "Edita un producto existente en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto editado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para editar el producto"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    //Con valid, se validara el parametro de la peticion
    public ResponseEntity<?> editProducto(@Valid @RequestBody ProductoEditDTO producto, @PathVariable Long id_producto){
        productoService.editProducto(producto, id_producto);
        return ResponseEntity.status(HttpStatus.OK).body("Producto editado");
    }

    /**
     * Elimina un producto del sistema.
     *
     * @param id_producto ID del producto a eliminar
     * @return mensaje indicando que el producto fue eliminado
     */
    @DeleteMapping("/productos/eliminar/{id_producto}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<?> deleteProducto(@PathVariable Long id_producto){
        productoService.deleteProducto(id_producto);
        return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado");
    }

    /**
     * Obtiene una lista de todos los productos que tengan menos de 5 unidades.
     *
     * @return lista de productos o mensaje si todos los productos tienen suficiente stock
     */
    @GetMapping("/productos/poco-stock")
    @Operation(summary = "Obtener todos los productos con poco stock", description = "Devuelve una lista con productos con poco stock")
    @ApiResponse(responseCode = "200", description = "Productos encontrados exitosamente")
    public ResponseEntity<?> getPocoStock(){
        List<ProductoGetDTO> productosPocoStock = productoService.getPocoStock();

        if (productosPocoStock.isEmpty()) {
            return ResponseEntity.ok("Todos los productos tienen 5 o mas items de stock");
        }

        return ResponseEntity.ok(productosPocoStock);

    }

}
