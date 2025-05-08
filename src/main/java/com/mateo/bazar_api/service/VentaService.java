package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.VentaEditDTO;
import com.mateo.bazar_api.dto.VentaGetDTO;
import com.mateo.bazar_api.dto.VentaSaveDTO;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.mapper.ClienteMapper;
import com.mateo.bazar_api.mapper.ProductoMapper;
import com.mateo.bazar_api.mapper.VentaMapper;
import com.mateo.bazar_api.model.Cliente;
import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.repository.IClienteRepository;
import com.mateo.bazar_api.repository.IVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Servicio que maneja la logica de negocio relacionada a ventas
 * Puede obtener, guardar, editar y eliminar ventas
 * */
@Service
public class VentaService implements IVentaService {
    //DI
    private final IVentaRepository ventaRepository;
    private final IClienteRepository clienteRepository;

    /*
     * Constructor con inyeccion de dependencias
     * */
    public VentaService(IVentaRepository ventaRepository, IClienteRepository clienteRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<VentaGetDTO> getVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();

        return listaVentas.stream().map(
                venta -> VentaMapper.mapper.ventaToVentaGetDto(venta)).collect(Collectors.toList());
    }

    @Override
    public VentaGetDTO getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Venta no encontrada")
        );

        return VentaMapper.mapper.ventaToVentaGetDto(venta);
    }

    @Override
    public void saveVenta(VentaSaveDTO ventaDTO) {
        Venta venta = VentaMapper.mapper.ventaSaveDtoToVenta(ventaDTO);
        venta.setFecha_venta(ventaDTO.getFecha_venta());

        Cliente cliente = clienteRepository.findById(
                ventaDTO.getUnCliente().getId_cliente()).orElseThrow(() ->
                new NotFoundException("Cliente no encontrado"));

        venta.setUnCliente(cliente);

        ventaRepository.save(venta);
    }

    @Override
    public void editVenta(VentaEditDTO ventaDTO, Long id) {
        Venta ventaEncontrada = ventaRepository.findById(id).orElseThrow(
                //si no encuentra al venta, retornar un no encontrado
                () -> new NotFoundException("Venta no encontrada")
        );

        Cliente cliente = clienteRepository.findById(
                ventaDTO.getUnCliente().getId_cliente()).orElseThrow(() ->
                new NotFoundException("Cliente no encontrado"));


        ventaEncontrada.setFecha_venta(ventaDTO.getFecha_venta());
        ventaEncontrada.setUnCliente(cliente);

        ventaRepository.save(ventaEncontrada);

    }

    @Override
    public void deleteVenta(Long id) {
        Venta ventaAEliminar = ventaRepository.findById(id).orElseThrow(
                //si no encuentra al venta, retornar un no encontrado
                () -> new NotFoundException("Venta no encontrada")
        );

        ventaRepository.delete(ventaAEliminar);
    }
}
