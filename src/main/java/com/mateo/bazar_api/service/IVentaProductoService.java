package com.mateo.bazar_api.service;

import com.mateo.bazar_api.model.Producto;
import com.mateo.bazar_api.model.Venta;
import com.mateo.bazar_api.model.VentaProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IVentaProductoService {
    public List<VentaProducto> getVentasProductos();
    public VentaProducto getVentaProductoById(Long id);
    public void saveVentaProducto(VentaProducto venta);
    public void editVentaProducto(VentaProducto venta, Long id);
    public void deleteVentaProducto(Long id);
    public void manejoStock(VentaProducto ventaProducto, Producto producto);
    public List<Producto> getProductosPorVenta(Long id);
    public VentaProducto getTotalMasAlto();
    public String montoTotalYCantidadDeVentasDeUnDia(LocalDate fecha);
}
