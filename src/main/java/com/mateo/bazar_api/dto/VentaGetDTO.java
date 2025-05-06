package com.mateo.bazar_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.bazar_api.model.Cliente;
import com.mateo.bazar_api.model.VentaProducto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class VentaGetDTO {
    private LocalDate fechaVenta;
    private ClienteGetDTO unCliente;

    public VentaGetDTO(LocalDate fechaVenta, ClienteGetDTO unCliente) {
        this.fechaVenta = fechaVenta;
        this.unCliente = unCliente;
    }

    public LocalDate getFecha_venta() {
        return fechaVenta;
    }

    public void setFecha_venta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public ClienteGetDTO getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(ClienteGetDTO unCliente) {
        this.unCliente = unCliente;
    }
}
