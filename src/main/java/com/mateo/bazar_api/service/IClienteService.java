package com.mateo.bazar_api.service;

import com.mateo.bazar_api.model.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> getClientes();
    public Cliente getClienteById(Long id);
    public void saveCliente(Cliente cliente);
    public void editCliente(Cliente cliente, Long id);
    public void deleteCliente(Long id);
}
