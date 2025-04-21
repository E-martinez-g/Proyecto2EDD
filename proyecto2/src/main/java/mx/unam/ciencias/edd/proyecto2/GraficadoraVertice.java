package mx.unam.ciencias.edd.proyecto2;

/**
 * Interfaz para graficadoras de vertices.
 */
public interface class GraficadoraVertice extends Graficadora {

    /**
     * Crea el vértice.
     */
    protected String vertice();

    /**
     * Crea el contenido del vértice.
     */
    protected String contenido();
}
