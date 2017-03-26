package SED;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tenistas
 */
public class FAM {

    public List<Combinaciones> listCombinaciones;
    public List<Variable> listVariables;

    //No se deben quitar las listas porque son usadas por el método crear modelo
    private List<Triangular> listTria;
    private List<Trapezoide> listTrap;
    private List<semiTriangular> listSemTria;
    private List<semiTrapezoide> listSemTrap;
    private Combinaciones tmpC;
    private int turno;

    //public FAM(List<Triangular> listTria, List<Trapezoide> lisTrap, List<semiTriangular> listSTria, List<semiTrapezoide> listTrap) {
    public FAM() {
        //INICIALIZA ATRIBUTOS
        listCombinaciones = new ArrayList<>();
        tmpC = new Combinaciones();
        turno = 0;
    }

    public void creaCombinaciones() throws IOException {
        GestionArchivos objG = new GestionArchivos();
        List<String> listRegistros = objG.leer("SED/Datos");

        for (String registro : listRegistros) {
            
        }
    }

    //solo genera las combinaciones, no calcula las salidas difusas
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
            //agrega el peso de la regla
            //registro = " " + listCombinaciones.get(0).pesoRegla + " ";

            registro += " 1"; //hasta aquí llega, en otro método se agregan también las etiquetas de salida
            objG.escribir("FAM", i, registro, "final");
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
                        tmpC.pesoRegla = calcPesoRegla(tmpC.listCombinaciones); //calcula el peso de la regla
                        listCombinaciones.add(tmpC); //agrega la info de la combinación generada
                        tmpC = new Combinaciones(); //se limpia para la siguiente combinación
                        break;
                }
                ++turno;
            }
        }
    }

    public void actualizaFAM() {
        GestionArchivos objG = new GestionArchivos();
        List<String> listRegistros;
        Combinaciones objC;
        Etiqueta objE;
        String[] parts, parts2;
        double tmpMem;
        listCombinaciones = new ArrayList<>();

        try {
            listRegistros = objG.leer("FAM");

            for (String registro : listRegistros) {
                parts = registro.split(" ");

                //antecedentes 1|0 EtiquetaSalida
                if (Integer.parseInt(parts[1]) == 1) { //regla seleccionada por el experto
                    objC = new Combinaciones();

                    if (parts.length == 3) { //guarda etiquetas de salida
                        parts2 = parts[2].split("|");
                        for (String etSalida : parts2) {
                            objC.listSalidas.add(etSalida);
                        }
                    }

                    parts = parts[0].split("^");

                    for (String antecedente : parts) {
                        tmpMem = buscaMembresia(antecedente);
                        if (tmpMem != -1) {
                            objE = new Etiqueta();
                            objE.etiqueta = antecedente;
                            objE.membresia = tmpMem;
                            objC.listCombinaciones.add(objE);
                        }
                    }
                    listCombinaciones.add(objC);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double buscaMembresia(String etiqueta) {
        if (listTria != null) {
            for (Triangular objT : listTria) {
                if (objT.etiqueta.equals(etiqueta)) {
                    return objT.membresiaY;
                }
            }
        }

        if (listTrap != null) {
            for (Trapezoide objT : listTrap) {
                if (objT.etiqueta.equals(etiqueta)) {
                    return objT.membresiaY;
                }
            }
        }

        if (listSemTria != null) {
            for (semiTriangular objST : listSemTria) {
                if (objST.etiqueta.equals(etiqueta)) {
                    return objST.membresiaY;
                }
            }
        }

        if (listSemTrap != null) {
            for (semiTrapezoide objSTrap : listSemTrap) {
                if (objSTrap.etiqueta.equals(etiqueta)) {
                    return objSTrap.membresiaY;
                }
            }
        }

        return -1;
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

    private double calcPesoRegla(List<Etiqueta> listC) {
        double minimo = listC.get(0).membresia; //toma la membresía del primer elemento
        for (Etiqueta objE : listC) {
            if (minimo > objE.membresia) { //compara con las demás membresías
                minimo = objE.membresia; //guarda el más pequeño
            }
        }
        return minimo; //regresa el más pequeño
    }

}
