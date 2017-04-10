package Interfaces;

import SED.Combinaciones;
import SED.FAM;
import SED.GestionArchivos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Tenistas
 */
public class GUI_Combinaciones extends JFrame {

    GestionArchivos objA = new GestionArchivos();
    int first = -1, last = -1;
    FAM objFAM;
    JPanel panel, panelboton;
    JList listaCombinaciones;
    List<Combinaciones> Combinaciones;
    JLabel msj;
    JButton aceptar;

    GUI_Combinaciones(FAM objFAM) {
        super("GUI_Combinaciones");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));//Crea el espacio para los paneles
        this.Combinaciones = objFAM.listCombinaciones;
        this.objFAM = objFAM;

        m_panel();
        m_panelBoton();

        this.add(panel);
        this.add(panelboton);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
    }

    void m_panelBoton() {
        panelboton = new JPanel();
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ocultarVentana();
                    actualizaArchivoFam();
                    objFAM.actualizaArchivo(); //Aqui es donde se llamara el metodo para crear la estructura con el FAM existente :3
                    JOptionPane.showMessageDialog(null, "Combinaciones creadas y cargadas");
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Combinaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelboton.add(aceptar);
    }

    void ocultarVentana() {
        this.setVisible(false);
    }

    void m_panel() {
        panel = new JPanel();
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Combinaciones"));
        if (Combinaciones != null) {
            listaCombinaciones = new JList(GeneraArray());
            /*String combinaciones[] = {"a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k", //Esto es para pruebas Ignorar en lugar de esto ira la linea de arriba que esta comentada y se comentara esta
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",
                                     "a ^ b ^ c ^ d ^ e ^ h ^ i ^ j ^ k",};*/
            //listaCombinaciones = new JList(combinaciones);
            javax.swing.JScrollPane barra = new javax.swing.JScrollPane(listaCombinaciones);
            listaCombinaciones.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent evt) {
                    if (evt.getValueIsAdjusting()) {
                        return;
                    }
                    int selected = -1;

                    selected = evt.getLastIndex();
                    String etiquetasSalida;
                    etiquetasSalida = JOptionPane.showInputDialog("Ingrese las etiquetas de salida separadas por un -", null);
                    if (etiquetasSalida.indexOf("-") != -1) {
                        String arraySalidas[] = etiquetasSalida.split("-");
                        for (int i = 0; i < arraySalidas.length; i++) {
                            Combinaciones.get(selected).listSalidas.add(arraySalidas[i]);
                        }
                    } else {
                        Combinaciones.get(selected).listSalidas.add(etiquetasSalida);
                    }
                }
            });
            panel.add(barra);
        } else {
            msj = new JLabel("No hay informacion de las combinaciones");
        }
    }

    private String[] GeneraArray() {
        Combinaciones combinacion;
        String[] combinaciones = new String[Combinaciones.size()];
        for (int i = 0; i < Combinaciones.size(); i++) {
            combinacion = Combinaciones.get(i);
            combinaciones[i] = "";
            for (int j = 0; j < combinacion.listCombinaciones.size(); j++) {
                if (j == (combinacion.listCombinaciones.size() - 1)) {
                    combinaciones[i] += combinacion.listCombinaciones.get(j).etiqueta;
                } else {
                    combinaciones[i] += combinacion.listCombinaciones.get(j).etiqueta + " ^ ";
                }
            }
        }
        return combinaciones;
    }

    void actualizaArchivoFam() {
        try {
            List<String> listRegistrosFam;
            List<String> listRegistrosFamNueva = new ArrayList<>();
            List<String> salidasEtiquetas = new ArrayList<>();
            listRegistrosFam = objA.leer("SED/FAM");
            String parts[];
            for (int i = 0; i < Combinaciones.size(); i++) {
                String aux = "";
                if (Combinaciones.get(i).listSalidas.size() == 0) {
                    parts = listRegistrosFam.get(i).split(" ");
                    parts[parts.length - 1] = "0";
                    for (int j = 0; j < parts.length; j++) {
                        aux += parts[j] + " ";
                    }
                    listRegistrosFamNueva.add(aux);
                } else {
                    String salidas = "";
                    salidasEtiquetas = Combinaciones.get(i).listSalidas;
                    for (int j = 0; j < salidasEtiquetas.size(); j++) {
                        if (j < (salidasEtiquetas.size() - 1)) {
                            salidas += salidasEtiquetas.get(j) + "-";
                        } else {
                            salidas += salidasEtiquetas.get(j);
                        }
                    }
                    parts = listRegistrosFam.get(i).split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        if (j < (parts.length - 2) || j == (parts.length - 1)) {
                            aux += parts[j] + " ";
                        }
                        if (j == (parts.length - 2)) {
                            aux += salidas + " ";
                        }
                    }
                    listRegistrosFamNueva.add(aux);
                }
            }
            objA.escribir("SED/FAM", 1, listRegistrosFamNueva.get(0), "nuevo"); //si empieza desde 1 la llave?
            for (int i = 1; i < listRegistrosFamNueva.size(); i++) {
                objA.escribir("SED/FAM", (i + 1), listRegistrosFamNueva.get(i), "final");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        new GUI_Combinaciones(null);
    }*/
}
