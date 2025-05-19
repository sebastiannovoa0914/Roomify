/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

import modelo.Habitacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOHabitacion {
    public List<Habitacion> listarHabitaciones() {
    List<Habitacion> habitaciones = new ArrayList<>();
    String sql = "SELECT * FROM habitacion";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Habitacion hab = new Habitacion();
            hab.setId(rs.getInt("id"));
            hab.setTipo(rs.getString("tipo"));
            hab.setPrecio(rs.getDouble("precio"));
            hab.setEstado(rs.getString("estado"));
            habitaciones.add(hab);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return habitaciones;
}
    public Habitacion obtenerPorId(int id) {
    String sql = "SELECT * FROM habitacion WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Habitacion hab = new Habitacion();
            hab.setId(rs.getInt("id"));
            hab.setTipo(rs.getString("tipo"));
            hab.setPrecio(rs.getDouble("precio"));
            hab.setEstado(rs.getString("estado"));
            return hab;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public boolean actualizarEstado(int id, String estado) {
    String sql = "UPDATE habitacion SET estado = ? WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, estado);
        stmt.setInt(2, id);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean agregarHabitacion(Habitacion habitacion) {
    String sql = "INSERT INTO habitacion (tipo, precio, estado) VALUES (?, ?, ?)";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, habitacion.getTipo());
        stmt.setDouble(2, habitacion.getPrecio());
        stmt.setString(3, habitacion.getEstado());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean eliminarHabitacion(int id) {
    String sql = "DELETE FROM habitacion WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public List<Habitacion> listarHabitacionesPorEstado(String estado) {
    List<Habitacion> habitaciones = new ArrayList<>();
    String sql = "SELECT * FROM habitacion WHERE estado = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, estado);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Habitacion hab = new Habitacion();
            hab.setId(rs.getInt("id"));
            hab.setTipo(rs.getString("tipo"));
            hab.setPrecio(rs.getDouble("precio"));
            hab.setEstado(rs.getString("estado"));
            habitaciones.add(hab);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return habitaciones;
}
    public boolean actualizarHabitacion(Habitacion habitacion) {
    String sql = "UPDATE habitacion SET tipo = ?, precio = ?, estado = ? WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, habitacion.getTipo());
        stmt.setDouble(2, habitacion.getPrecio());
        stmt.setString(3, habitacion.getEstado());
        stmt.setInt(4, habitacion.getId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
