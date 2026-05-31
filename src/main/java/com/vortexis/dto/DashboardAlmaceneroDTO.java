package com.vortexis.dto;

import com.vortexis.entities.MovimientoInventario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardAlmaceneroDTO {

    private Long totalProductos;

    private Long productosStockBajo;

    private List<MovimientoInventario> ultimosMovimientos;
}
