package com.mateo.bazar_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

/**
 * Entidad intermedia que representa la relacion entre una {@link Venta} y un {@link Producto},
 * incluyendo la cantidad comprada y el total de la venta para ese producto.
 */
@Entity
public class VentaProducto {
    /*
    * Id de VentaProducto
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * Venta asociada
    * */
    @ManyToOne
    @JoinColumn(name = "codigo_venta", nullable = false)
    private Venta unaVenta;

    /*
    * Producto asociado
    * */
    @ManyToOne
    @JoinColumn(name = "codigo_producto", nullable = false)
    private Producto unProducto;

    /*
    * Cantidad del producto comprado
    * */
    private Integer cantidad;

    /*
    * Total que se calcula automaticamente
    * */
    private Double total;

    /*
     * Constructor vacio
     */
    public VentaProducto(){};

    /**
     * Constructor completo para crear un objeto {@code VentaProducto}.
     *
     * @param id         ID del registro
     * @param unaVenta   Venta asociada
     * @param unProducto Producto vendido
     * @param cantidad   Cantidad vendida
     * @param total      Total del producto vendido
     */
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
