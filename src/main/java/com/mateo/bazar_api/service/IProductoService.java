package com.mateo.bazar_api.service;

import com.mateo.bazar_api.model.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> getProductos();
    public Producto getProductoById(Long id);
    public void saveProducto(Producto producto);
    public void editProducto(Producto producto, Long id);
    public void deleteProducto(Long id);
    public List<Producto> getPocoStock();
}
