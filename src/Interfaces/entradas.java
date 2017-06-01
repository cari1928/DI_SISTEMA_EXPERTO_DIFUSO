package Interfaces;

import RNA.Patron;
import RNA.extras;
import SED.GestionArchivos;
import SED.MotorInferencia;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Tenistas
 */
public class entradas extends JFrame {

    extras objE = new extras();
    int cont;
    boolean finalizar = false;
    private List<String> variables;
    private String variableSal;
    final private String directoio = "SED";
    private GestionArchivos objG = new GestionArchivos();
    private List<String> etiquetasE = new ArrayList<>();
    private List<String> etiquetasY = new ArrayList<>();
    private JLabel[] labels;
    private JTextField[] txts;
    private JPanel pnlSupIzq;
    private JPanel pnlSupDer;
    private JPanel pnlSup;
    private JPanel pnlinf;
    private Patron objP;
    private boolean fuzzy;

    public entradas(int cont, boolean fuzzy) {
        super("Patrones");
        this.cont = cont;
        this.fuzzy = fuzzy;
        obtenerEtiquetasE();
        inicializaListas();
        m_panelSupIzq();

        if (!fuzzy) {
            m_panelSupDer();
        }

        m_panelSup();
        panelInf();

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(pnlSup);
        //   this.add(pnlSupDer);
        this.add(pnlinf);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        //this.setSize(200, 460);

    }

    void obtenerEtiquetasE() {
        List<String> datos;
        String[] parts;
        try {
            variables = objG.leer(directoio + "\\Datos");
            for (int i = 0; i < variables.size(); i++) {
                if (variables.get(i).contains("-S")) {
                    variableSal = variables.get(i);
                    variables.remove(i);
                    datos = objG.leer(directoio + "\\" + variableSal.trim());
                    datos.remove(0);
                    for (int j = 0; j < datos.size(); j++) {
                        //aqui sacara los nombres de als funciones de la variable salida
                        parts = datos.get(j).split(" ");
                        if (parts[0].equals("Triangular")) {
                            etiquetasY.add(parts[2]);
                        } else {
                            etiquetasY.add(parts[3]);
                        }
                    }
                } else {
                    datos = objG.leer(directoio + "\\" + variables.get(i).trim());
                    datos.remove(0);
                    for (int j = 0; j < datos.size(); j++) {
                        //aqui sacara los nombres de als funciones de la variable salida
                        parts = datos.get(j).split(" ");
                        if (parts[0].equals("Triangular")) {
                            etiquetasE.add(parts[2]);
                        } else {
                            etiquetasE.add(parts[3]);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void inicializaListas() {
        int aux = 0;
        labels = new JLabel[variables.size() + 1];
        txts = new JTextField[variables.size() + 1];

        for (int i = 0; i < variables.size(); i++) {
            labels[i] = new JLabel(variables.get(i).trim());
            txts[i] = new JTextField();
        }

        labels[labels.length - 1] = new JLabel(variableSal.trim());
        txts[txts.length - 1] = new JTextField();
        txts[txts.length - 1].setText("");

    }

    void m_panelSupIzq() {
        pnlSupIzq = new JPanel();//Crea el espacio para el panel
        pnlSupIzq.setLayout(new GridLayout(variables.size(), 2));//Declara como irán los botones en el panel
        pnlSupIzq.setBorder(new TitledBorder(new EtchedBorder(), "Entradas"));
        for (int i = 0; i < variables.size(); i++) {
            pnlSupIzq.add(labels[i]);
            pnlSupIzq.add(txts[i]);
        }
    }

    void m_panelSupDer() {
        pnlSupDer = new JPanel();//Crea el espacio para el panel
        pnlSupDer.setLayout(new GridLayout(1, 2));//Declara como irán los botones en el panel
        pnlSupDer.setBorder(new TitledBorder(new EtchedBorder(), "Salida Deseada"));
        pnlSupDer.add(labels[(labels.length - 1)]);
        pnlSupDer.add(txts[(txts.length - 1)]);

    }

    void m_panelSup() {
        pnlSup = new JPanel();//Crea el espacio para el panel
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        pnlSup.add(pnlSupIzq);

        if (!fuzzy) {
            pnlSup.add(pnlSupDer);
        }

    }

    void panelInf() {
        pnlinf = new JPanel();//Crea el espacio para el panel
        //pnlinf.setLayout(new GridLayout(1, 1));//Declara como irán los botones en el panel
        pnlinf.setBorder(new TitledBorder(new EtchedBorder(), "Botón"));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Double> membresiaE = new ArrayList<>();
                List<Double> membresiaY = new ArrayList<>();
                MotorInferencia objM = new MotorInferencia();

                //while (cont < 4) {
                try {
                    for (int i = 0; i < variables.size(); i++) {

                        objM.fuzzyfication(Double.parseDouble(txts[i].getText()), directoio + "\\" + labels[i].getText().trim()); //Fusifica los falores de entrada
                        for (int j = 0; j < objM.listaMenbrecia.size(); j++) { //obtiene los grados de membresia de cada etiqueta
                            membresiaE.add(Double.parseDouble(objM.listaMenbrecia.get(j)));
                        }
                    }
                    objM.fuzzyfication(Double.parseDouble(txts[txts.length - 1].getText()), directoio + "\\" + labels[labels.length - 1].getText().trim()); //Fusifica los falores de entrada
                    for (int j = 0; j < objM.listaMenbrecia.size(); j++) { //obtiene los grados de membresia de cada etiqueta
                        membresiaY.add(Double.parseDouble(objM.listaMenbrecia.get(j)));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                objP = new Patron(membresiaE, membresiaY);
                objE.arrayP[cont] = objP;
                ocultar();
                //new entradas(cont + 1, arrayP);
                //}
                if (cont == 0) {
                    //System.out.println("Ya acabe");
                    //Se manda a llamar la siguiente interfacese
                    datosExtra objDE = new datosExtra();
                    objDE.setVisible(true);
                }
            }
        });
        pnlinf.add(btnAceptar);

    }

    void ocultar() {
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new entradas();
    }

}
