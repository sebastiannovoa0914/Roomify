/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Mauricio
 */
public class Empleado extends Usuario {
    public Empleado(int id, String usuario, String contraseña) {
        super(id, usuario, contraseña);
    }
public Empleado(){

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Menú del empleado: marcar mantenimiento, ver ocupadas...");
    }
}

