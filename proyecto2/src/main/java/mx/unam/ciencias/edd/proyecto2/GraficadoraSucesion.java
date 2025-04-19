package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase abstracta para graficadoras de estructuras
 * de datos con forma de sucesión.
 */
public abstract class GraficadoraSucesion extends GraficadoraEstructura {

    /**
     * Lista con los elementos de la sucesión.
     */
    protected Lista<Integer> elementos;

    /**
     * Constructor para las graficadoras de sucesiones.
     * @param lista la lista con los elementos en forma de cadena.
     * @param estructura la estructura que se graficará.
     */
    public GraficadoraSucesion(Lista<String> lista, Estructura estructura)
    throws IllegalArgumentException {
	elementos = verificaElementos(lista);
	lienzo = new Lienzo(estructura, elementos.getLongitud());
    }
}
