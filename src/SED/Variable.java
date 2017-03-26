package SED;

import java.util.List;

/**
 *
 * @author radog
 */
public class Variable {

    public UniversoDiscurso objU;
    public List<Triangular> listTriangular;
    public List<Trapezoide> listTrapezoide;
    public List<semiTriangular> listSemiTriangular;
    public List<semiTrapezoide> listSemiTrapezoide;
    public double punto;

    public Variable(UniversoDiscurso objU, List<Triangular> listTriangular, List<Trapezoide> listTrapezoide, List<semiTriangular> listSemiTriangular, List<semiTrapezoide> listSemiTrapezoide, double punto) {
        this.objU = objU;
        this.listTriangular = listTriangular;
        this.listTrapezoide = listTrapezoide;
        this.listSemiTriangular = listSemiTriangular;
        this.listSemiTrapezoide = listSemiTrapezoide;
        this.punto = punto;
    }

}
