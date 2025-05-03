package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.service.IVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    // GET Ventas
    @GetMapping("/ventas")
    public ResponseEntity<?> getVentas() {
        List<Venta> listaVentas = ventaService.getVentas();

        if (listaVentas.isEmpty()) {
            return ResponseEntity.ok("Lista vacia");
        }

        return ResponseEntity.ok(listaVentas);
    }

    // Get x id
    @GetMapping("ventas/{id_venta}")
    public ResponseEntity<?> getVentaById(@PathVariable Long id_venta){
        Venta venta = ventaService.getVentaById(id_venta);

        return ResponseEntity.ok(venta);
    }

    //post
    @PostMapping("/ventas/crear")
    public ResponseEntity<?> saveVenta(@Valid @RequestBody Venta venta){
        ventaService.saveVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada");
    }

    //put
    @PutMapping("/ventas/editar/{id_venta}")
    //Con valid, se validara el parametro de la peticion
    public ResponseEntity<?> editVenta(@Valid @RequestBody Venta venta, @PathVariable Long id_venta){
        ventaService.editVenta(venta, id_venta);
        return ResponseEntity.status(HttpStatus.OK).body("Venta editada");
    }
    
    //delete
    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id_venta){
        ventaService.deleteVenta(id_venta);
        return ResponseEntity.status(HttpStatus.OK).body("Venta eliminada");
    }
    
}
