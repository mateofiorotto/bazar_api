package com.mateo.bazar_api.service;

import com.mateo.bazar_api.exception.BadRequestException;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.VentaProducto;
import com.mateo.bazar_api.repository.IProductoRepository;
import com.mateo.bazar_api.repository.IVentaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {
    //DI
    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = productoRepository.findAll();

        return listaProductos;
    }

    @Override
    public Producto getProductoById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(
                //si no encuentra al producto, retornar un no encontrado
                () -> new NotFoundException("Producto no encontrado")
        );

        return producto;
    }

    @Override
    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void editProducto(Producto producto, Long id) {
        Producto productoEncontrado = this.getProductoById(id);

        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setMarca(producto.getMarca());
        productoEncontrado.setCosto(producto.getCosto());
        productoEncontrado.setCantidad_disponible(producto.getCantidad_disponible());

        this.saveProducto(productoEncontrado);

    }

    @Override
    public void deleteProducto(Long id) {
        Producto productoAEliminar = this.getProductoById(id);
        
        productoRepository.delete(productoAEliminar);
    }

    @Override
    public List<Producto> getPocoStock() {
        List<Producto> listaProductos = productoRepository.findAll();
        List<Producto> productosPocoStock = new ArrayList<Producto>();

        for (Producto p : listaProductos){
            if(p.getCantidad_disponible() < 5) {
                productosPocoStock.add(p);
            }
        }

        return productosPocoStock;
    }
}
