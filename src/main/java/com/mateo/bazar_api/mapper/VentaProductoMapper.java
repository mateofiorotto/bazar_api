package com.mateo.bazar_api.mapper;

import com.mateo.bazar_api.dto.*;
import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.model.VentaProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VentaProductoMapper {

    VentaProductoMapper mapper = Mappers.getMapper(VentaProductoMapper.class);

    VentaProductoGetDTO ventaProductoToVentaProductoGetDto(VentaProducto ventaProducto);
    VentaProducto ventaProductoGetDtoToVentaProducto(VentaProductoGetDTO ventaProductoGetDTO);

    //metodos para post
    VentaProductoSaveDTO ventaProductoToVentaProductoSaveDto(VentaProducto ventaProducto);
    VentaProducto ventaProductoSaveDtoToVentaProducto(VentaProductoSaveDTO ventaProductoSaveDTO);
}
