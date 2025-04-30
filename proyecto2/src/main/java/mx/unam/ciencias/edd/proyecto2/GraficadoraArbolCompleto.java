package mx.unam.ciencias.edd.proyecto2;

import java.util.NoSuchElementException;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para graficadoras de árboles completos.
 */
public class GraficadoraArbolCompleto extends GraficadoraArbol {

    /**
     * Constructor para Graficadoras de árboles completos.
     */
    public GraficadoraArbolCompleto(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolBinarioCompleto<Integer>(verificaElementos(lista));
	try {
	    raiz = arbol.raiz();
	} catch (NoSuchElementException nsee) {
	    raiz = null;
	}
	lienzo = new Lienzo(Estructura.ABC, arbol.altura());
    }

}
