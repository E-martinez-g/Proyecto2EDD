package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.VerticeGrafica;
import mx.unam.ciencias.edd.Lista;
import java.util.Iterator;

/**
 * Clase para graficadoras de gráficas.
 */
public class GraficadoraGrafica extends GraficadoraEstructura {

    /**
     * Clase interna para graficadoras de vértices de gráficas.
     */
    private class GraficadoraVerticeGrafica extends GraficadoraVertice {

	/**
	 * El vértice.
	 */
	private VerticeGrafica<Integer> vertice;

	/**
	 * Constructor para graficadoras de vértices de gráficas.
	 * @param lienzo el lienzo en que se graficará el vértice.
	 * @param vertice el vértice.
	 * @param i el índice del vértice entre el número de vértices.
	 */
	private GraficadoraVerticeGrafica(Lienzo lienzo, VerticeGrafica<Integer> vertice, double i) {
	    this.lienzo = lienzo;
	    this.vertice = vertice;
	    int radio = lienzo.getAncho();
	    radio /= 2;
	    radio -= 450;
	    double angulo = 2 * Math.PI;
	    angulo *= i;
	    x = lienzo.getAncho() / 2;
	    x += (int) radio * Math.cos(angulo);
	    y = lienzo.getAlto() / 2;
	    y += (int) radio * Math.sin(angulo);
	}

	/**
	 * Regresa el elemento del vértice.
	 * @return el elemento del vértice.
	 */
	private Integer get() {
	    return vertice.get();
	}

	/**
	 * Regresa la coordenada en x del vértice.
	 * @return la coordenada en x del vértice.
	 */
	private int getX() {
	    return x;
	}

	/**
	 * Regresa la coordenada en y del vértice.
	 * @return la coordenada en y del vértice.
	 */
	private int getY() {
	    return y;
	}

	/**
	 * Regresa la cadena del vértice y sus aristas adyacentes.
	 * @return la cadena del vértice y sus aristas adyacentes.
	 */
	@Override public String grafica() {
	    String s = "";
	    s += conecta();
	    s += vertice();
	    s += contenido();
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
	 * Regresa la cadena de las aristas del vértice.
	 * @return la cadena de las aristas del vértice.
	 */
	@Override protected String conecta() {
	    String s= "";
	    for (VerticeGrafica<Integer> vecino : vertice.vecinos()) {
		GraficadoraVerticeGrafica gvg = buscaGVG(vecino.get());
		s += linea(x, y, gvg.getX(), gvg.getY());
	    }
	    return s;
	}

	/**
	 * Regresa la graficadora correspondiente al elemento buscado.
	 * @param elemento el elemento a buscar.
	 * @return la graficadora correspondiente al vértice con el elemento buscado.
	 */
	private GraficadoraVerticeGrafica buscaGVG(Integer elemento) {
	    for (GraficadoraVerticeGrafica gvg : gvgs) {
		if (elemento.equals(gvg.get()))
		    return gvg;
	    }
	    return null;
	}
    }
    
    /**
     * Gráfica a graficar.
     */
    private Grafica<Integer> grafica;
    
    /**
     * Lista de los elementos que pertenecen a vértices que no son adyacentes a ningún otro.
     */
    private Lista<Integer> separados;
    
    /**
     * La lista de graficadoras de vértices.
     */
    private Lista<GraficadoraVerticeGrafica> gvgs;

    /**
     * Constructor para graficadoras de gráficas.
     * @param lista la lista de elementos como cadenas.
     */
    public GraficadoraGrafica(Lista<String> lista) throws IllegalArgumentException {
	if (lista.getLongitud() % 2 != 0)
	    throw new ExcepcionGraficaNoValida("El número de elementos no es par.");
	separados = new Lista<>();
	gvgs = new Lista<>();
	grafica = new Grafica<>();
	Lista<Integer> elementos = verificaElementos(lista);
	creaVertices(elementos);
	conectaVertices(elementos);
	lienzo = new Lienzo(Estructura.GRAFICA, grafica.getElementos());
    }

    /**
     * Crea los vértices.
     * @param elementos la lista de elementos a partir de la cual formará los vértices.
     */
    private void creaVertices(Lista<Integer> elementos) {
	for (Integer i : elementos)
	    if (!grafica.contiene(i))
		grafica.agrega(i);
    }

    /**
     * Asigna a los vértices de la gráfica sus adyacencias.
     * @param elementos la lista que usará para determinar las adyacencias.
     */
    private void conectaVertices(Lista<Integer> elementos) {
	Iterator<Integer> iterador = elementos.iterator();
	while (iterador.hasNext()) {
	    Integer a = iterador.next();
	    Integer b = iterador.next();
	    if (a.equals(b))
		if (separados.contiene(a))
		    throw new ExcepcionGraficaNoValida("No se puede desconectar dos veces a un vértice.");
		else if (grafica.vertice(a).getGrado() > 0)
		    throw new ExcepcionGraficaNoValida("No se puede desconectar un vértice que fue previamente conectado.");
		else
		    separados.agrega(a);
	    else
		if (separados.contiene(a) || separados.contiene(b))
		    throw new ExcepcionGraficaNoValida("No se puede conectar a un vértice que fue previamente desconectado.");
		else
		    try {
			grafica.conecta(a, b);
		    } catch (IllegalArgumentException iae) {
			throw new ExcepcionGraficaNoValida("No se puede volver a conectar un par de elementos ya conectados.");
		    }
	}
    }

    /**
     * Regresa la representación en cadena de la representación gráfica de la gráfica.
     * @return la representación en cadena de la representación gráfica de la gráfica.
     */
    @Override public String grafica() {
	String s = "";
	int c = 0;
	for (Integer integer : grafica)
	    gvgs.agrega(new GraficadoraVerticeGrafica(lienzo, grafica.vertice(integer),
						      (((double) c++) / grafica.getElementos())));
	for (GraficadoraVerticeGrafica gvg : gvgs) {
	    s += gvg.grafica();
	    grafica.elimina(gvg.get());
	    gvgs.elimina(gvg);
	}
	return s;
    }
}
