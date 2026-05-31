package com.vortexis.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nombres;

    private String dni;

    private String ruc;

    private String correo;

    private String telefono;

    private String direccion;
}
