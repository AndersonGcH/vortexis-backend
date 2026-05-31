package com.vortexis.services;

import com.vortexis.dto.DashboardAdminDTO;
import com.vortexis.dto.DashboardAlmaceneroDTO;
import com.vortexis.dto.DashboardVendedorDTO;
import com.vortexis.dto.ProductoMasVendidoDTO;
import com.vortexis.entities.MovimientoInventario;
import com.vortexis.entities.Venta;
import com.vortexis.repositories.DetalleVentaRepository;
import com.vortexis.repositories.MovimientoInventarioRepository;
import com.vortexis.repositories.ProductoRepository;
import com.vortexis.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public DashboardAdminDTO dashboardAdmin() {

        Long cantidadVentas =
                ventaRepository.contarVentas();

        BigDecimal totalVentas =
                ventaRepository.sumarVentas();

        Long totalProductos =
                productoRepository.contarProductos();

        Long stockBajo =
                productoRepository.contarStockBajo();

        List<ProductoMasVendidoDTO> topProductos =
                detalleVentaRepository
                        .productosMasVendidos();

        return new DashboardAdminDTO(
                cantidadVentas,
                totalVentas,
                totalProductos,
                stockBajo,
                topProductos
        );
    }

    public DashboardVendedorDTO dashboardVendedor(
            Long usuarioId
    ) {

        Long cantidad =
                ventaRepository
                        .contarVentasPorUsuario(
                                usuarioId
                        );

        BigDecimal total =
                ventaRepository
                        .totalVentasPorUsuario(
                                usuarioId
                        );

        List<Venta> ultimas =
                ventaRepository
                        .findTop5ByUsuarioIdOrderByFechaDesc(
                                usuarioId
                        );

        return new DashboardVendedorDTO(
                cantidad,
                total,
                ultimas
        );
    }

    public DashboardAlmaceneroDTO dashboardAlmacenero() {

        Long totalProductos =
                productoRepository.contarProductos();

        Long stockBajo =
                productoRepository.contarStockBajo();

        List<MovimientoInventario> movimientos =
                movimientoInventarioRepository
                        .findTop5ByOrderByFechaDesc();

        return new DashboardAlmaceneroDTO(
                totalProductos,
                stockBajo,
                movimientos
        );
    }
}
