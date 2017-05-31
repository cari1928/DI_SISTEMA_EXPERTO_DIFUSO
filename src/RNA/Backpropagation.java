package RNA;

import SED.GestionArchivos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tenistas
 */
public class Backpropagation {

    private Patron[] lPatrones;
    private List<Double[]> lPesos;
    private List<Double[]> lTPesos;
    private List<Double[]> lUmbrales;
    private List<Double[]> lTUmbrales;
    private List<Double[]> lFunEntrada;
    private List<Double[]> lFunActSalida;
    private List<Double[]> lError;
    private List<Double[]> lS;
    private double alpha;
    private int numNeuCapOculta, sizeCapa1, sizeCapa2, sizeCapa3, count;
    private double terminoError;

    public Backpropagation(Patron[] lPatrones, double alpha, int numNeuCapOculta, double terminoError) {
        this.lPatrones = lPatrones;
        this.alpha = alpha;
        this.numNeuCapOculta = numNeuCapOculta;
        this.terminoError = terminoError;
        count = 0;
    }

    public void iniEntrenamiento() throws Exception {
        lError = new ArrayList<>();
        lS = new ArrayList<>();
        lTPesos = new ArrayList<>();
        lTUmbrales = new ArrayList<>();
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
        calcError();
        calcS();

        double s, fa;
        int value = sizeCapa2 * sizeCapa3;
        for (int i = lPesos.size() - 1; i > 0; i++) {

            if (i >= lPesos.size() - value) {
                s = getValue(lPesos.get(i)[1], lS);
            } else {
                s = lPatrones[count].getGrados().get((int) (lPesos.get(i)[0] - 1));
            }

            fa = getValue(lPesos.get(i)[0], lFunActSalida);
            s = deltapeso(alpha, fa, s);

            lTPesos.add(new Double[]{lPesos.get(i)[0], lPesos.get(i)[1], s});
        }
    }

    double deltapeso(double TP, double Yj, double ese_delta) {
        double deltapesos = TP * Yj * ese_delta;
        return deltapesos;
    }

    private void calcS() {
        double value = sizeCapa1 + sizeCapa2 + sizeCapa3, value2;
        double fa, err = 0, res, tmp;
        List<Double> xi, wij;
        for (int i = (int) value; i > (sizeCapa1); i--) {
            err = 0;
            fa = getValue(i, lFunActSalida);

            if (i <= sizeCapa1 + sizeCapa2) {
                value2 = sizeCapa1 + sizeCapa2 + 1;
                for (int j = 0; j < sizeCapa3; j++, value2++) {
                    tmp = getValue(value2, lFunActSalida);
                    xi = new ArrayList<>();
                    xi.add(tmp);

                    wij = getPesoK(i, value2);
                    err += funEntrada(xi, wij, 0);
                }
                res = fa * (1 - fa) * err;
            } else {
                err = getValue(i, lError);
                res = S(fa, err);
            }
            lS.add(new Double[]{(double) i, res});
        }

        System.out.println("S");
        mostrar(lS);
    }

    private double S(double yr, double e) {
        return yr * (1 - yr) * e;
    }

    private void calcError() {
        double res;
        double value = sizeCapa1 + sizeCapa2 + 1;
        for (int i = 0; i < lPatrones[i].getSalidas().size(); i++) {
            res = getValue(value, lFunActSalida);
            if (res == -1) {
                return;
            }
            res = error(lPatrones[count].getSalidas().get(i), res);
            lError.add(new Double[]{value, res});
            ++value;
        }

        System.out.println("ERROR CALCULADO");
        mostrar(lError);
    }

