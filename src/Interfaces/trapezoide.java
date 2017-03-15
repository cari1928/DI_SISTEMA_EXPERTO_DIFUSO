package Interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tenistas
 */
public class trapezoide extends JFrame {

    JTextField txtPuntoC1, txtPuntoC2, txtEtiqueta;
    JLabel lblPuntoC1, lblPuntoC2, lblEtiqueta;
    JButton aceptar;
    JPanel pnlsup, pnlinf;

    public trapezoide() {
        super("Trapezoide");
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
        //this.pack();
    }

    void m_panelSup() {
        pnlsup = new JPanel();
        pnlsup.setLayout(new GridLayout(3, 2));//Declara como irán los botones en el panel
        txtPuntoC1 = new JTextField();
        txtPuntoC1.setText("");
        lblPuntoC1 = new JLabel("Punto critico 1: ");
        txtPuntoC2 = new JTextField();
        txtPuntoC2.setText("");
        lblPuntoC2 = new JLabel("Punto critico 2: ");
        txtEtiqueta = new JTextField();
        txtEtiqueta.setText("");
        lblEtiqueta = new JLabel("Etiqueta: ");
        pnlsup.add(lblPuntoC1);
        pnlsup.add(txtPuntoC1);
        pnlsup.add(lblPuntoC2);
        pnlsup.add(txtPuntoC2);
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
                tipoFunciones objFun = new tipoFunciones();
            }
        });
        pnlinf.add(aceptar);
    }

    void ocultarventana() {
        this.setVisible(false);
    }

}
