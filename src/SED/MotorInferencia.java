package SED;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tenistas
 */
public class MotorInferencia {

    UniversoDiscurso objU;
    List<Triangular> listTriangular;
    List<Trapezoide> listTrapezoide;
    List<semiTriangular> listSemiTriangular;
    List<semiTrapezoide> listSemiTrapezoide;
    double punto;
    String resultado;

    public MotorInferencia(double punto) {
        this.punto = punto;
        resultado = "";

        fuzzyfication();
    }

    public String getResultado() {
        return resultado;
    }

    private void fuzzyfication() {
        crearModelo();

        if (listTriangular != null) {
        }

        if (listTrapezoide != null) {
            for (Trapezoide objTrap : listTrapezoide) {
                //checar que el punto ingresado por el usuario esté entre los puntos no críticos de la figura
                if (objTrap.puntoIzq[0] < punto && punto < objTrap.puntoDer[0]) {
                    resultado += calcularY(objTrap) + " "; //fase de pruebas todavía
                }
            }
        }

        if (listSemiTriangular != null) {
            for (semiTriangular objSemTria : listSemiTriangular) {
                //checar orientacion
                if (objSemTria.orientacion == 'd') {
                    if (objSemTria.puntoC[0] < punto && punto < objSemTria.punto2[0]) {
                        resultado += calcularY(objSemTria) + " ";
                    }
                } else {
                    if (objSemTria.punto2[0] < punto && punto < objSemTria.puntoC[0]) {
                        resultado += calcularY(objSemTria) + " ";
                    }
                }
            }
        }

        if (listSemiTrapezoide != null) {

        }
    }

    private void crearModelo() {
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
            listRegistros = objG.leer();
            for (int i = 0; i < listRegistros.size(); i++) {
                registro = listRegistros.get(i);
                parts = registro.split(" ");

                switch (parts[0]) {
                    case "Ceros":
                        break;

                    case "Triangular": //0 = x, 1 = y
                        objTri = new Triangular();
                        objTri.puntoC[0] = Double.parseDouble(parts[1]);
                        objTri.puntoC[1] = 1;

                        //cálculo de la X de los puntos izq y der
                        distancia = Math.abs(objTri.puntoC[0] - Double.parseDouble(parts[parts.length - 1]));
                        objTri.puntoIzq[0] = objTri.puntoC[0] - distancia;
                        objTri.puntoDer[0] = objTri.puntoC[0] + distancia;

                        //Y en 0's
                        objTri.puntoIzq[1] = 0;
                        objTri.puntoDer[1] = 0;

                        objTri.etiqueta = parts[2];
                        objTri.turno = contFigura;
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
                        distancia = Math.abs(objTra.puntoC1[0] - Double.parseDouble(parts[parts.length - 1]));
                        objTra.puntoIzq[0] = objTra.puntoC1[0] - distancia;
                        objTra.puntoDer[0] = objTra.puntoC2[0] + distancia;

                        //Y en 0's
                        objTra.puntoC1[1] = 0;
                        objTra.puntoC2[1] = 0;

                        objTra.etiqueta = parts[3];
                        objTra.turno = contFigura;
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

                        //el otro punto
                        if (objSemTri.orientacion == 'i') { //izquierda
                            objSemTri.punto2[0] = objSemTri.puntoC[0] - objSemTri.longitud;
                        } else {
                            objSemTri.punto2[0] = objSemTri.puntoC[0] + objSemTri.longitud;
                        }
                        objSemTri.punto2[1] = 0;
                        objSemTri.turno = contFigura;
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
                            objSemTrap.punto2[0] = objSemTrap.puntoC[0] - Double.parseDouble(parts[parts.length - 1]);
                        } else {
                            objSemTrap.punto2[0] = objSemTrap.puntoC[0] + Double.parseDouble(parts[parts.length - 1]);
                        }
                        objSemTrap.punto2[1] = 0;
                        objSemTrap.turno = contFigura;
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

        } else if (objT.puntoC1[0] < punto && punto < objT.puntoC2[0]) { //verificar que el punto esté entre los dos puntos criticos
            return 1;
        }

        return ((punto - x1) / (x2 - x1)) * (y2 - y1) + y1;
    }

    private double calcularY(semiTriangular objSemTria) {
        double x1, y1, x2, y2, y;

        x1 = objSemTria.punto2[0];
        y1 = objSemTria.punto2[1];
        x2 = objSemTria.puntoC[0];
        y2 = objSemTria.puntoC[1];

        return ((punto - x1) / (x2 - x1)) * (y2 - y1) + y1;
    }

    private void calcularY(semiTrapezoide objSTrap) {
        char orientacion = objSTrap.orientacion;
        if (orientacion == 'i') {
            if (punto > objU.inicio && punto < objSTrap.punto2[0]) {
                //Esta dentro izquierda
                if (punto > objSTrap.puntoC[0] && punto < objSTrap.punto2[0]) {
                    //Esta en la pendiente
                    //Calcular pendiente
                    double m = (objSTrap.punto2[1] - objSTrap.puntoC[1]) / (objSTrap.punto2[0] - objSTrap.puntoC[0]);
                    double y = (m + objSTrap.puntoC[0]) + objSTrap.puntoC[1];
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                } else {
                    //Esta en la linea Recta de 1's
                    resultado += objSTrap.etiqueta + " Y = 1" + "\n";
                }
            } else {
                //El punto no pertenece a la funcion
                resultado += objSTrap.etiqueta + " Y = 0" + "\n";
            }
        } else {
            if (punto < objU.fin && punto > objSTrap.punto2[0]) {
                //Esta dentro derecha
                if (punto < objSTrap.puntoC[0] && punto > objSTrap.punto2[0]) {
                    //Esta en la pendiente
                    double m = (objSTrap.puntoC[1] - objSTrap.punto2[1]) / (objSTrap.puntoC[0] - objSTrap.punto2[0]);
                    double y = (m + objSTrap.punto2[0]) + objSTrap.punto2[1];
                    resultado += objSTrap.etiqueta + " Y = " + y + "\n";
                } else {
                    //Esta en la linea recta de 1's
                    resultado += objSTrap.etiqueta + " Y = 1" + "\n";
                }
            } else {
                //El punto no pertenece a la funcion
                resultado += objSTrap.etiqueta + " Y = 0" + "\n";
            }
        }
    }

    //para pruebas
    public static void main(String[] args) {
        MotorInferencia objM = new MotorInferencia(5.5);
    }

}
