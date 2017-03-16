package Interfaces;
//calcular semitrapecion

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import SED.*;

/**
 *
 * @author Tenistas
 */
public class tipoFunciones extends JFrame {

    String[] tipoFunciones = {"Ceros", "Triangular", "Trapezoide", "SemiTriangular", "SemiTrapezoide", "Finalizar"};
    JComboBox petList;
    JPanel panel;
    double origen, fin;
    int noFuncion;

    public tipoFunciones(int noFuncion, double origen, double fin) {
        super("Funciones de membrecia");
        noFuncion++;
        if (noFuncion < 10) {
            this.origen = origen;
            this.fin = fin;
            this.noFuncion = noFuncion;
            m_panel();
            this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            this.add(panel);
            this.setVisible(true);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//PARA CERRAR BIEN LA VENTANA
            //this.setExtendedState(this.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setSize(200, 160);
        } else {
            this.setVisible(false);
            MotorInferencia objM = new MotorInferencia();
        }
    }

    void m_panel() {
        panel = new JPanel();
        petList = new JComboBox(tipoFunciones);
        //petList.setSelectedIndex(4);
        petList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                String petName = (String) petList.getSelectedItem();
                ocultarventana();
                switch (petName) {
                    case "Ceros":
                        ceros objCe = new ceros(noFuncion, origen, fin);
                        break;
                    case "Triangular":
                        triangular objT = new triangular(noFuncion, origen, fin);
                        break;
                    case "Trapezoide":
                        trapezoide objTrap = new trapezoide(noFuncion, origen, fin);
                        break;
                    case "SemiTriangular":
                        semiTriangular objSTri = new semiTriangular(noFuncion, origen, fin);
                        break;
                    case "SemiTrapezoide":
                        semiTrapezoide objSTrap = new semiTrapezoide(noFuncion, origen, fin);
                        break;
                    case "Finalizar":
                        ocultarventana();
                        MotorInferencia objM = new MotorInferencia();
                        break;
                }
                System.out.println("Falta programar");
            }
        });
        panel.add(petList);
    }

    void ocultarventana() {
        this.setVisible(false);
    }
}
