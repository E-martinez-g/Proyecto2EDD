package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase abstracta para graficadoras de vertices.
 */
public abstract class GraficadoraVertice extends Graficadora {

    /**
     * La coordenada en x del vértice.
     */
    protected int x;
    
    /**
     * La coordenada en y del vértice.
     */
    protected int y;	

    /**
     * Construye una linea a partir de 4 números.
     * @param x1 la coordenada en x del primer punto.
     * @param y1 la coordenada en y del primer punto.
     * @param x2 la coordenada en x del segundo punto.
     * @param y2 la coordenada en y del segundo punto.
     * @return la cadena de una linea que empieza en el primer punto y
     *         termina en el segundo.
     */
    protected String linea(int x1, int y1, int x2, int y2) {
	String s = "";
	s += "<line stroke='black' stroke-width='3' x1='" + x1 + "' ";
	s += "y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' /\n>";
	return s;
    }

    /**
     * Crea el vértice.
     */
    protected abstract String vertice();

    /**
     * Crea el contenido del vértice.
     */
    protected abstract String contenido();

    /**
     * Conecta el vértice a sus adyacentes.
     */
    protected abstract String conecta();
}
