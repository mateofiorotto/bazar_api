package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    //oculto la id para cuando la muestre en VentaDTO pero poder recibirla x put y post
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id_cliente;
    private String nombre;
    private String apellido;
}
