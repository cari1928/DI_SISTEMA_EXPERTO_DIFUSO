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

    public FAM() {
        listCombinaciones = new ArrayList<>();
        listVariables = new ArrayList<>();
    }

    //solo genera las combinaciones, no calcula las salidas difusas
    public void crear(Variable varIni, int posNext, Combinaciones tmpC, Combinaciones antC) throws IOException {
        String registro;
        GestionArchivos objG = new GestionArchivos();
        Etiqueta tmpE;

        //checa los triangulos
        if (varIni.listTriangular != null) {
            for (int i = 0; i < varIni.listTriangular.size(); i++) {

                if (Math.abs(antC.listCombinaciones.size() - tmpC.listCombinaciones.size()) <= 1) {
                    antC = pasaValores(tmpC);
                } else {
                    tmpC = pasaValores(antC);
                    antC = pasaValores(tmpC);
                }

                tmpE = obtenerEtiqueta(varIni.listTriangular, i);
                tmpC.listCombinaciones.add(tmpE);

                condiciones(varIni, posNext, tmpE, tmpC, antC);
                if (posNext == listVariables.size()) {
                    listCombinaciones.add(tmpC); //agrega la info de la combinación generada
                    tmpC = antC;
                }
                if (varIni == listVariables.get(0)) {
                    tmpC = new Combinaciones();
                }
            }

        }

        //checa los trapezoides
        if (varIni.listTrapezoide != null) {
            for (int i = 0; i < varIni.listTrapezoide.size(); i++) {
                if (Math.abs(antC.listCombinaciones.size() - tmpC.listCombinaciones.size()) <= 1) {
                    antC = pasaValores(tmpC);
                } else {
                    tmpC = pasaValores(antC);
                    antC = pasaValores(tmpC);
                }

                tmpE = obtenerEtiqueta(varIni.listTrapezoide, i);
                tmpC.listCombinaciones.add(tmpE);

                condiciones(varIni, posNext, tmpE, tmpC, antC);

                if (posNext == listVariables.size()) {
                    listCombinaciones.add(tmpC); //agrega la info de la combinación generada
                    tmpC = antC;
                }

                if (varIni == listVariables.get(0)) {
                    tmpC = new Combinaciones();
                }
            }
        }
        //checa los semitriangulos
        if (varIni.listSemiTriangular != null) {
            for (int i = 0; i < varIni.listSemiTriangular.size(); i++) {

                if (Math.abs(antC.listCombinaciones.size() - tmpC.listCombinaciones.size()) <= 1) {
                    antC = pasaValores(tmpC);
                } else {
                    tmpC = pasaValores(antC);
                    antC = pasaValores(tmpC);
                }

                tmpE = obtenerEtiqueta(varIni.listSemiTriangular, i);
                tmpC.listCombinaciones.add(tmpE);

                condiciones(varIni, posNext, tmpE, tmpC, antC);

                if (posNext == listVariables.size()) {
                    listCombinaciones.add(tmpC); //agrega la info de la combinación generada
                    tmpC = antC;
                }

                if (varIni == listVariables.get(0)) {
                    tmpC = new Combinaciones();
                }
            }

        }
        //checa los semitrapezoides
        if (varIni.listSemiTrapezoide != null) {
            for (int i = 0; i < varIni.listSemiTrapezoide.size(); i++) {

                if (Math.abs(antC.listCombinaciones.size() - tmpC.listCombinaciones.size()) <= 1) {
                    antC = pasaValores(tmpC);
                } else {
                    tmpC = pasaValores(antC);
                    antC = pasaValores(tmpC);
                }

                tmpE = obtenerEtiqueta(varIni.listSemiTrapezoide, i);
                tmpC.listCombinaciones.add(tmpE);

                condiciones(varIni, posNext, tmpE, tmpC, antC);

                if (posNext == listVariables.size()) {
                    listCombinaciones.add(tmpC); //agrega la info de la combinación generada
                    tmpC = antC;
                }

                if (varIni == listVariables.get(0)) {
                    tmpC = new Combinaciones();
                }
            }
        }

    }

    public Combinaciones pasaValores(Combinaciones tmpC) {
        Combinaciones antC = new Combinaciones();
        for (int i = 0; i < tmpC.listCombinaciones.size(); i++) {
            antC.listCombinaciones.add(tmpC.listCombinaciones.get(i));
        }
        return antC;
    }

    //checa que sea la variable inicial
    //siendo asi´ejecuta cierta lógica
    //de lo contrario ejecuta otra
    public void condiciones(Variable varIni, int posNext, Etiqueta tmpE, Combinaciones objC, Combinaciones antC) throws IOException {
        if (posNext < listVariables.size()) {
            crear(listVariables.get(posNext), posNext + 1, objC, antC);
        }
        if (varIni != listVariables.get(0)) {
            if (varIni == listVariables.get(0)) {
                objC.listCombinaciones.add(tmpE);
            }
        }
    }

