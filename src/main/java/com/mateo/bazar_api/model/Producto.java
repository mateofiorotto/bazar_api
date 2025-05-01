package com.mateo.bazar_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    //realizar validaciones pertinentes
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    //Muchos productos pertenecen a una venta
    //Asociamos el nombre del codigo de la venta
    @ManyToOne
    @JoinColumn(name="codigo_venta", nullable = false)
    private Venta venta;
}
