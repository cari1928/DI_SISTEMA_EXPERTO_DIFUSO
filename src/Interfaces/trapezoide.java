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
public class trapezoide extends JFrame {

    double origen, fin, puntoC1, puntoC2, aux;
    int noFuncion;
    String etiqueta, nomFile;
    JTextField txtPuntoC1, txtPuntoC2, txtEtiqueta;
    JLabel lblPuntoC1, lblPuntoC2, lblEtiqueta;
    JButton aceptar;
    JPanel pnlsup, pnlinf;

    public trapezoide(int noFuncion, double origen, double fin, String nomFile) {
        super("Trapezoide");
        this.noFuncion = noFuncion;
        this.origen = origen;
        this.fin = fin;
        this.nomFile = nomFile;

        m_panelSup();
        m_panelInf();

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(pnlsup);
        this.add(pnlinf);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(200, 160);
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
                //System.out.println("Falta programar");
                if (capturaDatos() == true) {
                    try {
                        GestionArchivos objG = new GestionArchivos();
                        String Ftrapezoide = "Trapezoide " + puntoC1 + " " + puntoC2 + " " + etiqueta + " " + origen;
                        objG.escribir("baseConocimientos", (noFuncion + 1), Ftrapezoide, "final");
                        ocultarventana();
                        tipoFunciones objFun = new tipoFunciones(noFuncion, calcularTraslape(), fin, nomFile);
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
            puntoC1 = Double.parseDouble(txtPuntoC1.getText().toString());
            puntoC2 = Double.parseDouble(txtPuntoC2.getText().toString());
            etiqueta = txtEtiqueta.getText().toString();
            if (etiqueta.equals("")) {
                JOptionPane.showMessageDialog(this, "Error, llene todos los campos.");
                return false;
            }
            if (puntoC1 < origen || puntoC1 > fin || puntoC2 < origen || puntoC2 > fin) {
                JOptionPane.showMessageDialog(this, "Error, los puntos criticos no estan dentro del discurso disponible");
                return false;
            }
            if ((fin - origen) < ((puntoC1 - origen) + (puntoC2 - puntoC2) + (fin - puntoC2))) {
                JOptionPane.showMessageDialog(this, "La funcion abarcara todo el discurso disponible");
                aux = fin;
                //return false;
            }
//            if((puntoC1 - origen) < (fin - puntoC2)){
//                JOptionPane.showMessageDialog(this, "Error, la funcion no se puede colocar dentro del discurso disponible");
//                return false;
//            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al capturar los datos");
            return false;
        }
    }

    double calcularTraslape() {
        double nuevoOrigen = 0;
        if (aux == fin) {
            nuevoOrigen = (fin - puntoC2) * 0.6;
        } else {
            nuevoOrigen = (puntoC1 - origen) * 0.6;
        }
        nuevoOrigen = (puntoC2 + nuevoOrigen);
        return nuevoOrigen;
    }
}
