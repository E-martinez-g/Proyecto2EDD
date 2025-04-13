package mx.unam.ciencias.edd;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus vértices, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeAVL extends Vertice {

        /** La altura del vértice. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
            super(elemento);
	    actualizaAltura();
        }

        /**
         * Regresa la altura del vértice.
         * @return la altura del vértice.
         */
        @Override public int altura() {
            return altura;
        }

        /**
         * Regresa una representación en cadena del vértice AVL.
         * @return una representación en cadena del vértice AVL.
         */
        @Override public String toString() {
            return elemento.toString() + " " + altura + "/" + balance();
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeAVL}, su elemento es igual al elemento de éste
         *         vértice, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeAVL vertice = (VerticeAVL)objeto;
            return altura == vertice.altura && super.equals(objeto);
        }

	/**
	 * Realiza una audición para obtener al hijo izquierdo como instancia
	 * de {@link VerticeAVL}.
	 * @return el hijo izquierdo como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL getIzquierdo() {
	    return (VerticeAVL) izquierdo;
	}

	/**
	 * Realiza una audición para obtener al hijo derecho como instancia
	 * de {@link VerticeAVL}.
	 * @return el hijo derecho como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL getDerecho() {
	    return (VerticeAVL) derecho;
	}

	/**
	 * Realiza una audición para obtener al padre como instancia
	 * de {@link VerticeAVL}.
	 * @return el padre como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL getPadre() {
	    return (VerticeAVL) padre;
	}
	
	/**
	 * Regresa el balance del vértice.
	 * @return la altura del hijo izquierdo menos la del derecho.
	 */
	private int balance() {
	    return alturaAVL(getIzquierdo()) - alturaAVL(getDerecho());
	}

	/**
	 * Actualiza la altura del vértice.
	 */
	private void actualizaAltura() {
	    altura = 1 + Math.max(alturaAVL(getIzquierdo()), alturaAVL(getDerecho()));
	}
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Construye un árbol AVL a partir de una colección. El árbol AVL tiene los
     * mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol AVL.
     */
    public ArbolAVL(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link VerticeAVL}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol girándolo como
     * sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
	rebalancea((VerticeAVL) ultimoAgregado.padre);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
	VerticeAVL eliminar = (VerticeAVL) busca(elemento);
	if (eliminar == null)
	    return;
	if (eliminar.izquierdo != null && eliminar.derecho != null) {
	    eliminar = (VerticeAVL) intercambiaEliminable(eliminar);
	}
	eliminaVertice(eliminar);
	rebalancea((VerticeAVL) eliminar.padre);
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la derecha por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la izquierda por el " +
                                                "usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la izquierda por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la derecha por el " +
                                                "usuario.");
    }

    /**
     * Regresa la altura de un vértice aunque sea <code>null</code>.
     * @return -1 si el vértice es <code>null</code>, en otro caso
     *         su altura.
     */
    private int alturaAVL(VerticeAVL vertice) {
	return vertice == null ? -1 : vertice.altura;
    }

    /**
     * Rebalancea el árbol después de agregar o eliminar.
     * @param el vértice sobre el que se encuentra el algoritmo de rebalanceo.
     */
    private void rebalancea(VerticeAVL vertice) {
	if (vertice == null)
	    return;
	vertice.actualizaAltura();
	if (vertice.balance() == -2){
	    if (vertice.getDerecho().balance() == 1) {
		super.giraDerecha(vertice.derecho);
		vertice.getDerecho().getDerecho().actualizaAltura();
		vertice.getDerecho().actualizaAltura();
	    }
	    super.giraIzquierda(vertice);
	    vertice.actualizaAltura();
	    vertice = vertice.getPadre();
	    vertice.actualizaAltura();
	} else if (vertice.balance() == 2) {
	    if (vertice.getIzquierdo().balance() == -1) {
		super.giraIzquierda(vertice.izquierdo);
		vertice.getIzquierdo().getIzquierdo().actualizaAltura();
		vertice.getIzquierdo().actualizaAltura();
	    }
	    super.giraDerecha(vertice);
	    vertice.actualizaAltura();
	    vertice = vertice.getPadre();
	    vertice.actualizaAltura();
	}
	rebalancea(vertice.getPadre());
    }
    
}
