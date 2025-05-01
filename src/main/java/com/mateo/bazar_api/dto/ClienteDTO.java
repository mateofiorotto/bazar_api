package com.mateo.bazar_api.dto;

import com.mateo.bazar_api.model.Venta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClienteDTO {
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;
    @NotEmpty(message = "El apellido no puede estar vacio")
    @Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String apellido;
    //private List<VentaDTO> listaVentas;
}
