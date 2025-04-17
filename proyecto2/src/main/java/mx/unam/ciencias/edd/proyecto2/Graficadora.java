package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase abstracta para graficadoras.
 */
public abstract class Graficadora {

    /**
     * El lienzo cuyas dimensiones se usarán para graficar.
     */
    protected Lienzo lienzo;
    
    /**
     * Método para obtener la representación en cadena de la
     * representación gráfica de un objeto.
     */
    public String grafica();

}
