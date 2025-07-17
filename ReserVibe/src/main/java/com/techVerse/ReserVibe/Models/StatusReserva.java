package com.techVerse.ReserVibe.Models;

import lombok.Getter;

@Getter
public enum StatusReserva {
    ativo("Ativa"),
    cancelado("Cancelada"),
    confirmada("confirmada"),
    expirada("expirada");

    private String descricao;

    private StatusReserva(String descricao) {
        this.descricao = descricao;
    }

}
