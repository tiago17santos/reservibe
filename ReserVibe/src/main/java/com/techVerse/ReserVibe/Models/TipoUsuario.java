package com.techVerse.ReserVibe.Models;

public enum TipoUsuario {
    ADMIN("admin"),
    CLIENTE("cliente");

    private String tipo;

    private TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
