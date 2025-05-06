package com.mateo.bazar_api.dto;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VentaProductoSaveDTO {
    @NotNull(message = "La venta del producto debe tener el codigo de venta asignado")
    private VentaEditDTO unaVenta;
    @NotNull(message = "La venta del producto debe tener el codigo de producto asignado")
    private ProductoEditDTO unProducto;
    @NotNull(message = "Debe haber una cantidad especificada")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;
    private Double total;

    public VentaProductoSaveDTO(){};

    public VentaProductoSaveDTO(VentaEditDTO unaVenta, ProductoEditDTO unProducto, Integer cantidad, Double total) {
        this.unaVenta = unaVenta;
        this.unProducto = unProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public VentaEditDTO getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(VentaEditDTO unaVenta) {
        this.unaVenta = unaVenta;
    }

    public ProductoEditDTO getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(ProductoEditDTO unProducto) {
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
