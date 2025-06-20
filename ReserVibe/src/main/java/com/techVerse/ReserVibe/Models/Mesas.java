package com.techVerse.ReserVibe.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="mesas")
@EqualsAndHashCode(of = "id")
public class Mesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int capacidade;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    public Mesas() {}

    public Mesas (long id, String nome, int capacidade, StatusMesa status) {
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
