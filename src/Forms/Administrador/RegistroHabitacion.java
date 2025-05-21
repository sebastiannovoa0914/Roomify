package Forms.Administrador;

import dao.DAOHabitacion;
import javax.swing.JOptionPane;
import modelo.Habitacion;

public class RegistroHabitacion extends javax.swing.JFrame {

    public RegistroHabitacion() {
        initComponents();
        this.setLocationRelativeTo(null);
        JLPrecio.setText("90000");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistrar = new javax.swing.JButton();
        JCTipo = new javax.swing.JComboBox<>();
        JLPrecio = new javax.swing.JLabel();
        JCEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 140, 40));

        JCTipo.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        JCTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple", "Doble", "Suit" }));
        JCTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTipoActionPerformed(evt);
            }
        });
        getContentPane().add(JCTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        JLPrecio.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        getContentPane().add(JLPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 190, 25));

        JCEstado.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        JCEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Ocupada", "Mantenimiento" }));
        getContentPane().add(JCEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\geral\\OneDrive\\Documentos\\NetBeansProjects.jar\\Roomify\\src\\Imagenes\\Roomify (800 x 600 px) (11).png")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // Obtener datos de los campos
        String tipo = JCTipo.getSelectedItem().toString();
        String estado = JCEstado.getSelectedItem().toString();
        double precio = Double.parseDouble(JLPrecio.getText());

        // Crear DAO y verificar si el usuario existe por cédula
        DAOHabitacion dao = new DAOHabitacion();

        // Crear objeto Cliente
        Habitacion habitacion = new Habitacion();
        habitacion.setEstado(estado);
        habitacion.setTipo(tipo);
        habitacion.setPrecio(precio);

        // Intentar registrar
        boolean registrado = dao.agregarHabitacion(habitacion);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Registro exitoso");
            this.dispose();          // Cierra el formulario registro
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. Intente de nuevo.");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void JCTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTipoActionPerformed
        String tipo = JCTipo.getSelectedItem().toString();
        switch (tipo) {
            case "Simple":
                JLPrecio.setText("90000");
                break;
            case "Doble":
                JLPrecio.setText("150000");
                break;
            default:
                JLPrecio.setText("210000");
                break;
        }
    }//GEN-LAST:event_JCTipoActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroHabitacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCEstado;
    private javax.swing.JComboBox<String> JCTipo;
    private javax.swing.JLabel JLPrecio;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
