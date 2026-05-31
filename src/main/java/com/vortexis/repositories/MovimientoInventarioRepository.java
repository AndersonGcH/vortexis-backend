package com.vortexis.repositories;
import com.vortexis.entities.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository
        extends JpaRepository<MovimientoInventario, Long> {
    List<MovimientoInventario>
    findTop5ByOrderByFechaDesc();
}