package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficadoras de árboles ordenados.
 */
public class GraficadoraArbolOrdenado extends GraficadoraArbol {

    /**
     * Constructor para graficadoras de árboles ordenados.
     */
    public GraficadoraArbolCompleto(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolBinarioOrdenado<Integer>(verificaElementos(lista));
	raiz = arbol.raiz();
	lienzo = new Lienzo(Estructura.ABO, arbol.altura());
    }

}


