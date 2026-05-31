package com.vortexis.services;
import com.vortexis.dto.ReporteInventarioDTO;
import com.vortexis.entities.Categoria;
import com.vortexis.entities.Producto;
import com.vortexis.repositories.CategoriaRepository;
import com.vortexis.repositories.ProductoRepository;
import com.vortexis.repositories.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Categoria obtenerCategoria(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada"));
    }

    public ReporteInventarioDTO obtenerReporteInventario() {

        Long totalProductos =
                productoRepository.contarProductos();

        Long stockTotal =
                productoRepository.stockTotal();

        Long stockBajo =
                productoRepository.contarStockBajo();

        return new ReporteInventarioDTO(
                totalProductos,
                stockTotal,
                stockBajo
        );
    }
}
