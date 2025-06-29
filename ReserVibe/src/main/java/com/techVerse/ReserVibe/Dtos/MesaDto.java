package com.techVerse.ReserVibe.Dtos;

import com.techVerse.ReserVibe.Models.Mesa;
import com.techVerse.ReserVibe.Models.StatusMesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
public class MesaDto {

    private long id;

    @NotBlank
    private String nome;

    @NotNull
    private Integer capacidade;

    @NotNull
    private StatusMesa status;

    public MesaDto() {
    }

    public MesaDto(String nome, Integer capacidade, StatusMesa status) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.status = status;
    }

    public MesaDto(Mesa mesa) {
        this.nome = mesa.getNome();
        this.capacidade = mesa.getCapacidade();
        this.status = mesa.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
