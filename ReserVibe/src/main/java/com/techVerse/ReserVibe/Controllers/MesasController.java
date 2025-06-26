package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.MesasDto;
import com.techVerse.ReserVibe.Dtos.MesasResponseDto;
import com.techVerse.ReserVibe.Models.StatusMesa;
import com.techVerse.ReserVibe.Services.MesasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<MesasDto> criar(@RequestBody @Valid MesasDto mesasDto) {
        mesasDto = mesasService.criar(mesasDto);
        if (mesasDto != null) {
            return ResponseEntity.ok(mesasDto);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping()
    public ResponseEntity<Page<MesasResponseDto>> listarTodas(Pageable pageable,
                                                              @RequestParam(required = false) StatusMesa status,
                                                              @RequestParam(required = false) String nome,
                                                              @RequestParam(required = false) Integer capacidade) {

        Page<MesasResponseDto> mesas = mesasService.listarTodas(status, nome, capacidade, pageable);
        return ResponseEntity.ok(mesas);
    }
}
