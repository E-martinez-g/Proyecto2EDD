package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la pila.
     * @return una representación en cadena de la pila.
     */
    @Override public String toString() {
        String rep = "";
	Nodo n = cabeza;
	while (n != null) {
	    rep += n.elemento.toString() + "\n";
	    n = n.siguiente;
	}
	return rep;
    }

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    nuevo.siguiente = cabeza;
	    cabeza = nuevo;
	}
    }
}
