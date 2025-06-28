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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MesasDto> criarMesa(@RequestBody @Valid MesasDto mesasDto) {
        mesasDto = mesasService.criarMesa(mesasDto);

        if (mesasDto != null) return ResponseEntity.ok(mesasDto);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<Page<MesasResponseDto>> listarTodasMesas(Pageable pageable,
                                                              @RequestParam(required = false) StatusMesa status,
                                                              @RequestParam(required = false) String nome,
                                                              @RequestParam(required = false) Integer capacidade) {

        Page<MesasResponseDto> mesas = mesasService.listarTodasMesas(status, nome, capacidade, pageable);
        return ResponseEntity.ok(mesas);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MesasResponseDto> atualizarMesa(@PathVariable Integer id,
                                                          @RequestBody @Valid MesasResponseDto mesasDto) {

        MesasResponseDto mesa = mesasService.atualizarMesa(id,mesasDto);

        if (mesa != null) return ResponseEntity.ok(mesa);

        return ResponseEntity.badRequest().build();
    }
}
