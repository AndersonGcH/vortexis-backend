package com.vortexis.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String sku;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;

    @NotNull
    @Min(0)
    private Integer stock;

    private String descripcion;

    @NotNull
    private Boolean activo;

    @NotNull
    private Long categoriaId;
}
