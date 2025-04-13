package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            super(elemento);
	    color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override public String toString() {
            return (color == Color.ROJO ? "R{" : "N{") + elemento.toString() + "}";
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
                VerticeRojinegro vertice = (VerticeRojinegro)objeto;
	    return color == vertice.color && super.equals(objeto);
        }

	/**
	 * Verifica si un vértice es hijo derecho. Solo se usa cuando se sabe que hay padre.
	 * @return <code>true</code> si es el hijo derecho de su padre, <code>false</code> en otro caso.
	 */
	private boolean esDerecho() {
	    return this == padre.derecho;
	}
	
	/**
	 * Verifica si un vértice es hijo izquierdo. Solo se usa cuando se sabe que hay padre.
	 * @return <code>true</code> si es el hijo izquierdo, <code>false</code> en otro caso.
	 */
	private boolean esIzquierdo() {
	    return this == padre.izquierdo;
	}

	/**
	 * Regresa el tío de un vértice. Solo se usa cuando se sabe que hay abuelo.
	 * @return el hijo izquierdo del abuelo si el padre es derecho, si no el hijo derecho.
	 */
	private VerticeRojinegro getTio() {
	    return getPadre().esDerecho() ? getAbuelo().getIzquierdo() : getAbuelo().getDerecho();
	}

	/**
	 * Revisa si un vértice y su padre están cruzados.
	 * @return <code>true</code> si su padre tiene diferente dirección, <code>false</code> en otro caso.
	 */
	private boolean estaCruzado() {
	    return esDerecho() ? getPadre().esIzquierdo() : getPadre().esDerecho();
	}

	/**
	 * Hace una audición para obtener al padre como instancia de {@link VerticeRojinegro}.
	 * @return el padre como instancia de {@link VerticeRojinegro}.
	 */
	private VerticeRojinegro getPadre() {
	    return (VerticeRojinegro) padre;
	}

	/**
	 * Hace una audición para obtener al hijo izquierdo como instancia de {@link VerticeRojinegro}.
	 * @return el hijo izquierdo como instancia de {@link VerticeRojinegro}.
	 */
	private VerticeRojinegro getIzquierdo() {
	    return (VerticeRojinegro) izquierdo;
	}

	/**
	 * Hace una audición para obtener al hijo derecho como instancia de {@link VerticeRojinegro}.
	 * @return el hijo derecho como instancia de {@link VerticeRojinegro}.
	 */
	private VerticeRojinegro getDerecho() {
	    return (VerticeRojinegro) derecho;
	}
	
	/**
	 * Hace una audición para obtener al abuelo como instancia de {@link VerticeRojinegro}.
	 * @return el abuelo como instancia de {@link VerticeRojinegro}.
	 */
	private VerticeRojinegro getAbuelo() {
	    return (VerticeRojinegro) padre.padre;
	}

	/**
	 * Regresa el único hijo no nulo de un vértice, solo se usa cuando hay un hijo único.
	 * @return el hijo válido de un vértice.
	 */
	private VerticeRojinegro getHijoUnico() {
	    return izquierdo == null ? getDerecho() : getIzquierdo();
	}

	/**
	 * Regresa al hermano de un vértice.
	 * @return el hermano del vértice.
	 */
	private VerticeRojinegro getHermano() {
	    return esDerecho() ? getPadre().getIzquierdo() : getPadre().getDerecho();
	}
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() { super(); }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        return ((VerticeRojinegro) vertice).color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    return;
        super.agrega(elemento);
	VerticeRojinegro ultimo = (VerticeRojinegro) ultimoAgregado;
	ultimo.color = Color.ROJO;
	rebalanceaAgrega(ultimo);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
	VerticeRojinegro eliminar = (VerticeRojinegro) busca(elemento);
	if (eliminar == null)
	    return;
	if (eliminar.derecho != null && eliminar.izquierdo != null)
	    eliminar = (VerticeRojinegro) intercambiaEliminable(eliminar);
	VerticeRojinegro fantasma = null;
	if (eliminar.derecho == null && eliminar.izquierdo == null) {
	    fantasma = new VerticeRojinegro(null);
	    fantasma.padre = eliminar;
	    eliminar.izquierdo = fantasma;
	    fantasma.color = Color.NEGRO;
	}
	VerticeRojinegro subido = eliminar.getHijoUnico();
	eliminaVertice(eliminar);
	if (subido.color == Color.ROJO || eliminar.color == Color.ROJO)
	    subido.color = Color.NEGRO;
	else if (eliminar.color == Color.NEGRO && subido.color == Color.NEGRO)
	    rebalanceaElimina(subido);
	if (fantasma != null)
	    if (fantasma == raiz)
		raiz = null;
	    else if (fantasma.esDerecho())
		fantasma.padre.derecho = null;
	    else
		fantasma.padre.izquierdo = null;
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la izquierda " +
                                                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la derecha " +
                                                "por el usuario.");
    }

    /**
     * Balancea el árbol después de agregar.
     * @param vertice el vértice en el que se encuentra el algoritmo de balanceo.
     */
    private void rebalanceaAgrega(VerticeRojinegro vertice) {
	if (vertice.getPadre() == null) {
	    vertice.color = Color.NEGRO;
	    return;
	}
	if (vertice.getPadre().color == Color.NEGRO)
	    return;
	if (esRojo(vertice.getTio())) {
	    vertice.getTio().color = Color.NEGRO;
	    vertice.getPadre().color = Color.NEGRO;
	    vertice.getAbuelo().color = Color.ROJO;
	    rebalanceaAgrega(vertice.getAbuelo());
	    return;
	}
	if (vertice.estaCruzado()) {
	    VerticeRojinegro vater = vertice.getPadre();
	    if (vertice.esDerecho())
		super.giraIzquierda(vertice.getPadre());
	    else
		super.giraDerecha(vertice.getPadre());
	    vertice = vater;
	}
	vertice.getPadre().color = Color.NEGRO;
	vertice.getAbuelo().color = Color.ROJO;
	if (vertice.esDerecho())
	    super.giraIzquierda(vertice.padre.padre);
	else
	    super.giraDerecha(vertice.padre.padre);
    }

    /**
     * Balancea el árbol después de eliminar.
     * @param vertice el vértice en el que se encuentra el algoritmo de balanceo.
     */
    private void rebalanceaElimina(VerticeRojinegro vertice) {
        if (vertice.padre == null)
	    return;
	VerticeRojinegro h = vertice.getHermano();
	VerticeRojinegro p = vertice.getPadre();
	if (h.color == Color.ROJO) {
	    p.color = Color.ROJO;
	    h.color = Color.NEGRO;
	    if (vertice.esDerecho())
		super.giraDerecha(p);
	    else
		super.giraIzquierda(p);
	    h = vertice.getHermano();
	}
	VerticeRojinegro sI = h.getIzquierdo();
	VerticeRojinegro sD = h.getDerecho();
	if (esNegro(p) && esNegro(h) &&	esNegro(sI) && esNegro(sD)) {
	    h.color = Color.ROJO;
	    rebalanceaElimina(p);
	    return;
	}
	if (esNegro(h) && esNegro(sI) && esNegro(sD) && esRojo(p)) {
	    h.color = Color.ROJO;
	    p.color = Color.NEGRO;
	    return;
	}
	if (sonBicolores(sI, sD)) {
	    if (vertice.esDerecho() && esRojo(sD)) {
		h.color = Color.ROJO;
		sD.color = Color.NEGRO;
		super.giraIzquierda(h);
	    } else if (vertice.esIzquierdo() && esRojo(sI)) {
		h.color = Color.ROJO;
		sI.color = Color.NEGRO;
		super.giraDerecha(h);
	    }
	    h = vertice.getHermano();
	    sI = h.getIzquierdo();
	    sD = h.getDerecho();
	}
	h.color = p.color;
	p.color = Color.NEGRO;
	if (vertice.esDerecho()) {
	    sI.color = Color.NEGRO;
	    super.giraDerecha(p);
	} else {
	    sD.color = Color.NEGRO;
	    super.giraIzquierda(p);
	}
    }

    /**
     * Verifica que el color de un vértice sea rojo.
     * @param vertice el vértice cuyo color queremos saber si es rojo.
     * @return <code>true</code> si el vértice es rojo, <code>false</code> en otro caso.
     */
    private boolean esRojo(VerticeRojinegro vertice) {
	return vertice != null && vertice.color == Color.ROJO;
    }

    /**
     * Verifica que el color de un vértice sea negro.
     * @param vertice el vértice cuyo color queremos saber si es negro.
     * @return <code>true</code> si el vértice es negro, <code>false</code> en otro caso.
     */
    private boolean esNegro(VerticeRojinegro vertice) {
	return vertice == null || vertice.color == Color.NEGRO;
    }

    /**
     * Verifica si los vértice recibidos son de diferente color.
     * @param v1 el primer vértice a revisar.
     * @param v2 el segundo vértice a revisar.
     * @return <code>true</code> si v1 y v2 son de colores diferentes,
     *          <code>false</code> en otro caso.
     */
    private boolean sonBicolores(VerticeRojinegro v1, VerticeRojinegro v2) {
	return esNegro(v1) ? esRojo(v2) : esNegro(v2);
    }
}
