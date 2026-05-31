package com.vortexis.services;

import com.vortexis.dto.DetalleVentaRequestDTO;
import com.vortexis.dto.ProductoMasVendidoDTO;
import com.vortexis.dto.ReporteVentasDTO;
import com.vortexis.dto.VentaRequestDTO;
import com.vortexis.entities.*;
import com.vortexis.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    @Transactional
    public Venta registrar(VentaRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<DetalleVenta> detalles = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        Venta venta = new Venta();

        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setUsuario(usuario);
        venta.setCliente(cliente);

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


    public ReporteVentasDTO obtenerReporteVentas() {

        Long cantidad = ventaRepository.contarVentas();

        BigDecimal total = ventaRepository.sumarVentas();

        BigDecimal promedio = BigDecimal.ZERO;

        if(cantidad > 0) {

            promedio = total.divide(
                    BigDecimal.valueOf(cantidad),
                    2,
                    RoundingMode.HALF_UP
            );
        }

        return new ReporteVentasDTO(
                cantidad,
                total,
                promedio
        );
    }

    public List<ProductoMasVendidoDTO> obtenerProductosMasVendidos() {

        return detalleVentaRepository
                .productosMasVendidos();
    }

}
