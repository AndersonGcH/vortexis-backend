package com.vortexis.services;

import com.vortexis.dto.DashboardAdminDTO;
import com.vortexis.dto.ProductoMasVendidoDTO;
import com.vortexis.repositories.DetalleVentaRepository;
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
}
