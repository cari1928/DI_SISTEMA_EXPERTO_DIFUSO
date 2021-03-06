package SED;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tenistas
 */
public class MotorInferencia {

    public UniversoDiscurso objU;
    public List<Triangular> listTriangular;
    public List<Trapezoide> listTrapezoide;
    public List<semiTriangular> listSemiTriangular;
    public List<semiTrapezoide> listSemiTrapezoide;
    public double punto;
    public String resultado, rutaArchivo;
    public List<String> listaMenbrecia = new ArrayList<>();

    public MotorInferencia() {
        resultado = "";
    }

    public String getResultado() {
        return resultado;
    }

    public void fuzzyfication(double punto, String variable) throws IOException {
        this.punto = punto;
        this.rutaArchivo = variable;
        GestionArchivos objG = new GestionArchivos();
        listaMenbrecia = new ArrayList<>();
        crearModelo(rutaArchivo);

        if (listTriangular != null) {
            for (Triangular objTria : listTriangular) {
                //checar que el punto ingresado por el usuario esté entre los puntos no críticos de la figura
                if (objTria.puntoIzq[0] < punto && punto < objTria.puntoDer[0]) {

                    try {
                        objTria.membresiaY = calcularY(objTria);
                        resultado += objTria.etiqueta + " " + objTria.membresiaY + " "; //fase de pruebas todavía
                        String registro = objG.obtenerRegistroByID(this.rutaArchivo, objTria.turno), nuevoRegistro = "";
                        String parts[] = registro.split(" ");

                        for (int i = 0; i < parts.length; i++) {
                            if (i == 3 && parts.length == 4) {
                                nuevoRegistro += parts[i] + " " + objTria.membresiaY;
                            } else if (i == 4 && parts.length == 5) {
                                nuevoRegistro += objTria.membresiaY;
                            } else {
                                nuevoRegistro += parts[i] + " ";
                            }
                        }
                        listaMenbrecia.add(objTria.membresiaY+"");
                        objG.actualizar(this.rutaArchivo, objTria.turno, nuevoRegistro);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        if (listTrapezoide != null) {
            for (Trapezoide objTrap : listTrapezoide) {
                //checar que el punto ingresado por el usuario esté entre los puntos no críticos de la figura
                if (objTrap.puntoIzq[0] < punto && punto < objTrap.puntoDer[0]) {
                    try {
                        objTrap.membresiaY = calcularY(objTrap);
                        resultado += objTrap.etiqueta + " " + objTrap.membresiaY + "\n"; //fase de pruebas todavía
                        String registro = objG.obtenerRegistroByID(this.rutaArchivo, objTrap.turno), nuevoRegistro = "";
                        String parts[] = registro.split(" ");
                        for (int i = 0; i < parts.length; i++) {
                            if (i == 4 && parts.length == 5) {
                                nuevoRegistro += parts[i] + " " + objTrap.membresiaY;
                            } else if (i == 5 && parts.length == 6) {
                                nuevoRegistro += objTrap.membresiaY;
                            } else {
                                nuevoRegistro += parts[i] + " ";
                            }
                        }
                        listaMenbrecia.add(objTrap.membresiaY+"");
                        objG.actualizar(this.rutaArchivo, objTrap.turno, nuevoRegistro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    listaMenbrecia.add("0");
                }
            }
        }

        if (listSemiTriangular != null) {
            for (semiTriangular objSemTria : listSemiTriangular) {
                //checar orientacion
                if (objSemTria.orientacion == 'd') {
                    if (objSemTria.puntoC[0] > punto && punto > objSemTria.punto2[0]) {
                        objSemTria.membresiaY = calcularY(objSemTria);
                        resultado += objSemTria.etiqueta + " " + objSemTria.membresiaY + "\n";
                    }else objSemTria.membresiaY = 0;
                } else {
                    if (objSemTria.punto2[0] > punto && punto > objSemTria.puntoC[0]) {
                        objSemTria.membresiaY = calcularY(objSemTria);
                        resultado += objSemTria.etiqueta + " " + objSemTria.membresiaY + "\n";
                    } else objSemTria.membresiaY = 0;
                }
                try {
                    String registro = objG.obtenerRegistroByID(this.rutaArchivo, objSemTria.turno), nuevoRegistro = "";
                    String parts[] = registro.split(" ");
                    for (int i = 0; i < parts.length; i++) {
                        if (i == 5 && parts.length == 6) {
                            nuevoRegistro += parts[i] + " " + objSemTria.membresiaY;
                        } else if (i == 6 && parts.length == 7) {
                            nuevoRegistro += objSemTria.membresiaY;
                        } else {
                            nuevoRegistro += parts[i] + " ";
                        }
                    }
                    listaMenbrecia.add(objSemTria.membresiaY+"");
                    objG.actualizar(this.rutaArchivo, objSemTria.turno, nuevoRegistro);
                } catch (Exception e) {
                }
            }
        }

        if (listSemiTrapezoide != null) {
            for (int i = 0; i < listSemiTrapezoide.size(); i++) {
                semiTrapezoide objSemTrap = listSemiTrapezoide.get(i);
                objSemTrap.membresiaY = calcularY(objSemTrap);
                try {
                    String registro = objG.obtenerRegistroByID(this.rutaArchivo, objSemTrap.turno), nuevoRegistro = "";
                    String parts[] = registro.split(" ");
                    for (int j = 0; j < parts.length; j++) {
                        if (j == 4 && parts.length == 5) {
                            nuevoRegistro += parts[j] + " " + objSemTrap.membresiaY;
                        } else if (j == 5 && parts.length == 6) {
                            nuevoRegistro += objSemTrap.membresiaY;
                        } else {
                            nuevoRegistro += parts[j] + " ";
                        }
                    }
                    listaMenbrecia.add(objSemTrap.membresiaY+"");
                    objG.actualizar(this.rutaArchivo, objSemTrap.turno, nuevoRegistro);
                } catch (Exception e) {
                }
            }
        }

        if (resultado.equals("")) {
            resultado = "0";
        }
    }

    public void crearModelo(String ruta) {
        GestionArchivos objG = new GestionArchivos();
        List<String> listRegistros;
        Triangular objTri;
        Trapezoide objTra;
        semiTriangular objSemTri;
        semiTrapezoide objSemTrap;
        String registro;
        String[] parts;
        int contFigura = 1;
        double distancia;

        iniListas();

        try {
            listRegistros = objG.leer(ruta);
            for (int i = 0; i < listRegistros.size(); i++) {
                registro = listRegistros.get(i);
                parts = registro.split(" ");

                switch (parts[0]) {
                    case "Ceros":
                        ++contFigura;
                        break;

                    case "Triangular": //0 = x, 1 = y
                        objTri = new Triangular();
                        objTri.puntoC[0] = Double.parseDouble(parts[1]);
                        objTri.puntoC[1] = 1;

                        //cálculo de la X de los puntos izq y der
                        distancia = Math.abs(objTri.puntoC[0] - Double.parseDouble(parts[parts.length - 2]));
                        objTri.puntoIzq[0] = objTri.puntoC[0] - distancia;
                        objTri.puntoDer[0] = objTri.puntoC[0] + distancia;

                        //Y en 0's
                        objTri.puntoIzq[1] = 0;
                        objTri.puntoDer[1] = 0;

                        objTri.etiqueta = parts[2];
                        objTri.turno = contFigura;
                        objTri.membresiaY = Double.parseDouble(parts[4]);

                        listTriangular.add(objTri);
                        ++contFigura;
                        break;

                    case "Trapezoide":
                        objTra = new Trapezoide();

                        //X de puntos críticos
                        objTra.puntoC1[0] = Double.parseDouble(parts[1]);
                        objTra.puntoC2[0] = Double.parseDouble(parts[2]);

                        //Y de puntos críticos
                        objTra.puntoC1[1] = 1;
                        objTra.puntoC2[1] = 1;

                        //cálculo de la X de los puntos izq y der
                        distancia = Math.abs(objTra.puntoC1[0] - Double.parseDouble(parts[parts.length - 2]));
                        objTra.puntoIzq[0] = objTra.puntoC1[0] - distancia;
                        objTra.puntoDer[0] = objTra.puntoC2[0] + distancia;

                        objTra.etiqueta = parts[3];
                        objTra.turno = contFigura;
                        objTra.membresiaY = Double.parseDouble(parts[5]);

                        ++contFigura;
                        listTrapezoide.add(objTra);
                        break;

                    case "SemiTriangular":
                        objSemTri = new semiTriangular();

                        //punto critico
                        objSemTri.puntoC[0] = Double.parseDouble(parts[1]);
                        objSemTri.puntoC[1] = 1;

                        objSemTri.longitud = Double.parseDouble(parts[2]);
                        objSemTri.orientacion = parts[3].charAt(0);
                        objSemTri.etiqueta = parts[4];

                        //el otro punto
                        if (objSemTri.orientacion == 'i') { //izquierda
                            //objSemTri.punto2[0] = objSemTri.puntoC[0] - objSemTri.longitud;
                            objSemTri.punto2[0] = objSemTri.puntoC[0] + objSemTri.longitud;
                        } else {
                            objSemTri.punto2[0] = objSemTri.puntoC[0] - objSemTri.longitud;
                            //objSemTri.punto2[0] = objSemTri.puntoC[0] + objSemTri.longitud;
                        }
                        objSemTri.punto2[1] = 0;
                        objSemTri.turno = contFigura;
                        objSemTri.membresiaY = Double.parseDouble(parts[6]);
                        listSemiTriangular.add(objSemTri);
                        ++contFigura;
                        break;

                    case "SemiTrapezoide":
                        objSemTrap = new semiTrapezoide();

                        //punto critico
                        objSemTrap.puntoC[0] = Double.parseDouble(parts[1]);
                        objSemTrap.puntoC[1] = 1;

                        objSemTrap.orientacion = parts[2].charAt(0);
                        objSemTrap.etiqueta = parts[3];

                        //otro punto
                        if (objSemTrap.orientacion == 'i') {
                            distancia = objSemTrap.puntoC[0] - objU.inicio;
                            objSemTrap.punto2[0] = objSemTrap.puntoC[0] + distancia;
                        } else {
                            //mucho cuidado
                            //distancia = objU.fin - objSemTrap.puntoC[0];
                            //objSemTrap.punto2[0] = objSemTrap.puntoC[0] - distancia;

                            distancia = objSemTrap.puntoC[0] - Double.parseDouble(parts[parts.length - 2]);
                            objSemTrap.punto2[0] = objSemTrap.puntoC[0] - distancia;
                        }
                        objSemTrap.punto2[1] = 0;
                        objSemTrap.turno = contFigura;
                        listSemiTrapezoide.add(objSemTrap);
                        objSemTrap.membresiaY = Double.parseDouble(parts[5]);

                        ++contFigura;
                        break;

                    default: //universo de discurso
                        objU = new UniversoDiscurso();
                        objU.inicio = Double.parseDouble(parts[0]);
                        objU.fin = Double.parseDouble(parts[1]);
                        objU.unidad = parts[2];
                        objU.variable = parts[3];
                }
            }

            nullListas(); //vuelve nulas las listas que no tienen nada
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace(); //para pruebas
            JOptionPane.showMessageDialog(null, "CREACIÓN DEL MODEO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniListas() {
        listTriangular = new ArrayList<>();
        listTrapezoide = new ArrayList<>();
        listSemiTriangular = new ArrayList<>();
        listSemiTrapezoide = new ArrayList<>();
    }

    private void nullListas() {
        if (listTriangular.isEmpty()) {
            listTriangular = null;
        }

        if (listTrapezoide.isEmpty()) {
            listTrapezoide = null;
        }

        if (listSemiTriangular.isEmpty()) {
            listSemiTriangular = null;
        }

        if (listSemiTrapezoide.isEmpty()) {
            listSemiTrapezoide = null;
        }
    }

    private double calcularY(Triangular objTria) {
        double x1, y1, x2, y2, x3, y3;

        x1 = objTria.puntoIzq[0];
        y1 = objTria.puntoIzq[1];
        x2 = objTria.puntoC[0];
        y2 = objTria.puntoC[1];
        x3 = objTria.puntoDer[0];
        y3 = objTria.puntoDer[1];

        if (x1 <= punto && punto <= x2) {
            return (((punto - x1) / (x2 - x1)) * (y2 - y1) + y1);
        }
        if (x2 <= punto && punto <= x3) {
            return (((punto - x2) / (x3 - x2)) * (y3 - y2) + y2);
        }
        return -1;
    }

    private double calcularY(Trapezoide objT) {
        double x1, y1, x2, y2, y;
        x1 = y1 = x2 = y2 = 0;

        //verificar que el punto esté entre el puntoIzq y el puntoC1
        if (objT.puntoIzq[0] < punto && punto < objT.puntoC1[0]) {
            x1 = objT.puntoIzq[0];
            y1 = objT.puntoIzq[1];
            x2 = objT.puntoC1[0];
            y2 = objT.puntoC1[1];

        } else if (objT.puntoC2[0] < punto && punto < objT.puntoDer[0]) { //verificar que el punto esté entre el puntoDer y el puntoC2
            x1 = objT.puntoDer[0];
            y1 = objT.puntoDer[1];
            x2 = objT.puntoC2[0];
            y2 = objT.puntoC2[1];

        } else if (objT.puntoC1[0] <= punto && punto <= objT.puntoC2[0]) { //verificar que el punto esté entre los dos puntos criticos
            return 1;
        }

        return ((punto - x1) / (x2 - x1)) * (y2 - y1) + y1;
    }

    private double calcularY(semiTriangular objSemTria) {
        double x1, y1, x2, y2;

        x1 = objSemTria.punto2[0];
        y1 = objSemTria.punto2[1];
        x2 = objSemTria.puntoC[0];
        y2 = objSemTria.puntoC[1];

        return ((punto - x1) / (x2 - x1)) * (y2 - y1) + y1;
    }

    private double calcularY(semiTrapezoide objSTrap) {
        char orientacion = objSTrap.orientacion;
        double y = 0;
        if (orientacion == 'i') {
            if (objU.inicio < punto && punto < objSTrap.punto2[0]) {
                //Esta dentro izquierda
                if (objSTrap.puntoC[0] < punto && punto < objSTrap.punto2[0]) {
                    double p1[] = {objSTrap.puntoC[0], objSTrap.puntoC[1]};
                    double p2[] = {objSTrap.punto2[0], objSTrap.punto2[1]};
                    y = (punto - p1[0]) / (p2[0] - p1[0]) * (p2[1] - p1[1]) + (p1[1]);
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                } else {
                    //Esta en la linea Recta de 1's
                    y = 1;
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                }
            } else {
                //El punto no pertenece a la funcion
                y = 0;
                resultado += objSTrap.etiqueta + " Y = " + y + "\n";
            }
        } else {
            if (punto < objU.fin && punto > objSTrap.punto2[0]) {
                //Esta dentro derecha
                if (punto < objSTrap.puntoC[0] && punto > objSTrap.punto2[0]) {
                    double p1[] = {objSTrap.puntoC[0], objSTrap.puntoC[1]};
                    double p2[] = {objSTrap.punto2[0], objSTrap.punto2[1]};
                    y = (punto - p1[0]) / (p2[0] - p1[0]) * (p2[1] - p1[1]) + (p1[1]);
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                } else {
                    //Esta en la linea recta de 1's
                    y = 1;
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                }
            } else {
                //El punto no pertenece a la funcion
                y = 0;
                resultado += objSTrap.etiqueta + " Y = " + y + "\n";
            }
        }
        return y;
    }

    //-------------------------------------------------------------------------------------------------
    public List<String[]> obtenerXFormulaGuss(String Ruta, List<Etiqueta> listaEtiquetasSalida) {
        List<String[]> puntosFunciones;
        crearModelo(Ruta);
        puntosFunciones = formulaGuss(listaEtiquetasSalida);
        return puntosFunciones;
    }

    public List<String[]> formulaGuss(List<Etiqueta> listaEtiquetasSalida) {
        List<String[]> listaPuntosX = new ArrayList<>();
        double x1, y1, x2, y2, x3, y3;
        int pos = 0;
        GestionArchivos objG = new GestionArchivos();

        while (pos < listaEtiquetasSalida.size()) {
            if (listTriangular != null) {
                for (Triangular objTria : listTriangular) {

                    if (objTria.etiqueta.equals(listaEtiquetasSalida.get(pos).etiqueta)) {
                        try {
                            String[] puntosX = new String[4];
                            puntosX[0] = listaEtiquetasSalida.get(pos).membresia + "";
                            puntosX[3] = listaEtiquetasSalida.get(pos).etiqueta;
                            x1 = objTria.puntoIzq[0];
                            y1 = objTria.puntoIzq[1];
                            x2 = objTria.puntoC[0];
                            y2 = objTria.puntoC[1];
                            x3 = objTria.puntoDer[0];
                            y3 = objTria.puntoDer[1];

                            puntosX[1] = (((((listaEtiquetasSalida.get(pos).membresia - y1) / (y2 - y1)) * (x2 - x1)) + x1)) + "";
                            puntosX[2] = (((((listaEtiquetasSalida.get(pos).membresia - y2) / (y3 - y2)) * (x3 - x2)) + x2)) + "";
                            listaPuntosX.add(puntosX);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (listTrapezoide != null) {
                for (Trapezoide objTrap : listTrapezoide) {
                    //checar que el punto ingresado por el usuario esté entre los puntos no críticos de la figura

                    if (objTrap.etiqueta.equals(listaEtiquetasSalida.get(pos).etiqueta)) {

                        String[] puntosX = new String[4];
                        puntosX[0] = listaEtiquetasSalida.get(pos).membresia + "";
                        puntosX[3] = listaEtiquetasSalida.get(pos).etiqueta;
                        x1 = y1 = x2 = y2 = 0;
                        x1 = objTrap.puntoIzq[0];
                        y1 = objTrap.puntoIzq[1];
                        x2 = objTrap.puntoC1[0];
                        y2 = objTrap.puntoC1[1];
                        puntosX[1] = ((((listaEtiquetasSalida.get(pos).membresia - y1) / (y2 - y1)) * (x2 - x1)) + x1) + "";

                        x1 = objTrap.puntoDer[0];
                        y1 = objTrap.puntoDer[1];
                        x2 = objTrap.puntoC2[0];
                        y2 = objTrap.puntoC2[1];
                        puntosX[2] = ((((listaEtiquetasSalida.get(pos).membresia - y1) / (y2 - y1)) * (x2 - x1)) + x1) + "";
                        listaPuntosX.add(puntosX);

                    }
                }

                if (listSemiTriangular != null) {
                    for (semiTriangular objSemTria : listSemiTriangular) {
                        if (objSemTria.etiqueta.equals(listaEtiquetasSalida.get(pos).etiqueta)) {
                            String[] puntosX = new String[3];
                            puntosX[0] = listaEtiquetasSalida.get(pos).membresia + "";
                            puntosX[2] = listaEtiquetasSalida.get(pos).etiqueta;
                            x1 = objSemTria.punto2[0];
                            y1 = objSemTria.punto2[1];
                            x2 = objSemTria.puntoC[0];
                            y2 = objSemTria.puntoC[1];

                            puntosX[1] = ((((listaEtiquetasSalida.get(pos).membresia - y1) / (y2 - y1)) * (x2 - x1)) + x1) + "";
                            listaPuntosX.add(puntosX);

                        }
                    }
                }
                if (listSemiTrapezoide != null) {
                    for (semiTrapezoide objSemTrap : listSemiTrapezoide) {
                        if (objSemTrap.etiqueta.equals(listaEtiquetasSalida.get(pos).etiqueta)) {
                            String[] puntosX = new String[3];
                            puntosX[0] = listaEtiquetasSalida.get(pos).membresia + "";
                            puntosX[2] = listaEtiquetasSalida.get(pos).etiqueta;

                            double p1[] = {objSemTrap.puntoC[0], objSemTrap.puntoC[1]};
                            double p2[] = {objSemTrap.punto2[0], objSemTrap.punto2[1]};
                            puntosX[1] = (((listaEtiquetasSalida.get(pos).membresia - p1[1]) / (p2[1] - p1[1]) * (p2[0] - p1[0])) + (p1[0])) + "";
                            listaPuntosX.add(puntosX);

                        }
                    }
                }
            }
            pos++;
        }
        return listaPuntosX;
    }

    public double centroide(List<String[]> lVars) {
        List<Double> supElements = superior(lVars);
        double sup = supElements.remove(supElements.size() - 1);
        double inf = inferior(lVars, supElements);

        return sup / inf;
    }

    private List<Double> superior(List<String[]> lVars) {
        List<Double> lCounts = new ArrayList<>();
        int count = 0;
        double acum = 0, res = 0;
        double y;
        String label; //solo para indicar su existencia, viene incluido en la lVars

        for (String[] var : lVars) {
            y = Double.parseDouble(var[0]);
            label = var[var.length - 1];

            for (int i = 1; i < var.length - 1; i++) {
                while (acum <= Double.parseDouble(var[i])) {
                    acum += 0.1;
                    ++count;
                }
                lCounts.add((double) count);
                acum *= y;
                res += acum;
                acum = count = 0;
            }
        }

        lCounts.add(res);
        return lCounts;
    }

    private double inferior(List<String[]> lVars, List<Double> lCounts) {
        double y, c;
        double res = 0;

        for (int i = 0; i < lVars.size(); i++) {
            y = Double.parseDouble(lVars.get(i)[0]);
            c = lCounts.get(i);
            res += y * c;
        }

        return res;
    }

}
