package Interfaces;

import SED.GestionArchivos;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tenistas
 */
public class triangular extends JFrame {

    double PC;
    String etiquete;
    JLabel lblPuntoC, lblEtiqueta;
    JTextField txtPuntoC, txtEtiqueta;
    JPanel pnlinf, pnlsup;
    JButton aceptar;
    double origen, fin, puntoC;
    int noFuncion;
    double aux;

    triangular(int noFuncion, double origen, double fin) {
        super("Triangular");
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
        //this.pack();
    }

    void m_panelSup() {
        pnlsup = new JPanel();//Crea el espacio para el panel
        pnlsup.setLayout(new GridLayout(2, 2));//Declara como irán los botones en el panel
        txtPuntoC = new JTextField();
        txtPuntoC.setText("");
        lblPuntoC = new JLabel("Punto Critico: ");
        txtEtiqueta = new JTextField();
        txtEtiqueta.setText("");
        lblEtiqueta = new JLabel("Etiqueta: ");
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
                //System.out.println("Falta programar");
                if (capturaDatos() == true) {
                    try {
                        GestionArchivos objG = new GestionArchivos();
                        String Ftriangular = "Triangular " + puntoC + " " + etiquete + " " + origen;
                        objG.escribir((noFuncion + 1), Ftriangular, "final");
                        ocultarventana();
                        tipoFunciones objFun = new tipoFunciones(noFuncion, calculaTraslape(), fin);
                    } catch (Exception ex) {
                    }
                }
            }
        });
        pnlinf.add(aceptar);
    }

    void ocultarventana() {
        this.setVisible(false);
    }

    boolean capturaDatos() {
        try {
            puntoC = Double.parseDouble(txtPuntoC.getText().toString());
            etiquete = txtEtiqueta.getText().toString();
            if (etiquete.equals("")) {
                JOptionPane.showMessageDialog(this, "Error llene todos los campos");
                return false;
            }
            if (puntoC <= origen || puntoC >= fin) {
                JOptionPane.showMessageDialog(this, "El punto critico esta fuera del discurso disponible");
                return false;
            }
            double rango = puntoC - origen;
            if ((fin - origen) < (rango + rango)) {
                JOptionPane.showMessageDialog(this, "La función abarcara todo el discurso disponible");
                aux = fin;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al capturar los datos");
            return false;
        }
    }

    double calculaTraslape() {
        double rango = 0;
        if (aux == fin) {
            rango = fin - puntoC;
        } else {
            rango = puntoC - origen;
        }

        double nuevoOrigen = rango * 0.6;
        nuevoOrigen = nuevoOrigen + puntoC;
        return nuevoOrigen;
    }

}
