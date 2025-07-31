package com.techVerse.ReserVibe.Dtos;

import com.techVerse.ReserVibe.Models.Mesa;
import com.techVerse.ReserVibe.Models.Reserva;
import com.techVerse.ReserVibe.Models.StatusReserva;

import java.util.Date;

public class ReservaDto {

    private long id;

    private Date dataReserva;

    private StatusReserva statusReserva;

    private UsuarioDto usuario;

    private Mesa mesa;

    public ReservaDto() {
    }

    public ReservaDto(Date dataReserva, StatusReserva statusReserva, UsuarioDto usuario, Mesa mesa) {
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
        this.usuario = usuario;
        this.mesa = mesa;
    }

    public ReservaDto(Reserva reserva) {
        this.id = reserva.getId();
        this.dataReserva = reserva.getDataReserva();
        this.statusReserva = reserva.getStatusReserva();
        this.usuario = new UsuarioDto(reserva.getUsuario());
        this.mesa = reserva.getMesa();
    }

    public long getId() {
        return id;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
