package com.mateo.bazar_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

//Cliente utilizado para las respuestas GET
public class ClienteGetDTO implements Serializable {
    private String nombre;
    private String apellido;

    public ClienteGetDTO(){};

    public ClienteGetDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
