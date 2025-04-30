package mx.unam.ciencias.edd.proyecto2;

import java.util.NoSuchElementException;
import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para graficadoras de árboles AVL.
 */
public class GraficadoraArbolAVL extends GraficadoraArbol {

    /**
     * Clase interna para graficadoras de vértices de árboles AVL.
     */
    protected class GraficadoraVerticeArbolAVL extends GraficadoraVerticeArbol {

	/**
	 * Booleano para ayudar a posicionar la altura y el balance.
	 */
	private boolean esIzquierdo;
	
	/**
	 * Constructor para graficadoras de vértices de árboles AVL.
	 * @param vertice el vértice a graficar.
	 * @param num el numerador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param den el denominador de la fracción que se usará para calcular la posición
	 *        del vértice en el lienzo.
	 * @param y el valor de y para el vértice.
	 * @param lienzo el espacio en que se graficará el vértice.
	 */
	protected GraficadoraVerticeArbolAVL(VerticeArbolBinario<Integer> vertice,
					     int num, int den, int y, Lienzo lienzo) {
	    super(vertice, num, den, y, lienzo);
	    if (vertice != null)
		esIzquierdo = buscaLado();
	}

	/**
	 * Regresa la representación en cadena del vértice y las
	 * aristas que llevan a sus hijos.
	 * @return la cadena del vértice y sus referencias izquierdo
	 *         y derecho.
	 */
	@Override public String grafica() {
	    String s = "";
	    if (vertice == null)
		return s;
	    s += conecta();
	    s += vertice();
	    s += contenido();
	    s += ayb();
	    s += hijos();
	    return s;
	}

	/**
	 * Regresa la cadena de la altura y el balance del vértice.
	 * @return la cadena de la altura y el balance del vértice.
	 */
	private String ayb() {
	    int altura = vertice.altura();
	    int hI = -1;
	    int hD = -1;
	    if (vertice.hayIzquierdo())
		hI = vertice.izquierdo().altura();
	    if (vertice.hayDerecho())
		hD = vertice.derecho().altura();
	    int balance = hI - hD;
	    String s = "";
	    s += "<text fill='black' font-family='sans-serif' font-size='20' ";
	    s += "text-anchor='middle' y='" + (y - 125) + "' ";
	    if (esIzquierdo)
		s += "x='" + (x - 125) + "'>";
	    else
		s += "x='" + (x + 125) + "'>";
	    s += "[" + altura + "/" + balance + "]</text>\n";
	    return s;
	}

	/**
	 * Revisa de qué lado está el vértice con respecto a su padre.
	 * @return <code>true</code> si el vértice es el hijo izquierdo de su padre,
	 *         <code>false</code> en otro caso.
	 */
	private boolean buscaLado() {
	    try {
		return vertice.padre() == null || vertice.padre().izquierdo() == vertice;
	    } catch (NoSuchElementException nsee) {
		return true;
	    }
	}

	/**
	 * Obtiene la altura de un vértice aunque sea nulo.
	 * @param el vértice.
	 * @return la altura del vértice.
	 */
	private int altura(VerticeArbolBinario<Integer> vertice) {
	    return vertice == null ? -1 : vertice.altura();
	}
    }
    
    /**
     * Constructor para graficadoras de árboles AVL.
     */
    public GraficadoraArbolAVL(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolAVL<Integer>(verificaElementos(lista));
	try {
	    raiz = arbol.raiz();
	} catch (NoSuchElementException nsee) {
	    raiz = null;
	}
	lienzo = new Lienzo(Estructura.ARN, arbol.altura());
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
     * @return una objeto instancia de {@link GraficadoraVerticeArbolAVL}.
     */
    @Override
    protected GraficadoraVerticeArbol gva(VerticeArbolBinario<Integer> vertice,
					  int num, int den, int y, Lienzo lienzo) {
	return new GraficadoraVerticeArbolAVL(vertice, num, den, y, lienzo);
    }
}
