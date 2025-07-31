package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.DashboardMesasDto;
import com.techVerse.ReserVibe.Dtos.DashboardReservasDto;
import com.techVerse.ReserVibe.Models.StatusMesa;
import com.techVerse.ReserVibe.Models.StatusReserva;
import com.techVerse.ReserVibe.Repositories.MesaRepository;
import com.techVerse.ReserVibe.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public DashboardMesasDto gerarResumoMesas(){
        return new DashboardMesasDto(
                mesaRepository.countByMesaStatus(StatusMesa.disponivel),
                mesaRepository.countByMesaStatus(StatusMesa.reservada),
                mesaRepository.countByMesaStatus(StatusMesa.inativa)
        );
    }

    public DashboardReservasDto gerarResumoReservas(){
        return new DashboardReservasDto(
                reservaRepository.countByReservaStatus(StatusReserva.ativo),
                reservaRepository.countByReservaStatus(StatusReserva.confirmada),
                reservaRepository.countByReservaStatus(StatusReserva.cancelado),
                reservaRepository.countByReservaStatus(StatusReserva.expirada)
        );
    }
}
