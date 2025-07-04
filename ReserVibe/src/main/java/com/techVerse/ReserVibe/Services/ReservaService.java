package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.ReservaDto;
import com.techVerse.ReserVibe.Models.Mesa;
import com.techVerse.ReserVibe.Models.Reserva;
import com.techVerse.ReserVibe.Models.StatusReserva;
import com.techVerse.ReserVibe.Models.Usuario;
import com.techVerse.ReserVibe.Repositories.MesaRepository;
import com.techVerse.ReserVibe.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MesaRepository mesaRepository;

    public ReservaDto criarReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();

        Usuario usuario = usuarioService.authenticated();
        reserva.setUsuario(usuario);

        reserva.setDataReserva(new Date());
        reserva.setStatusReserva(StatusReserva.ativo);

        Long mesaId = reservaDto.getMesa().getId();
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada com ID: " + mesaId));

        reserva.setMesa(mesa);

        return new ReservaDto(reservaRepository.save(reserva));

    }
}
