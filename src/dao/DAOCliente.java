package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCliente {

    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (usuario, cedula, correo, contrasena) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getUsuario());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getContraseña());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente login(String usuario, String contrasena) {
        String sql = "SELECT * FROM cliente WHERE usuario = ? AND contrasena = ?";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setContraseña(rs.getString("contrasena"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setContraseña(rs.getString("contrasena"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET usuario = ?, cedula = ?, correo = ?, contrasena = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getUsuario());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getContraseña());
            stmt.setInt(5, cliente.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCliente(int cedula) {
        String sql = "DELETE FROM cliente WHERE cedula = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cedula);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object[][] listarClientes() {
        List<Object[]> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] datos = new Object[4];
                datos[0] = rs.getInt("cedula");
                datos[1] = rs.getString("usuario");
                datos[2] = rs.getString("contrasena");
                datos[3] = rs.getString("correo");
                clientes.add(datos);
            }
            return clientes.toArray(new Object[clientes.size()][4]);

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return new Object[0][0];
    }

    public boolean existeUsuarioOCedula(String usuario, String cedula) {
        String sql = "SELECT * FROM cliente WHERE usuario = ? OR cedula = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, cedula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    public Cliente obtenerPorCedula(String cedula) {
        String sql = "SELECT * FROM cliente WHERE cedula = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setContraseña(rs.getString("contrasena"));
                return cliente;
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());;
        }
        return null;
    }
}
