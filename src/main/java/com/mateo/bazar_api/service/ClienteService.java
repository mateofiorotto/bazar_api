package com.mateo.bazar_api.service;

import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.model.Cliente;
import com.mateo.bazar_api.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    //DI
    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        return listaClientes;
    }

    @Override
    public Cliente getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                //si no encuentra al cliente, retornar un no encontrado
                () -> new NotFoundException("Cliente no encontrado")
        );

        return cliente;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void editCliente(Cliente cliente, Long id) {
        Cliente clienteEncontrado = this.getClienteById(id);

        clienteEncontrado.setNombre(cliente.getNombre());
        clienteEncontrado.setApellido(cliente.getApellido());

        this.saveCliente(clienteEncontrado);

    }

    @Override
    public void deleteCliente(Long id) {
        Cliente clienteAEliminar = this.getClienteById(id);

        clienteRepository.delete(clienteAEliminar);
    }
}
