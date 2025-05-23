package dao;

import modelo.Administrador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOAdministrador {

    public boolean verificarExistencia(String usuario) {
        String sql = "SELECT contrasena FROM administrador WHERE usuario = ?";
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

    public boolean registrarAdministrador(Administrador admin) {
        String sql = "INSERT INTO administrador (usuario, contrasena) VALUES (?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getUsuario());
            stmt.setString(2, admin.getContraseña());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public Administrador obtenerAdministradorPorId(int id) {
        String sql = "SELECT * FROM administrador WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setContraseña(rs.getString("contrasena"));
                return admin;
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    public Object[][] listarAdministradores() {
        List<Object[]> admins = new ArrayList<>();
        String sql = "SELECT * FROM administrador";

        try (Connection conn = ConexionDB.obtenerConexion(); 
                PreparedStatement stmt = conn.prepareStatement(sql); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] datos = new Object[3];
                datos[0] = rs.getInt("id");
                datos[1] = rs.getString("usuario");
                datos[2] = rs.getString("contrasena");
                admins.add(datos);
            }
            return admins.toArray(new Object[admins.size()][3]);

        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return new Object[0][0];
    }

    public boolean actualizarAdministrador(Administrador admin) {
        String sql = "UPDATE administrador SET usuario = ?, contrasena = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getUsuario());
            stmt.setString(2, admin.getContraseña());
            stmt.setInt(3, admin.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAdministrador(int id) {
        String sql = "DELETE FROM administrador WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para login básico (opcional)
    public Administrador login(String usuario, String contraseña) {
        String sql = "SELECT * FROM administrador WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = ConexionDB.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Administrador admin = new Administrador();
                admin.setId(rs.getInt("id"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setContraseña(rs.getString("contrasena"));
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        
    }
}
