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

    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (usuario, contrasena) VALUES (?, ?)";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, empleado.getUsuario());
            stmt.setString(2, empleado.getContraseña());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleado SET usuario = ?, contrasena = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getUsuario());
            stmt.setString(2, empleado.getContraseña());
            stmt.setInt(3, empleado.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object[][] listarEmpleados() {
        List<Object[]> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] datos = new Object[3];
                datos[0] = rs.getInt("id");
                datos[1] = rs.getString("usuario");
                datos[2] = rs.getString("contrasena");
                empleados.add(datos);
            }
            return empleados.toArray(new Object[empleados.size()][3]);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Object[0][0];
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        String sql = "SELECT * FROM empleado WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setContraseña(rs.getString("contrasena"));
                return empleado;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean verificarExistencia(String usuario) {
        String sql = "SELECT contrasena FROM empleado WHERE usuario = ?";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rta = stmt.executeQuery();
            if (rta.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public Empleado obtenerEmpleadoPorUsuario(String usuario) {
        String sql = "SELECT * FROM empleado WHERE usuario = ?";
        Empleado emp = null;

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT h.id AS idHabitacion, h.tipo, r.idCliente, c.usuario AS usuarioCliente, h.precio, r.estado "
                + "FROM habitacion h "
                + "JOIN reserva r ON h.id = r.idHabitacion "
                + "JOIN cliente c ON r.idCliente = c.id "
                + "WHERE h.estado = 'Ocupada' AND r.estado = 'Activa'";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
