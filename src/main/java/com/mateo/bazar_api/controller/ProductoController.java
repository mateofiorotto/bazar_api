package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    // GET Productos
    @GetMapping("/productos")
    public ResponseEntity<?> getProductos() {
        List<Producto> listaProductos = productoService.getProductos();

        // ListaProductos = empty, entonces vacia pero retornar 200. La solicitud es valida
        if (listaProductos.isEmpty()) {
            return ResponseEntity.ok("Lista vacia");
        }

        return ResponseEntity.ok(listaProductos);
    }

    // Get x id
    @GetMapping("productos/{id_producto}")
    public Producto getProductoById(@PathVariable Long id_producto){
        Producto producto = productoService.getProductoById(id_producto);

        return producto;
    }

    //post
    @PostMapping("/productos/crear")
    public ResponseEntity<?> saveProducto(@Valid @RequestBody Producto producto){
        productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
    }

    //put
    @PutMapping("/productos/editar/{id_producto}")
    //Con valid, se validara el parametro de la peticion
    public ResponseEntity<?> editProducto(@Valid @RequestBody Producto producto, @PathVariable Long id_producto){
        productoService.editProducto(producto, id_producto);
        return ResponseEntity.status(HttpStatus.OK).body("Producto editado");
    }

    //delete
    @DeleteMapping("/productos/eliminar/{id_producto}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id_producto){
        productoService.deleteProducto(id_producto);
        return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado");
    }

    //get poco stock
    @GetMapping("/productos/poco-stock")
    public ResponseEntity<?> getPocoStock(){
        List<Producto> productosPocoStock = productoService.getPocoStock();

        if (productosPocoStock.isEmpty()) {
            return ResponseEntity.ok("Todos los productos tienen 5 o mas items de stock");
        }

        return ResponseEntity.ok(productosPocoStock);

    }

}
