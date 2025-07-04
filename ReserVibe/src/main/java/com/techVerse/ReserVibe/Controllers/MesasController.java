package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.MesaDto;
import com.techVerse.ReserVibe.Dtos.MesaResponseDto;
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

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MesaDto> criarMesa(@RequestBody @Valid MesaDto mesaDto) {
        mesaDto = mesasService.criarMesa(mesaDto);

        if (mesaDto != null) return ResponseEntity.ok(mesaDto);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<Page<MesaResponseDto>> listarTodasMesas(Pageable pageable,
                                                                  @RequestParam(required = false) StatusMesa status,
                                                                  @RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) Integer capacidade) {

        Page<MesaResponseDto> mesas = mesasService.listarTodasMesas(status, nome, capacidade, pageable);
        return ResponseEntity.ok(mesas);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MesaResponseDto> atualizarMesa(@PathVariable Long id,
                                                         @RequestBody @Valid MesaResponseDto mesasDto) {

        MesaResponseDto mesa = mesasService.atualizarMesa(id,mesasDto);

        if (mesa != null) return ResponseEntity.ok(mesa);

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> excluirMesa(@PathVariable Long id) {
        String mesa = mesasService.deletarMesa(id);

        if (mesa != null) return ResponseEntity.ok(mesa);

        return ResponseEntity.badRequest().build();
    }
}
