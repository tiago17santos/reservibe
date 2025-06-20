package com.techVerse.ReserVibe.Dtos;

import com.techVerse.ReserVibe.Models.Mesas;
import com.techVerse.ReserVibe.Models.StatusMesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of="id")
public class MesasDto {

    private long id;

    @NotBlank
    private String nome;

    private int capacidade;

    @NotNull
    private StatusMesa status;

    public MesasDto() {}

    public MesasDto( String nome, int capacidade, StatusMesa status) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.status = status;
    }

    public MesasDto(Mesas mesas) {
        this.nome = mesas.getNome();
        this.capacidade = mesas.getCapacidade();
        this.status = mesas.getStatus();
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

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }
}
