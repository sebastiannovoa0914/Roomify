/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms.Empleado;

import Forms.CalendarioForm;
import Forms.CalendarioForm;
import Forms.CalendarioFormEmpleado;
import Forms.FormPrincipal;
import dao.DAOHabitacion;
import dao.DAOReserva;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.Habitacion;
import modelo.Reserva;
import java.awt.event.MouseAdapter;

public class PanelEmpleado extends javax.swing.JFrame {

    private Empleado empleado;
    private JTable tablaReservadas;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu menuOpciones2;
    private JMenu menuOpciones;
    private JMenuItem itemCerrar;
    private JMenuItem itemVerHabitaciones;
    private JMenuItem itemVerReservadas;

    public PanelEmpleado(Empleado empleado) {
        this.empleado = empleado;
        initComponentes();
        setLocationRelativeTo(null);

    }

    private void initComponentes() {
        setTitle("Panel de Empleado");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menú
        menuBar = new JMenuBar();
        menuOpciones = new JMenu("Opciones");
        menuOpciones2 = new JMenu("Cerrar Sesión");
        itemCerrar = new JMenuItem("Cerrar Sesión");
        itemVerHabitaciones = new JMenuItem("Ver Habitaciones");
        itemVerReservadas = new JMenuItem("Ver Habitaciones Reservadas");

        menuOpciones.add(itemVerHabitaciones);
        menuOpciones.add(itemVerReservadas);
        menuOpciones2.add(itemCerrar);
        menuBar.add(menuOpciones);
        menuBar.add(menuOpciones2);
        setJMenuBar(menuBar);

        // Tabla vacía por defecto
        tablaReservadas = new JTable();
        scrollPane = new JScrollPane(tablaReservadas);
        add(scrollPane, BorderLayout.CENTER);

        // Acciones
        itemVerReservadas.addActionListener(e -> mostrarHabitacionesReservadas());
        itemVerHabitaciones.addActionListener(e -> mostrarHabitaciones());
        itemCerrar.addActionListener(e -> {
            new FormPrincipal().setVisible(true);
            this.dispose();
        });
    }

    private void mostrarHabitacionesReservadas() {
        DAOReserva daoReserva = new DAOReserva();
        List<Reserva> reservas = daoReserva.listarReservas(); // este método debe existir

        String[] columnas = {"ID Reserva", "ID Cliente", "ID Habitación", "Fecha Ingreso", "Fecha Salida", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Reserva r : reservas) {
            Object[] fila = {
                r.getId(),
                r.getIdCliente(),
                r.getIdHabitacion(),
                r.getFechaReserva(),
                r.getFechaEstadia(),
                r.getEstado()
            };
            modelo.addRow(fila);
        }

        tablaReservadas.setModel(modelo);
    }

    private void mostrarHabitaciones() {
        DAOHabitacion daoHabitacion = new DAOHabitacion();
        List<Habitacion> habitaciones = daoHabitacion.listarHabitaciones();

        String[] columnas = {"ID", "Tipo", "Precio", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Habitacion h : habitaciones) {
            Object[] fila = {
                h.getId(),
                h.getTipo(),
                h.getPrecio(),
                h.getEstado()
            };
            modelo.addRow(fila);
        }

        tablaReservadas.setModel(modelo);

        // Agregar listener de clic para abrir el calendario
        tablaReservadas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // doble clic
                    int fila = tablaReservadas.getSelectedRow();
                    if (fila != -1) {
                        int idHabitacion = (int) tablaReservadas.getValueAt(fila, 0);

                        // Mostrar el CalendarioForm en modo empleado
                        CalendarioForm calendario = new CalendarioForm(idHabitacion, true);
                        calendario.setVisible(true);
                    }
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHabitaciones = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaHabitaciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 770, 260));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHabitaciones;
    // End of variables declaration//GEN-END:variables
}
