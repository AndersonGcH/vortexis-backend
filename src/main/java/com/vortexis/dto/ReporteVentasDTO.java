package com.vortexis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ReporteVentasDTO {

    private Long cantidadVentas;

    private BigDecimal totalVentas;

    private BigDecimal promedioVenta;
}
