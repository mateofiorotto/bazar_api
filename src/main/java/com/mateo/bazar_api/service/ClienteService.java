package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;
import com.mateo.bazar_api.exception.NotFoundException;
import com.mateo.bazar_api.mapper.ClienteMapper;
import com.mateo.bazar_api.model.Cliente;
import com.mateo.bazar_api.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
* Servicio que maneja la logica de negocio relacionada a clientes
* Puede obtener, guardar, editar y eliminar clientes
* */
@Service
public class ClienteService implements IClienteService {
    //DI
    private final IClienteRepository clienteRepository;

    /*
    * Constructor con inyeccion de dependencias
    * */
    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteGetDTO> getClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        return listaClientes.stream().map(
          cliente -> ClienteMapper.mapper.clienteToClienteGetDto(cliente)).collect(Collectors.toList());
    }

    @Override
    public ClienteGetDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                //si no encuentra al cliente, retornar un no encontrado
                () -> new NotFoundException("Cliente no encontrado")
        );

        return ClienteMapper.mapper.clienteToClienteGetDto(cliente);
    }

    @Override
    public void saveCliente(ClienteSaveDTO clienteDTO) {
        Cliente cliente = ClienteMapper.mapper.clienteSaveDtoToCliente(clienteDTO);
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());

        clienteRepository.save(cliente);
    }

    @Override
    public void editCliente(ClienteEditDTO clienteDTO, Long id) {
        Cliente clienteEncontrado = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        clienteEncontrado.setNombre(clienteDTO.getNombre());
        clienteEncontrado.setApellido(clienteDTO.getApellido());

        clienteRepository.save(clienteEncontrado);
    }

    @Override
    public void deleteCliente(Long id) {
//        Cliente clienteAEliminar = this.getClienteById(id); --> no llamar al metodo x id porq devuelve dto, en este caso eliminamos la entidad

        Cliente clienteAEliminar = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        clienteRepository.delete(clienteAEliminar);
    }
}
