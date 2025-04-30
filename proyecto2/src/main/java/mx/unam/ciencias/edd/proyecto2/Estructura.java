package mx.unam.ciencias.edd.proyecto2;

/**
 * Enumeración para estructuras de datos.
 */
public enum Estructura {
    
    /** Lista */
    LISTA,
    /** Pila */
    PILA,
    /** Cola */
    COLA,
    /** Árbol Binario Completo */
    ABC,
    /** Árbol Binario Ordenado */
    ABO,
    /** Árbol Rojinegro */
    ARN,
    /** Árbol AVL */
    AAVL,
    /** Gráfica */
    GRAFICA;

    /**
     * Decide a qué estructura de datos se refiere la cadena
     * recibida.
     * @param linea la cadena que queremos relacionar a una
     *        estructura de datos.
     * @return la estructura adecuada o <code>null</code> si
     *         la cadena no es válida.
     */
    public static Estructura getEstructura(String linea) {
	switch ( linea ) {
	case "Lista" : return Estructura.LISTA;
	case "Pila" : return Estructura.PILA;
	case "Cola" : return Estructura.COLA;
	case "ArbolBinarioCompleto" : return Estructura.ABC;
	case "ArbolBinarioOrdenado" : return Estructura.ABO;
	case "ArbolRojinegro" : return Estructura.ARN;
	case "ArbolAVL" : return Estructura.AAVL;
	case "Grafica" : return Estructura.GRAFICA;
	default : return null;
	}
    }
}
