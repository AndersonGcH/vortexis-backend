package com.vortexis.services;
import com.vortexis.entities.MovimientoInventario;
import com.vortexis.entities.Producto;
import com.vortexis.entities.Usuario;
import com.vortexis.enums.TipoMovimiento;
import com.vortexis.repositories.MovimientoInventarioRepository;
import com.vortexis.repositories.ProductoRepository;
import com.vortexis.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public MovimientoInventario registrar(
            MovimientoInventario movimiento
    ) {

        Producto producto = productoRepository.findById(
                movimiento.getProducto().getId()
        ).orElseThrow(() ->
                new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(
                movimiento.getUsuario().getId()
        ).orElseThrow(() ->
                new RuntimeException("Usuario no encontrado"));

        if (movimiento.getTipo() == TipoMovimiento.ENTRADA) {

            producto.setStock(
                    producto.getStock() + movimiento.getCantidad()
            );

        } else if (movimiento.getTipo() == TipoMovimiento.SALIDA) {

            if (producto.getStock() < movimiento.getCantidad()) {
                throw new RuntimeException("Stock insuficiente");
            }

            producto.setStock(
                    producto.getStock() - movimiento.getCantidad()
            );

        } else if (movimiento.getTipo() == TipoMovimiento.AJUSTE) {

            producto.setStock(
                    movimiento.getCantidad()
            );
        }

        productoRepository.save(producto);

        movimiento.setFecha(LocalDateTime.now());
        movimiento.setProducto(producto);
        movimiento.setUsuario(usuario);

        return movimientoRepository.save(movimiento);
    }

    public List<MovimientoInventario> listar() {
        return movimientoRepository.findAll();
    }
}
