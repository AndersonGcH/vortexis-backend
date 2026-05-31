package com.vortexis.repositories;

import com.vortexis.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository
        extends JpaRepository<DetalleVenta, Long> {
}
