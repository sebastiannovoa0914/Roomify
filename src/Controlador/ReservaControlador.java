
package Controlador;

import modelo.Reserva;

import modelo.Reserva;
import java.time.LocalDate;
import java.util.List;

public class ReservaControlador {

    // Verifica si una fecha ya tiene una reserva activa para una habitación específica
    public boolean estaReservada(List<Reserva> reservas, int idHabitacion, LocalDate fechaEstadia) {
        for (Reserva r : reservas) {
            if (r.getIdHabitacion() == idHabitacion &&
                r.getFechaEstadia().equals(fechaEstadia) &&
                r.getEstado().equalsIgnoreCase("Activa")) {
                return true;
            }
        }
        return false;
    }

    // Cambia el estado de una reserva a "Cancelada"
    public void cancelarReserva(Reserva reserva) {
        reserva.setEstado("Cancelada");
    }

    // Valida si una reserva es válida antes de crearla
    public boolean validarReserva(Reserva reserva) {
        return reserva.getIdCliente() > 0 &&
               reserva.getIdHabitacion() > 0 &&
               reserva.getFechaReserva() != null &&
               reserva.getFechaEstadia() != null &&
               reserva.getPrecioPagado() > 0 &&
               (reserva.getEstado() != null && !reserva.getEstado().isEmpty());
    }
}