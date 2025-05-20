package modelo;
public class Habitacion {
    private int id;
    private String tipo;
    private double precio;
    private String estado; // Disponible, Ocupada, Mantenimiento

          // Constructor vacío
    public Habitacion() {
    }
    
    public Habitacion(int id, String tipo, double precio, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setEstado(String estado) { this.estado = estado; }
}
