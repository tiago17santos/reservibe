package com.techVerse.ReserVibe.Repositories;

import com.techVerse.ReserVibe.Models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
