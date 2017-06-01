package Interfaces;

import RNA.Patron;
import RNA.extras;
import SED.Etiqueta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Tenistas
 */
public class tipoFunciones extends JFrame {

    double origen, fin;
    int noFuncion;
    String nomFile;
    String[] tipoFunciones = {"Ceros", "Triangular", "Trapezoide", "SemiTriangular", "SemiTrapezoide", "Finalizar"};
    JComboBox petList;
    JPanel panel;

    public tipoFunciones(int numFuncion, double origen, double fin, String nomFile) {
        super("Funciones de membrecía");
        this.nomFile = nomFile;

        numFuncion++;
        if (numFuncion < 10) {
            this.origen = origen;
            this.fin = fin;
            this.noFuncion = numFuncion;

            m_panel();

            this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            this.add(panel);
            this.setVisible(true);
            this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setSize(200, 160);

        } else {
            JOptionPane.showMessageDialog(null, "Variable agregada");
            if (nomFile.contains("-S")) {
                extras objE = new extras();
                objE.inicia();
                for (int i = 0; i < 4; i++) {
                    new entradas(i);
                }

            }
        }
    }

    void m_panel() {
        panel = new JPanel();
        petList = new JComboBox(tipoFunciones);
        petList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                String petName = (String) petList.getSelectedItem();
                ocultarventana();
                switch (petName) {
                    case "Ceros":
                        new ceros(noFuncion, origen, fin, nomFile);
                        break;
                    case "Triangular":
                        new triangular(noFuncion, origen, fin, nomFile);
                        break;
                    case "Trapezoide":
                        new trapezoide(noFuncion, origen, fin, nomFile);
                        break;
                    case "SemiTriangular":
                        //if (listaEtiquetasSalida != null) {
                        //    if (noFuncion == 1 || noFuncion == listaEtiquetasSalida.size()) {
                        //        new semiTriangular(noFuncion, origen, fin, nomFile, listaEtiquetasSalida);
                        //    } else {
                        //        JOptionPane.showMessageDialog(panel, "No se puede insertar esta función");
                        //        tipoFunciones objFun = new tipoFunciones(noFuncion - 1, origen, fin, nomFile);
                        //        objFun.setListaEtiquetasSalida(listaEtiquetasSalida);
                        //    }
                        //} else {
                        new semiTriangular(noFuncion, origen, fin, nomFile);
                        //}
                        break;
                    case "SemiTrapezoide":
//                        if (listaEtiquetasSalida != null) {
//                            if (noFuncion == 1 || noFuncion == listaEtiquetasSalida.size()) {
//                                new semiTrapezoide(noFuncion, origen, fin, nomFile, listaEtiquetasSalida);
//                            } else {
//                                JOptionPane.showMessageDialog(panel, "No se puede insertar esta función");
//                                tipoFunciones objFun = new tipoFunciones(noFuncion - 1, origen, fin, nomFile);
//                                objFun.setListaEtiquetasSalida(listaEtiquetasSalida);
//                            }
//                        }else{
                        new semiTrapezoide(noFuncion, origen, fin, nomFile);
//                        }
                        break;
                    case "Finalizar":
                        JOptionPane.showMessageDialog(null, "Funciones de membresía ingresadas");
//                        entradas[] ent = new entradas[4];
                        if (nomFile.contains("-S")) {
                            extras objE = new extras();
                            objE.inicia();
                            for (int i = 0; i < 4; i++) {
                                new entradas(i);
                            }
                        }
                }
            }
        });
        panel.add(petList);
    }

    void ocultarventana() {
        this.setVisible(false);
    }
}
