package com.mateo.bazar_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.bazar_api.model.VentaProducto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class VentaEditDTO {
    private Long codigo_venta;
    @NotNull(message = "La fecha no puede ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_venta;
    @JsonIgnore
    private List<VentaProducto> ventaProductos;
    @NotNull(message = "La venta debe tener cliente asignado")
    private ClienteEditDTO unCliente;

    //esta vez si hago el constructor a mano porque si no deberia mandar la lista de prods
    public VentaEditDTO(LocalDate fecha_venta, ClienteEditDTO unCliente) {
        this.fecha_venta = fecha_venta;
        this.unCliente = unCliente;
    }
}
