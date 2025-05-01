package com.mateo.bazar_api.service;

import com.mateo.bazar_api.model.Venta;

import java.util.List;

public interface IVentaService {
    public List<Venta> getVentas();
    public Venta getVentaById(Long id);
    public void saveVenta(Venta venta);
    public void editVenta(Venta venta, Long id);
    public void deleteVenta(Long id);
}
