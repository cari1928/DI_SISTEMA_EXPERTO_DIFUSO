package SED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tenistas
 */
public class GestionArchivos {

    RandomAccessFile raf;
    final int TAMAÑO = 150;

    /*
    * tipo = nuevo | final. 
    * nuevo = borrar contenido e insertar nuevo
    * final = agrega después del últmo registro
     */
    public void escribir(String nomFile, int llave, String registro, String tipo) throws IOException {
        StringBuilder builder = null;
        File archivo = new File(nomFile);

        if (tipo.equals("nuevo")) {
            archivo.delete();
        }

        raf = new RandomAccessFile("baseConocimientos", "rw");

        if (tipo.equals("final")) {
            raf.seek(raf.length());
        }

        raf.writeInt(llave);
        builder = new StringBuilder(registro);
        builder.setLength(TAMAÑO);
        raf.writeChars(builder.toString());
        raf.close();
    }

    public List leer(String nomFile) throws FileNotFoundException, IOException {
        long ap_actual, ap_final;
        int tamaño = contarRengs(), llave; //cantidad de objetos
        List<String> list = new ArrayList<>();
        String convert;

        raf = new RandomAccessFile(nomFile, "r");

        while ((ap_actual = raf.getFilePointer()) != (ap_final = raf.length())) {
            llave = raf.readInt(); //lee la llave
            char[] registro = new char[TAMAÑO];
            char tmp;
            for (int i = 0; i < registro.length; i++) {
                tmp = raf.readChar();
                registro[i] = tmp;
            }

            new String(registro).replace('\0', ' ');

            convert = "";
            for (int i = 0; i < registro.length; i++) {
                convert += registro[i];
            }

            list.add(convert);
        }

        raf.close();
        return list;
    }

    public int contarRengs() throws FileNotFoundException, IOException {
        long ap_actual, ap_final;
        int cont = 0, llaves;
        raf = new RandomAccessFile("baseConocimientos", "r");

        while ((ap_actual = raf.getFilePointer()) != (ap_final = raf.length())) {
            raf.readInt(); //lee la llave
            char[] registro = new char[TAMAÑO];
            char tmp;
            for (int i = 0; i < registro.length; i++) {
                tmp = raf.readChar();
                registro[i] = tmp;
            }

            new String(registro).replace('\0', ' ');
            ++cont;
        }

        raf.close();
        return cont;
    }

    //ejemplo de uso
    public static void main(String[] args) {
        GestionArchivos objG = new GestionArchivos();
        List<String> list = null;
        try {
            objG.escribir("baseConocimientos", 0, "-30 60 unidades variable", "nuevo");
            //objG.escribir(0, "-30 30 unidades variable", "final");
            //objG.escribir(0, "-30 40 unidades variable", "final");
            //objG.escribir(0, "-30 50 unidades variable", "final");
            list = objG.leer("baseConocimientos");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
