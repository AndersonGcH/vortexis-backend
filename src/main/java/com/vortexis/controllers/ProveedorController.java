package com.vortexis.controllers;

import com.vortexis.dto.ProveedorRequestDTO;
import com.vortexis.entities.Proveedor;
import com.vortexis.services.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @PostMapping
    public Proveedor registrar(
            @RequestBody ProveedorRequestDTO dto
    ) {
        return proveedorService.registrar(dto);
    }

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.listar();
    }
}