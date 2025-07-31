package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.DashboardMesasDto;
import com.techVerse.ReserVibe.Dtos.DashboardReservasDto;
import com.techVerse.ReserVibe.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/mesas")
    public ResponseEntity<DashboardMesasDto> mesas() {
        return ResponseEntity.ok(dashboardService.gerarResumoMesas());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/reservas")
    public ResponseEntity<DashboardReservasDto> reservas() {
        return ResponseEntity.ok(dashboardService.gerarResumoReservas());
    }
}
