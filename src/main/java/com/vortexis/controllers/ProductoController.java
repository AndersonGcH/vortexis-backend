package com.vortexis.controllers;
import com.vortexis.dto.ProductoRequestDTO;
import com.vortexis.entities.Categoria;
import com.vortexis.entities.Producto;
import com.vortexis.services.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public Producto guardar(
            @RequestBody @Valid ProductoRequestDTO dto
    ) {

        Categoria categoria = productoService
                .obtenerCategoria(dto.getCategoriaId());

        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .sku(dto.getSku())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .descripcion(dto.getDescripcion())
                .activo(dto.getActivo())
                .categoria(categoria)
                .build();

        return productoService.guardar(producto);
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }
}
