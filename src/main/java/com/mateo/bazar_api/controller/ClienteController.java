package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;
import com.mateo.bazar_api.service.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona las operaciones CRUD sobre los clientes
 */
@RestController
public class ClienteController {

    private final IClienteService clienteService;

    /*
    * Constructor con inyeccion de dependencias
    * */
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Obtiene una lista de todos los clientes
     *
     * @return lista de clientes o mensaje que la lista esta vacia
     */
    @GetMapping("/clientes")
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista con todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados exitosamente")

    public ResponseEntity<?> getClientes() {
        List<ClienteGetDTO> listaClientes = clienteService.getClientes();

        // ListaClientes = empty, entonces vacia pero retornar 200. La solicitud es valida
        if (listaClientes.isEmpty()) {
            return ResponseEntity.ok("Lista vacia");
        }

        return ResponseEntity.ok(listaClientes);
    }

    /**
     * Busca un cliente por su ID
     *
     * @param id_cliente ID del cliente a buscar
     * @return cliente correspondiente al ID
     */
    @GetMapping("clientes/{id_cliente}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente específico dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<?> getClienteById(@PathVariable Long id_cliente){
        ClienteGetDTO cliente = clienteService.getClienteById(id_cliente);

        return ResponseEntity.ok(cliente);
    }

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente DTO con los datos del nuevo cliente
     * @return mensaje indicando que el cliente fue creado
     */
    @PostMapping("/clientes/crear")
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente y lo guarda en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el cliente")
    })
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteSaveDTO cliente){
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    /**
     * Edita un cliente existente.
     *
     * @param cliente     DTO con los nuevos datos del cliente
     * @param id_cliente  ID del cliente a editar
     * @return mensaje indicando que el cliente fue editado
     */
    @PutMapping("/clientes/editar/{id_cliente}")
    @Operation(summary = "Editar un cliente", description = "Edita un cliente existente en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente editado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para editar el cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    //Con valid, se validara el parametro de la peticion
    public ResponseEntity<?> editCliente(@Valid @RequestBody ClienteEditDTO cliente, @PathVariable Long id_cliente){
        clienteService.editCliente(cliente, id_cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente editado");
    }

    /**
     * Elimina un cliente del sistema.
     *
     * @param id_cliente ID del cliente a eliminar
     * @return mensaje indicando que el cliente fue eliminado
     */
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_cliente){
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado");
    }

}
