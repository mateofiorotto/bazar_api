package com.mateo.bazar_api.mapper;

import com.mateo.bazar_api.dto.VentaEditDTO;
import com.mateo.bazar_api.dto.VentaGetDTO;
import com.mateo.bazar_api.dto.VentaSaveDTO;
import com.mateo.bazar_api.model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//Indicamos que usa
@Mapper
public interface VentaMapper {

    VentaMapper mapper = Mappers.getMapper(VentaMapper.class);

    // De venta a VentaGetDTO
    VentaGetDTO ventaToVentaGetDto(Venta venta);
    Venta ventaGetDtoToVenta(VentaGetDTO ventaGetDTO);

    //metodos para post
    VentaSaveDTO ventaToVentaSaveDto(Venta venta);
    Venta ventaSaveDtoToVenta(VentaSaveDTO ventaSaveDTO);

    //para put
    VentaEditDTO ventaToVentaEditDto(Venta venta);
    Venta ventaEditDtoToVenta(VentaEditDTO ventaEditDTO);

    // No se utilizo put ni post en este caso pero los dejamos en caso de futuro uso
}
