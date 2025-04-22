package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase abstracta para graficadoras de vertices.
 */
public abstract class GraficadoraVertice extends Graficadora {

    /**
     * Crea el vértice.
     */
    protected abstract String vertice();

    /**
     * Crea el contenido del vértice.
     */
    protected abstract String contenido();
}
