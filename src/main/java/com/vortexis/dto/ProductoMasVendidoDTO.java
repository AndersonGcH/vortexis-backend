package com.vortexis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoMasVendidoDTO {

    private String producto;

    private Long cantidadVendida;
}
