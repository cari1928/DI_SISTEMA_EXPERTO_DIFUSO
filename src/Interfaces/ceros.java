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
public class ceros extends JFrame {

    JTextField txtInicio, txtFinal;
    JLabel lblInicio, lblFinal;
    JPanel pnlsup, pnlinf;
    String inicio, fin;
    JButton aceptar;

    ceros() {
        super("Ceros");
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
