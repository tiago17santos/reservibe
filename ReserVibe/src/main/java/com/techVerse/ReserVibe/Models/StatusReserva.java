package com.techVerse.ReserVibe.Models;

import lombok.Getter;

@Getter
public enum StatusReserva {
    ativo("Ativa"),
    cancelado("Cancelada");

    private String descricao;

    private StatusReserva(String descricao) {
        this.descricao = descricao;
    }

}
