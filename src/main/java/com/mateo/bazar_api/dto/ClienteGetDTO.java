package com.mateo.bazar_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

//Cliente utilizado para las respuestas GET
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClienteGetDTO implements Serializable {
    private String nombre;
    private String apellido;
}
