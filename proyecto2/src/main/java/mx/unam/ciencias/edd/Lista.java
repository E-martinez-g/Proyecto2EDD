package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
	    siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if (siguiente == null)
		throw new NoSuchElementException();
	    anterior = siguiente;
	    siguiente = siguiente.siguiente;
	    return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (anterior == null)
		throw new NoSuchElementException();
	    siguiente = anterior;
	    anterior = anterior.anterior;
	    return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
	    anterior = null;
	    siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            anterior = rabo;
	    siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	longitud++;
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    rabo.siguiente = nuevo;
	    nuevo.anterior = rabo;
	    rabo = nuevo;
	}
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	longitud++;
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    rabo.siguiente = nuevo;
	    nuevo.anterior = rabo;
	    rabo = nuevo;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	Nodo nuevo = new Nodo(elemento);
	longitud++;
	if (cabeza == null)
	    cabeza = rabo = nuevo;
	else {
	    cabeza.anterior = nuevo;
	    nuevo.siguiente = cabeza;
	    cabeza = nuevo;
	}
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
        if (i <= 0) {
	    agregaInicio(elemento);
	} else if (i >= longitud) {
	    agrega(elemento);
	} else {
	    Nodo nuevo = new Nodo(elemento);
	    longitud++;
	    Nodo buscador = cabeza;
	    for (int c = 0; c < i; c++)
		buscador = buscador.siguiente;
	    buscador.anterior.siguiente = nuevo;
	    nuevo.anterior = buscador.anterior;
	    buscador.anterior = nuevo;
	    nuevo.siguiente = buscador;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if (elemento == null || cabeza == null)
	    return;
	if (longitud == 1 && elemento.equals(cabeza.elemento))
	    limpia();
	else if (elemento.equals(cabeza.elemento))
	    eliminaPrimero();
	else {
	    Nodo buscador = cabeza;
	    while (buscador != null) {
		if (elemento.equals(buscador.elemento))
		    break;
		buscador = buscador.siguiente;
	    }
	    if (buscador == null)
		return;
	    if (buscador.siguiente == null)
		eliminaUltimo();
	    else {
		longitud--;
		buscador.anterior.siguiente = buscador.siguiente;
		buscador.siguiente.anterior = buscador.anterior;
	    }
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if (cabeza == null)
	    throw new NoSuchElementException();
	longitud--;
	T eliminado = cabeza.elemento;
	if (longitud == 0)
	    cabeza = rabo = null;
	else {
	    cabeza = cabeza.siguiente;
	    cabeza.anterior = null;
	}
	return eliminado;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if (cabeza == null)
	    throw new NoSuchElementException();
	longitud--;
	T eliminado = rabo.elemento;
	if (longitud == 0)
	    rabo = cabeza = null;
	else {
	    rabo = rabo.anterior;
	    rabo.siguiente = null;
	}
	return eliminado;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        if (elemento == null || cabeza == null)
	    return false;
	Nodo buscador = cabeza;
	while (buscador != null) {
	    if (elemento.equals(buscador.elemento))
		return true;
	    buscador = buscador.siguiente;
	}
	return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> opuesta = new Lista<T>();
	Nodo recorredor = cabeza;
	while (recorredor != null) {
	    opuesta.agregaInicio(recorredor.elemento);
	    recorredor = recorredor.siguiente;
	}
	return opuesta;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista <T> igual = new Lista<T>();
	Nodo recorredor = cabeza;
	while (recorredor != null) {
	    igual.agrega(recorredor.elemento);
	    recorredor = recorredor.siguiente;
	}
	return igual;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza = rabo = null;
	longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if (cabeza == null)
	    throw new NoSuchElementException();
	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if (rabo == null)
	    throw new NoSuchElementException();
	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i < 0 || i >= longitud)
	    throw new ExcepcionIndiceInvalido();
	Nodo buscador = cabeza;
	for (int c = 0; c < i; c++)
	    buscador = buscador.siguiente;
	return buscador.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
	if (elemento == null || cabeza == null)
	    return -1;
	Nodo buscador = cabeza;
	for (int c = 0; buscador != null; c++) {
	    if (elemento.equals(buscador.elemento))
		return c;
	    buscador = buscador.siguiente;
	}
	return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        String rep = "[";
	if (cabeza == null)
	    return (rep + "]");
	Nodo recorredor = cabeza;
	while (recorredor.siguiente != null) {
	    rep += recorredor.elemento.toString() + ", ";
	    recorredor = recorredor.siguiente;
	}
	return (rep + recorredor.elemento.toString() + "]");
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if (longitud != lista.longitud)
	    return false;
	Nodo este = cabeza;
	Nodo otro = lista.cabeza;
	while (este != null) {
	    if (!este.elemento.equals(otro.elemento))
		return false;
	    este = este.siguiente;
	    otro = otro.siguiente;
	}
	return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
	if (longitud <= 1)
	    return copia();
        Lista<T> l1 = new Lista<T>();
	Lista<T> l2 = new Lista<T>();
	Lista<T> esta = copia();
	int mitad = longitud / 2;
	for (int c = 0; c < mitad; c++)
	    l1.agrega(esta.eliminaPrimero());
	while (esta.cabeza != null)
	    l2.agrega(esta.eliminaPrimero());
	return merge(comparador,
		     l1.mergeSort(comparador),
		     l2.mergeSort(comparador));
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Toma dos listas previamente ordenadas y regresa una lista con los elementos
     * de ambas ordenados, el método necesita una instancia de {@link Comparator}
     * para mezclar las listas.
     * @param comparador el comparador que se utilizará para mezclar las listas.
     * @param l1 la primera de las listas a mezclar.
     * @param l2 la segunda de las listas a mezclar.
     * @return una lista nueva con los elementos de las dos anteriores ordenados.
     **/
    private Lista<T> merge(Comparator<T> comparador, Lista<T> l1, Lista<T> l2) {
	Lista<T> mezclada = new Lista<T>();
	while (l1.cabeza != null && l2.cabeza != null)
	    if (comparador.compare(l1.cabeza.elemento, l2.cabeza.elemento) <= 0)
		mezclada.agrega(l1.eliminaPrimero());
	    else
		mezclada.agrega(l2.eliminaPrimero());
	if (l1.cabeza == null){
	    concatenaListas(mezclada, l2);
	} else {
	    concatenaListas(mezclada, l1);
	}
	return mezclada;
    }

    /**
     * Concatena dos listas en una sola. No regresa nada porque la primer lista
     * se vuelve la concatenada.
     * @param l1 la lista cuya cabeza será igual a la de la concatenada.
     * @param l2 la lista cuya cola será igual a la de la concatenada.
     */
    private void concatenaListas(Lista<T> l1, Lista<T> l2) {
	l1.rabo.siguiente = l2.cabeza;
	l2.cabeza.anterior = l1.rabo;
	l1.rabo = l2.rabo;
	l1.longitud += l2.longitud;
    }
    
    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        if (elemento == null || cabeza == null)
	    return false;
	Nodo buscador = cabeza;
	while (buscador != null) {
	    if (comparador.compare(elemento, buscador.elemento) == 0)
		return true;
	    if (comparador.compare(elemento, buscador.elemento) < 0)
		return false;
	    buscador = buscador.siguiente;
	}
	return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
