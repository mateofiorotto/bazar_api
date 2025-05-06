package com.mateo.bazar_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class VentaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "codigo_venta", nullable = false)
    private Venta unaVenta;
    @ManyToOne
    @JoinColumn(name = "codigo_producto", nullable = false)
    private Producto unProducto;
    private Integer cantidad;
    private Double total;

}
