package com.mateo.bazar_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.bazar_api.model.VentaProducto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class VentaEditDTO {
    private Long codigo_venta;
    @NotNull(message = "La fecha no puede ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVenta;
    @JsonIgnore
    private List<VentaProducto> ventaProductos;
    @NotNull(message = "La venta debe tener cliente asignado")
    private ClienteEditDTO unCliente;

    public VentaEditDTO(LocalDate fechaVenta, ClienteEditDTO unCliente) {
        this.fechaVenta = fechaVenta;
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

    public ClienteEditDTO getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(ClienteEditDTO unCliente) {
        this.unCliente = unCliente;
    }
}
