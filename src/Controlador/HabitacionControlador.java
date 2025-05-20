
package Controlador;


import modelo.Habitacion;

import java.util.List;

public class HabitacionControlador {

    // Valida que una habitación tenga datos correctos antes de registrarse o actualizarse
    public boolean validarHabitacion(Habitacion hab) {
    return hab.getId() > 0
        && hab.getTipo() != null && !hab.getTipo().isEmpty()
        && hab.getPrecio() > 0
        && hab.getEstado() != null && !hab.getEstado().isEmpty();
}

    // Cambiar estado de la habitación a Mantenimiento, Disponible u Ocupada
    public void cambiarEstado(Habitacion hab, String nuevoEstado) {
        if (nuevoEstado.equalsIgnoreCase("Disponible") ||
            nuevoEstado.equalsIgnoreCase("Ocupada") ||
            nuevoEstado.equalsIgnoreCase("Mantenimiento")) {
            hab.setEstado(nuevoEstado);
        }
    }

    // Filtrar habitaciones ocupadas (útil para empleados)
    public List<Habitacion> obtenerHabitacionesOcupadas(List<Habitacion> habitaciones) {
        return habitaciones.stream()
                .filter(h -> h.getEstado().equalsIgnoreCase("Ocupada"))
                .toList();
    }

    // Verifica si una habitación está disponible
    public boolean estaDisponible(Habitacion hab) {
        return hab.getEstado().equalsIgnoreCase("Disponible");
    }
}
