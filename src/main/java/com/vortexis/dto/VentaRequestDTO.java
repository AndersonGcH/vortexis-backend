package com.vortexis.dto;
import com.vortexis.enums.MetodoPago;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class VentaRequestDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    private MetodoPago metodoPago;

    @NotNull
    private List<DetalleVentaRequestDTO> detalles;
}
