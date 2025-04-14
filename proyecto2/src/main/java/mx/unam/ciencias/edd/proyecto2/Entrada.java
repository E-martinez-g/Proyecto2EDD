package mx.unam.ciencias.edd.proyecto2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para procesar la entrada.
 */
public class Entrada {

    private BufferedReader lector;

    /**
     * Constructor para entrada estándar.
     * @throws {@link IOException} si algo falla al conectarse con la
     *         entrada estándar.
     */
    public Entrada() throws IOException {
	lector = new BufferedReader(
		     new InputStreamReader(System.in, "utf-8"))
    }

    /**
     * Constructor para leer de un archivo.
     * @throws {@link IOException} si algo falla al intentar acceder
     *         al archivo.
     */
    public Entrada(String archivo) throws IOException {
	lector = new BufferedReader(
		     new InputStreamReader(
			 new FileInputStream(archivo), "utf-8"))
    }

    /**
     * Crea una lista con cada subcadena no vacía recibida a
     * través de la entrada elegida.
     * @return Una lista con cada subcadena no vacía de las
     *         líneas recibidas.
     */
    public Lista<String> recibe() {
	String linea;
	Lista<String> sinComentarios = new Lista<>();
	while ((linea = lector.readLine()) != null) {
	    String s = borraComentarios(linea);
	    if (!borraComentarios.equals(""))
		sinComentarios.agrega(s);
	}
	return obtenSubcadenas(sinComentarios);
    }

    /**
     * Regresa la cadena recibida sin los comentarios en esta.
     * @param linea la cadena de cuyos comentarios queremos
     *        deshacernos.
     * @return la cadena sin comentarios.
     */
    private String borraComentarios(String linea) {
	if (!linea.contains("#"))
	    return linea;
	for (int i = 0; i < linea.length(); i++)
	    if (linea.charAt(i) == '#')
		return linea.substring(0, --i).trim();
    }

    /**
     * Regresa una lista con todas las subcadenas de las cadenas
     * en una lista.
     * @param lista la lista de cuyas cadenas se obtendrán las
     *        subcadenas.
     * @return una lista de todas las subcadenas.
     */
    private Lista<String> obtenSubcadenas(Lista<String> lista) {
	Lista<String> trabajable = new Lista<>();
        for (String s : lista) {
	    for (String l : s.split(" "))
		if (!l.equals(""))
		    trabajable.agrega(l);
	}
    }
}
