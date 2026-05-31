package com.vortexis.services;

import com.vortexis.dto.ProveedorRequestDTO;
import com.vortexis.entities.Proveedor;
import com.vortexis.repositories.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public Proveedor registrar(
            ProveedorRequestDTO dto
    ) {

        Proveedor proveedor = Proveedor.builder()
                .razonSocial(dto.getRazonSocial())
                .ruc(dto.getRuc())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .activo(true)
                .build();

        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedor(Long id) {

        return proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Proveedor no encontrado"));
    }
}
