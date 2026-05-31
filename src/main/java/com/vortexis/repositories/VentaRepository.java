package com.vortexis.repositories;

import com.vortexis.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface VentaRepository
        extends JpaRepository<Venta, Long> {

    @Query("""
SELECT COUNT(v)
FROM Venta v
""")
    Long contarVentas();

    @Query("""
SELECT COALESCE(SUM(v.total),0)
FROM Venta v
""")
    BigDecimal sumarVentas();
}