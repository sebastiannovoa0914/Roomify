
package Controlador;

import modelo.Cliente;

public class ClienteControlador {

    public boolean validarRegistro(Cliente cliente) {
        return cliente.getUsuario() != null && !cliente.getUsuario().isEmpty()
            && cliente.getCedula() != null && !cliente.getCedula().isEmpty()
            && cliente.getCorreo() != null && !cliente.getCorreo().isEmpty()
            && cliente.getContraseña() != null && !cliente.getContraseña().isEmpty();
    }

    public boolean validarLogin(String usuario, String contrasena) {
        return usuario != null && !usuario.isEmpty()
            && contrasena != null && !contrasena.isEmpty();
    }

    // Mostrar info del cliente (puede usarse para un JOptionPane)
    public String obtenerDatosCliente(Cliente cliente) {
        return "Usuario: " + cliente.getUsuario() + "\n" +
               "Cédula: " + cliente.getCedula() + "\n" +
               "Correo: " + cliente.getCorreo();
    }
}
