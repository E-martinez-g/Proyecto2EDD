package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

public class Fabrica {

    Lista<String> piezas;
    Estructura estructura;
    
    public Fabrica(Lista<String> piezas) {
	this.piezas = piezas;
    }

    public void crea() throws ExcepcionEstructuraFaltante {
	if (piezas.esVacia())
	    throw new ExcepcionEstructuraFaltante();
	Iterator<String> iterador = piezas.iterator();
	estructura = Estructura.getEstructura(iterador.next());
	if (estructura == null)
	    throw new ExcepcionEstructuraFaltante();
	
    }
}
