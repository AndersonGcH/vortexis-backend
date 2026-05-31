package com.vortexis.controllers;

import com.vortexis.dto.ProductoMasVendidoDTO;
import com.vortexis.dto.ReporteVentasDTO;
import com.vortexis.services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final VentaService ventaService;

    @GetMapping("/ventas")
    public ReporteVentasDTO ventas() {

        return ventaService.obtenerReporteVentas();
    }

    @GetMapping("/productos-mas-vendidos")
    public List<ProductoMasVendidoDTO> productosMasVendidos() {

        return ventaService
                .obtenerProductosMasVendidos();
    }
}
