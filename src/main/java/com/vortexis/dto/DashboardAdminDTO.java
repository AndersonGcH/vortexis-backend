package com.vortexis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class DashboardAdminDTO {

    private Long cantidadVentas;

    private BigDecimal totalVentas;

    private Long totalProductos;

    private Long productosStockBajo;

    private List<ProductoMasVendidoDTO> topProductos;
}