package SED;

import java.util.List;

/**
 *
 * @author Tenistas
 */
public class MotorInferencia {

    List<Triangular> listTriangular;
    List<Trapezoide> listTrapezoide;
    List<semiTriangular> listSemiTriangular;
    List<semiTrapezoide> listSemiTrapezoide;
    double[] rangoUniverso;
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
    }

    private void crearModelo() {
        GestionArchivos objG = new GestionArchivos();
        List<String> listRegistros;
        String registro;
        String[] parts;

        try {
            listRegistros = objG.leer();
            for (int i = 0; i < listRegistros.size(); i++) {
                registro = listRegistros.get(i);
                parts = registro.split(" ");
            }

        } catch (Exception e) {
            e.printStackTrace(); //para pruebas
        }

    }

    //para pruebas
    public static void main(String[] args) {
        MotorInferencia objM = new MotorInferencia(5.5);
    }

}
