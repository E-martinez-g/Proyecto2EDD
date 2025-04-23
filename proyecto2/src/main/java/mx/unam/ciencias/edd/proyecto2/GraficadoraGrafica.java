package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.VerticeGrafica;
import mx.unam.ciencias.edd.Lista;
import java.util.Iterator;

/**
 * Clase para graficadoras de gráficas.
 */
public class GraficadoraGrafica extends GraficadoraEstructura {

    //sonVecinos(Integer a, Integer b)
    //get()
    //adyace(GraficadoraVerticeGrafica gvg)
    
    /**
     * Lista de los vértices.
     */
    Lista<GraficadoraVerticeGrafica> vertices;

    /**
     * Lista de los vértices que no son adyacentes a ningún otro.
     */
    Lista<GraficadoraVerticeGrafica> separados;

    /**
     * Constructor para graficadoras de gráficas.
     * @param lista la lista de elementos como cadenas.
     */
    public GraficadoraGrafica(Lista<String> lista) throws IllegalArgumentException {
	if (lista.getLongitud() % 2 != 0)
	    throw new ExcepcionGraficaNoValida("El número de elementos no es par.");
	vertices = new Lista<>();
	separados = new Lista<>();
	creaVertices(verificaElementos(lista));
	lienzo = new Lienzo(Estructura.GRAFICA, elementos);
	for (GraficadoraVerticeGrafica gvg : vertices) {
	    gvg.setLienzo(lienzo);
	}
    }

    /**
     * Crea los vértices y les asigna sus adyacencias.
     * @param elementos la lista de elementos a partir de la cual formará los vértices.
     */
    private void creaVertices(Lista<Integer> elementos) throws ExcepcionGraficaNoValida {
	Iterator iterador = elementos.iterator();
	while (iterador.hasNext()) {
	    Integer a = iterador.next();
	    Integer b = iterador.next();
	    if (noExiste(a))
		vertices.agrega(new GraficadoraVerticeGrafica(a));
	    if (noExiste(b))
		vertices.agrega(new GraficadoraVerticeGrafica(b));
	    if (a.equals(b)) {
		
	    }  else {
		if (sonVecinos(a, b))
		    throw new ExcepcionGraficaNoValida("Los vértices" + a + "y" + b + "no se pueden " +
						       "hacer adyacentes más de una vez.");
		for (GraficaVerticeGrafica gvg : separados) {
		    if (a.equals(gvg.get()))
			throw new ExcepcionGraficaNoValida("El vértice" + a + "no se puede hacer adyacente a otro " +
							   "vértice pues fue indicado como desconectado previamente.");
		    if (b.equals(gvg.get()))
			throw new ExcepcionGraficaNoValida("El vértice" + b + "no se puede hacer adyacente a otro " +
							   "vértice pues fue indicado como desconectado previamente.");
		}
		adyace(a,b);
	    }
	}
    }

    /**
     * Revisa si aún no existe un vértice con un elemento en específico.
     * @param elemento el elemento que queremos saber si ya cuenta con un vértice.
     */
    private boolean noExiste(Integer elemento) {
	for (GraficadoraVerticeGrafica gvg : vertices)
	    if (elemento.equals(gvg.get()))
		return false;
	return true;
    }

    /**
     * Busca un vértice y lo hace adyacente a otro.
     * @param e1 el elemento del primer vértice a buscar.
     * @param e2 el elemento del segundo vértice a buscar.
     */
    private void adyace(Integer e1, Integer e2) {
	GraficadoraVerticeGrafica gvg1;
	GraficadoraVerticeGrafica gvg2;
	for (GraficadoraVerticeGrafica gvg : vertices) {
	    if (e1.equals(gvg.get()))
		gvg1 = gvg;
	    if (e2.equals(gvg.get()))
		gvg2 = gvg;
	}
	gvg1.adyace(gvg2);
	gvg2.adyace(gvg1);
    }
}
