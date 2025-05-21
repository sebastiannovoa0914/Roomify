package Forms.Administrador;
import Forms.LoginForm;
import dao.DAOCliente;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class RegistroClienteA extends javax.swing.JFrame {

    public RegistroClienteA() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        btnRegistrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 280, 30));
        getContentPane().add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 280, 30));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 274, 280, 30));
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 280, 30));

        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 170, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Roomify (800 x 600 px) (10).png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // Obtener datos de los campos
        String usuario = txtUsuario.getText().trim();
        String cedula = txtCedula.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = String.valueOf(txtContraseña.getPassword()).trim();

        // Validar que no estén vacíos
        if (usuario.isEmpty() || cedula.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        // Validar formato de correo (opcional, básico)
        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un correo válido.");
            return;
        }

        // Crear DAO y verificar si el usuario existe por cédula
        DAOCliente dao = new DAOCliente();

        if (dao.existeUsuarioOCedula(usuario, cedula)) {
            JOptionPane.showMessageDialog(this, "El usuario o la cédula ya están registrados.");
            return;
        }

        // Crear objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setCedula(cedula);
        cliente.setCorreo(correo);
        cliente.setContraseña(contrasena);

        // Intentar registrar
        boolean registrado = dao.registrarCliente(cliente);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Registro exitoso. Ya puede iniciar sesión.");
            this.dispose();          // Cierra el formulario registro
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. Intente de nuevo.");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroClienteA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroClienteA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroClienteA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroClienteA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroClienteA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
