package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.MesasDto;
import com.techVerse.ReserVibe.Dtos.MesasResponseDto;
import com.techVerse.ReserVibe.Models.Mesas;
import com.techVerse.ReserVibe.Services.MesasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesasController {

    @Autowired
    private MesasService mesasService;

    @PostMapping()
    public ResponseEntity<MesasDto> criar(@RequestBody @Valid MesasDto mesasDto) {
        mesasDto = mesasService.criar(mesasDto);
        if (mesasDto != null) {
            return ResponseEntity.ok(mesasDto);
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping()
    public ResponseEntity<List<MesasResponseDto>> listar() {
        List<MesasResponseDto> mesas = mesasService.listarTodas();
        return ResponseEntity.ok(mesas);
    }
}
