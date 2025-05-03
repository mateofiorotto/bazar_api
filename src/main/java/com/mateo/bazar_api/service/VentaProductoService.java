package com.mateo.bazar_api.service;

import com.mateo.bazar_api.exception.BadRequestException;
import com.mateo.bazar_api.exception.NotFoundException;
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

@Service
public class VentaProductoService implements IVentaProductoService {
    //DI
    private final IVentaProductoRepository ventaProductoRepository;
    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;

    public VentaProductoService(IVentaProductoRepository ventaProductoRepository, IVentaRepository ventaRepository, IProductoRepository productoRepository) {
        this.ventaProductoRepository = ventaProductoRepository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<VentaProducto> getVentasProductos() {
        List<VentaProducto> ventasProductos = ventaProductoRepository.findAll();

        return ventasProductos;
    }

    @Override
    public VentaProducto getVentaProductoById(Long id) {
        VentaProducto ventaProducto = ventaProductoRepository.findById(id).orElseThrow(
                //si no encuentra al ventaProducto, retornar un no encontrado
                () -> new NotFoundException("VentaProducto no encontrado")
        );

        return ventaProducto;
    }

    @Override
    public void saveVentaProducto(VentaProducto ventaProducto) {
        if (ventaProducto.getUnProducto() == null || ventaProducto.getUnaVenta() == null || ventaProducto.getCantidad() == 0) {
            throw new BadRequestException("VentaProducto no válido");
        }

        //obtengo el prod desde la db con la id
        Producto producto = productoRepository.findById(ventaProducto.getUnProducto().getCodigo_producto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        //mismo con la venta
        Venta venta = ventaRepository.findById(ventaProducto.getUnaVenta().getCodigo_venta())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        manejoStock(ventaProducto, producto);

        //asignar objetos completos a ventaProducto. CantidadDisponible se reta a la cantidad q compramos
        ventaProducto.setUnProducto(producto);
        ventaProducto.setUnaVenta(venta);


        //calcular total
        ventaProducto.setTotal(producto.getCosto() * ventaProducto.getCantidad());

        //Guardar
        ventaProductoRepository.save(ventaProducto);
    }

    @Override
    public void editVentaProducto(VentaProducto ventaProducto, Long id) {
        VentaProducto ventaProductoEncontrado = this.getVentaProductoById(id);

        Producto produc = productoRepository.findById(ventaProducto.getUnProducto().getCodigo_producto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        //seteo la id y lo mando a manejostock asi puedo modificar el ventaPROD viejo
        ventaProducto.setId(id); // muy importante
        manejoStock(ventaProducto, produc);

        ventaProductoEncontrado.setUnProducto(ventaProducto.getUnProducto());
        ventaProductoEncontrado.setUnaVenta(ventaProducto.getUnaVenta());
        ventaProductoEncontrado.setCantidad(ventaProducto.getCantidad());
        ventaProductoEncontrado.setTotal(ventaProducto.getTotal());

        Producto producto = productoRepository.findById(ventaProducto.getUnProducto().getCodigo_producto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        this.saveVentaProducto(ventaProductoEncontrado);
    }

    @Override
    public void deleteVentaProducto(Long id) {
        VentaProducto ventaProductoAEliminar = this.getVentaProductoById(id);
        Producto producto = ventaProductoAEliminar.getUnProducto();

        //devolviendo stock en caso de eliminar venta
        producto.setCantidad_disponible(producto.getCantidad_disponible() + ventaProductoAEliminar.getCantidad());
        productoRepository.save(producto);

        ventaProductoRepository.delete(ventaProductoAEliminar);
    }

    //metodo para manejar stock en POST y PUT
    @Override
    public void manejoStock(VentaProducto ventaProducto, Producto producto) {
        int cantidadVieja = 0;

        //obtener viejo valor, para comparar la cant vieja con la nueva que se carga x postman
        if (ventaProducto.getId() != null) {
            VentaProducto ventaProductoViejo = ventaProductoRepository.findById(ventaProducto.getId())
                    .orElseThrow(() -> new NotFoundException("VentaProducto no encontrado"));
            cantidadVieja = ventaProductoViejo.getCantidad();
        }

        int cantidadNueva = ventaProducto.getCantidad();

        int diferencia = cantidadNueva - cantidadVieja;

        // Si la dif es menor a 0 entonces no hay stock
        if (diferencia > 0) {
            if (diferencia > producto.getCantidad_disponible()) {
                throw new BadRequestException("No hay stock suficiente");
            }
            //la cant disponible del producto sera a la cantidad Disponible - diferencia entre la cant nueva y vieja
            producto.setCantidad_disponible(producto.getCantidad_disponible() - diferencia);
        } else if (diferencia < 0) {
            //Devolver stock
            producto.setCantidad_disponible(producto.getCantidad_disponible() + Math.abs(diferencia));
        }
    }

    @Override
    public List<Producto> getProductosPorVenta(Long id) {
        List<VentaProducto> listaVentasProductos = this.getVentasProductos();
        List<Producto> listaSoloProductos = new ArrayList<>();

        for ( VentaProducto vp : listaVentasProductos ){
            if (id.equals(vp.getUnaVenta().getCodigo_venta())) {
                listaSoloProductos.add(vp.getUnProducto());
            }
        }
        return listaSoloProductos;
    }

    @Override
    //POR EL momento devolvemos todo venta producto ya que no usamos dto
    public VentaProducto getTotalMasAlto() {
        return ventaProductoRepository.findTopByOrderByTotalDesc().orElse(null);
    }

    @Override
    public String montoTotalYCantidadDeVentasDeUnDia(LocalDate fecha) {
        List<VentaProducto> listaProductos = this.getVentasProductos();
        int total = 0;
        int cantVentas = 0;

        for (VentaProducto p : listaProductos){
            System.out.println(p.getUnaVenta().getFecha_venta());
            if(p.getUnaVenta().getFecha_venta().equals(fecha)){
                total+=p.getTotal();
                cantVentas++;
            }
        }

        if(cantVentas == 0){
            return "No hubo ventas ese dia";
        }

        return "El monto total del dia " + fecha + " es: " + total + " y la cantidad de ventas: " + cantVentas;
    }
}

