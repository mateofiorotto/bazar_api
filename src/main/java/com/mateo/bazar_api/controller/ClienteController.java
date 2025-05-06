package com.mateo.bazar_api.controller;

import com.mateo.bazar_api.dto.ClienteEditDTO;
import com.mateo.bazar_api.dto.ClienteGetDTO;
import com.mateo.bazar_api.dto.ClienteSaveDTO;
import com.mateo.bazar_api.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET Clientes
    @GetMapping("/clientes")
    public ResponseEntity<?> getClientes() {
        List<ClienteGetDTO> listaClientes = clienteService.getClientes();

        // ListaClientes = empty, entonces vacia pero retornar 200. La solicitud es valida
        if (listaClientes.isEmpty()) {
            return ResponseEntity.ok("Lista vacia");
        }

        return ResponseEntity.ok(listaClientes);
    }

    // Get x id
    @GetMapping("clientes/{id_cliente}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id_cliente){
        ClienteGetDTO cliente = clienteService.getClienteById(id_cliente);

        return ResponseEntity.ok(cliente);
    }

    //post
    @PostMapping("/clientes/crear")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody ClienteSaveDTO cliente){
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    //put
    @PutMapping("/clientes/editar/{id_cliente}")
    //Con valid, se validara el parametro de la peticion
    public ResponseEntity<?> editCliente(@Valid @RequestBody ClienteEditDTO cliente, @PathVariable Long id_cliente){
        clienteService.editCliente(cliente, id_cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente editado");
    }

    //delete
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id_cliente){
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado");
    }

}
