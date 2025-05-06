package com.mateo.bazar_api.dto;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VentaProductoGetDTO {
    @NotNull(message = "La venta del producto debe tener el codigo de venta asignado")
    private VentaGetDTO unaVenta;
    @NotNull(message = "La venta del producto debe tener el codigo de producto asignado")
    private ProductoGetDTO unProducto;
    @NotNull(message = "Debe haber una cantidad especificada")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;
    private Double total;
}
