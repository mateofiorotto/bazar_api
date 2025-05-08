package com.mateo.bazar_api.service;

import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;
import com.mateo.bazar_api.exception.NotFoundException;

import java.util.List;

public interface IClienteService {
    /**
     * Devuelve una lista de clientes desde la BD
     *
     * @return Lista de objetos ClienteGetDTO
     */
    public List<ClienteGetDTO> getClientes();

    /**
     * Devuelve un cliente segun su ID
     *
     * @param id ID del cliente a buscar
     * @return Objeto ClienteGetDTO
     * @throws NotFoundException si el cliente no existe
     */
    public ClienteGetDTO getClienteById(Long id);

    /**
     * Guarda un cliente nuevo en la BD
     *
     * @param clienteDTO DTO con datos del cliente a guardar
     */
    public void saveCliente(ClienteSaveDTO clienteDTO);

    /**
     * Edita un cliente existente en la BD
     *
     * @param clienteDTO DTO con datos del cliente a guardar
     * @param id ID del cliente a editar
     * @throws NotFoundException si el cliente no existe
     */
    public void editCliente(ClienteEditDTO clienteDTO, Long id);

    /**
     * Elimina un cliente existente en la BD
     *
     * @param id ID del cliente a borrar
     * @throws NotFoundException si el cliente no existe
     */
    public void deleteCliente(Long id);
}