    private List<Double> getPesoK(double value1, double value2) {
        double res;
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < lPesos.size(); i++) {
            res = lPesos.get(i)[2];
            if (lPesos.get(i)[0] == value1 && lPesos.get(i)[1] == value2) {
                tmp.add(res);
                return tmp;
            }
        }
        return tmp;
    }

    private double getValue(double value, List<Double[]> list) {
        double res;
        for (int i = 0; i < list.size(); i++) {
            res = list.get(i)[1];
            if (list.get(i)[0] == value) {
                return res;
            }
        }
        return -1;
    }

    private void calcFunEntActSalida() {
        List<Double> xi, wij;
        int c2;
        double tmpEntrada, tmpActSalida;
        lFunEntrada = new ArrayList<>();
        lFunActSalida = new ArrayList<>();

        //cálculo de las funciones de entrada
        c2 = sizeCapa1 + 1;
        for (int i = 0; i < lUmbrales.size(); i++) {
            xi = lPatrones[count].getGrados(); //para la versión final

            wij = getWij(c2);
            if (i >= (lUmbrales.size() - sizeCapa3)) {
                xi = getWij(c2);
                wij = getFA(c2 - (sizeCapa1 + 1));
            }

            tmpEntrada = funEntrada(xi, wij, lUmbrales.get(i)[1]);
            lFunEntrada.add(new Double[]{(double) c2, tmpEntrada});

            tmpActSalida = funActivacion(tmpEntrada);
            lFunActSalida.add(new Double[]{(double) c2, tmpActSalida});
            ++c2;

            System.out.println("Funciones entrada");
            mostrar(lFunEntrada);
            System.out.println("Funciones salida");
            mostrar(lFunActSalida);
        }
    }

    private List<Double> getFA(int j) {
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < j; i++) {
            tmp.add(lFunActSalida.get(i)[1]);
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
        sizeCapa1 = lPatrones[0].getGrados().size();
        sizeCapa3 = getSizeLayer3(); //leer archivo con -S, de Datos, final
        sizeCapa2 = numNeuCapOculta;
        //lPesos = fillPesos(sizeCapa1, sizeCapa2, sizeCapa3); //usar esta

        //pruebas
        lPesos = new ArrayList<>();
        lPesos.add(new Double[]{1.0, 6.0, 0.388041549});
        lPesos.add(new Double[]{1.0, 7.0, 0.403900043});
        lPesos.add(new Double[]{2.0, 6.0, 0.294988421});
        lPesos.add(new Double[]{2.0, 7.0, 0.421921661});
        lPesos.add(new Double[]{3.0, 6.0, 0.446224016});
        lPesos.add(new Double[]{3.0, 7.0, 0.118666065});
        lPesos.add(new Double[]{4.0, 6.0, 0.086577537});
        lPesos.add(new Double[]{4.0, 7.0, 0.463159164});
        lPesos.add(new Double[]{5.0, 6.0, 0.102648999});
        lPesos.add(new Double[]{5.0, 7.0, 0.355183387});
        lPesos.add(new Double[]{6.0, 8.0, 0.0822});
        lPesos.add(new Double[]{6.0, 9.0, 0.4677});
        lPesos.add(new Double[]{7.0, 8.0, 0.2161});
        lPesos.add(new Double[]{7.0, 9.0, 0.4412});

        System.out.println("Pesos");
        mostrar(lPesos);
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
        //lUmbrales = fillUmbrales(sizeCapa2, sizeCapa3);

        lUmbrales = new ArrayList<>();
        lUmbrales.add(new Double[]{6.0, 0.441592067});
        lUmbrales.add(new Double[]{7.0, 0.079930952});
        lUmbrales.add(new Double[]{8.0, 0.232708258});
        lUmbrales.add(new Double[]{9.0, 0.341826872});

        System.out.println("Umbrales");
        mostrar(lUmbrales);

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

        mostrar(list);
        return list;

    }

    private void mostrar(List<Double[]> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }

    private List<Double[]> fillUmbrales(int c2, int c3) {
        List<Double[]> list = new ArrayList<>();
        Double[] tmp;
        int limite = c2 + c3;
        int lbl = sizeCapa1 + 1;
        ++c2;
        for (int i = 0; i < limite; i++) {
            tmp = new Double[2]; //0 para el número correspondiente 1 para el valor aleatorio
            tmp[0] = (double) lbl;
            tmp[1] = (double) getRandom(-0.5, 0.5);
            ++lbl;
            list.add(tmp);
        }
        return list;
    }

    /**
     * Para pruebas
     */
    public static void main(String[] args) {
        Patron[] p = new Patron[4];

        List<Double> grados = new ArrayList<>();
        List<Double> salidas = new ArrayList<>();
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        salidas.add(0.0);
        salidas.add(0.0);
        p[0] = new Patron(grados, salidas);

        grados = new ArrayList<>();
        salidas = new ArrayList<>();
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.1);
        salidas.add(0.0);
        salidas.add(0.1);
        p[1] = new Patron(grados, salidas);

        grados = new ArrayList<>();
        salidas = new ArrayList<>();
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(1.0);
        grados.add(0.0);
        salidas.add(1.0);
        salidas.add(0.0);
        p[2] = new Patron(grados, salidas);

        grados = new ArrayList<>();
        salidas = new ArrayList<>();
        grados.add(0.0);
        grados.add(0.0);
        grados.add(0.0);
        grados.add(1.0);
        grados.add(1.0);
        salidas.add(1.0);
        salidas.add(1.0);
        p[3] = new Patron(grados, salidas);

        //Patron[] lPatrones, double alpha, int numNeuCapOculta, double terminoError
        Backpropagation objB = new Backpropagation(p, 0.1, 2, 0.001);
        try {
            objB.iniEntrenamiento();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
