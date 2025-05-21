package Forms;

import dao.DAOReserva;
import dao.DAOHabitacion;
import modelo.Cliente;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.List;

public class CalendarioForm extends JFrame {

    private int idHabitacion;
    private Cliente cliente;
    private DAOReserva daoReserva;
    private DAOHabitacion daoHabitacion;
    private boolean esEmpleado = false;

    private YearMonth mesActual;
    private JPanel panelDias;
    private JLabel labelMesAnio;
    private JButton btnMesAnterior;
    private JButton btnMesSiguiente;
    private JLabel labelTotalDias;
    private JLabel labelTotalPagar;

    private Set<LocalDate> diasSeleccionados = new HashSet<>();
    private double precioPorDia;

    // Constructor para cliente
    public CalendarioForm(int idHabitacion, Cliente cliente, double precioPorDia) {
        this.idHabitacion = idHabitacion;
        this.cliente = cliente;
        this.precioPorDia = precioPorDia;
        this.daoReserva = new DAOReserva();
        this.daoHabitacion = new DAOHabitacion();
        this.mesActual = YearMonth.now();

        setTitle("Reservar habitación ID: " + idHabitacion);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponentes();
        refrescarCalendario();
    }

    public CalendarioForm(int idHabitacion, boolean esEmpleado) {
        this.idHabitacion = idHabitacion;
        this.daoReserva = new DAOReserva();
        this.daoHabitacion = new DAOHabitacion();
        this.mesActual = YearMonth.now();
        this.esEmpleado = esEmpleado;

        setTitle("Calendario de habitación ID: " + idHabitacion);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponentes();
        refrescarCalendario();
    }

    private void initComponentes() {
        this.setLayout(new BorderLayout());

        JPanel panelNavegacion = new JPanel(new BorderLayout());
        btnMesAnterior = new JButton("<");
        btnMesSiguiente = new JButton(">");
        labelMesAnio = new JLabel("", SwingConstants.CENTER);

        btnMesAnterior.addActionListener(e -> {
            mesActual = mesActual.minusMonths(1);
            refrescarCalendario();
        });

        btnMesSiguiente.addActionListener(e -> {
            mesActual = mesActual.plusMonths(1);
            refrescarCalendario();
        });

        panelNavegacion.add(btnMesAnterior, BorderLayout.WEST);
        panelNavegacion.add(labelMesAnio, BorderLayout.CENTER);
        panelNavegacion.add(btnMesSiguiente, BorderLayout.EAST);

        this.add(panelNavegacion, BorderLayout.NORTH);

        panelDias = new JPanel(new GridLayout(0, 7));
        this.add(panelDias, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new GridLayout(4, 1));
        labelTotalDias = new JLabel("Días seleccionados: 0");
        labelTotalPagar = new JLabel("Total a pagar: $0.00");

        JButton btnReservar = new JButton("Confirmar Reserva");
        btnReservar.addActionListener(e -> {
            confirmarReserva();
            new Pago().setVisible(true);
        });

        // Aquí justo después de crear los componentes, ocultamos si es empleado:
        if (esEmpleado) {
            btnReservar.setVisible(false);
            labelTotalDias.setVisible(false);
            labelTotalPagar.setVisible(false);
        }

        panelInferior.add(labelTotalDias);
        panelInferior.add(labelTotalPagar);
        panelInferior.add(btnReservar);

        this.add(panelInferior, BorderLayout.SOUTH);
        if (esEmpleado) {
            JButton btnMantenimiento = new JButton("Opciones de Mantenimiento");
            btnMantenimiento.addActionListener(e -> mostrarOpcionesMantenimiento());
            panelInferior.add(btnMantenimiento);
        }
    }

