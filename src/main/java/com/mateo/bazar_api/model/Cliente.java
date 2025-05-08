package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Representa un cliente del sistema
 * Cada cliente puede estar asociado a una sola venta
 */
@Entity
public class Cliente {

    /**
     * ID del cliente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Apellido del cliente.
     */
    private String apellido;

    /*
    * Constructor vacio
    * */
    public Cliente(){};

    /**
     * Constructor con todos los campos
     *
     * @param id_cliente ID del cliente
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     */
    public Cliente(Long id_cliente, String nombre, String apellido) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
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
