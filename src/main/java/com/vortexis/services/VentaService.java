package com.vortexis.services;

import com.vortexis.dto.DetalleVentaRequestDTO;
import com.vortexis.dto.VentaRequestDTO;
import com.vortexis.entities.DetalleVenta;
import com.vortexis.entities.Producto;
import com.vortexis.entities.Usuario;
import com.vortexis.entities.Venta;
import com.vortexis.repositories.ProductoRepository;
import com.vortexis.repositories.UsuarioRepository;
import com.vortexis.repositories.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Venta registrar(VentaRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        List<DetalleVenta> detalles = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        Venta venta = new Venta();

        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setUsuario(usuario);

        for (DetalleVentaRequestDTO item : dto.getDetalles()) {

            Producto producto = productoRepository
                    .findById(item.getProductoId())
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException(
                        "Stock insuficiente para "
                                + producto.getNombre()
                );
            }

            BigDecimal subtotal =
                    producto.getPrecio()
                            .multiply(
                                    BigDecimal.valueOf(item.getCantidad())
                            );

            producto.setStock(
                    producto.getStock()
                            - item.getCantidad()
            );

            productoRepository.save(producto);

            DetalleVenta detalle =
                    DetalleVenta.builder()
                            .venta(venta)
                            .producto(producto)
                            .cantidad(item.getCantidad())
                            .precioUnitario(producto.getPrecio())
                            .subtotal(subtotal)
                            .build();

            detalles.add(detalle);

            total = total.add(subtotal);
        }

        venta.setTotal(total);
        venta.setDetalles(detalles);

        return ventaRepository.save(venta);
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Venta obtenerPorId(Long id) {

        return ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));
    }

}
