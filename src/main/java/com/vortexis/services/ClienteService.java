package com.vortexis.services;

import com.vortexis.dto.ClienteRequestDTO;
import com.vortexis.entities.Cliente;
import com.vortexis.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente registrar(
            ClienteRequestDTO dto
    ) {

        Cliente cliente = Cliente.builder()
                .nombres(dto.getNombres())
                .dni(dto.getDni())
                .ruc(dto.getRuc())
                .correo(dto.getCorreo())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .activo(true)
                .build();

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
}
