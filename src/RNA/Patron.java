package RNA;

import java.util.List;

/**
 *
 * @author Tenistas
 */
public class Patron {

    private List<Double> grados;
    private List<Double> salidas;

    public Patron() {
    }

    public Patron(List<Double> grados, List<Double> salidas) {
        this.grados = grados;
        this.salidas = salidas;
    }

    public List<Double> getGrados() {
        return grados;
    }

    public void setGrados(List<Double> grados) {
        this.grados = grados;
    }

    public List<Double> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<Double> salidas) {
        this.salidas = salidas;
    }

}
