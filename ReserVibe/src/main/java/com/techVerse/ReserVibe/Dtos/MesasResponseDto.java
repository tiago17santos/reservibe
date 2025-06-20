package com.techVerse.ReserVibe.Dtos;

import com.techVerse.ReserVibe.Models.Mesas;

public class MesasResponseDto {
    private Long id;
    private String nome;
    private Integer capacidade;
    private String status; // aqui já vem o valor legível

    public MesasResponseDto(Mesas mesa) {
        this.id = mesa.getId();
        this.nome = mesa.getNome();
        this.capacidade = mesa.getCapacidade();
        this.status = mesa.getStatus().getDescricao(); // transforma DISPONIVEL → Disponível
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

