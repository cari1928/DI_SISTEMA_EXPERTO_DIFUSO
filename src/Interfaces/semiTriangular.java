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
public class semiTriangular extends JFrame {

    char v_orientacion;
    double origen, fin, puntoC, longitud, aux;
    int noFuncion;
    String nomFile;
    String[] arrayO = {"Derecha", "Izquierda"};
    JTextField txtEtiqueta, txtLongitud;
    JComboBox orientacion;
    JLabel lblEtiqueta, lblLongitud, lblOrientacion;
    JButton aceptar;
    JPanel pnlsup, pnlinf;
    String etiqueta;

    public semiTriangular(int noFuncion, double origen, double fin, String nomFile) {
        super("Semi Trapesoide");
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
        pnlsup = new JPanel();//Crea el espacio para el panel
        pnlsup.setLayout(new GridLayout(4, 2));//Declara como irán los botones en el panel
        orientacion = new JComboBox(arrayO);
        lblOrientacion = new JLabel("Orientacion: ");
        txtLongitud = new JTextField();
        txtLongitud.setText("");
        lblLongitud = new JLabel("Longitud: ");
        txtEtiqueta = new JTextField();
        txtEtiqueta.setText("");
        lblEtiqueta = new JLabel("Etiqueta: ");
        pnlsup.add(lblOrientacion);
        pnlsup.add(orientacion);
        pnlsup.add(lblLongitud);
        pnlsup.add(txtLongitud);
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
                if (capturaDatos() == true) {
                    try {
                        GestionArchivos objG = new GestionArchivos();
                        String Fsemitriangular = "SemiTriangular " + puntoC + " " + longitud + " " + v_orientacion + " " + etiqueta + " " + origen;
                        objG.escribir(nomFile, (noFuncion + 1), Fsemitriangular, "final");

                        ocultarventana();
                        if (v_orientacion == 'd') {
                            new tipoFunciones(10, fin, fin, nomFile);

                        } else {

                            if (noFuncion == 1) {
                                new tipoFunciones(noFuncion, calculaTraslape(), fin, nomFile);
                            } else {
                                System.out.println("No se puede colocar la función debido a que no es la primera");
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
            etiqueta = txtEtiqueta.getText();
            v_orientacion = orientacion.getSelectedItem().toString().toLowerCase().charAt(0);
            longitud = Double.parseDouble(txtLongitud.getText());

            if (etiqueta.equals("")) {
                JOptionPane.showMessageDialog(this, "Error llene todos los campos");
                return false;
            }
            if (orientacion.getSelectedItem().toString().equalsIgnoreCase("Derecha")) {
                puntoC = fin;
            } else {
                puntoC = origen;
            }
            if ((fin - origen) < longitud) {
                JOptionPane.showMessageDialog(this, "La función abarcará todo el discurso disponible");
                longitud = fin - origen;
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
        if (orientacion.getSelectedObjects().toString().equals("Derecha")) {
            return fin;
        } else {
            double nuevoOrigen = ((origen + longitud) - origen) * 0.6;
            nuevoOrigen = origen + nuevoOrigen;
            return nuevoOrigen;
        }
    }

    void ocultarventana() {
        this.setVisible(false);
    }

}
