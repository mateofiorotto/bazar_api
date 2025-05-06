package com.mateo.bazar_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

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

    public VentaProducto(){};

    public VentaProducto(Long id, Venta unaVenta, Producto unProducto, Integer cantidad, Double total) {
        this.id = id;
        this.unaVenta = unaVenta;
        this.unProducto = unProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(Venta unaVenta) {
        this.unaVenta = unaVenta;
    }

    public Producto getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
