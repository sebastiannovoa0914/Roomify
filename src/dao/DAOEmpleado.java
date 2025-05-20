
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Empleado;
import modelo.Habitacion;

public class DAOEmpleado {
    // Método para obtener un empleado por su usuario (login)
    public Empleado obtenerEmpleadoPorUsuario(String usuario) {
        String sql = "SELECT * FROM empleado WHERE usuario = ?";
        Empleado emp = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setUsuario(rs.getString("usuario"));
                emp.setContraseña(rs.getString("contrasena"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    // Método para listar todas las habitaciones usando HabitacionDAO
    public List<Habitacion> listarHabitaciones() {
        DAOHabitacion habitacionDAO = new DAOHabitacion();
        return habitacionDAO.listarHabitaciones();
    }

    // Método para actualizar el estado de una habitación (Disponible, Ocupada, Mantenimiento)
    public boolean actualizarEstadoHabitacion(int idHabitacion, String estado) {
        DAOHabitacion habitacionDAO = new DAOHabitacion();
        return habitacionDAO.actualizarEstado(idHabitacion, estado);
    }

    // Método para listar habitaciones ocupadas con info del cliente que reservó
    public List<Map<String, Object>> listarHabitacionesOcupadas() {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT h.id AS idHabitacion, h.tipo, r.idCliente, c.usuario AS usuarioCliente, h.precio, r.estado " +
                     "FROM habitacion h " +
                     "JOIN reserva r ON h.id = r.idHabitacion " +
                     "JOIN cliente c ON r.idCliente = c.id " +
                     "WHERE h.estado = 'Ocupada' AND r.estado = 'Activa'";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> registro = new HashMap<>();
                registro.put("idHabitacion", rs.getInt("idHabitacion"));
                registro.put("tipo", rs.getString("tipo"));
                registro.put("idCliente", rs.getInt("idCliente"));
                registro.put("usuarioCliente", rs.getString("usuarioCliente"));
                registro.put("precio", rs.getDouble("precio"));
                registro.put("estado", rs.getString("estado"));
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
