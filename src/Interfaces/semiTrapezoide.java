package Interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tenistas
 */
public class semiTrapezoide extends JFrame {

    String[] arrayO = {"Derecha", "Izquierda"};
    JComboBox orientacion;
    JPanel pnlsup, pnlinf;
    JTextField txtPuntoC, txtEtiqueta;
    JLabel lblPuntoC, lblEtiqueta, lblOrientacion;
    JButton aceptar;
    double origen, fin;
    int noFuncion;

    public semiTrapezoide(int noFuncion, double origen, double fin) {
        super("Semi Trapezoide");
        this.noFuncion = noFuncion;
        this.origen = origen;
        this.fin = fin;
        m_panelSup();
        m_panelInf();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(pnlsup);
        this.add(pnlinf);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//PARA CERRAR BIEN LA VENTANA
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(200, 160);
    }

    void m_panelSup() {
        pnlsup = new JPanel();
        pnlsup.setLayout(new GridLayout(3, 2));//Declara como irán los botones en el panel
        orientacion = new JComboBox(arrayO);
        txtPuntoC = new JTextField();
        txtEtiqueta = new JTextField();
        lblEtiqueta = new JLabel("Etiqueta: ");
        lblPuntoC = new JLabel("Punto creitico: ");
        lblOrientacion = new JLabel("Orientacion: ");
        pnlsup.add(lblOrientacion);
        pnlsup.add(orientacion);
        pnlsup.add(lblPuntoC);
        pnlsup.add(txtPuntoC);
        pnlsup.add(lblEtiqueta);
        pnlsup.add(txtEtiqueta);
    }

    void m_panelInf() {
        pnlinf = new JPanel();//Crea el espacio para el panel
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                System.out.println("Falta programar");
                ocultarventana();
                tipoFunciones objFun = new tipoFunciones(noFuncion, origen, fin);
            }
        });
        pnlinf.add(aceptar);
    }

    void ocultarventana() {
        this.setVisible(false);
    }

}
