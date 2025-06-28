package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.MesasDto;
import com.techVerse.ReserVibe.Dtos.MesasResponseDto;
import com.techVerse.ReserVibe.Models.Mesas;
import com.techVerse.ReserVibe.Models.StatusMesa;
import com.techVerse.ReserVibe.Repositories.MesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesasService {

    @Autowired
    private MesasRepository mesasRepository;

    @Transactional
    public MesasDto criarMesa(MesasDto mesas) {
        Mesas mesa = new Mesas();
        mesa.setNome(mesas.getNome());
        mesa.setCapacidade(mesas.getCapacidade());
        mesa.setStatus(mesas.getStatus());
        mesa = mesasRepository.save(mesa);

        return new MesasDto(mesa);
    }

    @Transactional(readOnly = true)
    public Page<MesasResponseDto> listarTodasMesas(StatusMesa status, String nome, Integer capacidade, Pageable page) {
        Page<Mesas> resultado = mesasRepository.filtrarMesas(status, nome, capacidade, page);
        return resultado.map(x -> new MesasResponseDto(x));
    }

    @Transactional
    public MesasResponseDto atualizarMesa(Integer id, MesasResponseDto mesas) {
        try {
            Mesas mesa = mesasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

            if (mesas.getNome() != null) {
                String nome = mesas.getNome().trim();
                if (nome.isEmpty()) {
                    throw new IllegalArgumentException("Nome não pode ser vazio.");
                }
                mesa.setNome(mesas.getNome());
            }

            if (mesas.getCapacidade() != null) {
                if (mesas.getCapacidade() <= 0) {
                    throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
                }
                mesa.setCapacidade(mesas.getCapacidade());
            }

            if (mesas.getStatus() != null) {
                mesa.setStatus(mesas.getStatus());
            }

            mesa = mesasRepository.save(mesa);

            return new MesasResponseDto(mesa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar mesas", e);
        }
    }
}
