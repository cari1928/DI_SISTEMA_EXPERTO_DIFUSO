package Interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tenistas
 */
public class discurso extends JFrame {

    JDesktopPane escritorio;
    double origen, fin;
    String variable;
    String unidad;
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
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);//PARA CERRAR BIEN LA VENTANA
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(200, 160);
        //this.pack();
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
