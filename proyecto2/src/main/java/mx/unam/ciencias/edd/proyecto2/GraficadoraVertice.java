package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase abstracta para graficadoras de vertices.
 */
public abstract class GraficadoraVertice extends Graficadora {

    /**
     * El elemento que estará en el vértice.
     */
    protected int elemento;

    /**
     * Crea las lineas que conectan al vértice con los
     * vértices a los que es adyacente.
     */
    public abstract String conecta();
}
