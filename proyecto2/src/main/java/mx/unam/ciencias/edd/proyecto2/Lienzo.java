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
	ancho = calculaAncho(decisor);
	alto = calculaAlto(decisor);
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
     * Calcula el alto del lienzo a partir de un número.
     * @param decisor el número importante a partir del cuál se calculará
     *        el alto.
     * @return el alto del lienzo
     */
    private int calculaAlto(int decisor) {
	switch (estructura) {
	case PILA : return altoPila(decisor);
	case COLA : return 140;
	case LISTA : return 140;
	case GRAFICA : return tamanoGrafica(decisor);
	default : return altoArbol(decisor);
	}
    }
    
    /**
     * Calcula el ancho del lienzo a partir de un número.
     * @param decisor el número importante a partir del cuál se calculará
     *        el ancho.
     * @return el ancho del lienzo
     */
    private int calculaAncho(int decisor) {
	switch (estructura) {
	case PILA : return 330;
	case COLA : return anchoCola(decisor);
	case LISTA : return anchoLista(decisor);
	case GRAFICA : return tamanoGrafica(decisor);
	default : return anchoArbol(decisor);
	}
    }

    /**
     * Calcula el alto del lienzo para una pila a partir de su número de
     * elementos.
     * @param decisor el número de elementos a partir del cuál se calculará
     *        el alto del lienzo para la pila.
     * @return el alto del lienzo para la pila.
     */
    private int altoPila(int decisor) {
	return (decisor * 70) + 10;
    }

    /**
     * Calcula el ancho del lienzo para una cola a partir de su número de
     * elementos.
     * @param decisor el número de elementos a partir del cuál se calculará
     *        el ancho del lienzo para la cola.
     * @return el ancho del lienzo para la cola.
     */
    private int anchoCola(int decisor) {
	return (decisor * 270) + 70;
    }

    /**
     * Calcula el ancho del lienzo para una lista a partir de su número de
     * elementos.
     * @param decisor el número de elementos a partir del cuál se calculará
     *        el ancho del lienzo para la lista.
     * @return el ancho del lienzo para la lista.
     */
    private int anchoLista(int decisor) {
	return decisor > 0 ? (decisor * 350) + 180 : 80;
    }

    /**
     * Calcula el tamaño del lienzo para una gráfica a partir de su
     * número de elementos.
     * @param decisor el número de elementos a partir del cuál se calculará
     *        el ancho y el alto del lienzo para la gráfica.
     * @return el ancho y el alto del lienzo para la gráfica.
     */
    private int tamanoGrafica(int decisor) {
	double radio = 450;
	radio /= 2;
	radio /= Math.sin(Math.PI/decisor);
	int tamano = (int) Math.ceil(radio);
	tamano += 450;
	tamano *= 2;
	return tamano;
    }
    
    /**
     * Calcula el alto del lienzo para un árbol binario a partir de su
     * altura.
     * @param decisor la altura del árbol a partir de la cuál se calculará
     *        el alto del lienzo para el árbol.
     * @return el alto del lienzo para el árbol
     */
    private int altoArbol(int decisor) {
	return decisor >= 0 ? 450 * (decisor + 1) : 450;
    }
    
    /**
     * Calcula el ancho del lienzo para un árbol binario a partir de su
     * altura.
     * @param decisor la altura del árbol a partir de la cuál se calculará
     *        el ancho del lienzo para el árbol.
     * @return el ancho del lienzo para el árbol
     */
    private int anchoArbol(int decisor) {
	return (int) (450 * Math.pow(2, decisor));
    }
}
