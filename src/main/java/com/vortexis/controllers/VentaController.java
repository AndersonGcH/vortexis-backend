package com.vortexis.controllers;

import com.vortexis.dto.VentaRequestDTO;
import com.vortexis.entities.Venta;
import com.vortexis.services.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public Venta registrar(
            @RequestBody @Valid VentaRequestDTO dto
    ) {
        return ventaService.registrar(dto);
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listar();
    }

    @GetMapping("/{id}")
    public Venta obtenerPorId(
            @PathVariable Long id
    ) {
        return ventaService.obtenerPorId(id);
    }
}
