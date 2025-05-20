

package controlador;

import modelo.Administrador;

public class AdministradorControlador {

    public boolean validarLogin(String usuario, String contrasena) {
        return usuario != null && !usuario.isEmpty()
            && contrasena != null && !contrasena.isEmpty();
    }

    public String obtenerDatosAdministrador(Administrador admin) {
        return "Administrador: " + admin.getUsuario();
    }
}