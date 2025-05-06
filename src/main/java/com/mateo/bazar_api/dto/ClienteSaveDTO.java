package com.mateo.bazar_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

//Cliente usado para las respuestas de post y put
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClienteSaveDTO implements Serializable {
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;
    @NotEmpty(message = "El apellido no puede estar vacio")
    @Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String apellido;
}
