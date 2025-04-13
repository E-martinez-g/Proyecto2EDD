package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
            pila = new Pila<Vertice>();
	    if (raiz != null) {
		Vertice siguiente = raiz;
		pila.mete(raiz);
		while (siguiente.izquierdo != null) {
		    pila.mete(siguiente.izquierdo);
		    siguiente = siguiente.izquierdo;
		}
	    }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
	    Vertice siguiente = pila.saca();
	    if (siguiente.derecho != null) {
		pila.mete(siguiente.derecho);
		Vertice siguienteIz = siguiente.derecho;
		while (siguienteIz.izquierdo != null) {
		    pila.mete(siguienteIz.izquierdo);
		    siguienteIz = siguienteIz.izquierdo;
		}
	    }
	    return siguiente.elemento;
	}
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    throw new IllegalArgumentException();
	Vertice nuevo = nuevoVertice(elemento);
	elementos++;
	if (raiz == null)
	    raiz = nuevo;
	else {
	    Vertice p = null;
	    Vertice actual = raiz;
	    while (actual != null) {
		p = actual;
		if (elemento.compareTo(actual.elemento) > 0)
		    actual = actual.derecho;
		else
		    actual = actual.izquierdo;
	    }
	    nuevo.padre = p;
	    if (elemento.compareTo(p.elemento) > 0)
		p.derecho = nuevo;
	    else
		p.izquierdo = nuevo;
	}
	ultimoAgregado = nuevo;
    }
    
    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        Vertice eliminado = vertice(busca(elemento));
	if (eliminado == null)
	    return;
	if (eliminado.izquierdo != null && eliminado.derecho != null)
	    eliminado = intercambiaEliminable(eliminado);
        eliminaVertice(eliminado);
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        Vertice maxMenor = maximoEnSubarbol(vertice.izquierdo);
	vertice.elemento = maxMenor.elemento;
	return maxMenor;
    }

    /**
     * Regresa el vértice con el elemento más grande en el subárbol que tiene como
     * raíz al vértice recibido.
     * @param vertice el vértice raíz del subárbol en el que buscaremos el máximo.
     * @return el vértice con el elemento más grande en el subárbol elegido.
     */
    private Vertice maximoEnSubarbol(Vertice vertice) {
	if (vertice.derecho == null)
	    return vertice;
	return maximoEnSubarbol(vertice.derecho);
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
	elementos--;
	if (vertice.padre == null) {
	    if (vertice.izquierdo != null) {
		vertice.izquierdo.padre = null;
		raiz = vertice.izquierdo;
	    } else if (vertice.derecho != null) {
		vertice.derecho.padre = null;
		raiz = vertice.derecho;
	    } else
		raiz = null;
	} else if (vertice.padre.izquierdo == vertice) {
	    if (vertice.izquierdo != null) {
		vertice.padre.izquierdo = vertice.izquierdo;
		vertice.izquierdo.padre = vertice.padre;
	    } else if (vertice.derecho != null) {
		vertice.padre.izquierdo = vertice.derecho;
		vertice.derecho.padre = vertice.padre;
	    } else
		vertice.padre.izquierdo = null;
	} else { 
	    if (vertice.izquierdo != null) {
		vertice.padre.derecho = vertice.izquierdo;
		vertice.izquierdo.padre = vertice.padre;
	    } else if (vertice.derecho != null) {
		vertice.padre.derecho = vertice.derecho;
		vertice.derecho.padre = vertice.padre;
	    } else
		vertice.padre.derecho = null;
	}
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
        if (elemento == null || raiz == null)
	    return null;
	Vertice actual = raiz;
	while (actual != null) {
	    if (elemento.equals(actual.elemento))
		return actual;
	    if (elemento.compareTo(actual.elemento) < 0)
		actual = actual.izquierdo;
	    else
		actual = actual.derecho;
	}
	return null;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
	Vertice v = vertice(vertice);
	if (v.izquierdo == null)
	    return;
	Vertice pV = v.padre;
	Vertice iD = v.izquierdo.derecho;
	if (pV != null) {
	    v.izquierdo.padre = pV;
	    if (pV.izquierdo == v)
		pV.izquierdo = v.izquierdo;
	    else
		pV.derecho = v.izquierdo;
	} else
	    v.izquierdo.padre = null;
	v.izquierdo.derecho = v;
	v.padre = v.izquierdo;
	v.izquierdo = null;
	if (iD != null) {
	    iD.padre = v;
	    v.izquierdo = iD;
	}
	if (raiz.padre != null)
	    raiz = raiz.padre;
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
	Vertice v = vertice(vertice);
	if (v.derecho == null)
	    return;
	Vertice pV = v.padre;
	Vertice dI = v.derecho.izquierdo;
	if (pV != null) {
	    v.derecho.padre = pV;
	    if (pV.izquierdo == v)
		pV.izquierdo = v.derecho;
	    else
		pV.derecho = v.derecho;
	} else
	    v.derecho.padre = null;
	v.derecho.izquierdo = v;
	v.padre = v.derecho;
	v.derecho = null;
	if (dI != null) {
	    dI.padre = v;
	    v.derecho = dI;
	}
	if (raiz.padre != null)
	    raiz = raiz.padre;
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
        if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<Vertice>();
	pila.mete(raiz);
	while (!pila.esVacia()) {
	    Vertice siguiente = pila.saca();
	    if (siguiente.derecho != null)
		pila.mete(siguiente.derecho);
	    if (siguiente.izquierdo != null)
		pila.mete(siguiente.izquierdo);
	    accion.actua(siguiente);
	}
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<Vertice>();
	Vertice siguiente = raiz;
	pila.mete(raiz);
	while (siguiente.izquierdo != null) {
	    pila.mete(siguiente.izquierdo);
	    siguiente = siguiente.izquierdo;
	}
	while (!pila.esVacia()) {
	    siguiente = pila.saca();
	    if (siguiente.derecho != null) {
		pila.mete(siguiente.derecho);
		Vertice siguienteIz = siguiente.derecho;
		while (siguienteIz.izquierdo != null) {
		    pila.mete(siguienteIz.izquierdo);
		    siguienteIz = siguienteIz.izquierdo;
		}
	    }
	accion.actua(siguiente);
	}
    }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
	if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<Vertice>();
	pila.mete(raiz);
	pila.mete(raiz);
	while (!pila.esVacia()) {
	    Vertice siguiente = pila.saca();
	    if (pila.esVacia() || siguiente != pila.mira())
		accion.actua(siguiente);
	    else {
		if (siguiente.derecho != null) {
		    pila.mete(siguiente.derecho);
		    pila.mete(siguiente.derecho);
		}
		if (siguiente.izquierdo != null) {
		    pila.mete(siguiente.izquierdo);
		    pila.mete(siguiente.izquierdo);
		}
	    }
	}
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