//crea la pura combinación
//no calcula el peso de la regla ni las salidas difusas
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

    private Etiqueta obtenerEtiqueta(List list, int i) {
        Etiqueta tmp;
        tmp = obtenerEtTriangular(list, i);
        if (tmp != null) {
            return tmp;
        }

        tmp = obtenerEtTrapezoide(list, i);
        if (tmp != null) {
            return tmp;
        }

        tmp = obtenerEtSemiTriangular(list, i);
        if (tmp != null) {
            return tmp;
        }

        tmp = obtenerEtSemiTrapezoide(list, i);
        if (tmp != null) {
            return tmp;
        }

        return null;
    }

    private Etiqueta obtenerEtTriangular(List<Triangular> listTria, int i) {
        Triangular tmpTria;
        Etiqueta objE = new Etiqueta();

        try {
            tmpTria = listTria.get(i);
            objE.etiqueta = tmpTria.etiqueta;
            objE.membresia = tmpTria.membresiaY;
            return objE;
        } catch (Exception e) {
            return null;
        }
    }

    private Etiqueta obtenerEtTrapezoide(List<Trapezoide> listTrap, int i) {
        Trapezoide tmpTrap;
        Etiqueta objE = new Etiqueta();

        try {
            tmpTrap = listTrap.get(i);
            objE.etiqueta = tmpTrap.etiqueta;
            objE.membresia = tmpTrap.membresiaY;
            return objE;
        } catch (Exception e) {
            return null;
        }
    }

    private Etiqueta obtenerEtSemiTriangular(List<semiTriangular> listSemiTria, int i) {
        semiTriangular tmpSemTria;
        Etiqueta objE = new Etiqueta();

        try {
            tmpSemTria = listSemiTria.get(i);
            objE.etiqueta = tmpSemTria.etiqueta;
            objE.membresia = tmpSemTria.membresiaY;
            return objE;
        } catch (Exception e) {
            return null;
        }
    }

    private Etiqueta obtenerEtSemiTrapezoide(List<semiTrapezoide> listSemiTrap, int i) {
        semiTrapezoide tmpSemTrap;
        Etiqueta objE = new Etiqueta();

        try {
            tmpSemTrap = listSemiTrap.get(i);
            objE.etiqueta = tmpSemTrap.etiqueta;
            objE.membresia = tmpSemTrap.membresiaY;
            return objE;
        } catch (Exception e) {
            return null;
        }
    }

    public double calcPesoRegla(List<Etiqueta> listC) {
        double minimo = listC.get(0).membresia; //toma la membresía del primer elemento
        for (Etiqueta objE : listC) {
            if (minimo > objE.membresia) { //compara con las demás membresías
                minimo = objE.membresia; //guarda el más pequeño
            }
        }
        return minimo; //regresa el más pequeño
    }

    public void crearArchivo() throws IOException {
        GestionArchivos objG = new GestionArchivos();
        Combinaciones objC;
        String registro = "", parte = "nuevo";

        for (int i = 0; i < listCombinaciones.size(); i++) {
            objC = listCombinaciones.get(i);

            for (Etiqueta objE : objC.listCombinaciones) {
                registro += objE.etiqueta + "," + objE.membresia + " ";
            }
            registro += " 1";

            if (i > 0) {
                parte = "final";
            }

            objG.escribir("SED/FAM", i, registro, parte);
            registro = "";
        }

    }

    public void actualizaArchivo() throws IOException {
        GestionArchivos objG = new GestionArchivos();
        List<String> listFAM = objG.leer("SED/FAM");
        Combinaciones objC;
        Etiqueta objE;
        String[] parts, minip;
        String part;
        double valor;

        listCombinaciones = new ArrayList<>();
        for (String listF : listFAM) {
            objC = new Combinaciones();

            parts = listF.split(" ");

            if (parts[parts.length - 1].charAt(0) != '1') {
                valor = Double.parseDouble(parts[parts.length - 2]);
            } else {
                valor = Double.parseDouble(parts[parts.length - 1]);
            }

            //checa el último elemento				
            if (valor == 1) { //verifica que sea un 1				
                for (int i = 0; i < parts.length; i++) {
                    part = parts[i];

                    if (part.contains(",")) {
                        objE = new Etiqueta();
                        minip = part.split(",");
                        objE.etiqueta = minip[0];
                        objE.membresia = Double.parseDouble(minip[1]);
                        objC.listCombinaciones.add(objE); //agrega etiquetas		      		

                    } else if (part.contains("-")) {
                        minip = part.split("-");
                        for (String m : minip) {
                            objC.listSalidas.add(m); //agrega etiquetas de salida				
                        }
                    } else if (i != parts.length - 1) {
                        objC.listSalidas.add(part);
                    }
                }
                listCombinaciones.add(objC);
            }
        }
    }
    
        public List<String> buscaSalidas(){
        boolean bandera = true;
        String etiquetaSalidaAux="";
        List<String> salidas = new ArrayList<>();
        for (int i = 0; i < listCombinaciones.size(); i++) {
            for (int j = 0; j < listCombinaciones.get(i).listSalidas.size(); j++) {
                etiquetaSalidaAux = listCombinaciones.get(i).listSalidas.get(j);
                bandera = true;
                for (int k = 0; k < salidas.size(); k++) {
                    if(salidas.get(k).equals(etiquetaSalidaAux)){
                        bandera = false; 
                    }
                }
                if(bandera){
                    salidas.add(etiquetaSalidaAux);
                }
            }
        }
        return salidas;
    }
    
    public List<Etiqueta> obtenerReult(List<String> salidas){
        List<Etiqueta> listEtqSalida = new ArrayList<>();
        Etiqueta objE;
        for (int i = 0; i < salidas.size(); i++) {  //Recorrer cada salida
            objE = new Etiqueta();
            objE.etiqueta = salidas.get(i); //Guardo la salida en un objeto Etiqueta
            objE.membresia = -1000000000;
            for (int j = 0; j < listCombinaciones.size(); j++) { //Recorrer cada combinacion
                for (int k = 0; k < listCombinaciones.get(j).listSalidas.size(); k++) { //Recorrer cada etiqueta de salida para las combinaciones
                    if(listCombinaciones.get(j).listSalidas.get(k).equals(objE.etiqueta)){ //Comparar etiqueta de la combinacion con etiqueta del parametro
                        if(listCombinaciones.get(j).pesoRegla > objE.membresia){
                            objE.membresia = listCombinaciones.get(j).pesoRegla;
                            break;
                        }
                    }
                }
            }
            listEtqSalida.add(objE);
        }
        return listEtqSalida;
    }
    
}
