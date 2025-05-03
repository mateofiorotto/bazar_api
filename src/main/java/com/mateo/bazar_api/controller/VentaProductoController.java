package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.VentaProducto;
import com.mateo.bazar_api.service.VentaProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaProductoController {

    private final VentaProductoService ventaProductoService;

    public VentaProductoController(VentaProductoService ventaProductoService) {
        this.ventaProductoService = ventaProductoService;
    }

    // GET
    @GetMapping("/ventas-productos")
    public ResponseEntity<?> getVentasProductos() {
        List<VentaProducto> ventasProductos = ventaProductoService.getVentasProductos();

        if(ventasProductos.isEmpty()) {
            return ResponseEntity.ok("No hay ventas de productos");
        }

        return ResponseEntity.ok(ventasProductos);
    }

    // GET id
    @GetMapping("/ventas-productos/{id}")
    public ResponseEntity<?> getVentaProductoById(@PathVariable Long id) {

        VentaProducto ventaProducto = ventaProductoService.getVentaProductoById(id);

        return ResponseEntity.ok(ventaProducto);
    }

    // post
    @PostMapping("/ventas-productos/crear")
    public ResponseEntity<?> saveVentaProducto(@Valid @RequestBody VentaProducto ventaProducto) {

        ventaProductoService.saveVentaProducto(ventaProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body("VentaProducto creada");
    }

    //put
    @PutMapping("/ventas-productos/editar/{id}")
    public ResponseEntity<?> editVentaProducto(@Valid @RequestBody VentaProducto ventaProducto, @PathVariable Long id) {

        ventaProductoService.editVentaProducto(ventaProducto, id);

        return ResponseEntity.status(HttpStatus.OK).body("VentaProducto editada");
    }

    //delete
    @DeleteMapping("/ventas-productos/eliminar/{id}")
    public ResponseEntity<?> deleteVentaProducto(@PathVariable Long id) {

        ventaProductoService.deleteVentaProducto(id);

        return ResponseEntity.status(HttpStatus.OK).body("VentaProducto eliminada");
    }

    // get productos de una venta (ppv = productos por venta)
    @GetMapping("/ventas-productos/ppv/{id}")
    public ResponseEntity<?> getProductosPorVenta(@PathVariable Long id) {
        List<Producto> productosPorVenta = ventaProductoService.getProductosPorVenta(id);

        if(productosPorVenta.isEmpty()) {
            return ResponseEntity.ok("No hay productos por venta");
        }

        return ResponseEntity.ok(productosPorVenta);
    }

    // get total
    @GetMapping("/ventas-productos/total-mas-alto")
    public ResponseEntity<?> getTotalMasAlto(){
        return ResponseEntity.ok(ventaProductoService.getTotalMasAlto());
    }

    // get montoTotalYCantidadDeVentasDeUnDia(LocalDate fecha)
    // total-cmyv --> cantidad monto y ventas
    @GetMapping("/ventas-productos/total-cmyv/{fecha}")
    public ResponseEntity<?> montoTotalYCantidadDeVentasDeUnDia(@PathVariable LocalDate fecha){

        return ResponseEntity.ok(ventaProductoService.montoTotalYCantidadDeVentasDeUnDia(fecha));
    }
}
