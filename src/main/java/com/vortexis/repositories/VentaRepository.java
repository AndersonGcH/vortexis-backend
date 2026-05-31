package com.vortexis.repositories;

import com.vortexis.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository
        extends JpaRepository<Venta, Long> {
}