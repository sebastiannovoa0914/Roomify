/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Mauricio
 */
public class Cliente extends Usuario{
        private String correo;
    private String cedula;

 // Constructor vacío (sin parámetros)
    public Cliente() {
    }
    

    public Cliente(int id, String usuario, String cedula, String correo, String contraseña) {
        super(id, usuario, contraseña);
        this.cedula = cedula;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public void mostrarMenu() {
        // implementación si deseas mostrar el menú del cliente
    }
}
