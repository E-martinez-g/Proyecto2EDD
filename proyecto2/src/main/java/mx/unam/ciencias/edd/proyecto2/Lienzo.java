package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase para calcular el tamaño del lienzo.
 */
public class Lienzo {

    /**
     * Valor máximo de Y.
     */
    private int alto;

    /**
     * Valor máximo de X.
     */
    private int ancho;

    /**
     * La estructura con la que se calculará el tamaño del
     * lienzo.
     */ 
    private Estructura estructura;

    /**
     * Calcula el tamaño del lienzo a partir de una
     * estructura y un número importante que depende de esta.
     * @param estructura la estructura a partir de la cual se
     *        calculará el tamaño.
     * @param decisor, el número importante. Este puede ser
     *        el número de elementos o la altura dependiendo.
     *        del caso.
     */
    public Lienzo(Estructura estructura, int decisor) {
	this.estructura = estructura;
	calculaTamano(decisor);
    }
	
    /**
     * Regresa el valor máximo de Y.
     * @return el valor máximo de Y.
     */
    public int getAlto() {
	return alto;
    }
    
    /**
     * Regresa el valor máximo de X.
     * @return el valor máximo de X.
     */
    public int getAncho() {
	return ancho;
    }

    /**
     * Calcula los valores de altura y ancho.
     */
    private void calculaTamano(int decisor) {
	switch (estructura) {
	case Estructura.PILA : tamanoPila(int decisor);
	    break;
	case Estructura.COLA : tamanoCola(int decisor);
	    break;
	case Estructura.LISTA : tamanoLista(int decisor);
	    break;
	case Estructura.GRAFICA : tamanoGrafica(int decisor);
	    break;
	case default : tamanoArbol();
	}
    }

    /**
     * Calcula el tamaño del lienzo para una pila a partir de
     * su número de elementos.
     * @param decisor el número de elementos en la pila.
     */
    private void tamanoPila(int decisor) {
	ancho = 330;
	alto = 10;
	alto += decisor * 70;
    }

    /**
     * Calcula el tamaño del lienzo para una cola a partir de
     * su número de elementos.
     * @param decisor el número de elementos en la cola.
     */
    private void tamanoCola(int decisor) {
	alto = 140;
	ancho = 70;
	ancho += decisor * 260; 
    }

    /**
     * Calcula el tamaño del lienzo para una lista a partir
     * de su número de elementos.
     * @param decisor el número de elementos en la lista.
     */
    private void tamanoLista(int decisor) {
	alto = 140;
	ancho = 80;
	if (decisor > 0) {
	    ancho += decisor * 350;
	    ancho += 100;
	}
    }

    /**
     * Calcula el tamaño del lienzo para una gráfica a partir
     * de su número de elementos.
     * @param decisor el número de elementos de la gráfica.
     */
    private void tamanoGrafica(int decisor) {

    }

    /**
     * Calcula el tamaño del lienzo para un árbol binario a
     * partir de su altura.
     * @param decisor la altura del árbol.
     */
    private void tamanoArbol(int decisor) {

    }
}
