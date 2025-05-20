/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

import modelo.Reserva;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.Habitacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DAOReserva {
    public boolean registrarReserva(Reserva reserva) {
    if (reserva.getFechaReserva() == null) {
        System.out.println("FechaReserva es null");
        return false;
    }
    if (reserva.getFechaEstadia() == null) {
        System.out.println("FechaEstadia es null");
        return false;
    }
    
    String sql = "INSERT INTO reserva (id_cliente, id_habitacion, fecha_reserva, fecha_estadia, precio_pagado, estado) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, reserva.getIdCliente());
        stmt.setInt(2, reserva.getIdHabitacion());
        stmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaReserva()));
        stmt.setDate(4, java.sql.Date.valueOf(reserva.getFechaEstadia()));
        stmt.setDouble(5, reserva.getPrecioPagado());
        stmt.setString(6, reserva.getEstado());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public List<Reserva> listarReservasPorCliente(int idCliente) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE id_cliente = ? ORDER BY fecha_reserva DESC";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setIdCliente(rs.getInt("id_cliente"));
                reserva.setIdHabitacion(rs.getInt("id_habitacion"));
                reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
                reserva.setFechaEstadia(rs.getDate("fecha_estadia").toLocalDate());
                reserva.setPrecioPagado(rs.getDouble("precio_pagado"));
                reserva.setEstado(rs.getString("estado"));
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public boolean cancelarReserva(int idReserva) {
        String sql = "UPDATE reserva SET estado = 'Cancelada' WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        String sql = "SELECT * FROM reserva WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setIdCliente(rs.getInt("id_cliente"));
                reserva.setIdHabitacion(rs.getInt("id_habitacion"));
                reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
                reserva.setFechaEstadia(rs.getDate("fecha_estadia").toLocalDate());
                reserva.setPrecioPagado(rs.getDouble("precio_pagado"));
                reserva.setEstado(rs.getString("estado"));
                return reserva;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Habitacion obtenerHabitacionPorId(int idHabitacion) {
    String sql = "SELECT * FROM habitacion WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Habitacion habitacion = new Habitacion();
            habitacion.setId(rs.getInt("id"));
            habitacion.setTipo(rs.getString("tipo"));
            habitacion.setPrecio(rs.getDouble("precio"));
            habitacion.setEstado(rs.getString("estado"));
            return habitacion;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean estaHabitacionReservada(int idHabitacion, LocalDate fechaEstadia) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE id_habitacion = ? AND fecha_estadia = ? AND estado = 'Activa'";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);
            stmt.setDate(2, java.sql.Date.valueOf(fechaEstadia));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Si cuenta es mayor que 0, está reservada
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Map<LocalDate, String> obtenerEstadosPorDias(int idHabitacion, LocalDate desde, LocalDate hasta) {
    Map<LocalDate, String> estadoDias = new HashMap<>();

    // Obtener estado general de la habitación
    Habitacion habitacion = obtenerHabitacionPorId(idHabitacion);
    String estadoGeneral = habitacion.getEstado();

    // Inicializamos todos los días con el estado general
    LocalDate dia = desde;
    while (!dia.isAfter(hasta)) {
        estadoDias.put(dia, estadoGeneral);
        dia = dia.plusDays(1);
    }

    // Consultar reservas activas de esa habitación en el rango de fechas
    String sql = "SELECT fecha_estadia FROM reserva WHERE id_habitacion = ? AND fecha_estadia BETWEEN ? AND ? AND estado = 'Activa'";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        stmt.setDate(2, java.sql.Date.valueOf(desde));
        stmt.setDate(3, java.sql.Date.valueOf(hasta));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            LocalDate fechaReservada = rs.getDate("fecha_estadia").toLocalDate();
            estadoDias.put(fechaReservada, "Reservada");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return estadoDias;
}
    public boolean verificarDisponibilidad(int idHabitacion, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE id_habitacion = ? AND fecha_estadia = ? AND estado <> 'Cancelada'";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);
            // Como en la base de datos tienes LocalDateTime, lo convertimos al inicio del día
            LocalDateTime inicioDia = fecha.atStartOfDay();
            stmt.setDate(2, java.sql.Date.valueOf(fecha));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // Si no hay reservas, está disponible
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Por defecto si falla la consulta, asumimos no disponible
        return false;
    }
    public Set<LocalDate> obtenerFechasOcupadas(int idHabitacion, int anio, int mes) {
    Set<LocalDate> fechasOcupadas = new HashSet<>();
    
    // Rango de fechas del mes especificado
    LocalDate inicioMes = LocalDate.of(anio, mes, 1);
    LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

    String sql = "SELECT fecha_estadia FROM reserva " +
                 "WHERE id_habitacion = ? " +
                 "AND fecha_estadia BETWEEN ? AND ? " +
                 "AND estado = 'Activa'";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        stmt.setDate(2, java.sql.Date.valueOf(inicioMes));
        stmt.setDate(3, java.sql.Date.valueOf(finMes));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            LocalDate fecha = rs.getDate("fecha_estadia").toLocalDate();
            fechasOcupadas.add(fecha);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return fechasOcupadas;
}
    public List<Reserva> listarHistorialPorCliente(int idCliente) {
    List<Reserva> reservas = new ArrayList<>();
    String sql = "SELECT r.*, h.numero FROM reserva r " +
                 "JOIN habitacion h ON r.id_habitacion = h.id " +
                 "WHERE r.id_cliente = ? ORDER BY r.fecha_reserva DESC";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCliente);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setIdCliente(rs.getInt("id_cliente"));
            reserva.setIdHabitacion(rs.getInt("id_habitacion"));
            reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
            reserva.setFechaEstadia(rs.getDate("fecha_estadia").toLocalDate());
            reserva.setPrecioPagado(rs.getDouble("precio_pagado"));
            reserva.setEstado(rs.getString("estado"));



            reservas.add(reserva);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservas;
}
    public int obtenerIdHabitacionPorReserva(int idReserva) {
    String sql = "SELECT id_habitacion FROM reserva WHERE id = ?";
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idReserva);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_habitacion");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Retorna -1 si no encontró la reserva o hubo error
}
    public List<Reserva> listarReservas() {
    List<Reserva> reservas = new ArrayList<>();
    String sql = "SELECT * FROM reserva WHERE estado = 'Activa' ORDER BY fecha_estadia ASC";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setIdCliente(rs.getInt("id_cliente"));
            reserva.setIdHabitacion(rs.getInt("id_habitacion"));
            reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
            reserva.setFechaEstadia(rs.getDate("fecha_estadia").toLocalDate());
            reserva.setPrecioPagado(rs.getDouble("precio_pagado"));
            reserva.setEstado(rs.getString("estado"));
            reservas.add(reserva);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservas;
}
    public List<LocalDate> obtenerDiasMantenimiento(int idHabitacion) {
    List<LocalDate> diasMantenimiento = new ArrayList<>();
    String sql = "SELECT fecha FROM mantenimiento WHERE id_habitacion = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            diasMantenimiento.add(rs.getDate("fecha").toLocalDate());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return diasMantenimiento;
}
    public List<LocalDate> obtenerDiasMantenimientos(int idHabitacion) {
    List<LocalDate> fechas = new ArrayList<>();
    String sql = "SELECT fecha FROM mantenimiento WHERE id_habitacion = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            fechas.add(rs.getDate("fecha").toLocalDate());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return fechas;
}
}
