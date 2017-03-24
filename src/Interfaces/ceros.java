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
public class ceros extends JFrame {

    double inicio, fin, origen, finD;
    int noFuncion;
    String nomFile;
    JTextField txtInicio, txtFinal;
    JLabel lblInicio, lblFinal;
    JPanel pnlsup, pnlinf;
    JButton aceptar;

    public ceros(int noFuncion, double origen, double finD, String nomFile) {
        super("Ceros");
        this.finD = finD;
        this.origen = origen;
        this.noFuncion = noFuncion;
        this.nomFile = nomFile; //ya viene con el nombre de la carpeta

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
        pnlsup.setLayout(new GridLayout(2, 2));//Declara como irán los botones en el panel
        txtInicio = new JTextField();
        txtInicio.setText("");
        lblInicio = new JLabel("Origen: ");
        txtFinal = new JTextField();
        txtFinal.setText("");
        lblFinal = new JLabel("Final: ");
        pnlsup.add(lblInicio);
        pnlsup.add(txtInicio);
        pnlsup.add(lblFinal);
        pnlsup.add(txtFinal);
    }

    void m_panelInf() {
        pnlinf = new JPanel();
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String fCeros;
                GestionArchivos objG;
                tipoFunciones objFun;

                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                if (capturaDatos() == true) {
                    try {
                        objG = new GestionArchivos();
                        fCeros = "Ceros" + " " + inicio + " " + fin;
                        objG.escribir(nomFile, (noFuncion + 1), fCeros, "final");
                        ocultarventana();
                        objFun = new tipoFunciones(noFuncion, fin, finD, nomFile);
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
            inicio = Double.parseDouble(txtInicio.getText().toString());
            fin = Double.parseDouble(txtFinal.getText().toString());
            if (inicio >= fin) {
                JOptionPane.showMessageDialog(this, "El punto inicial no puede ser mayor o igual al final");
                return false;
            }
            if (inicio < origen || inicio > finD) {
                JOptionPane.showMessageDialog(this, "El punto inicial no esta dentro del discurso disponible");
                return false;
            }
            if (fin < origen || fin > finD) {
                JOptionPane.showMessageDialog(this, "El punto final no esta dentro del discurso disponible");
                return false;
            }
            double longitud = fin - inicio;
            double longitudD = finD - origen;
            if (longitud >= longitudD) {
                JOptionPane.showMessageDialog(this, "La funcion no cabe en el discurso disponible");
                return false;
            }
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al capturar los datos");
            return false;
        }
    }
}
