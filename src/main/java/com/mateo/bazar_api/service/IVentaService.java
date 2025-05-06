package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.VentaEditDTO;
import com.mateo.bazar_api.dto.VentaGetDTO;
import com.mateo.bazar_api.dto.VentaSaveDTO;

import java.util.List;

public interface IVentaService {
    public List<VentaGetDTO> getVentas();
    public VentaGetDTO getVentaById(Long id);
    public void saveVenta(VentaSaveDTO ventaEditDTO);
    public void editVenta(VentaEditDTO ventaEditDTO, Long id);
    public void deleteVenta(Long id);
}
