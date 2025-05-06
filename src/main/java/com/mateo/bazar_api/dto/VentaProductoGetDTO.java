package com.mateo.bazar_api.dto;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VentaProductoGetDTO {
    private VentaGetDTO unaVenta;
    private ProductoGetDTO unProducto;
    private Integer cantidad;
    private Double total;

    public VentaProductoGetDTO(){};

    public VentaProductoGetDTO(VentaGetDTO unaVenta, ProductoGetDTO unProducto, Integer cantidad, Double total) {
        this.unaVenta = unaVenta;
        this.unProducto = unProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public VentaGetDTO getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(VentaGetDTO unaVenta) {
        this.unaVenta = unaVenta;
    }

    public ProductoGetDTO getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(ProductoGetDTO unProducto) {
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
