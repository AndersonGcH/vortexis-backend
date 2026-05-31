package com.vortexis.repositories;

import com.vortexis.dto.ProductoMasVendidoDTO;
import com.vortexis.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleVentaRepository
        extends JpaRepository<DetalleVenta, Long> {

    @Query("""
SELECT new com.vortexis.dto.ProductoMasVendidoDTO(
    d.producto.nombre,
    SUM(d.cantidad)
)
FROM DetalleVenta d
GROUP BY d.producto.id, d.producto.nombre
ORDER BY SUM(d.cantidad) DESC
""")
    List<ProductoMasVendidoDTO> productosMasVendidos();
}