    private void refrescarCalendario() {
        panelDias.removeAll();

        labelMesAnio.setText(
                mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")) + " " + mesActual.getYear()
        );

        String[] diasSemana = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
        for (String dia : diasSemana) {
            JLabel lbl = new JLabel(dia, SwingConstants.CENTER);
            lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
            panelDias.add(lbl);
        }

        // Obtener fechas ocupadas (reservas) y fechas en mantenimiento
        Set<LocalDate> fechasOcupadas = daoReserva.obtenerFechasOcupadas(idHabitacion, mesActual.getYear(), mesActual.getMonthValue());
        List<LocalDate> diasMantenimiento = daoReserva.obtenerDiasMantenimientos(idHabitacion);

        LocalDate primerDiaMes = mesActual.atDay(1);
        int diaSemanaInicio = primerDiaMes.getDayOfWeek().getValue();

        // Agrega espacios vacíos antes del primer día del mes
        for (int i = 0; i < diaSemanaInicio - 1; i++) {
            panelDias.add(new JLabel(""));
        }

        int diasEnMes = mesActual.lengthOfMonth();
        for (int dia = 1; dia <= diasEnMes; dia++) {
            LocalDate fecha = mesActual.atDay(dia);
            JButton btnDia = new JButton(String.valueOf(dia));
            btnDia.setMargin(new Insets(2, 2, 2, 2));

            if (fecha.isBefore(LocalDate.now())) {
                btnDia.setEnabled(false);
                btnDia.setBackground(Color.LIGHT_GRAY);
            } else if (diasMantenimiento.contains(fecha)) {
                btnDia.setBackground(Color.YELLOW);
                btnDia.setEnabled(false); // opcional: no permitir selección si está en mantenimiento
            } else if (fechasOcupadas.contains(fecha)) {
                btnDia.setBackground(Color.RED);
                btnDia.setForeground(Color.WHITE);
                btnDia.setEnabled(true);
                btnDia.addActionListener(e -> {
                    JOptionPane.showMessageDialog(this, "La habitación ya fue reservada en esta fecha", "Fecha ocupada", JOptionPane.INFORMATION_MESSAGE);
                });
            } else {
                if (diasSeleccionados.contains(fecha)) {
                    btnDia.setBackground(Color.GREEN);
                } else {
                    btnDia.setBackground(null);
                }

                btnDia.addActionListener(e -> {
                    if (diasSeleccionados.contains(fecha)) {
                        diasSeleccionados.remove(fecha);
                        btnDia.setBackground(null);
                    } else {
                        diasSeleccionados.add(fecha);
                        btnDia.setBackground(Color.GREEN);
                    }
                    actualizarTotales();
                });
            }

            panelDias.add(btnDia);
        }

        panelDias.revalidate();
        panelDias.repaint();
        actualizarTotales();
    }

    private void actualizarTotales() {
        int totalDias = diasSeleccionados.size();
        labelTotalDias.setText("Días seleccionados: " + totalDias);
        labelTotalPagar.setText(String.format("Total a pagar: $%.2f", totalDias * precioPorDia));
    }

    private void confirmarReserva() {
        if (diasSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un día para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (LocalDate dia : diasSeleccionados) {
            boolean disponible = daoReserva.verificarDisponibilidad(idHabitacion, dia);
            if (!disponible) {
                JOptionPane.showMessageDialog(this, "La habitación no está disponible el día " + dia.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        boolean exito = true;
        LocalDate fechaReserva = LocalDate.now();

        for (LocalDate dia : diasSeleccionados) {
            Reserva res = new Reserva();
            res.setIdHabitacion(idHabitacion);
            res.setIdCliente(cliente.getId());
            res.setFechaReserva(fechaReserva);
            res.setFechaEstadia(dia);
            res.setPrecioPagado(precioPorDia);
            res.setEstado("Activa");

            if (!daoReserva.registrarReserva(res)) {
                exito = false;
                break;
            }
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, "Reserva confirmada con éxito.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al confirmar la reserva. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Constructor vacío si lo necesitas para el modo empleado u otras versiones
    public CalendarioForm() {
        // Constructor vacío opcional
    }

    private void mostrarOpcionesMantenimiento() {
        String[] opciones = {"Poner en Mantenimiento", "Marcar como Disponible", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Qué acción desea realizar para esta habitación?",
                "Opciones de Mantenimiento",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        String nuevoEstado = null;

        if (eleccion == 0) {
            nuevoEstado = "Mantenimiento";
        } else if (eleccion == 1) {
            nuevoEstado = "Disponible";
        }

        if (nuevoEstado != null) {
            DAOHabitacion dao = new DAOHabitacion();
            boolean exito = dao.actualizarEstado(idHabitacion, nuevoEstado);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Estado actualizado a: " + nuevoEstado);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el estado.");
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
