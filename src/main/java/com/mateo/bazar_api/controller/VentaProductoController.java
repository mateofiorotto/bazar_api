package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoSaveDTO;
import com.mateo.bazar_api.service.VentaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST que gestiona las operaciones CRUD sobre ventas de productos
 */
@RestController
public class VentaProductoController {

    private final VentaProductoService ventaProductoService;

    /*
     * Constructor con inyeccion de dependencias
     * */
    public VentaProductoController(VentaProductoService ventaProductoService) {
        this.ventaProductoService = ventaProductoService;
    }

    /**
     * Obtiene una lista de todas las ventas de productos
     *
     * @return lista de ventas de productos o mensaje que la lista esta vacia
     */
    @GetMapping("/ventas-productos")
    @Operation(summary = "Obtener todas las ventas de productos", description = "Devuelve una lista con todas las ventas de productos registradas")
    @ApiResponse(responseCode = "200", description = "Ventas encontradas exitosamente")
    public ResponseEntity<?> getVentasProductos() {
        List<VentaProductoGetDTO> ventasProductos = ventaProductoService.getVentasProductos();

        if(ventasProductos.isEmpty()) {
            return ResponseEntity.ok("No hay ventas de productos");
        }

        return ResponseEntity.ok(ventasProductos);
    }

    /**
     * Busca un venta de productos por su ID
     *
     * @param id ID de la venta de productos a buscar
     * @return venta de productos correspondiente al ID
     */
    @GetMapping("/ventas-productos/{id}")
    @Operation(summary = "Obtener venta de productos por ID", description = "Devuelve una venta de productos específica dada su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VentaProducto encontrada"),
            @ApiResponse(responseCode = "404", description = "VentaProducto no encontrada")
    })
    public ResponseEntity<?> getVentaProductoById(@PathVariable Long id) {

        VentaProductoGetDTO ventaProducto = ventaProductoService.getVentaProductoById(id);

        return ResponseEntity.ok(ventaProducto);
    }

    /**
     * Crea una nueva venta en la base de datos.
     *
     * @param ventaProducto DTO con los datos de la nueva venta de productos
     * @return mensaje indicando que la venta de productos fue creada
     */
    @PostMapping("/ventas-productos/crear")
    @Operation(summary = "Crear una nueva venta de productos", description = "Crea una nueva venta de productos y la guarda en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear la venta / Producto ya agregado a la venta"),
            @ApiResponse(responseCode = "404", description = "Codigo de venta / producto no encontrado(s)")
    })
    public ResponseEntity<?> saveVentaProducto(@Valid @RequestBody VentaProductoSaveDTO ventaProducto) {

        ventaProductoService.saveVentaProducto(ventaProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body("VentaProducto creada");
    }

    /**
     * Elimina una venta de un producto del sistema.
     *
     * @param id ID de la venta de producto a eliminar
     * @return mensaje indicando que la venta de un producto fue eliminada
     */
    @DeleteMapping("/ventas-productos/eliminar/{id}")
    @Operation(summary = "Eliminar una venta de un producto (si se cancela)", description = "Elimina/cancela una venta de un producto de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta de producto eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venta de producto no encontrada")
    })
    public ResponseEntity<?> deleteVentaProducto(@PathVariable Long id) {

        ventaProductoService.deleteVentaProducto(id);

        return ResponseEntity.status(HttpStatus.OK).body("VentaProducto eliminada");
    }

    // get productos de una venta (ppv = productos por venta)
    /**
     * Obtiene una lista de todos los productos que estan en una venta
     *
     * @return lista de productos o mensaje que no hay productos en la venta publicada
     */
    @GetMapping("/ventas-productos/ppv/{id}")
    @Operation(summary = "Obtener todas los productos de una venta", description = "Devuelve una lista con todos los productos de una venta especifica")
    @ApiResponse(responseCode = "200", description = "Productos de la venta encontrados exitosamente / Venta publicada que no tiene productos vendidos")
    public ResponseEntity<?> getProductosPorVenta(@PathVariable Long id) {
        List<ProductoGetDTO> productosPorVenta = ventaProductoService.getProductosPorVenta(id);

        if(productosPorVenta.isEmpty()) {
            return ResponseEntity.ok("Esta venta no tiene productos");
        }

        return ResponseEntity.ok(productosPorVenta);
    }

    // get total
    // get productos de una venta (ppv = productos por venta)
    /**
     * Obtiene la venta de un producto con el total mas alto
     *
     * @return venta de un producto con el total mas alto
     */
    @GetMapping("/ventas-productos/total-mas-alto")
    @Operation(summary = "Obtener la venta de productos con el total mas alto", description = "Devuelve una venta de productos con el total mas alto")
    @ApiResponse(responseCode = "200", description = "Productos de la venta encontrados exitosamente")
    public ResponseEntity<?> getTotalMasAlto(){
        return ResponseEntity.ok(ventaProductoService.getTotalMasAlto());
    }

    // get montoTotalYCantidadDeVentasDeUnDia(LocalDate fecha)
    // total-cmyv --> cantidad monto y ventas
    /**
     * Obtiene el monto total y la cantidad de ventas de una fecha ingresada
     *
     * @return String con monto total y cantidad de ventas de un dia
     */
    @Operation(summary = "Obtener monto total y cantidad de ventas de una fecha", description = "Devuelve el monto sumado total y la cantidad de ventas de una fecha ingresada")
    @ApiResponse(responseCode = "200", description = "Devolviendo monto total, fecha y cantidad de ventas")
    @GetMapping("/ventas-productos/total-cmyv/{fecha}")
    public ResponseEntity<?> montoTotalYCantidadDeVentasDeUnDia(@PathVariable LocalDate fecha){

        return ResponseEntity.ok(ventaProductoService.montoTotalYCantidadDeVentasDeProductosDeUnDia(fecha));
    }
}
