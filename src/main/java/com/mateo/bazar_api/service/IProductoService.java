package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoEditDTO;
import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.ProductoSaveDTO;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.model.Producto;

import java.util.List;

public interface IProductoService {
    /**
     * Devuelve una lista de productos desde la BD
     *
     * @return Lista de objetos ProductoGetDTO
     */
    public List<ProductoGetDTO> getProductos();

    /**
     * Devuelve un producto segun su ID
     *
     * @param id ID del producto a buscar
     * @return Objeto ProductoGetDTO
     * @throws NotFoundException si el producto no existe
     */
    public ProductoGetDTO getProductoById(Long id);

    /**
     * Guarda un producto nuevo en la BD
     *
     * @param productoDTO DTO con datos del producto a guardar
     */
    public void saveProducto(ProductoSaveDTO productoDTO);

    /**
     * Edita un producto existente en la BD
     *
     * @param productoDTO DTO con datos del producto a guardar
     * @param id ID del producto a editar
     * @throws NotFoundException si el producto no existe
     */
    public void editProducto(ProductoEditDTO productoDTO, Long id);

    /**
     * Elimina un producto existente en la BD
     *
     * @param id ID del producto a borrar
     * @throws NotFoundException si el producto no existe
     */
    public void deleteProducto(Long id);

    /**
     * Devuelve una lista de productos con menos de 5 unidades (cantidad_disponible en la BD)
     *
     * @return Lista de objetos ProductoGetDTO
     */
    public List<ProductoGetDTO> getPocoStock();
}
