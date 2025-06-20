package com.techVerse.ReserVibe.Repositories;

import com.techVerse.ReserVibe.Models.Mesas;
import com.techVerse.ReserVibe.Models.StatusMesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MesasRepository extends JpaRepository<Mesas, Integer> {

    @Query("""
        SELECT m FROM Mesas m
        WHERE (:status IS NULL OR m.status = :status)
          AND (:nome IS NULL OR UPPER(m.nome) LIKE UPPER(CONCAT('%', :nome, '%')))
          AND (:capacidade IS NULL OR m.capacidade = :capacidade)
    """)
    Page<Mesas> filtrarMesas(@Param("status") StatusMesa status,
                             @Param("nome") String nome,
                             @Param("capacidade") Integer capacidade,
                             Pageable pageable);



}
