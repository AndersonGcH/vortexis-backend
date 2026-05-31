package com.vortexis.controllers;

import com.vortexis.dto.DashboardAdminDTO;
import com.vortexis.dto.DashboardAlmaceneroDTO;
import com.vortexis.dto.DashboardVendedorDTO;
import com.vortexis.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ReporteService reporteService;

    @GetMapping("/admin")
    public DashboardAdminDTO admin() {

        return reporteService.dashboardAdmin();
    }

    @GetMapping("/almacenero")
    public DashboardAlmaceneroDTO almacenero() {

        return reporteService
                .dashboardAlmacenero();
    }

    @GetMapping("/vendedor/{usuarioId}")
    public DashboardVendedorDTO vendedor(
            @PathVariable Long usuarioId
    ) {

        return reporteService
                .dashboardVendedor(usuarioId);
    }
}
