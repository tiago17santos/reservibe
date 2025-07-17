package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.ReservaDto;
import com.techVerse.ReserVibe.Models.Reserva;
import com.techVerse.ReserVibe.Services.ReservaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ReservaDto> criarReserva(@RequestBody  ReservaDto reservaDto) {
        reservaDto = reservaService.criarReserva(reservaDto);
        if(reservaDto != null) {
            return ResponseEntity.ok(reservaDto);
        }
        return ResponseEntity.badRequest().build();
    }



}
