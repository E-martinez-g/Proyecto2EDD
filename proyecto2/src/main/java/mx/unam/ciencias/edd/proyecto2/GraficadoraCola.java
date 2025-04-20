package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.Lista;

/**
 * Clase para graficadoras de colas.
 */
public class GraficadoraCola extends GraficadoraSucesion {

    /**
     * Constructor para graficadoras de colas.
     * @param lista la lista con los elementos en forma de
     *        sucesión.
     */
    public GraficadoraCola(Lista<String> lista) {
	super(lista, Estructura.COLA);
    }

    /**
     * Regresa la representación en cadena de la representación
     * gráfica de la pila.
     * @return la representación en cadena de la representación
     *         gráfica de la pila
     */
    @Override public String grafica() {
	String s = "";
	if (!elementos.esVacia()) {
	    int c = 0;
	    for (Integer i : elementos) {
		s += caja(c);
		s += contenido(i, c++);
	    }
	}
	return s;
    }

    /**
     * Regresa la cadena correspondiente a una caja que contenga al
     * elemento que está siendo graficado actualmente.
     * @param i el índice de la caja.
     * @return la cadena de la caja.
     */
    @Override protected String caja(int i) {
	int x = 40;
	x += 270 * i;
	String s = "";
	s += "<rect width=\"250\" height=\"60\" x=\"40\" ";
	s += "y=\"" + x + "\" fill=\"white\" stroke=\"black\" ";
	s += "stroke-width=\"3\"/>\n";
	return s;
    }

    /**
     * Regresa la cadena correspondiente al elemento que está
     * siendo graficado actualmente.
     * @param elemento el elemento actual.
     * @param i el índice del elemento actual.
     * @return la cadena del contenido.
     */
    @Override protected String contenido(Integer elemento, int i) {
	
    }
}
