package Interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import SED.GestionArchivos;

/**
 *
 * @author Tenistas
 */
public class discurso extends JFrame {

    private final String ruta = "SED/";
    double origen, fin;
    String variable, unidad;
    JDesktopPane escritorio;
    JPanel pnlsup, pnlinf;
    JTextField txtorigen, txtFin, txtVariable, txtUnidad;
    JLabel lblOrigen, a, lblVariable, lblUnidad;
    JButton aceptar;

    public discurso() {
        super("Discurso");

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
        txtorigen = new JTextField();
        txtorigen.setText("");

        lblOrigen = new JLabel("Origen: ");
        txtFin = new JTextField();
        txtFin.setText("");

        a = new JLabel("a: ");
        txtVariable = new JTextField();

        txtVariable.setText("");
        lblVariable = new JLabel("Variable: ");
        txtUnidad = new JTextField();
        txtUnidad.setText("");

        lblUnidad = new JLabel("Unidad: ");
        pnlsup.add(lblOrigen);
        pnlsup.add(txtorigen);
        pnlsup.add(a);
        pnlsup.add(txtFin);
        pnlsup.add(lblVariable);
        pnlsup.add(txtVariable);
        pnlsup.add(lblUnidad);
        pnlsup.add(txtUnidad);
    }

    void m_panelInf() {
        pnlinf = new JPanel();//Crea el espacio para el panel
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String discurso;
                GestionArchivos objG;

                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                if (capturaDatos()) {
                    try {
                        objG = new GestionArchivos();
                        discurso = origen + " " + fin + " " + unidad + " " + variable;
                        objG.escribir(ruta + variable, 1, discurso, "nuevo"); //crea el archivo con el nombre de la variable
                        objG.escribir(ruta + "Datos", 1, variable, "final"); //guarda el nombre de la variable en el archivo Datos

                        ocultarventana();
                        new tipoFunciones(0, origen, fin, ruta + variable);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    origen = fin = 0;
                    variable = unidad = "";
                }
            }
        });
        pnlinf.add(aceptar);
    }

    void ocultarventana() {
        this.setVisible(false);
    }

    public boolean capturaDatos() {
        try {
            origen = Double.parseDouble(txtorigen.getText());
            fin = Double.parseDouble(txtFin.getText());
            variable = txtVariable.getText();
            unidad = txtUnidad.getText();
            if (unidad.equals("") || variable.equals("")) {
                JOptionPane.showMessageDialog(this, "Se deben dellanr todos los campos");
                return false;
            }

            if (origen >= fin) {
                JOptionPane.showMessageDialog(this, "El origen es igual o mayor que el final" + origen + " " + fin);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al capturar los datos");
            return false;
        }
        return true;
    }

}
