
package modelo;

/**
 *
 * @author Mauricio
 */
public class Administrador extends Usuario {
    public Administrador(int id, String usuario, String contraseña) {
        super(id, usuario, contraseña);
    }
    public Administrador (){
    
    }

    @Override
    public void mostrarMenu() {
        System.out.println("Menú del administrador: gestionar usuarios, habitaciones...");
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
    
}


