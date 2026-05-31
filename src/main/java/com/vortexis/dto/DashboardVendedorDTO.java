package com.vortexis.dto;

import com.vortexis.entities.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class DashboardVendedorDTO {

    private Long cantidadVentas;

    private BigDecimal totalVentas;

    private List<Venta> ultimasVentas;
}