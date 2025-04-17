package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

public class Fabrica {

    Lista<String> piezas;
    Estructura estructura;
    
    public Fabrica(Lista<String> piezas) {
	this.piezas = piezas;
    }

    /**
     * Imprime en la consola el código para crear la estructura de datos escogida con
     * los elementos pedidos.
     */
    public void crea() {
	if (piezas.esVacia())
	    throw new ExcepcionEstructuraNoValida("No se recibió ninguna estructura");
	String s = piezas.eliminaPrimero();
	estructura = Estructura.getEstructura(s);
	if (estructura == null)
	    throw new ExcepcionEstructuraFaltante("La estructura" + s + "no es válida");
	
    }
}
