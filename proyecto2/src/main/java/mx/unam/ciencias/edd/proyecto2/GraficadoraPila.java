package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para graficadoras de pilas.
 */
public class GraficadoraPila extends GraficadoraSucesion {

    /**
     * Constructor para graficadoras de pilas.
     * @param lista la lista con los elementos en forma de
     *        sucesión.
     */
    public GraficadoraPila(Lista<String> lista) {
	super(lista, Estructura.PILA);
    }

    /**
     * Regresa la representación en cadena de la representación
     * gráfica de la pila.
     * @return la representación en cadena de la representación
     *         gráfica de la pila.
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
	int y = lienzo.getAlto() - 61;
	y -= 70 * i;
	String s = "";
	s += "<rect width=\"250\" height=\"60\" ";
	s += "x=\"40\" y=\"" + y + "\" fill=\"white\" ";
	s += "stroke=\"black\" stroke-width=\"3\"/>\n";
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
	int y = lienzo.getAlto() - 20;
	y -= 70 * i;
	String s = "";
	s += "<text fill='black' font-family='sans-serif' ";
	s += "font-size='30' x='165' y='" + y + "' ";
	s += "text-anchor='middle'>" + elemento + "</text>\n";
	return s;
    }
}
