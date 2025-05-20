/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private int idCliente;
    private int idHabitacion;
    private LocalDate fechaReserva;   // fecha cuando se hizo la reserva
    private LocalDate fechaEstadia;   // fecha real de la estadía
    private double precioPagado;
    private String estado;            // Activa o Cancelada
     private String usuarioCliente; // Nuevo campo opcional

    // Constructor vacío
    public Reserva() {}

    // Constructor completo
    public Reserva(int id, int idCliente, int idHabitacion, LocalDate fechaReserva, LocalDate fechaEstadia, double precioPagado, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaReserva = fechaReserva;
        this.fechaEstadia = fechaEstadia;
        this.precioPagado = precioPagado;
        this.estado = estado;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }
    

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public LocalDate getFechaEstadia() { return fechaEstadia; }
    public void setFechaEstadia(LocalDate fechaEstadia) { this.fechaEstadia = fechaEstadia; }

    public double getPrecioPagado() { return precioPagado; }
    public void setPrecioPagado(double precioPagado) { this.precioPagado = precioPagado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
