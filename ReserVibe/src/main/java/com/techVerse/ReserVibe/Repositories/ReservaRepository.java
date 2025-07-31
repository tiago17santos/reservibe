package com.techVerse.ReserVibe.Repositories;

import com.techVerse.ReserVibe.Models.Reserva;
import com.techVerse.ReserVibe.Models.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.statusReserva = 'ativo' AND r.dataReserva < :agora")
    List<Reserva> findReservasAtivasComHorarioPassado(@Param("agora") LocalDateTime agora);

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.statusReserva = :status")
    long countByReservaStatus(@Param("status") StatusReserva status);
}
