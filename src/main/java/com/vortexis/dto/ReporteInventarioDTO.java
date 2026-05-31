package com.vortexis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteInventarioDTO {

    private Long totalProductos;

    private Long stockTotal;

    private Long productosStockBajo;
}
