package com.vortexis.repositories;

import com.vortexis.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

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

    @Query("""
SELECT COUNT(v)
FROM Venta v
WHERE v.usuario.id = :usuarioId
""")
    Long contarVentasPorUsuario(Long usuarioId);

    @Query("""
SELECT COALESCE(SUM(v.total),0)
FROM Venta v
WHERE v.usuario.id = :usuarioId
""")
    BigDecimal totalVentasPorUsuario(Long usuarioId);

    List<Venta> findTop5ByUsuarioIdOrderByFechaDesc(
            Long usuarioId
    );
}