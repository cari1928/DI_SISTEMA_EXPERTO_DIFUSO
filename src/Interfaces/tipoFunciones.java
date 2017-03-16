package Interfaces;
//calcular semitrapecio

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tenistas
 */
public class tipoFunciones extends JFrame {

    String[] tipoFunciones = {"Seleccione una Opción", "Ceros", "Triangular", "Trapezoide", "SemiTriangular", "SemiTrapezoide", "Finalizar"};
    JComboBox petList;
    JPanel panel;
    double origen, fin;
    int noFuncion;

    public tipoFunciones(int numFuncion, double origen, double fin) {
        super("Funciones de membrecía");
        numFuncion++;
        if (numFuncion < 10) {
            this.origen = origen;
            this.fin = fin;
            this.noFuncion = numFuncion;
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
            //no creo que se llame aquí al motor de inferencias, éste se llama desde la interfaz dato_x
            //MotorInferencia objM = new MotorInferencia(); 
            ocultarventana();
            dato_x guiD = new dato_x(); //podría ser más bien esto
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
                        dato_x guiD = new dato_x();
                        break;
                }
            }
        });
        panel.add(petList);
    }

    void ocultarventana() {
        this.setVisible(false);
    }
}
