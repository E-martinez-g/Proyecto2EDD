package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

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
	 * La coordenada en y del vértice.
	 */
	protected int y;
	
	/**
	 * La coordenada en x del vértice.
	 */
	protected int x;
	
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
	public GraficadoraVerticeArbol(VerticeArbolBinario<Integer> vertice,
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
	    s += conecta();
	    s += vertice();
	    s += contenido();
	    s += hijos();
	    return s;
	}
	
	/**
	 * Regresa la cadena del vértice.
	 * @return la cadena del vértice.
	 */
	@Override protected String vertice() {
	    String s = "";
	    s += "<circle r='125' fill='white' stroke='black' ";
	    s += "stroke-width='3' cx='" + x + "' cy='" + y + "' />\n";
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
	    s += vertice.get() + "</text>\n"
		}
	
	/**
	 * Regresa la cadena de las referencias izquierdo y derecho del vértice.
	 * @return la cadena de las referencias izquierdo y derecho del vértice.
	 */
	private String conecta() {
	    String s = "";
	    int y2 = y + 450;
	    int x2;
	    if (vertice.hayDerecho()) {
		x2 = lienzo.getAncho();
		x2 *= (numerador * 2) + 1;
		x2 /= denominador * 2;
		s += linea(x, y, x2, y2);
	    }
	    if (vertice.hayIzquierdo()) {
		x2 = lienzo.getAncho();
		x2 *= (numerador * 2) - 1;
		x2 /= denominador * 2;
		s += linea(x, y, x2, y2);
	    }
	    return s;
	}
	
	/**
	 * Construye una linea a partir de 4 números.
	 * @param x1 la coordenada en x del primer punto.
	 * @param y1 la coordenada en y del primer punto.
	 * @param x2 la coordenada en x del segundo punto.
	 * @param y2 la coordenada en y del segundo punto.
	 * @return la cadena de una linea que empieza en el primer punto y
	 *         termina en el segundo.
	 */
	private String linea(int x1, int y1, int x2, int y2) {
	    String s = "";
	    s += "<line stroke='black' stroke-width='3' x1='" + x1 + "' ";
	    s += "y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' /\n>";
	    return s;
	}

	/**
	 * Llama el método grafica de los hijos del vértice actual.
	 * Este método recorre el árbol recursivamente en dfs-preorder.
	 * @return la cadena de los hijos.
	 */
	private String hijos() {
	    String s = "";
	    if (vertice.hayIzquierdo()) {
		s += gva(vertice.izquierdo(),
			 ((numerador * 2) - 1),
			 (denominador * 2),
			 (y + 450), lienzo).grafica();
	    }
	    if (vertice.hayDerecho()) {
		s += gva(vertice.derecho(),
			 ((numerador * 2) + 1),
			 (denominador * 2),
			 (y + 450), lienzo).grafica();
	    }
	    return s;
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
	
    /**
     * Arbol binario.
     */
    protected ArbolBinario<Integer> arbol;
    
    /**
     * Raíz del árbol binario.
     */
    protected VerticeArbolBinario<Integer> raiz;
    
}
