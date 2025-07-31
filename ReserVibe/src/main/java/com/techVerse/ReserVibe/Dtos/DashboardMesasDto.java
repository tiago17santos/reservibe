package com.techVerse.ReserVibe.Dtos;

public class DashboardMesasDto {
    private long disponiveis;
    private long reservadas;
    private long inativas;

    public DashboardMesasDto(long disponiveis, long reservadas, long inativas) {
        this.disponiveis = disponiveis;
        this.reservadas = reservadas;
        this.inativas = inativas;
    }


}

