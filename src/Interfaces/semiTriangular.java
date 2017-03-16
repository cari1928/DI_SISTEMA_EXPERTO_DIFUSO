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

    JTextField txtEtiqueta, txtPuntoC, txtLongitud;
    String[] arrayO = {"Derecha", "Izquierda"};
    JComboBox orientacion;
    JLabel lblEtiqueta, lblPuntoC, lblLongitud, lblOrientacion;
    JButton aceptar;
    JPanel pnlsup, pnlinf;
    String etiqueta;
    char v_orientacion;
    double origen, fin,puntoC,longitud,aux;
    int noFuncion;
    
    public semiTriangular(int noFuncion, double origen, double fin) {
        super("Semi Trapesoide");
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
        pnlsup = new JPanel();//Crea el espacio para el panel
        pnlsup.setLayout(new GridLayout(4, 2));//Declara como irán los botones en el panel
        orientacion = new JComboBox(arrayO);
        lblOrientacion = new JLabel("Orientacion: ");
        txtPuntoC = new JTextField();
        txtPuntoC.setText("");
        lblPuntoC = new JLabel("Punto Critico: ");
        txtLongitud = new JTextField();
        txtLongitud.setText("");
        lblLongitud = new JLabel("Longitud: ");
        txtEtiqueta = new JTextField();
        txtEtiqueta.setText("");
        lblEtiqueta = new JLabel("Etiqueta: ");
        pnlsup.add(lblOrientacion);
        pnlsup.add(orientacion);
        pnlsup.add(lblPuntoC);
        pnlsup.add(txtPuntoC);
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
                        objG.escribir((noFuncion + 1), Fsemitriangular, "final");
                        ocultarventana();
                        if(v_orientacion == 'd')
                        {
                            tipoFunciones objFun = new tipoFunciones(10, fin, fin);
                        }else
                        {
                            if(noFuncion==1)
                            {
                                tipoFunciones objFun = new tipoFunciones(noFuncion, calculaTraslape(), fin);
                            }else
                            {
                                System.out.println("No se puede insertar debido a que no es la primera función");
                            }
                        }
                    } catch (Exception ex) {
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
            v_orientacion=orientacion.getSelectedItem().toString().toLowerCase().charAt(0);
            longitud=Double.parseDouble(lblLongitud.getText());
            if (etiqueta.equals("")) {
                JOptionPane.showMessageDialog(this, "Error llene todos los campos");
                return false;
            }
            if (puntoC <= origen || puntoC >= fin) {
                JOptionPane.showMessageDialog(this, "El punto critico esta fuera del discurso disponible");
                return false;
            }
            if(v_orientacion == 'i' && puntoC==origen)
            {
                return false;
            }
            if(v_orientacion == 'd' && puntoC==fin)
            {
                return false;
            }
            double rango = puntoC - origen;
            if ((fin - origen) < (rango + rango)) {
                JOptionPane.showMessageDialog(this, "La función abarcará todo el discurso disponible");
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

    void ocultarventana() {
        this.setVisible(false);
    }

}
