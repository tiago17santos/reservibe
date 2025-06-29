package com.techVerse.ReserVibe.Dtos;

import com.techVerse.ReserVibe.Models.Mesa;
import com.techVerse.ReserVibe.Models.StatusMesa;

public class MesaResponseDto {
    private Long id;
    private String nome;
    private Integer capacidade;
    private StatusMesa status;

    public MesaResponseDto() {}

    public MesaResponseDto(long id, String nome, Integer capacidade, StatusMesa status) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.status = status;
    }

    public MesaResponseDto(Mesa mesa) {
        this.id = mesa.getId();
        this.nome = mesa.getNome();
        this.capacidade = mesa.getCapacidade();
        this.status = mesa.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }
}

