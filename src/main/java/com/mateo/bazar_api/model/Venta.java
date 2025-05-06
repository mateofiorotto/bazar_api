package com.mateo.bazar_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    private LocalDate fecha_venta;

//    @NotNull(message = "El total no puede ser nulo")
//    @PositiveOrZero(message = "El total debe ser mayor o igual a cero")
//    private Double total;

//    @OneToMany(mappedBy = "venta") //Una venta tiene muchos productos, asociamos el objeto venta
//    @JsonIgnore
//    private List<Producto> listaProductos;

    @OneToMany(mappedBy = "unaVenta") //Una venta tiene muchos productos, asociamos el objeto venta
    private List<VentaProducto> ventaProductos;

    //Muchas ventas las puede efectuar 1 cliente
    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente unCliente; //aca va a ir el clienteGet
}
