package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.*;
import com.mateo.bazar_api.exception.BadRequestException;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.mapper.ProductoMapper;
import com.mateo.bazar_api.mapper.VentaMapper;
import com.mateo.bazar_api.mapper.VentaProductoMapper;
import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.model.VentaProducto;
import com.mateo.bazar_api.repository.IProductoRepository;
import com.mateo.bazar_api.repository.IVentaProductoRepository;
import com.mateo.bazar_api.repository.IVentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Servicio que maneja la logica de negocio relacionada a la venta de productos
 * Puede obtener, guardar y eliminar ventas de productos.
 * Tambien puede manejar el stock de producto, devolver el total mas alto,
 * devolver el total y cantidad de ventas de un dia especifico y los productos
 * por venta especifica
 * */
@Service
public class VentaProductoService implements IVentaProductoService {
    //DI
    private final IVentaProductoRepository ventaProductoRepository;
    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;

    /*
     * Constructor con inyeccion de dependencias
     * */
    public VentaProductoService(IVentaProductoRepository ventaProductoRepository, IVentaRepository ventaRepository, IProductoRepository productoRepository) {
        this.ventaProductoRepository = ventaProductoRepository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<VentaProductoGetDTO> getVentasProductos() {
        List<VentaProducto> ventasProductos = ventaProductoRepository.findAll();

        return ventasProductos.stream()
                .map(vp -> VentaProductoMapper.mapper.ventaProductoToVentaProductoGetDto(vp))
                .collect(Collectors.toList());
    }

    @Override
    public VentaProductoGetDTO getVentaProductoById(Long id) {
        VentaProducto ventaProducto = ventaProductoRepository.findById(id).orElseThrow(
                //si no encuentra al ventaProducto, retornar un no encontrado
                () -> new NotFoundException("VentaProducto no encontrado")
        );

        return VentaProductoMapper.mapper.ventaProductoToVentaProductoGetDto(ventaProducto);
    }

    @Override
    public void saveVentaProducto(VentaProductoSaveDTO ventaProductoDTO) {
        if (ventaProductoDTO.getUnProducto() == null || ventaProductoDTO.getUnaVenta() == null || ventaProductoDTO.getCantidad() == 0) {
            throw new BadRequestException("VentaProducto no válido");
        }

       //validar si el codigo del producto ya existe en la venta
        List<VentaProducto> productosDeEsaVenta = ventaProductoRepository.findAll();

        for (VentaProducto vp : productosDeEsaVenta) {
            if (vp.getUnaVenta().getCodigo_venta().equals(ventaProductoDTO.getUnaVenta().getCodigo_venta()) &&
                    vp.getUnProducto().getCodigo_producto().equals(ventaProductoDTO.getUnProducto().getCodigo_producto())) {
                throw new BadRequestException("Este producto ya está agregado a esta venta");
            }
        }
        //obtengo el prod desde la db con la id
        Producto producto = productoRepository.findById(ventaProductoDTO.getUnProducto().getCodigo_producto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        ProductoEditDTO productoEdit = ProductoMapper.mapper.productoToProductoEditDto(producto);

        //mismo con la venta
        Venta venta = ventaRepository.findById(ventaProductoDTO.getUnaVenta().getCodigo_venta())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        VentaEditDTO ventaEdit = VentaMapper.mapper.ventaToVentaEditDto(venta);


        manejoStock(ventaProductoDTO, producto);

        //asignar objetos completos a ventaProducto. CantidadDisponible se resta a la cantidad q compramos
        ventaProductoDTO.setUnProducto(productoEdit);
        ventaProductoDTO.setUnaVenta(ventaEdit);


        //calcular total
        ventaProductoDTO.setTotal(producto.getCosto() * ventaProductoDTO.getCantidad());

        //Guardar
        ventaProductoRepository.save(VentaProductoMapper.mapper.ventaProductoSaveDtoToVentaProducto(ventaProductoDTO));
    }

    @Override
    public void deleteVentaProducto(Long id) {
        VentaProducto ventaProductoAEliminar = ventaProductoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("VentaProducto No encontrado"));

        Producto producto = ventaProductoAEliminar.getUnProducto();

        //devolviendo stock en caso de eliminar venta
        producto.setCantidad_disponible(producto.getCantidad_disponible() + ventaProductoAEliminar.getCantidad());
        productoRepository.save(producto);

        ventaProductoRepository.delete(ventaProductoAEliminar);
    }

    //metodo para manejar stock en POST y PUT
    @Override
    public void manejoStock(VentaProductoSaveDTO ventaProducto, Producto producto) {

        if (ventaProducto.getCantidad() > producto.getCantidad_disponible()) {
            throw new BadRequestException("No hay stock suficiente");
        } else {
            producto.setCantidad_disponible(producto.getCantidad_disponible() - ventaProducto.getCantidad());
        }
    }

    @Override
    public List<ProductoGetDTO> getProductosPorVenta(Long id) {
        //definimos lista de ventas de productos y una lista nueva tipo producto
        List<VentaProducto> listaVentasProductos = ventaProductoRepository.findAll();
        List<Producto> listaSoloProductos = new ArrayList<>();

        for ( VentaProducto vp : listaVentasProductos ){
            //si la id ingresada es igual al codigo de venta, entonces agregar los productos
            if (id.equals(vp.getUnaVenta().getCodigo_venta())) {
                listaSoloProductos.add(vp.getUnProducto());
            }
        }
        return listaSoloProductos.stream()
                .map(p -> ProductoMapper.mapper.productoToProductoGetDto(p))
                .collect(Collectors.toList());
    }

    @Override
    //POR EL momento devolvemos todo venta producto ya que no usamos dto
    public VentaProductoGetDTO getTotalMasAlto() {
        VentaProducto totalMasAlto = ventaProductoRepository.findTopByOrderByTotalDesc().orElseThrow(() -> new NotFoundException("No se encontraron productos"));

        return VentaProductoMapper.mapper.ventaProductoToVentaProductoGetDto(totalMasAlto);
    }

    //devuelve la cantidad de ventas en x fecha y el monto total de la suma de productos
    @Override
    public String montoTotalYCantidadDeVentasDeProductosDeUnDia(LocalDate fecha) {
        List<VentaProductoGetDTO> listaProductos = this.getVentasProductos();
        int total = 0;
        int cantVentas = 0;

        //si la fecha equivale a la que ingreso el user, sumar el total por cada producto y la cant ventas
        for (VentaProductoGetDTO p : listaProductos){
            if(p.getUnaVenta().getFecha_venta().equals(fecha)){
                total+=p.getTotal();
                cantVentas++;
            }
        }

        if(cantVentas == 0){
            return "No hubo productos vendidos ese dia";
        }

        return "El monto total del dia " + fecha + " es: " + total + " y la cantidad de productos vendidos: " + cantVentas;
    }
}

