package Interfaces;

import SED.Combinaciones;
import SED.Etiqueta;
import SED.FAM;
import SED.GestionArchivos;
import SED.MotorInferencia;
import SED.Variable;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tenistas
 */
public class Principal extends javax.swing.JFrame {

    private MotorInferencia objMI;
    private FAM objFAM;
    List<Etiqueta> listResultado;
    boolean salida;

    public Principal() {
        initComponents();
        listResultado = new ArrayList<>();
        objMI = new MotorInferencia();
        setLocationRelativeTo(null);
        setResizable(false);
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
        jmiArchivos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmiFAMNuevo = new javax.swing.JMenuItem();
        jmiFAMExistente = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmPesoRegla = new javax.swing.JMenuItem();
        jmiSalidaDifusa = new javax.swing.JMenuItem();

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

        jmiArchivos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jmiArchivos.setText("Archivos");
        jmiArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiArchivosActionPerformed(evt);
            }
        });
        jMenu1.add(jmiArchivos);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Motor Inferencia");

        jMenuItem2.setText("Fuzzyfication");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenu4.setText("Inferencia");

        jMenuItem4.setText("Inferenciar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
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

        jMenu7.setText("Defuzzyfication");

        jMenuItem5.setText("Variable Salida");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuItem6.setText("Centro de gravedad");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Mostrar");

        jmPesoRegla.setText("Peso de la Regla");
        jmPesoRegla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPesoReglaActionPerformed(evt);
            }
        });
        jMenu5.add(jmPesoRegla);

        jmiSalidaDifusa.setText("Salida Difusa");
        jmiSalidaDifusa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalidaDifusaActionPerformed(evt);
            }
        });
        jMenu5.add(jmiSalidaDifusa);

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
        String directorio = "SED";
        File index = new File(directorio);
        String[] entries = index.list();

        if (!index.exists()) {
            index.mkdir();
            JOptionPane.showMessageDialog(null, "Los archivos se han limpiado exitosamente");
        } else {
            for (String s : entries) {
                File currentFile = new File(index.getPath(), s);
                currentFile.delete();
            }
            JOptionPane.showMessageDialog(null, "Los archivos se han limpiado exitosamente");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiFAMNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFAMNuevoActionPerformed
        GestionArchivos objG = new GestionArchivos();
        GUI_Combinaciones guiC;
        List<String> listRegistros;
        Combinaciones objC;
        objFAM = new FAM();
        Variable objV;
        String rutaArchivo;
        try {
            listRegistros = objG.leer("SED/Datos");

            //llena listVariables
            for (String registro : listRegistros) {
                rutaArchivo = "SED/" + registro.trim();
                objMI.crearModelo(rutaArchivo.trim());
                objV = new Variable(objMI.objU, objMI.listTriangular, objMI.listTrapezoide, objMI.listSemiTriangular, objMI.listSemiTrapezoide, objMI.punto, registro);
                objFAM.listVariables.add(objV);
            }
            objC = new Combinaciones();
            objFAM.crear(objFAM.listVariables.get(0), 1, objC, new Combinaciones());
            objFAM.crearArchivo();
            guiC = new GUI_Combinaciones(objFAM);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jmiFAMNuevoActionPerformed

    private void jmiFAMExistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFAMExistenteActionPerformed
        try {
            objFAM = new FAM();
            objFAM.actualizaArchivo();
            JOptionPane.showMessageDialog(this, "Combinaciones cargadas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jmiFAMExistenteActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        salida = false;
        new discurso(salida);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        GestionArchivos objG = new GestionArchivos();
        List<String> listVars;
        dato_x guiD;
        try {
            listVars = objG.leer("SED/Datos");

            for (String variable : listVars) {
                guiD = new dato_x(variable.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Combinaciones temp;
        Etiqueta objR = new Etiqueta();
        double minimo;
        boolean check;

        for (Combinaciones listC : objFAM.listCombinaciones) {
            minimo = objFAM.calcPesoRegla(listC.listCombinaciones);
            listC.pesoRegla = minimo;
        }

        listResultado = objFAM.obtenerReult(objFAM.buscaSalidas()); 
        JOptionPane.showMessageDialog(this, "Inferencia completada");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmPesoReglaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPesoReglaActionPerformed
        String salida = "";
        DecimalFormat decimales = new DecimalFormat("0.0000");
        DecimalFormat cero = new DecimalFormat("0.0");

        for (int i = 0; i < objFAM.listCombinaciones.size(); i++) {
            for (int j = 0; j < objFAM.listCombinaciones.get(i).listSalidas.size(); j++) {
                for (int k = 0; k < objFAM.listCombinaciones.get(i).listCombinaciones.size(); k++) {
                    if (objFAM.listCombinaciones.get(i).listCombinaciones.get(k).membresia == 0.00000) {
                        salida += objFAM.listCombinaciones.get(i).listCombinaciones.get(k).etiqueta + "(" + cero.format(objFAM.listCombinaciones.get(i).listCombinaciones.get(k).membresia) + ") ";
                    } else {
                        salida += objFAM.listCombinaciones.get(i).listCombinaciones.get(k).etiqueta + "(" + decimales.format(objFAM.listCombinaciones.get(i).listCombinaciones.get(k).membresia) + ") ";
                    }
                    if (k != objFAM.listCombinaciones.get(i).listCombinaciones.size() - 1) {
                        salida += " ^ ";
                    }
                }
                salida += "-> ";
                if (objFAM.listCombinaciones.get(i).pesoRegla == 0.00000) {
                    salida += objFAM.listCombinaciones.get(i).listSalidas.get(j) + "(" + cero.format(objFAM.listCombinaciones.get(i).pesoRegla) + ") \n";
                } else {
                    salida += objFAM.listCombinaciones.get(i).listSalidas.get(j) + "(" + decimales.format(objFAM.listCombinaciones.get(i).pesoRegla) + ") \n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, salida);
    }//GEN-LAST:event_jmPesoReglaActionPerformed

    private void jmiSalidaDifusaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalidaDifusaActionPerformed
        String resultado = "";
        try {
            for (int i = 0; i < listResultado.size(); i++) {
                resultado += listResultado.get(i).etiqueta + " : " + listResultado.get(i).membresia + "\n";
            }

            JOptionPane.showMessageDialog(this, resultado, "Salidas Difusas", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Archivos no disponibles");
        }
    }//GEN-LAST:event_jmiSalidaDifusaActionPerformed

    private void jmiArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiArchivosActionPerformed
        //PARA PRUEBAS!!!!!
        GestionArchivos objG = new GestionArchivos();
        List<String> listR, listV;
        String archivo;
        try {
            listR = objG.leer("SED/Datos");
            System.out.println("DATOS");
            for (String rVar : listR) {
                System.out.println(rVar);
                archivo = "SED/" + rVar;
                listV = objG.leer(archivo.trim());

                for (String datos : listV) {
                    System.out.println(datos);
                }
            }

            listR = objG.leer("SED/FAM");
            System.out.println("FAM");
            for (String registros : listR) {
                System.out.println(registros);
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiArchivosActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if(!listResultado.isEmpty()){
            salida = true;
            discurso objD = new discurso(salida, listResultado);
        } else{
            JOptionPane.showMessageDialog(this, "Se requiere inferenciar primero");
        }
        
        //objD.setListaEtiquetasSalida(listResultado);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        try {
            // TODO add your handling code here:
            GestionArchivos objG = new GestionArchivos();
            String variable = "";
            List<String> registros;
            registros = objG.leer("SED/Datos");
            for (String reg : registros) {
                if(reg.contains("-S")){
                        variable = reg;
                }
            }
            MotorInferencia objMI = new MotorInferencia();
            List<String[]> listaPuntosX = objMI.obtenerXFormulaGuss("SED/" + variable.trim(), listResultado);
            //ListaPuntosX es lo que ocupas tu cari!!!
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jmPesoRegla;
    private javax.swing.JMenuItem jmiArchivos;
    private javax.swing.JMenuItem jmiFAMExistente;
    private javax.swing.JMenuItem jmiFAMNuevo;
    private javax.swing.JMenuItem jmiSalidaDifusa;
    // End of variables declaration//GEN-END:variables
}
