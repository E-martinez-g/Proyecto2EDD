package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

/**
 * Clase para graficadoras de árboles completos.
 */
public class GraficadoraArbolCompleto extends GraficadoraArbol {

    /**
     * Constructor para Graficadoras de árboles completos.
     */
    public GraficadoraArbolCompleto(Lista<String> lista) throws IllegalArgumentException {
	arbol = new ArbolBinarioCompleto<Integer>(verificaElementos(lista));
	raiz = arbol.raiz();
	lienzo = new Lienzo(Estructura.ABC, arbol.altura());
    }

}
