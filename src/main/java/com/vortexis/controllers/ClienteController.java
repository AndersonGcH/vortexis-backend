package com.vortexis.controllers;

import com.vortexis.dto.ClienteRequestDTO;
import com.vortexis.entities.Cliente;
import com.vortexis.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public Cliente registrar(
            @RequestBody ClienteRequestDTO dto
    ) {
        return clienteService.registrar(dto);
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }
}
