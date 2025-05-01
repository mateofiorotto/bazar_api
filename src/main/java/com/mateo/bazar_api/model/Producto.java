package com.mateo.bazar_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;

    @NotEmpty(message = "La marca no puede estar vacia")
    @Size(min = 3, max = 20, message = "La marca debe tener entre 3 y 20 caracteres")
    private String marca;

    @NotNull(message = "El costo no puede ser nulo")
    @PositiveOrZero(message = "El costo debe ser mayor o igual a cero")
    private Double costo;

    @NotNull(message = "La cantidad disponible no puede ser nula")
    @PositiveOrZero(message = "La cantidad disponible debe ser mayor o igual a cero")
    private int cantidad_disponible;

    @NotNull(message = "La cantidad vendida no puede ser nula")
    @PositiveOrZero(message = "La cantidad vendida debe ser mayor o igual a cero")
    private int cantidad_vendida;

    //Muchos productos pertenecen a una venta
    //Asociamos el nombre del codigo de la venta
    @ManyToOne
    @JoinColumn(name="codigo_venta", nullable = false)
    @NotNull(message = "El producto debe tener venta asignada")
    private Venta venta;
}
