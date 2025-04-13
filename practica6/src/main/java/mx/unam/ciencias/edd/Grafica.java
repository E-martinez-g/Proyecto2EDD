package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
	    iterador = vertices.iterator();
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            return iterador.next().elemento;
        }
    }

    /* Clase interna privada para vértices. */
    private class Vertice implements VerticeGrafica<T> {

        /* El elemento del vértice. */
        private T elemento;
        /* El color del vértice. */
        private Color color;
        /* La lista de vecinos del vértice. */
        private Lista<Vertice> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento = elemento;
	    color = Color.NINGUNO;
	    vecinos = new Lista<>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T get() {
	    return elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return color;
        }

        /* Regresa un iterable para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecinos;
        }
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        vertices = new Lista<>();
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    @Override public int getElementos() {
        return vertices.getLongitud();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        return aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
        if (elemento == null || buscaVertice(elemento) != null)
	    throw new IllegalArgumentException();
	vertices.agrega(new Vertice(elemento));
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
	if (a == null || b == null)
	    throw new NoSuchElementException();
	if (a.equals(b))
	    throw new IllegalArgumentException();
        Vertice v1 = buscaVertice(a);
	Vertice v2 = buscaVertice(b);
	if (v1 == null || v2 == null)
	    throw new NoSuchElementException();
	if (sonVecinosV(v1, v2))
	    throw new IllegalArgumentException();
	v1.vecinos.agrega(v2);
	v2.vecinos.agrega(v1);
	aristas++;
    }

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
	Vertice v1 = buscaVertice(a);
	Vertice v2 = buscaVertice(b);
	if (v1 == null || v2 == null)
	    throw new NoSuchElementException();
	if (!sonVecinosV(v1, v2))
	    throw new IllegalArgumentException();
	v1.vecinos.elimina(v2);
	v2.vecinos.elimina(v1);
	aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <code>true</code> si el elemento está contenido en la gráfica,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	return buscaVertice(elemento) != null;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
	Vertice v1 = buscaVertice(elemento);
        if (v1 == null)
	    throw new NoSuchElementException();
	for (Vertice v : v1.vecinos) {
		v.vecinos.elimina(v1);
		aristas--;
	}
	vertices.elimina(v1);
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <code>true</code> si a y b son vecinos, <code>false</code> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
	Vertice v1 = buscaVertice(a);
	Vertice v2 = buscaVertice(b);
	if (v1 == null || v2 == null)
	    throw new NoSuchElementException();
	return v1.vecinos.contiene(v2);
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
	Vertice v = buscaVertice(elemento);
	if (v == null)
	    throw new NoSuchElementException();
	return v;
    }

    /**
     * Define el color del vértice recibido.
     * @param vertice el vértice al que queremos definirle el color.
     * @param color el nuevo color del vértice.
     * @throws IllegalArgumentException si el vértice no es válido.
     */
    public void setColor(VerticeGrafica<T> vertice, Color color) {
	if (vertice == null || Vertice.class != vertice.getClass())
	    throw new IllegalArgumentException();
	Vertice v = (Vertice) vertice;
	v.color = color;
    }

    /**
     * Nos dice si la gráfica es conexa.
     * @return <code>true</code> si la gráfica es conexa, <code>false</code> en
     *         otro caso.
     */
    public boolean esConexa() {
        Cola<Vertice> cola = new Cola<>();
	if (vertices.esVacia())
	    return true;
	int n = vertices.getLongitud();
	if (aristas < (n - 1))
	    return false;
	if (aristas > (((n - 1) * (n - 2)) / 2))
	    return true;
	despinta();
	IteradorLista<Vertice> il = vertices.iteradorLista();
	cola.mete(il.next());
	il.previous().color = Color.ROJO;
	while (!cola.esVacia()) {
	    Vertice u = cola.saca();
	    for (Vertice w : u.vecinos)
		if (w.color == Color.NINGUNO) {
		    w.color = Color.ROJO;
		    cola.mete(w); 
		}
	}
	while (il.hasNext()) {
	    Vertice o = il.next();
	    if (o.color != Color.ROJO)
		return false;
	}
	return true;
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        for (Vertice v : vertices)
	    accion.actua(v);
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
	Vertice v = buscaVertice(elemento);
	if (v == null)
	    throw new NoSuchElementException();
	despinta();
	Cola<Vertice> cola = new Cola<>();
	v.color = Color.ROJO;
	cola.mete(v);
	while (!cola.esVacia()) {
	    Vertice u = cola.saca();
	    accion.actua(u);
	    for (Vertice w : u.vecinos)
		if (w.color != Color.ROJO) {
		    w.color = Color.ROJO;
		    cola.mete(w);
		}
	}
	despinta();
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
        Vertice v = buscaVertice(elemento);
	if (v == null)
	    throw new NoSuchElementException();
	despinta();
	Pila<Vertice> pila = new Pila<>();
	v.color = Color.ROJO;
	pila.mete(v);
	while (!pila.esVacia()) {
	    Vertice u = pila.saca();
	    accion.actua(u);
	    for (Vertice w : u.vecinos)
		if (w.color != Color.ROJO) {
		    w.color = Color.ROJO;
		    pila.mete(w);
		}
	}
	despinta();
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return vertices.esVacia();
    }

    /**
     * Limpia la gráfica de vértices y aristas, dejándola vacía.
     */
    @Override public void limpia() {
	aristas = 0;
	vertices.limpia();
    }

    /**
     * Regresa una representación en cadena de la gráfica.
     * @return una representación en cadena de la gráfica.
     */
    @Override public String toString() {
        String s = "{";
	despinta();
	for (Vertice v : vertices)
	    s += v.elemento.toString() + ", ";
	s += "}, {";
	for (Vertice u : vertices) {
	    u.color = Color.ROJO;
	    for (Vertice w : u.vecinos)
		if (w.color != Color.ROJO) {
		    s += "(" + u.elemento.toString() + ", ";
		    s += w.elemento.toString() + "), ";
		}
	}
	s += "}";
	return s;
    }

    /**
     * Nos dice si la gráfica es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la gráfica es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Grafica<T> grafica = (Grafica<T>)objeto;
        if (vertices.getLongitud() != grafica.vertices.getLongitud() ||
	    aristas != grafica.aristas)
	    return false;
	for (Vertice v : vertices) {
	    if (!grafica.contiene(v.elemento))
		return false;
	    for (Vertice u : v.vecinos)
		if (!grafica.sonVecinos(u.elemento, v.elemento))
		    return false;
	}
	return true;
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Busca un elemento en la lista de vértices de la gráfica y regresa el
     * vértice que lo contiene.
     * @param elemento el elemento a buscar en la gráfica.
     * @return el vértice que contiene al elemento o <code>null</code> en
     *         otro caso.
     */
    private Vertice buscaVertice(T elemento) {
	if (elemento != null)
	    for (Vertice vertice : vertices)
		if (elemento.equals(vertice.elemento))
		    return vertice;
	return null;
    }

    /**
     * Regresa verdadero si dos vértices son vecinos el uno del otro, falso
     * en otro caso.
     * @param v1 el vértice en cuya lista de vecinos revisaremos si el otro
     *        se encuentra.
     * @param v2 el vértice que buscaremos en la lista de vecinos del otro.
     * @return <code>true</code> si v2 se encuentra en la lista de vecinos
     *         de v1, <code>false</code> en otro caso.
     */
    private boolean sonVecinosV(Vertice v1, Vertice v2) {
        return v1.vecinos.contiene(v2);
    }

    /**
     * Asigna el color de todos los vértices a {@link Color#NINGUNO}.
     */
    private void despinta() {
	for (Vertice v : vertices)
	    v.color = Color.NINGUNO;
    }
}
