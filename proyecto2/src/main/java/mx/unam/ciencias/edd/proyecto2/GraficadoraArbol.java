package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;

public abstract class GraficadoraArbol extends GraficadoraEstructura {
    
    /**
     * Arbol binario.
     */
    protected ArbolBinario<Integer> arbol;
    
    /**
     * Raíz del árbol binario.
     */
    protected VerticeArbolBinario<Integer> raiz;
    
}
