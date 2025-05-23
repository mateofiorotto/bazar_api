package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Representa un producto del sistema
 * Un producto puede estar asociado a múltiples ventas a traves de la entidad VentaProducto.
 */
@Entity
public class Producto {
    /**
     * ID Producto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;

    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Marca del producto
     */
    private String marca;

    /**
     * Costo unitario del producto
     */
    private Double costo;

    /**
     * Stock disponible
     */
    private int cantidad_disponible;

    /**
     * Lista de asociaciones del producto con ventas.
     * Relación uno a muchos con la entidad VentaProducto.
     */
    @OneToMany(mappedBy = "unProducto") //Una venta tiene muchos productos, asociamos el objeto venta
    private List<VentaProducto> ventaProductos;

    /*
    * Constructor vacio
    * */
    public Producto() {
    }

    /**
     * Constructor completo con todos los atributos.
     *
     * @param codigo_producto ID del producto
     * @param nombre Nombre del producto
     * @param marca Marca del producto
     * @param costo Costo del producto
     * @param cantidad_disponible Stock disponible
     * @param ventaProductos Lista de relaciones con ventas
     */
    public Producto(Long codigo_producto, String nombre, String marca, Double costo, int cantidad_disponible, List<VentaProducto> ventaProductos) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.ventaProductos = ventaProductos;
    }

    public Long getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(Long codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public List<VentaProducto> getVentaProductos() {
        return ventaProductos;
    }

    public void setVentaProductos(List<VentaProducto> ventaProductos) {
        this.ventaProductos = ventaProductos;
    }
}
