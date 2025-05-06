package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoGetDTO;
import com.mateo.bazar_api.dto.VentaProductoSaveDTO;
import com.mateo.bazar_api.model.Producto;

import java.time.LocalDate;
import java.util.List;

public interface IVentaProductoService {
    public List<VentaProductoGetDTO> getVentasProductos();
    public VentaProductoGetDTO getVentaProductoById(Long id);
    public void saveVentaProducto(VentaProductoSaveDTO ventaProductoDTO);
    //La venta no deberia poder ser modificable, mas bien deberia eliminarse y volver a crearse.
    //Esto por el motivo de que sera generada automaticamente desde la solicitud de un hipotetico frontend
//    public void editVentaProducto(VentaProductoEditDTO ventaProductoDTO, Long id);
    public void deleteVentaProducto(Long id);
    public void manejoStock(VentaProductoSaveDTO ventaProducto, Producto producto);
    public List<ProductoGetDTO> getProductosPorVenta(Long id);
    public VentaProductoGetDTO getTotalMasAlto();
    public String montoTotalYCantidadDeVentasDeProductosDeUnDia(LocalDate fecha);
}
