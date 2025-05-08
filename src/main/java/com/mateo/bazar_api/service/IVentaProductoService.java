package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoSaveDTO;
import com.mateo.bazar_api.exception.BadRequestException;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.model.Producto;

import java.time.LocalDate;
import java.util.List;

public interface IVentaProductoService {
    /**
     * Devuelve una lista de ventas de productos desde la BD
     *
     * @return Lista de objetos VentaProductoGetDTO
     */
    public List<VentaProductoGetDTO> getVentasProductos();

    /**
     * Devuelve una venta de un producto segun su ID
     *
     * @param id ID del VentaProducto a buscar
     * @return Objeto VentaProducto
     * @throws NotFoundException si la venta de un producto no existe
     */
    public VentaProductoGetDTO getVentaProductoById(Long id);

    /**
     * Guarda una nueva venta de un producto en la DB
     *
     * @param ventaProductoDTO DTO con datos del cliente a guardar
     * @throws BadRequestException Si la venta de un producto no es valida
     * @throws BadRequestException Si un producto ya esta agregado a una venta
     * @throws NotFoundException Si un codigo de producto no se encuentra
     * @throws NotFoundException Si un codigo de venta no se encuentra
     */
    public void saveVentaProducto(VentaProductoSaveDTO ventaProductoDTO);

    /**
     * Elimina una venta de producto existente en la BD
     *
     * @param id ID de la venta de un producto a borrar
     * @throws NotFoundException si la venta de un producto no existe
     */
    public void deleteVentaProducto(Long id);

    /**
     * Se encarga de manejar el stock de un producto al guardar una venta de un producto
     *
     * @param ventaProducto un objeto VentaProductoSaveDTO para obtener la cantidad que se compro
     * @param producto un objeto Producto para obtener la cantidad que hay en stock de ese producto
     * @throws BadRequestException si la cantidad comprada ingresada es mayor al stock disponible
     */
    public void manejoStock(VentaProductoSaveDTO ventaProducto, Producto producto);

    /**
     * Atribuye el total de productos relacionados a una venta especifica
     *
     * @param id ID ingresada por el usuario que debe coincidir con una venta en la tabla VentaProducto
     * @return Lista de objetos ProductoGetDTO
     */
    public List<ProductoGetDTO> getProductosPorVenta(Long id);

    /**
     * Obtiene el total mas alto con el uso de un metodo JPA (findByTopOrderByTotalDesc)
     *
     * @return Lista de objetos VentaProductoGetDTO
     * @throws NotFoundException si no se encuentran productos (db vacia)
     */
    public VentaProductoGetDTO getTotalMasAlto();

    /**
     * Obtiene el monto total y la cantidad de ventas de productos en una fecha
     *
     * @return String con la fecha, el total y la cantidad de ventas o si la cantidad de ventas es 0, devuelve que no hubo ventas ese dia
     */
    public String montoTotalYCantidadDeVentasDeProductosDeUnDia(LocalDate fecha);

    /*
    La venta no deberia poder ser modificable, mas bien deberia eliminarse y volver a crearse.
    Esto por el motivo de que sera generada automaticamente desde la solicitud de un hipotetico frontend

    public void editVentaProducto(VentaProductoEditDTO ventaProductoDTO, Long id);
    */
}
