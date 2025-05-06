package com.mateo.bazar_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.bazar_api.model.VentaProducto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductoGetDTO {
    private String nombre;
    private String marca;
    private Double costo;
    private int cantidad_disponible;
}
