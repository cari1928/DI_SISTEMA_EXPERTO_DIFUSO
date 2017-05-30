package RNA;

import SED.GestionArchivos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tenistas
 */
public class Backpropagation {

    private List<Patron> lPatrones;
    private List<Double[]> lPesos;
    private List<Double[]> lUmbrales;
    private List<Double> lFunEntrada;
    private List<Double> lFunActSalida;
    private double alpha;
    private int numNeuCapOculta, sizeCapa1, sizeCapa2, sizeCapa3;
    private double terminoError;

    public Backpropagation(List<Patron> lPatrones, double alpha, int numNeuCapOculta, double terminoError) {
        this.lPatrones = lPatrones;
        this.alpha = alpha;
        this.numNeuCapOculta = numNeuCapOculta;
        this.terminoError = terminoError;

    }

    public void iniEntrenamiento() throws Exception {
        iniPesos();
        iniUmbrales();
        entrenamiento();
    }

    private List<Double> getWij(int j) {
        Double[] peso;
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < lPesos.size(); i++) {
            peso = lPesos.get(i);
            if (peso[1] == j) {
                tmp.add(peso[2]);
            }
        }
        return tmp;
    }

    private void ejecucion() {

    }

    /**
     * Cálculo de la funcion de entrada
     */
    public double funEntrada(List<Double> xi, List<Double> wij, double uj) {
        double res = 0;
        for (int i = 0; i < xi.size(); i++) {
            res += xi.get(i) * wij.get(i);
        }
        return res -= uj;
    }

    public void entrenamiento() {
        calcFunEntActSalida();
    }

    private void calcFunEntActSalida() {
        List<Double> xi, wij;
        xi = wij = new ArrayList<>();
        int c2;
        double tmpEntrada, tmpActSalida;
        lFunEntrada = lFunActSalida = new ArrayList<>();

        //para pruebas
        xi.add((double) 1);
        xi.add((double) 1);

        //cálculo de las funciones de entrada
        c2 = sizeCapa1 + 1;
        for (int i = 0; i < lUmbrales.size(); i++) {
            //xi = lPatrones.get(i).getGrados(); //para la versión final

            if (i >= (lUmbrales.size() - sizeCapa3)) {
                xi = getFA(c2);
            }

            wij = getWij(c2);
            tmpEntrada = funEntrada(xi, wij, lUmbrales.get(i)[1]);
            lFunEntrada.add(tmpEntrada);

            tmpActSalida = funActivacion(tmpEntrada);
            lFunActSalida.add(tmpActSalida);
            ++c2;
        }
    }

    private List<Double> getFA(int j) {
        List<Double> tmp = new ArrayList<>();
        for (int i = j; i < lFunEntrada.size(); i++) {
            tmp.add(lFunEntrada.get(i));
        }
        return tmp;
    }

    public double funActivacion(double fe) {
        return 1 / (1 + Math.exp(-1 * fe));
    }

    public double error(double yd, double yr) {
        return yd - yr;
    }

    /**
     * Obtiene el número total de pesos que son necesarios y asigna un valor
     * aleatorio a cada uno
     */
    private void iniPesos() throws Exception {
        //sizeCapa1 = lPatrones.get(0).getGrados().size(); //final
        //sizeCapa3 = getSizeLayer3(); //leer archivo con -S, de Datos, final

        sizeCapa1 = 2; //pruebas
        sizeCapa2 = numNeuCapOculta;
        sizeCapa3 = 1; //pruebas
        lPesos = fillPesos(sizeCapa1, sizeCapa2, sizeCapa3);
    }

    /**
     * Obtiene un número aleatorio dentro del rango especificado
     */
    private double getRandom(double limInf, double limSup) {
        Random random = new Random();
        double dato;
        do {
            dato = random.nextDouble();
            if (limInf < dato && dato < limSup) {
                return dato;
            }
        } while (true);
    }

    /**
     * IOException para archivos ; Exception para cuando no se encuentra el
     * archivo de salida
     */
    private int getSizeLayer3() throws Exception {
        Exception exception = new Exception();
        GestionArchivos objG = new GestionArchivos();
        List<String> registros = objG.leer("SED/Datos");
        List<String> tmp;
        for (String r : registros) {
            if (r.contains("-S")) {
                tmp = objG.leer(("SED/" + r).trim());
                return tmp.size() - 1; //-1 para no tomar en cuenta el universo de discurso
            }
        }
        throw exception;
    }

    /**
     * Calcula el número total de umbrales necesarios y agrega a cada uno un
     * valor aleatorio
     */
    private void iniUmbrales() {
        lUmbrales = fillUmbrales(sizeCapa2, sizeCapa3);
    }

    /**
     * Inicializa listas con valores aleatorios
     */
    private List<Double[]> fillPesos(int c1, int c2, int c3) {
        List<Double[]> list = new ArrayList<>();
        Double[] tmp;
        int nNeuronaC1 = 1, nNeuronaC2 = c1 + 1;
        int lim = (c1 * c2);
        boolean flag = true;

        //para capa 1 y 2
        for (int i = 0; i < lim; i++) {
            for (int j = 0; j < c2; j++) {
                tmp = new Double[3]; //0 para c1 1 para c2 3 para valor random
                tmp[0] = (double) nNeuronaC1;
                tmp[1] = (double) nNeuronaC2;
                tmp[2] = (double) getRandom(-0.5, 0.5);
                list.add(tmp); //se guarda
                ++nNeuronaC2;
                i += j;
            }
            ++nNeuronaC1;
            if (i == lim - 1) {
                if (flag) {
                    flag = false;
                    lim = c2 * c3;
                    i = -1;
                }
            } else {
                if (flag) {
                    nNeuronaC2 = c1 + 1;
                } else {
                    nNeuronaC2 = c1 + c2 + 1;
                }
            }
        }
        return list;
    }

    private List<Double[]> fillUmbrales(int c2, int c3) {
        List<Double[]> list = new ArrayList<>();
        Double[] tmp;
        int limite = c2 + c3;
        ++c2;
        for (int i = 0; i < limite; i++) {
            tmp = new Double[2]; //0 para el número correspondiente 1 para el valor aleatorio
            tmp[0] = (double) c2;
            tmp[1] = (double) getRandom(-0.5, 0.5);
            ++c2;
            list.add(tmp);
        }
        return list;
    }

    /**
     * Para pruebas
     */
    public static void main(String[] args) {
        Backpropagation objB = new Backpropagation(null, 0.1, 2, 0.001);
        try {
            objB.iniEntrenamiento();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
