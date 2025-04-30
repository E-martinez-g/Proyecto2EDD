package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficadoras de árboles binarios.
 */
public abstract class GraficadoraArbol extends GraficadoraEstructura {
 
    /**
     * Clase interna para graficadoras de vértices de árboles binarios.
     */
    protected class GraficadoraVerticeArbol extends GraficadoraVertice {
	
	/**
	 * El vértice.
	 */
	protected VerticeArbolBinario<Integer> vertice;
	
	/**
	 * El numerador de la fracción que se usará para calcular la posición
	 * del vértice en el lienzo.
	 */
	protected int num;
	
	/**
	 * El denominador de la fracción que se usará para calcular la posición
	 * del vértice en el lienzo.
	 */
	protected int den;
	
	/**
	 * Constructor para graficadoras de vértices de árboles.
	 * @param vertice el vértice a graficar.
	 * @param num el numerador de la fracción que se usará para calcular la
	 *        posición del vértice en el lienzo.
	 * @param den el denominador de la fracción que se usará para calcular
	 *        la posición del vértice en el lienzo.
	 * @param y el valor de y para el vértice.
	 * @param lienzo el espacio en que se graficará el vértice.
	 */
	protected GraficadoraVerticeArbol(VerticeArbolBinario<Integer> vertice,
				       int num, int den, int y, Lienzo lienzo) {
	    this.vertice = vertice;
	    this.num = num;
	    this.den = den;
	    this.y = y;
	    this.lienzo = lienzo;
	    x = lienzo.getAncho();
	    x *= num;
	    x /= den;
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
	    s += hijos();
	    return s;
	}
	
	/**
	 * Regresa la cadena del contenido del vértice.
	 * @return la cadena del contenido del vértice.
	 */
	@Override protected String contenido() {
	    String s = "";
	    s += "<text fill='black' font-family='sans-serif' font-size='30' ";
	    s += "text-anchor='middle' x='" + x + "' y='" + y + "'>";
	    s += vertice.get() + "</text>\n";
	    return s;
	}
	
	/**
	 * Regresa la cadena de las referencias izquierdo y derecho del vértice.
	 * @return la cadena de las referencias izquierdo y derecho del vértice.
	 */
	@Override protected String conecta() {
	    String s = "";
	    int y2 = y + 450;
	    int x2;
	    if (vertice.hayDerecho()) {
		x2 = lienzo.getAncho();
		x2 *= (num * 2) + 1;
		x2 /= den * 2;
		s += linea(x, y, x2, y2);
	    }
	    if (vertice.hayIzquierdo()) {
		x2 = lienzo.getAncho();
		x2 *= (num * 2) - 1;
		x2 /= den * 2;
		s += linea(x, y, x2, y2);
	    }
	    return s;
	}

	/**
	 * Llama el método grafica de los hijos del vértice actual.
	 * Este método recorre el árbol recursivamente en dfs-preorder.
	 * @return la cadena de los hijos.
	 */
	protected String hijos() {
	    String s = "";
	    if (vertice.hayIzquierdo()) {
		s += gva(vertice.izquierdo(),
			 ((num * 2) - 1),
			 (den * 2),
			 (y + 450), lienzo).grafica();
	    }
	    if (vertice.hayDerecho()) {
		s += gva(vertice.derecho(),
			 ((num * 2) + 1),
			 (den * 2),
			 (y + 450), lienzo).grafica();
	    }
	    return s;
	}
	
    }
	
    /**
     * Arbol binario.
     */
    protected ArbolBinario<Integer> arbol;
    
    /**
     * Raíz del árbol binario.
     */
    protected VerticeArbolBinario<Integer> raiz;

    /**
     * Regresa la representación en cadena de la representación gráfica del árbol.
     * @return la representación en cadena de la representación gráfica del árbol.
     */
    @Override public String grafica() {
	return gva(raiz, 1, 2, 225, lienzo).grafica();
    }
    
    /**
     * Regresa una graficadora de vértices con los parámetros recibidos.
     * @param vertice el vértice a graficar.
     * @param num el numerador de la fracción que se usará para calcular la
     *        posición del vértice en el lienzo.
     * @param den el denominador de la fracción que se usará para calcular
     *        la posición del vértice en el lienzo.
     * @param y el valor de y para el vértice.
     * @param lienzo el espacio en que se graficará el vértice.
     * @return una objeto instancia de {@link GraficadoraVerticeArbol}.
     */
    protected GraficadoraVerticeArbol gva(VerticeArbolBinario<Integer> vertice,
					  int num, int den, int y, Lienzo lienzo) {
	return new GraficadoraVerticeArbol(vertice, num, den, y, lienzo);
    }
}
