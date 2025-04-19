package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase abstracta para graficadoras de estructuras de datos.
 */
public abstract class GraficadoraEstructura extends Graficadora {

    /**
     * Obtiene el ancho del lienzo.
     * @return el ancho del lienzo.
     */
    public int anchoLienzo() {
	return lienzo.getAncho();
    }

    /**
     * Obtiene el alto del lienzo.
     * @return el alto del lienzo.
     */
    public int altoLienzo() {
	return lienzo.getAlto();
    }
    
    /**
     * Verifica que todos los elementos recibidos sean de la forma
     * esperada y regresa una lista en que los elementos son
     * ahora números en lugar de cadenas.
     * @param lista la lista de cadenas que queremos verificar.
     * @throws {@link IllegalArgumentException} si alguno de los
     *         elementos no es un número.
     * @return una lista de los elementos como instancia de
     *         {@link Integer}.
     */
    protected Lista<Integer> verificaElementos(Lista<String> lista)
	throws IllegalArgumentException {
	Lista<Integer> construible = new Lista<>();
	for (String s : lista) {
	    try {
		construible.agrega(Integer.parseInt(s));
	    } catch (NumberFormatException nfe) {
		throw new IllegalArgumentException("Algún elemento recibido no es un número.");
	    }
	}
    }
}
