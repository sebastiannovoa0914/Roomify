package Forms.Administrador;

import Forms.FormPrincipal;
import modelo.Administrador;

public class PanelAdministrador extends javax.swing.JFrame {

    private Administrador administrador;

    public PanelAdministrador(Administrador administrador) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.administrador = administrador;
    }

    public PanelAdministrador() {
        this.setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMResgistrar = new javax.swing.JMenu();
        subCliente = new javax.swing.JMenuItem();
        subEmpleado = new javax.swing.JMenuItem();
        subAdministrador = new javax.swing.JMenuItem();
        subHabitacion = new javax.swing.JMenuItem();
        JMVisualizar = new javax.swing.JMenu();
        subClientes = new javax.swing.JMenuItem();
        subEmpleados = new javax.swing.JMenuItem();
        subAdministradores = new javax.swing.JMenuItem();
        JMCerrarSesion = new javax.swing.JMenu();
        subCerrarSesión = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Roomify (800 x 600 px) (8).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 560));

        jMenuBar1.setMinimumSize(new java.awt.Dimension(245, 40));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(245, 40));

        JMResgistrar.setText("Registrar");
        JMResgistrar.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N

        subCliente.setText("Cliente");
        subCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subClienteActionPerformed(evt);
            }
        });
        JMResgistrar.add(subCliente);

        subEmpleado.setText("Empleado");
        subEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEmpleadoActionPerformed(evt);
            }
        });
        JMResgistrar.add(subEmpleado);

        subAdministrador.setText("Administrador");
        subAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAdministradorActionPerformed(evt);
            }
        });
        JMResgistrar.add(subAdministrador);

        subHabitacion.setText("Habitacion");
        subHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subHabitacionActionPerformed(evt);
            }
        });
        JMResgistrar.add(subHabitacion);

        jMenuBar1.add(JMResgistrar);

        JMVisualizar.setText("Visualizar");
        JMVisualizar.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N

        subClientes.setText("Clientes");
        subClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subClientesActionPerformed(evt);
            }
        });
        JMVisualizar.add(subClientes);

        subEmpleados.setText("Empleados");
        subEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEmpleadosActionPerformed(evt);
            }
        });
        JMVisualizar.add(subEmpleados);

        subAdministradores.setText("Administradores");
        subAdministradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAdministradoresActionPerformed(evt);
            }
        });
        JMVisualizar.add(subAdministradores);

        jMenuBar1.add(JMVisualizar);

        JMCerrarSesion.setText("Cerrar Sesión");
        JMCerrarSesion.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N

        subCerrarSesión.setText("Cerrar Sesión");
        subCerrarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCerrarSesiónActionPerformed(evt);
            }
        });
        JMCerrarSesion.add(subCerrarSesión);

        jMenuBar1.add(JMCerrarSesion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEmpleadoActionPerformed
        new RegistroEmpleado().setVisible(true);
    }//GEN-LAST:event_subEmpleadoActionPerformed

    private void subCerrarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCerrarSesiónActionPerformed
        new FormPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_subCerrarSesiónActionPerformed

    private void subClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subClienteActionPerformed
        new RegistroClienteA().setVisible(true);
    }//GEN-LAST:event_subClienteActionPerformed

    private void subAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAdministradorActionPerformed
        new RegistroAdministrador().setVisible(true);
    }//GEN-LAST:event_subAdministradorActionPerformed

    private void subHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subHabitacionActionPerformed
        new RegistroHabitacion().setVisible(true);
    }//GEN-LAST:event_subHabitacionActionPerformed

    private void subClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subClientesActionPerformed
        new VisualizarClientes().setVisible(true);
    }//GEN-LAST:event_subClientesActionPerformed

    private void subEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEmpleadosActionPerformed
        new VisualizarEmpleados().setVisible(true);
    }//GEN-LAST:event_subEmpleadosActionPerformed

    private void subAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAdministradoresActionPerformed
        new VisualizarAdministradores().setVisible(true);
    }//GEN-LAST:event_subAdministradoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JMCerrarSesion;
    private javax.swing.JMenu JMResgistrar;
    private javax.swing.JMenu JMVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem subAdministrador;
    private javax.swing.JMenuItem subAdministradores;
    private javax.swing.JMenuItem subCerrarSesión;
    private javax.swing.JMenuItem subCliente;
    private javax.swing.JMenuItem subClientes;
    private javax.swing.JMenuItem subEmpleado;
    private javax.swing.JMenuItem subEmpleados;
    private javax.swing.JMenuItem subHabitacion;
    // End of variables declaration//GEN-END:variables
}
