package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;

import java.util.List;

public interface IClienteService {
    public List<ClienteGetDTO> getClientes();
    public ClienteGetDTO getClienteById(Long id);
    public void saveCliente(ClienteSaveDTO clienteDTO);
    public void editCliente(ClienteEditDTO clienteDTO, Long id);
    public void deleteCliente(Long id);
}
