package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.List;

/*
* Representa una venta realizada del sistema
* Cada venta se asocia a un cliente y puede tener productos asociados
*/
@Entity
public class Venta {
    /**
     * ID de la venta
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venta;

    /**
     * Fecha en la que se realizo la venta
     */
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    /**
     * Lista de productos vendidos en esta venta
     * Se establece la relación uno a muchos con {@link VentaProducto}
     */
    @OneToMany(mappedBy = "unaVenta") //Una venta tiene muchos productos, asociamos el objeto venta
    private List<VentaProducto> ventaProductos;

    /**
     * Cliente que realizo la venta.
     * Se establece una relación uno a uno con {@link Cliente}
     */
    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente unCliente; //aca va a ir el clienteGet

    /*
    * Constructor vacio
    * */
    public Venta() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param codigo_venta ID de la venta
     * @param fechaVenta Fecha de la venta
     * @param ventaProductos Lista de productos vendidos
     * @param unCliente Cliente que realizo la venta
     */
    public Venta(Long codigo_venta, LocalDate fechaVenta, List<VentaProducto> ventaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fechaVenta = fechaVenta;
        this.ventaProductos = ventaProductos;
        this.unCliente = unCliente;
    }

    public Long getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(Long codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fechaVenta;
    }

    public void setFecha_venta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public List<VentaProducto> getVentaProductos() {
        return ventaProductos;
    }

    public void setVentaProductos(List<VentaProducto> ventaProductos) {
        this.ventaProductos = ventaProductos;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }
}
