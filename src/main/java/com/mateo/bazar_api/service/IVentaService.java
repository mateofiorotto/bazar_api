package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.VentaEditDTO;
import com.mateo.bazar_api.dto.VentaGetDTO;
import com.mateo.bazar_api.dto.VentaSaveDTO;
import com.mateo.bazar_api.exception.NotFoundException;

import java.util.List;

public interface IVentaService {
    /**
     * Devuelve una lista de ventas desde la BD
     *
     * @return Lista de objetos VentaGetDTO
     */
    public List<VentaGetDTO> getVentas();

    /**
     * Devuelve una venta segun su ID
     *
     * @param id ID del venta a buscar
     * @return Objeto VentaGetDTO
     * @throws NotFoundException si la venta no existe
     */
    public VentaGetDTO getVentaById(Long id);

    /**
     * Guarda una venta nueva en la BD
     *
     * @param ventaDTO DTO con datos del venta a guardar
     * @throws NotFoundException si el cliente no existe
     */
    public void saveVenta(VentaSaveDTO ventaDTO);

    /**
     * Edita un venta existente en la BD
     *
     * @param ventaDTO DTO con datos de la venta a guardar
     * @param id ID de la venta a editar
     * @throws NotFoundException si la venta no existe
     * @throws NotFoundException si el cliente no existe
     */
    public void editVenta(VentaEditDTO ventaDTO, Long id);

    /**
     * Elimina un venta existente en la BD
     *
     * @param id ID de la venta a borrar
     * @throws NotFoundException si la venta no existe
     */
    public void deleteVenta(Long id);
}
