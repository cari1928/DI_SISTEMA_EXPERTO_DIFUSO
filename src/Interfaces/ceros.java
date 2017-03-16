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

    JTextField txtInicio, txtFinal;
    JLabel lblInicio, lblFinal;
    JPanel pnlsup, pnlinf;
    double inicio, fin;
    JButton aceptar;
    double origen, finD;
    int noFuncion;

    ceros(int noFuncion, double origen, double finD) {
        super("Ceros");
        this.finD = finD;
        this.origen = origen;
        this.noFuncion = noFuncion;
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
                //Aqui va lo que tiene que hacer al momento de pulsar aceptar.
                //System.out.println("Falta programar");
                if(capturaDatos() == true){
                    try {
                    GestionArchivos objG = new GestionArchivos();
                    String Fceros = inicio + " " + fin;
                    objG.escribir((noFuncion+1), Fceros, "final");
                    ocultarventana();
                    tipoFunciones objFun = new tipoFunciones(noFuncion, fin, finD);   
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

    boolean capturaDatos(){
        try {
            inicio = Double.parseDouble(txtInicio.getText().toString());
            fin = Double.parseDouble(txtFinal.getText().toString());
            if(inicio >= fin){
                JOptionPane.showMessageDialog(this, "El punto inicial no puede ser mayor o igual al final");
                return false;
            }
            if(inicio < origen || inicio> finD){
                JOptionPane.showMessageDialog(this, "El punto inicial no esta dentro del discurso disponible");
                return false;
            }
            if(fin < origen || fin > finD){
                JOptionPane.showMessageDialog(this, "El punto final no esta dentro del discurso disponible");
                return false;
            }
            double longitud = fin - inicio;
            double longitudD = finD - origen;
            if(longitud >= longitudD){
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
