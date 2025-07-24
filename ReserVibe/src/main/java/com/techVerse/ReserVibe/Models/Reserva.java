package com.techVerse.ReserVibe.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Entity
@Table(name = "reservas")
@EqualsAndHashCode(of = "id")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dataReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reserva")
    private StatusReserva statusReserva;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    public Reserva() {
    }

    public Reserva(Date dataReserva, StatusReserva statusReserva, Usuario usuario, Mesa mesa) {
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
        this.usuario = usuario;
        this.mesa = mesa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setStatusReserva(StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMesa(Mesa mesas) {
        this.mesa = mesas;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
