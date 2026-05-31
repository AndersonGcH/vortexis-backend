package com.vortexis.controllers;

import com.vortexis.dto.DashboardAdminDTO;
import com.vortexis.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
}
