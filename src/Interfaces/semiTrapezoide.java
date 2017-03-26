package Interfaces;

import SED.GestionArchivos;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tenistas
 */
public class semiTrapezoide extends JFrame {

    char v_orientacion;
    double origen, fin, puntoC, aux;
    int noFuncion;
    String etiqueta, nomFile;
    String[] arrayO = {"Derecha", "Izquierda"};
    JComboBox orientacion;
    JPanel pnlsup, pnlinf;
    JTextField txtPuntoC, txtEtiqueta;
    JLabel lblPuntoC, lblEtiqueta, lblOrientacion;
    JButton aceptar;

    public semiTrapezoide(int noFuncion, double origen, double fin, String nomFile) {
        super("Semi Trapezoide");
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
        orientacion = new JComboBox(arrayO);
        txtPuntoC = new JTextField();
        txtEtiqueta = new JTextField();
        lblEtiqueta = new JLabel("Etiqueta: ");
        lblPuntoC = new JLabel("Punto crítico: ");
        lblOrientacion = new JLabel("Orientación: ");
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
                String fSemiTrapezoide;
                GestionArchivos objG;
                tipoFunciones objFun;

                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                if (capturaDatos() == true) {
                    try {
                        objG = new GestionArchivos();
                        fSemiTrapezoide = "SemiTrapezoide " + puntoC + " " + v_orientacion + " " + etiqueta + " " + origen + " 0";
                        objG.escribir(nomFile, (noFuncion + 1), fSemiTrapezoide, "final");

                        ocultarventana();
                        if (v_orientacion == 'd') {
                            objFun = new tipoFunciones(10, fin, fin, nomFile);
                        } else {
                            if (noFuncion == 1) {
                                objFun = new tipoFunciones(noFuncion, calculaTraslape(), fin, nomFile);
                            } else {
                                System.out.println("No se puede insertar debido a que no es la primera función");
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        pnlinf.add(aceptar);
    }

    boolean capturaDatos() {
        try {
            puntoC = Double.parseDouble(txtPuntoC.getText().toString());
            etiqueta = txtEtiqueta.getText().toString();
            v_orientacion = orientacion.getSelectedItem().toString().toLowerCase().charAt(0);
            if (etiqueta.equals("")) {
                JOptionPane.showMessageDialog(this, "Error, llene todos los campos.");
                return false;
            }
            if (origen > puntoC || puntoC > fin) {
                JOptionPane.showMessageDialog(this, "Error, el punto crítico no está dentro del discurso disponible");
                return false;
            }
            if (v_orientacion == 'i' && puntoC == origen) {
                return false;
            }
            if (v_orientacion == 'd' && puntoC == fin) {
                return false;
            }
            double rango = puntoC - origen;
            if ((fin - origen) < (rango + rango)) {
                JOptionPane.showMessageDialog(this, "La función abarcará todo el discurso disponible");
                aux = fin;
            }

            return true;

        } catch (Exception e) {
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

    void ocultarventana() {
        this.setVisible(false);
    }

}
