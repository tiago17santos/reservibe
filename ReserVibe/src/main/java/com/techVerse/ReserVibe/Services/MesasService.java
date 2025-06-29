package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.MesaDto;
import com.techVerse.ReserVibe.Dtos.MesaResponseDto;
import com.techVerse.ReserVibe.Models.Mesa;
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
    public MesaDto criarMesa(MesaDto mesas) {
        Mesa mesa = new Mesa();
        mesa.setNome(mesas.getNome());
        mesa.setCapacidade(mesas.getCapacidade());
        mesa.setStatus(mesas.getStatus());
        mesa = mesasRepository.save(mesa);

        return new MesaDto(mesa);
    }

    @Transactional(readOnly = true)
    public Page<MesaResponseDto> listarTodasMesas(StatusMesa status, String nome, Integer capacidade, Pageable page) {
        Page<Mesa> resultado = mesasRepository.filtrarMesas(status, nome, capacidade, page);
        return resultado.map(x -> new MesaResponseDto(x));
    }

    @Transactional
    public MesaResponseDto atualizarMesa(Integer id, MesaResponseDto mesas) {
        try {
            Mesa mesa = mesasRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

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

            return new MesaResponseDto(mesa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar a mesa!", e);
        }
    }

    public String deletarMesa(Integer id) {
        try{
            Mesa mesa = mesasRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada!"));
            if (mesa.getStatus() == StatusMesa.inativa) {
                mesasRepository.deleteById(id);

                return "Mesa "+ id + "- " + mesa.getNome() + " deletada com sucesso!";
            }
            return "Mesa "+ id + "- " + mesa.getNome() + " está " + mesa.getStatus() + " e não pode ser deletada! ";

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a mesa!",e);
        }
    }
}
