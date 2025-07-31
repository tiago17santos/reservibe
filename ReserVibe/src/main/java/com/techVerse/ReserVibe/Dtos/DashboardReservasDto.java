package com.techVerse.ReserVibe.Dtos;

public class DashboardReservasDto {
    private long ativas;
    private long confirmadas;
    private long canceladas;
    private long expiradas;


    public DashboardReservasDto(long ativas, long confirmadas, long canceladas, long expiradas) {
        this.ativas = ativas;
        this.confirmadas = confirmadas;
        this.canceladas = canceladas;
        this.expiradas = expiradas;
    }


}

