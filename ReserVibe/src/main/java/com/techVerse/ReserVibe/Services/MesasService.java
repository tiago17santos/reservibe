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

    @Transactional()
    public MesasDto criar(MesasDto mesas) {
        Mesas mesa = new Mesas();
        mesa.setNome(mesas.getNome());
        mesa.setCapacidade(mesas.getCapacidade());
        mesa.setStatus(mesas.getStatus());
        mesa = mesasRepository.save(mesa);

        return new MesasDto(mesa);
    }

    @Transactional(readOnly = true)
    public Page<MesasResponseDto> listarTodas(StatusMesa status, String nome, Integer capacidade, Pageable page) {
        Page<Mesas> resultado = mesasRepository.filtrarMesas(status, nome, capacidade, page);
        return resultado.map(x -> new MesasResponseDto(x));
    }

}
