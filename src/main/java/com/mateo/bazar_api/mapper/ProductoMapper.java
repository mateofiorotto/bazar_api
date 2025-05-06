package com.mateo.bazar_api.mapper;

import com.mateo.bazar_api.dto.ProductoEditDTO;
import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.ProductoSaveDTO;
import com.mateo.bazar_api.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {

    ProductoMapper mapper = Mappers.getMapper(ProductoMapper.class);

    ProductoGetDTO productoToProductoGetDto(Producto producto); // Prod --> ProdGetDTO
    Producto productoGetDtoToProducto(ProductoGetDTO productoGetDTO); // Viceversa

    //metodos para post
    ProductoSaveDTO productoToProductoSaveDto(Producto producto);
    Producto productoSaveDtoToProducto(ProductoSaveDTO productoSaveDTO);

    //para put - No usado
    ProductoEditDTO productoToProductoEditDto(Producto producto);
    Producto productoEditDtoToProducto(ProductoEditDTO productoEditDTO);
}
