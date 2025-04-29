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
	 * El número del vértice.
	 */
	private int indice;

	/**
	 * La lista de graficadoras de vértices.
	 */
	private Lista<GraficadoraVerticeGrafica> gvgs;

	/**
	 * Regresa el elemento del vértice.
	 * @return el elemento del vértice.
	 */
	private Integer get() {
	    return vertice.get();
	}

	/**
	 * Regresa la cadena del vértice y sus aristas adyacentes.
	 * @return la cadena del vértice y sus aristas adyacentes.
	 */
	@Override protected String grafica() {

	}

	/**
	 * Regresa la cadena del vértice.
	 * @return la cadena del vértice.
	 */
	@Override protected String vertice() {

	}
	
	/**
	 * Regresa la cadena del contenido del vértice.
	 * @return la cadena del contenido del vértice.
	 */
	@Override protected String contenido() {

	}

	/**
	 * Regresa la cadena de las aristas del vértice.
	 * @return la cadena de las aristas del vértice.
	 */
	@Override protected String conecta() {
	    
	}
    }
    
    /**
     * Gráfica a graficar.
     */
    private Grafica<Integer> grafica;
    
    /**
     * Lista de los vértices que no son adyacentes a ningún otro.
     */
    private Lista<GraficadoraVerticeGrafica> separados;

    /**
     * Constructor para graficadoras de gráficas.
     * @param lista la lista de elementos como cadenas.
     */
    public GraficadoraGrafica(Lista<String> lista) throws IllegalArgumentException {
	if (lista.getLongitud() % 2 != 0)
	    throw new ExcepcionGraficaNoValida("El número de elementos no es par.");
	separados = new Lista<>();
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
	    if (!grafica.contains(i))
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
		if (separados.contains(a))
		    throw new ExcepcionGraficaNoValida("No se puede desconectar dos veces a un vértice.");
		else if (grafica.vertice(a).getGrado() > 0)
		    throw new ExcepcionGraficaNoValida("No se puede desconectar un vértice que fue previamente conectado.");
		else
		    separados.agrega(a);
	    else
		if (separados.contains(a) || separados.contains(b))
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
	Lista<GraficadoraVerticeGrafica> gvgs = new Lista<>();
	int c = 0;
	for (Integer integer : grafica)
	    gvgs.agrega(new GraficadoraVerticeGrafica(gvgs, c++, grafica.vertice(integer), lienzo));
	for (GraficadoraVerticeGrafica gvg : gvgs) {
	    s += gvg.grafica();
	    grafica.elimina(gvg.get());
	}
	return s;
    }
}
