package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase para excepciones de estructuras de datos inválidas.
 */
public class ExcepcionEstructuraFaltante extends IllegalArgumentException() {

    /**
     * Constructor vacío.
     */
    public ExcepcionEstructuraFaltante() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje el mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionEstructuraFaltante(String mensaje) {
	super(mensaje);
    }
}
