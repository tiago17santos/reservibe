package com.techVerse.ReserVibe.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "mesas")
@EqualsAndHashCode(of = "id")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    public Mesa() {
    }

    public Mesa(long id, String nome, Integer capacidade, StatusMesa status) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.status = status;
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
