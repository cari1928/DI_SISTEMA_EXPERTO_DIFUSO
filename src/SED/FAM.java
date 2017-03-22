package SED;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tenistas
 */
public class FAM {

    List<Combinaciones> listCombinaciones;

    private final List<Triangular> listTria;
    private final List<Trapezoide> listTrap;
    private final List<semiTriangular> listSemTria;
    private final List<semiTrapezoide> listSemTrap;
    private Combinaciones tmpC;
    private int turno;

    public FAM(List<Triangular> listTria, List<Trapezoide> lisTrap, List<semiTriangular> listSTria, List<semiTrapezoide> listTrap) {
        this.listTria = listTria;
        this.listTrap = lisTrap;
        this.listSemTria = listSTria;
        this.listSemTrap = listTrap;

        listCombinaciones = new ArrayList<>();
        tmpC = new Combinaciones();
        turno = 0;
    }

    //solo genera las combinaciones, no calcula el peso de la regla ni las salidas difusas
    public void crear() throws IOException {
        String registro;
        Combinaciones objC;
        GestionArchivos objG = new GestionArchivos();

        //genera las combinaciones
        creaCombinaciones(listTria, 0, listTrap);

        //escribe las combinaciones en el archivo FAM
        for (int i = 0; i < listCombinaciones.size(); i++) {
            registro = ""; //limpia el registro
            objC = listCombinaciones.get(i);

            for (int j = 0; j < objC.listCombinaciones.size(); j++) {
                registro += objC.listCombinaciones.get(j);

                if (j < objC.listCombinaciones.size() - 1) {
                    registro += "^";
                }
            }

            registro += " 1"; //hasta aquí llega, en otro método se agregan también las etiquetas de salida
            objG.escribir(i, registro, "final");
        }

    }

    //crea la pura combinación
    //no calcula el peso de la regla ni las salidas difusas
    private void creaCombinaciones(List lista, int position, List listSig) {
        Etiqueta tmpEt;

        if (lista != null) {
            //FOR RECURSIVO
            for (int i = position; i < lista.size(); i++) {

                tmpEt = obtenerEtiqueta(lista, i); //se genera la etiqueta de la figura
                tmpC.listCombinaciones.add(tmpEt); //se guarda la etiqueta en una lista

                switch (turno) {
                    case 0:
                        //listSig = trapezoide
                        creaCombinaciones(listSig, i, listSemTria);
                        break;
                    case 1:
                        //listSig = listSemTria
                        creaCombinaciones(listSig, i, listSemTrap);
                        break;
                    case 2:
                        //listSig = listSemTrap
                        creaCombinaciones(listSig, i, null);
                    case 3:
                        //ya recorrió todas las listas en la posicion i
                        listCombinaciones.add(tmpC); //agrega la combinación generada
                        tmpC = new Combinaciones(); //se limpia para la siguiente combinación
                        break;
                }
                ++turno;
            }
        }

    }

    public void actualizaFAM() {

    }

    private Etiqueta obtenerEtiqueta(List lista, int i) {
        Triangular tmpTria;
        Trapezoide tmpTrap;
        semiTriangular tmpSemTria;
        semiTrapezoide tmpSemTrap;
        Etiqueta objE = new Etiqueta();

        //crea el objeto según su tipo de forma
        switch (turno) {
            case 0:
                tmpTria = (Triangular) lista.get(i);
                objE.etiqueta = tmpTria.etiqueta;
                objE.membresia = tmpTria.membresiaY;
                break;
            case 1:
                tmpTrap = (Trapezoide) lista.get(i);
                objE.etiqueta = tmpTrap.etiqueta;
                objE.membresia = tmpTrap.membresiaY;
                break;
            case 2:
                tmpSemTria = (semiTriangular) lista.get(i);
                objE.etiqueta = tmpSemTria.etiqueta;
                objE.membresia = tmpSemTria.membresiaY;
                break;
            case 3:
                tmpSemTrap = (semiTrapezoide) lista.get(i);
                objE.etiqueta = tmpSemTrap.etiqueta;
                objE.membresia = tmpSemTrap.membresiaY;
        }

        return objE;
    }

}
