package mx.unam.ciencias.edd.proyecto2;

import java.util.NoSuchElementException;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para graficadoras de árboles ordenados.
 */
public class GraficadoraArbolOrdenado extends GraficadoraArbol {

    /**
     * Constructor para graficadoras de árboles ordenados.
     */
    public GraficadoraArbolOrdenado(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolBinarioOrdenado<Integer>(verificaElementos(lista));
	try {
	    raiz = arbol.raiz();
	} catch (NoSuchElementException nsee) {
	    raiz = null;
	}
	lienzo = new Lienzo(Estructura.ABO, arbol.altura());
    }

}


