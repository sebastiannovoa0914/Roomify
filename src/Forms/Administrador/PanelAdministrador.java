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
        initComponents();
        this.setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMRegistrar = new javax.swing.JMenu();
        subCliente = new javax.swing.JMenuItem();
        subEmpleado = new javax.swing.JMenuItem();
        subAdministrador = new javax.swing.JMenuItem();
        subHabitacion = new javax.swing.JMenuItem();
        JMVisualizar = new javax.swing.JMenu();
        subClientes = new javax.swing.JMenuItem();
        subEmpleados = new javax.swing.JMenuItem();
        subAdministradores = new javax.swing.JMenuItem();
        JMCerrarSesión = new javax.swing.JMenu();
        subCerrarSesión = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PANEL ADMINISTRADOR");

        JMRegistrar.setText("Registrar");
        JMRegistrar.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N

        subCliente.setText("Cliente");
        subCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subClienteActionPerformed(evt);
            }
        });
        JMRegistrar.add(subCliente);

        subEmpleado.setText("Empleado");
        subEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEmpleadoActionPerformed(evt);
            }
        });
        JMRegistrar.add(subEmpleado);

        subAdministrador.setText("Administrador");
        subAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAdministradorActionPerformed(evt);
            }
        });
        JMRegistrar.add(subAdministrador);

        subHabitacion.setText("Habitación");
        subHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subHabitacionActionPerformed(evt);
            }
        });
        JMRegistrar.add(subHabitacion);

        jMenuBar1.add(JMRegistrar);

        JMVisualizar.setText("Visualizar");

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

        JMCerrarSesión.setText("Cerrar Sesión");

        subCerrarSesión.setText("Cerrar Sesión");
        subCerrarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCerrarSesiónActionPerformed(evt);
            }
        });
        JMCerrarSesión.add(subCerrarSesión);

        jMenuBar1.add(JMCerrarSesión);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAdministradorActionPerformed
        new RegistrarAdministrador().setVisible(true);
    }//GEN-LAST:event_subAdministradorActionPerformed

    private void subCerrarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCerrarSesiónActionPerformed
        new FormPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_subCerrarSesiónActionPerformed

    private void subClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subClienteActionPerformed
        new RegistrarCliente().setVisible(true);
    }//GEN-LAST:event_subClienteActionPerformed

    private void subEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEmpleadoActionPerformed
        new RegistrarEmpleado().setVisible(true);
    }//GEN-LAST:event_subEmpleadoActionPerformed

    private void subClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subClientesActionPerformed
        new VisualizarClientes().setVisible(true);
    }//GEN-LAST:event_subClientesActionPerformed

    private void subAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAdministradoresActionPerformed
        new VisualizarAdministradores().setVisible(true);
    }//GEN-LAST:event_subAdministradoresActionPerformed

    private void subEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEmpleadosActionPerformed
        new VisualizarEmpleados().setVisible(true);
    }//GEN-LAST:event_subEmpleadosActionPerformed

    private void subHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subHabitacionActionPerformed
        new RegistrarHabitacion().setVisible(true);
    }//GEN-LAST:event_subHabitacionActionPerformed

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
    private javax.swing.JMenu JMCerrarSesión;
    private javax.swing.JMenu JMRegistrar;
    private javax.swing.JMenu JMVisualizar;
    private javax.swing.JLabel jLabel1;
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
