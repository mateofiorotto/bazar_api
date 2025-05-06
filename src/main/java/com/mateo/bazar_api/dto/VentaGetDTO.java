package com.mateo.bazar_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.bazar_api.model.Cliente;
import com.mateo.bazar_api.model.VentaProducto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class VentaGetDTO {
    @NotNull(message = "La fecha no puede ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_venta;
    @NotNull(message = "La venta debe tener cliente asignado")
    private ClienteGetDTO unCliente;

    //esta vez si hago el constructor a mano porque si no deberia mandar la lista de prods
    public VentaGetDTO(LocalDate fecha_venta, ClienteGetDTO unCliente) {
        this.fecha_venta = fecha_venta;
        this.unCliente = unCliente;
    }
}
