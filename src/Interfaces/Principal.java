package Interfaces;

import SED.FAM;
import SED.MotorInferencia;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Tenistas
 */
public class Principal extends javax.swing.JFrame {

    private MotorInferencia objMI;

    public Principal() {
        initComponents();

        objMI = new MotorInferencia();

        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmiFAMNuevo = new javax.swing.JMenuItem();
        jmiFAMExistente = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/tennis-racket-155963_640.png"))); // NOI18N

        jMenu1.setText("Modelo");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenu3.setText("Actualizar");

        jMenuItem3.setText("Nueva Variable");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Motor Inferencia");

        jMenuItem2.setText("Fuzzyfication");
        jMenu2.add(jMenuItem2);

        jMenu4.setText("Inferencia");

        jMenuItem4.setText("Inferenciar");
        jMenu4.add(jMenuItem4);

        jMenu6.setText("FAM");

        jmiFAMNuevo.setText("Nuevo");
        jmiFAMNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFAMNuevoActionPerformed(evt);
            }
        });
        jMenu6.add(jmiFAMNuevo);

        jmiFAMExistente.setText("Usar Existente");
        jmiFAMExistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFAMExistenteActionPerformed(evt);
            }
        });
        jMenu6.add(jmiFAMExistente);

        jMenu4.add(jMenu6);

        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Mostrar");

        jMenuItem6.setText("Peso de la Regla");
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Salida Difusa");
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        String directorio = "SED";
        File index = new File(directorio);
        String[]entries = index.list();
        
        if (!index.exists()) 
        {
            index.mkdir();
            JOptionPane.showMessageDialog(null, "Los archivos se han limpiado exitosamente");
        } else 
        {
            for(String s: entries)
            {
                File currentFile = new File(index.getPath(),s);
                currentFile.delete();
            }
            JOptionPane.showMessageDialog(null, "Los archivos se han limpiado exitosamente");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiFAMNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFAMNuevoActionPerformed
        try {
            FAM objFAM = new FAM(objMI.listTriangular, objMI.listTrapezoide, objMI.listSemiTriangular, objMI.listSemiTrapezoide);
            objFAM.crear();
            JOptionPane.showMessageDialog(this, "FAM creada exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);

            new GUI_Combinaciones(objFAM.listCombinaciones);
            System.out.println("LLAMAR GUI COMBINACIONES");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jmiFAMNuevoActionPerformed

    private void jmiFAMExistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFAMExistenteActionPerformed
        FAM objFAM = new FAM(objMI.listTriangular, objMI.listTrapezoide, objMI.listSemiTriangular, objMI.listSemiTrapezoide);
        objFAM.actualizaFAM();

        //FALTA
        System.out.println("FALTA");

    }//GEN-LAST:event_jmiFAMExistenteActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new dato_x();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jmiFAMExistente;
    private javax.swing.JMenuItem jmiFAMNuevo;
    // End of variables declaration//GEN-END:variables
}
