package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.ReservaDto;
import com.techVerse.ReserVibe.Execptions.*;
import com.techVerse.ReserVibe.Models.*;
import com.techVerse.ReserVibe.Repositories.MesaRepository;
import com.techVerse.ReserVibe.Repositories.ReservaRepository;
import com.techVerse.ReserVibe.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public ReservaDto criarReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();

        Usuario usuario = usuarioService.authenticated();
        reserva.setUsuario(usuario);
        reserva.setStatusReserva(StatusReserva.ativo);

        Date data = reservaDto.getDataReserva();

        if (data.before(new Date())) {
            throw new DataInvalidaException("Inserir data da reserva para dias posteriores ao atual.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        if (hora < 18 || (hora == 23 && minuto > 30)) {
            throw new DataInvalidaException("As reservas só podem ser feitas entre 18h e 23:30h.");
        }

        reserva.setDataReserva(data);

        Long mesaId = reservaDto.getMesa().getId();
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new MesaNaoEncontradaException("Mesa não encontrada com ID: " + mesaId));

        if (mesa.getStatus() != StatusMesa.disponivel) {
            throw new MesaInvalidaException("Mesa não disponível para reserva.");
        }

        reserva.setMesa(mesa);
        atualizarStatusMesaComBaseNaReserva(reserva);
        return new ReservaDto(reservaRepository.save(reserva));
    }

    @Transactional
    public ReservaDto cancelaReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException("Mesa não encontrada!"));
        Long userId = usuarioRepository.findByEmail(getUsuarioLogado()).getId();

        if (userId.equals(reserva.getUsuario().getId())) {
            reserva.setStatusReserva(StatusReserva.cancelado);
            reserva = reservaRepository.save(reserva);

            atualizarStatusMesaComBaseNaReserva(reserva);
        } else {
            throw new CancelamentoInvalidoException("Selecione uma reserva válida para cancelar!");
        }

        return new ReservaDto(reserva);
    }

    public void atualizarStatusMesaComBaseNaReserva(Reserva reserva) {
        Mesa mesa = reserva.getMesa();

        if (reserva.getStatusReserva() == StatusReserva.ativo || reserva.getStatusReserva() == StatusReserva.confirmada) {
            mesa.setStatus(StatusMesa.reservada);
        } else {
            mesa.setStatus(StatusMesa.disponivel);
        }
    }

    public void confirmarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException("Reserva não encontrada!"));
        reserva.setStatusReserva(StatusReserva.confirmada);
        reserva.getMesa().setStatus(StatusMesa.reservada);
        reservaRepository.save(reserva);
    }

    public String getUsuarioLogado() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
