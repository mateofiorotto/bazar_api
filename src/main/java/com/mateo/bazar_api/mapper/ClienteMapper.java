package com.mateo.bazar_api.mapper;

import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;
import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);

    // De cliente a ClienteGetDTO
    ClienteGetDTO clienteToClienteGetDto(Cliente cliente);
//    @Mapping(source = "nombre, target = "nombreCliente") --> si quisieramos cambiar el nombre de un campo al mostrarse, en el dto seria nombre y en cliente, nombreCliente
    Cliente clienteGetDtoToCliente(ClienteGetDTO clienteGetDTO);

    //metodos para post
    ClienteSaveDTO clienteToClienteSaveDto(Cliente cliente);
    Cliente clienteSaveDtoToCliente(ClienteSaveDTO clienteSaveDTO);

    //para put - No usado
    ClienteEditDTO clienteToClienteEditDto(Cliente cliente);
    Cliente clienteEditDtoToCliente(ClienteEditDTO clienteEditDTO);
}
