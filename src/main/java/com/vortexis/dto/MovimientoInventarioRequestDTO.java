package com.vortexis.dto;

import com.vortexis.enums.TipoMovimiento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovimientoInventarioRequestDTO {

    @NotNull
    private TipoMovimiento tipo;

    @NotNull
    @Min(1)
    private Integer cantidad;

    private String motivo;

    @NotNull
    private Long productoId;

    @NotNull
    private Long usuarioId;
}