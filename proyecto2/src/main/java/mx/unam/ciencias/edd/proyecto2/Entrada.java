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
     * @throws {@link IOException} si algo falla al intentar acceder al
     *         archivo.
     */
    public Entrada(String archivo) throws IOException {
	lector = new BufferedReader(
		     new InputStreamReader(
			 new FileInputStream(archivo), "utf-8"))
    }

    /**
     * Crea una lista con cada subcadena no vacía recibida a través de
     * la entrada elegida.
     * @return Una lista con cada subcadena no vacía de las líneas
     *         recibidas.
     */
    public Lista<String> recibe() {
	String linea;
	Lista<String> recibidas = new Lista<>();
	while ((linea = borraComentarios(lector.readLine())) != null) {
	    if (!linea.equals(""))
		guardaSubcadenas(linea, recibidas);
	}
	return recibidas;
    }

    /**
     * Regresa la cadena recibida sin los comentarios en esta.
     * @param linea la cadena de cuyos comentarios queremos
     *        deshacernos.
     * @return la cadena sin comentarios.
     */
    private String borraComentarios(String linea) {
	if (linea == null)
	    return null;
	if (!linea.contains("#"))
	    return linea.trim();
	for (int i = 0; i < linea.length(); i++)
	    if (linea.charAt(i) == '#')
		return linea.substring(0, --i).trim();
    }

    /**
     * Guarda en una lista las subcadenas no vacías de la cadena
     * recibida
     * @param linea la cadena de la que se obtendrán las subcadenas.
     * @param lista la lista en que se guardarán las subcadenas.
     */
    private void guerdaSubcadenas(String linea, Lista<String> lista) {
	for (String l : linea.split(" "))
	    if (!l.equals(""))
		lista.agrega(l);
    }
}
