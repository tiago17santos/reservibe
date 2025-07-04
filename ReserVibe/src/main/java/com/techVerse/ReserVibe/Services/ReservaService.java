package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.ReservaDto;
import com.techVerse.ReserVibe.Execptions.DataInvalidaException;
import com.techVerse.ReserVibe.Execptions.MesaInvalidaException;
import com.techVerse.ReserVibe.Execptions.MesaNaoEncontradaException;
import com.techVerse.ReserVibe.Models.*;
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
        reserva.setStatusReserva(StatusReserva.ativo);


        Date data = reservaDto.getDataReserva();

        if(data.before(new Date())) {
            throw new DataInvalidaException("Inserir data da reserva para dias posteriores ao atual.");
        }
        reserva.setDataReserva(data);

        Long mesaId = reservaDto.getMesa().getId();
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new MesaNaoEncontradaException("Mesa não encontrada com ID: " + mesaId));

        if (mesa.getStatus() != StatusMesa.disponivel){
            throw new MesaInvalidaException("Mesa não disponível para reserva.");
        }

        reserva.setMesa(mesa);

        return new ReservaDto(reservaRepository.save(reserva));

    }
}
