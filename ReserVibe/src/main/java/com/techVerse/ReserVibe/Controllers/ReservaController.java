package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.ReservaDto;
import com.techVerse.ReserVibe.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping()
    public ResponseEntity<ReservaDto> criarReserva(@RequestBody ReservaDto reservaDto) {
        reservaDto = reservaService.criarReserva(reservaDto);
        if (reservaDto != null) {
            return ResponseEntity.ok(reservaDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<ReservaDto> cancelaReserva(@PathVariable Long id) {
        ReservaDto reservaDto = reservaService.cancelaReserva(id);

        if (reservaDto != null) {
            return ResponseEntity.ok(reservaDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/confirmar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> confirmarReserva(@PathVariable Long id) {
        reservaService.confirmarReserva(id);
        return ResponseEntity.ok().build();
    }


}
