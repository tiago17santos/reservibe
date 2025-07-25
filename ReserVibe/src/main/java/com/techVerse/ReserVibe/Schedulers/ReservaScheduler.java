package com.techVerse.ReserVibe.Schedulers;

import com.techVerse.ReserVibe.Models.Reserva;
import com.techVerse.ReserVibe.Models.StatusMesa;
import com.techVerse.ReserVibe.Models.StatusReserva;
import com.techVerse.ReserVibe.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservaScheduler {

    @Autowired
    private ReservaRepository reservaRepository;

    @Scheduled(fixedRate = 600000) // a cada 10 minutos
    public void verificarReservasExpiradas() {
        List<Reserva> reservas = reservaRepository.findReservasAtivasComHorarioPassado(LocalDateTime.now());
        for (Reserva r : reservas) {
            r.setStatusReserva(StatusReserva.expirada);
            r.getMesa().setStatus(StatusMesa.disponivel);
            reservaRepository.save(r);
        }
    }
}

