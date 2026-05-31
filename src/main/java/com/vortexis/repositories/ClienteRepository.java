package com.vortexis.repositories;

import com.vortexis.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {
}
