package com.mateo.bazar_api.service;

import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.repository.IVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {
    //DI
    private final IVentaRepository ventaRepository;

    public VentaService(IVentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();

        return listaVentas;
    }

    @Override
    public Venta getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(
                //si no encuentra al venta, retornar un no encontrado
                () -> new NotFoundException("Venta no encontrada")
        );

        return venta;
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void editVenta(Venta venta, Long id) {
        Venta ventaEncontrada = this.getVentaById(id);

        ventaEncontrada.setFecha_venta(venta.getFecha_venta());
        ventaEncontrada.setTotal(venta.getTotal());
        ventaEncontrada.setListaProductos(venta.getListaProductos());
        ventaEncontrada.setUnCliente(venta.getUnCliente());

        this.saveVenta(ventaEncontrada);

    }

    @Override
    public void deleteVenta(Long id) {
        Venta ventaAEliminar = this.getVentaById(id);

        ventaRepository.delete(ventaAEliminar);
    }
}
