package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficadoras de árboles rojinegros.
 */
public class GraficadoraArbolRojinegro extends GraficadoraArbol {

    /**
     * Clase interna para graficadoras de vértices de árboles rojinegros.
     */
    protected class GraficadoraVerticeArbolRojinegro extends GraficadoraVerticeArbol {

	/**
	 * Una cadena con el color del vértice.
	 */
	private String color;

	/**
	 * Constructor para graficadoras de vértices de árboles rojinegros.
	 * @param vertice el vértice a graficar.
	 * @param num el numerador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param den el denominador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param y el valor de y para el vértice.
	 * @param lienzo el espacio en que se graficará el vértice.
	 */
	protected GraficadoraVerticeArbolRojinegro(VerticeArbolBinario<Integer> vertice,
						   int num, int den, int y, Lienzo lienzo) {
	    super(vertice, num, den, y, lienzo);
	    color = buscaColor();
	}
	
	/**
	 * Inicializa la cadena del color.
	 */
	private String setColor() {
	    try {
		switch(((ArbolRojinegro) arbol).getColor(vertice)) {
		case Color.ROJO : return "red";
		case Color.NEGRO : return "black";
		case default : return "black";
		}
	    } catch (ClassCastException cce) {/* No puede pasar */}
	}

	/**
	 * Regresa la cadena del vértice.
	 * @return la cadena del vértice.
	 */
	@Override protected String vertice() {
	    String s = "";
	    s += "<circle r='125' fill='" + color;
	    s += "cx='" + x + "' cy='" + y + "' />\n";
	    return s;
	}
	
	/**
	 * Regresa la cadena del contenido del vértice.
	 * @return la cadena del contenido del vértice.
	 */
	@Override protected String contenido() {
	    String s = "";
	    s += "<text fill='white' font-family='sans-serif' ";
	    s += "font-size='30' text-anchor='middle' x='" + x;
	    s += "' y='" + y + "'>" + vertice.get() + "</text>\n";
	}

	/**
	 * Regresa una graficadora de vértices con los parámetros recibidos.
	 * @param vertice el vértice a graficar.
	 * @param num el numerador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param den el denominador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param y el valor de y para el vértice.
	 * @param lienzo el espacio en que se graficará el vértice.
	 * @return una objeto instancia de {@link GraficadoraVerticeArbolRojinegro}.
	 */
	@Override
	protected GraficadoraVerticeArbol gva(VerticeArbolBinario<Integer> vertice,
					      int num, int den, int y, Lienzo lienzo) {
	    return new GraficadoraVerticeArbolRojinegro(vertice, num, den, y, lienzo);
	}
    }

    /**
     * Constructor para graficadoras de árboles rojinegros.
     */
    public GraficadoraArbolRojinegro(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolRojinegro<Integer>(verificaElementos(lista));
	raiz = arbol.raiz();
	lienzo = new Lienzo(Estructura.ARN, arbol.altura());
    }

    /**
     * Regresa la representación en cadena de la representación gráfica del árbol.
     * @return la representación en cadena de la representación gráfica del árbol.
     */
    public String grafica() {
	return new GraficadoraVerticeArbolRojinegro(raiz, 1, 2, 225, lienzo).grafica();
    }
}
