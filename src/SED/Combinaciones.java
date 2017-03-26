package SED;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tenistas
 */
public class Combinaciones {

    public List<Etiqueta> listCombinaciones;
    public List<String> listSalidas;
    public double pesoRegla;
    

    public Combinaciones() {
        listCombinaciones = new ArrayList<>();
        listSalidas = new ArrayList<>();
    }

    public Combinaciones(List<Etiqueta> listCombinaciones) {
        this.listCombinaciones = listCombinaciones;
    }
}
