package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venta;
    //realizar validaciones pertinentes
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany(mappedBy = "venta") //Una venta tiene muchos productos, asociamos el objeto venta
    @JsonIgnore
    private List<Producto> listaProductos;
    //Muchas ventas las puede efectuar 1 cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente unCliente;
}
