package com.vortexis.repositories;

import com.vortexis.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository
        extends JpaRepository<Producto, Long> {

    @Query("""
SELECT COUNT(p)
FROM Producto p
WHERE p.activo = true
""")
    Long contarProductos();


    @Query("""
SELECT COALESCE(SUM(p.stock),0)
FROM Producto p
WHERE p.activo = true
""")
    Long stockTotal();

    @Query("""
SELECT COUNT(p)
FROM Producto p
WHERE p.stock <= p.stockMinimo
AND p.activo = true
""")
    Long contarStockBajo();
}