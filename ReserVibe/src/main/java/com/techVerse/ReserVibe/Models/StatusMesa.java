package com.techVerse.ReserVibe.Models;

public enum StatusMesa {
    disponivel("Disponível"),
    reservada("Reservada"),
    inativa("Inativa");

    private String descricao;

    private StatusMesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
