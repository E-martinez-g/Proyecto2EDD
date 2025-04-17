package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase para excepciones de estructuras de datos faltantes.
 */
public class ExcepcionEstructuraNoValida extends IllegalArgumentException() {

    /**
     * Constructor vacío.
     */
    public ExcepcionEstructuraNoValida() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje el mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionEstructuraNoValida(String mensaje) {
	super(mensaje);
    }
}
