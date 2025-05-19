
package Controlador;


import modelo.Empleado;

public class EmpleadoControlador {

    public boolean validarLogin(String usuario, String contrasena) {
        return usuario != null && !usuario.isEmpty()
            && contrasena != null && !contrasena.isEmpty();
    }

    public String obtenerDatosEmpleado(Empleado emp) {
        return "Usuario: " + emp.getUsuario();
    }
}