package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase para excepciones de graficas no válidas.
 */
public class ExcepcionGraficaNoValida extends ExcepcionEstructuraNoValida {

    /**
     * Constructor vacío.
     */
    public ExcepcionGraficaNoValida() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje el mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionGraficaNoValida(String mensaje) {
	super(mensaje);
    }
}
