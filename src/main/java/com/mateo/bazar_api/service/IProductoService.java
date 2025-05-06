package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoEditDTO;
import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.ProductoSaveDTO;
import com.mateo.bazar_api.model.Producto;

import java.util.List;

public interface IProductoService {
    public List<ProductoGetDTO> getProductos();
    public ProductoGetDTO getProductoById(Long id);
    public void saveProducto(ProductoSaveDTO productoDTO);
    public void editProducto(ProductoEditDTO productoDTO, Long id);
    public void deleteProducto(Long id);
    public List<ProductoGetDTO> getPocoStock();
}
