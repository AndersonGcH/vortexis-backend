package com.vortexis.controllers;

import com.vortexis.dto.MovimientoInventarioRequestDTO;
import com.vortexis.entities.MovimientoInventario;
import com.vortexis.entities.Producto;
import com.vortexis.entities.Usuario;
import com.vortexis.services.MovimientoInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    @PostMapping
    public MovimientoInventario registrar(
            @RequestBody @Valid MovimientoInventarioRequestDTO dto
    ) {

        Producto producto = Producto.builder()
                .id(dto.getProductoId())
                .build();

        Usuario usuario = Usuario.builder()
                .id(dto.getUsuarioId())
                .build();

        MovimientoInventario movimiento =
                MovimientoInventario.builder()
                        .tipo(dto.getTipo())
                        .cantidad(dto.getCantidad())
                        .motivo(dto.getMotivo())
                        .producto(producto)
                        .usuario(usuario)
                        .build();

        return movimientoService.registrar(movimiento);
    }

    @GetMapping
    public List<MovimientoInventario> listar() {
        return movimientoService.listar();
    }
}
