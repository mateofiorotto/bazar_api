package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoEditDTO;
import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.ProductoSaveDTO;
import com.mateo.bazar_api.exception.BadRequestException;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.mapper.ProductoMapper;
import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.VentaProducto;
import com.mateo.bazar_api.repository.IProductoRepository;
import com.mateo.bazar_api.repository.IVentaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Servicio que maneja la logica de negocio relacionada a productos
 * Puede obtener, guardar, editar y eliminar productos
 * Tambien puede devolver productos que tengan una cantidad disponible menor a 5
 * */
@Service
public class ProductoService implements IProductoService {
    //DI
    private final IProductoRepository productoRepository;

    /*
     * Constructor con inyeccion de dependencias
     * */
    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoGetDTO> getProductos() {
        List<Producto> listaProductos = productoRepository.findAll();

        return listaProductos.stream().map(
                producto -> ProductoMapper.mapper.productoToProductoGetDto(producto)).collect(Collectors.toList());
    }

    @Override
    public ProductoGetDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(
                //si no encuentra al producto, retornar un no encontrado
                () -> new NotFoundException("Producto no encontrado")
        );

        return ProductoMapper.mapper.productoToProductoGetDto(producto);
    }

    @Override
    public void saveProducto(ProductoSaveDTO productoDTO) {
        Producto producto = ProductoMapper.mapper.productoSaveDtoToProducto(productoDTO);
        producto.setNombre(productoDTO.getNombre());
        producto.setMarca(productoDTO.getMarca());
        producto.setCosto(productoDTO.getCosto());
        producto.setCantidad_disponible(productoDTO.getCantidad_disponible());

        productoRepository.save(producto);
    }

    @Override
    public void editProducto(ProductoEditDTO productoDTO, Long id) {
        Producto productoEncontrado = productoRepository.findById(id).orElseThrow(
                //si no encuentra al producto, retornar un no encontrado
                () -> new NotFoundException("Producto no encontrado")
        );

        productoEncontrado.setNombre(productoDTO.getNombre());
        productoEncontrado.setMarca(productoDTO.getMarca());
        productoEncontrado.setCosto(productoDTO.getCosto());
        productoEncontrado.setCantidad_disponible(productoDTO.getCantidad_disponible());

        productoRepository.save(productoEncontrado);

    }

    @Override
    public void deleteProducto(Long id) {
        Producto productoAEliminar = productoRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Producto no encontrado"));
        
        productoRepository.delete(productoAEliminar);
    }

    @Override
    public List<ProductoGetDTO> getPocoStock() {
        List<Producto> listaProductos = productoRepository.findAll();

        return listaProductos.stream()
                .filter(producto -> producto.getCantidad_disponible() < 5)
                .map(producto -> ProductoMapper.mapper.productoToProductoGetDto(producto))
                .collect(Collectors.toList());
//        List<Producto> productosPocoStock = new ArrayList<Producto>();

//        for (Producto p : listaProductos){
//            if(p.getCantidad_disponible() < 5) {
//                productosPocoStock.add(p);
//            }
//        }
//
//        return productosPocoStock;
    }
}
