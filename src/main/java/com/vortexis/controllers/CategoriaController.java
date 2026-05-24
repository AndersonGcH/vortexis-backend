package com.vortexis.controllers;

import com.vortexis.dto.CategoriaRequestDTO;
import com.vortexis.entities.Categoria;
import com.vortexis.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public Categoria guardar(
            @RequestBody @Valid CategoriaRequestDTO dto
    ) {

        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .build();

        return categoriaService.guardar(categoria);
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }
}
