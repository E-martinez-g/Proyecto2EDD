package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para gradicadoras de Listas.
 */
public class GraficadoraLista extends GraficadoraSucesion {

    /**
     * Constructor para graficadoras de Listas.
     * @param lista la lista con los elementos en forma de cadena.
     */
    public GraficadoraLista(Lista<String> lista) {
	super(lista, Estructura.LISTA);
    }

    /**
     * Regresa la representación en cadena de la representación gráfica
     * de la lista.
     * @return la representación en cadena de la
     *         representación gráfica de la lista.
     */
    @Override public String grafica() {
	String s = "";
	if (!elementos.esVacia()) {
	    s += punta();
	    int c = 0;
	    for (Integer i : elementos) {
		s += flechaA(c);
		s += flechaS(c);
		s += caja(c);
		s += contenido(i, c++);
	    }
	}
	return s;
    }

    /**
     * Regresa la punta de la flecha.
     * @return una cadena con la definición de la punta de la flecha.
     */
    private String punta() {
	String s = "";
	s += "<defs>\n";
	s += "<marker id='punta' orient=\"auto\"";
	s += " markerWidth='3' markerHeight='4'";
	s += " refX='0.1' refY='2'>\n";
	s += "<path d='M0,0 V4 L2,2 Z' fill=\"black\"/>\n";
	s += "</marker>\n";
	s += "</defs>\n";
	return s;
    }

    /**
     * Regresa la cadena correspondiente a la flecha anterior del
     * nodo que se está graficando actualmente.
     * @param i el índice del nodo.
     * @return la cadena de la flecha anterior.
     */
    private String flechaA(int i) {
	int ini = 140;
	int fin = 50;
	ini += i * 350;
	fin += i * 350;
	String s = "";
	s += "<path ";
	s += "id='anterior" + i + "' ";
	s += "marker-end='url(#punta)' ";
	s += "stroke-width='4' ";
	s += "fill='none' stroke='black' ";
	s += "d='M" + ini + " 55 " + fin + " 55' ";
	s += "/>\n";
	return s;
    }

    /**
     * Regresa la cadena correspondiente a la flecha siguiente del
     * nodo que se está graficando actualmente.
     * @param i el índice del nodo.
     * @return la cadena de la flecha siguiente.
     */
    private String flechaS(int i) {
	int ini = 390;
	int fin = 480;
	ini += i * 350;
	fin += i * 350;
	String s = "";
	s += "<path ";
	s += "id='siguiente" + i + "' ";
	s += "marker-end='url(#punta)' ";
	s += "stroke-width='4' ";
	s += "fill='none' stroke='black' ";
	s += "d='M" + ini + " 85 " + fin + " 85' ";
	s += "/>\n";
	return s;
    }

    /**
     * Regresa la cadena correspondiente al nodo que se está
     * graficando actualmente.
     * @param i el indice del nodo
     * @return la cadena del nodo.
     */
    @Override protected String caja(int i) {
	int x = 140;
	x += i * 350;
	String s = "";
	s += "<rect width=\"250\" height=\"60\" ";
	s += "x=\"" + x + "\" y=\"40\" fill=\"white\" ";
	s += "stroke=\"black\" stroke-width=\"3\"/>\n";
	return s;
    }

    /**
     * Regresa la cadena correspondiente al elemento del nodo que
     * se está graficando actualmente.
     * @param elemento el elemento del nodo.
     * @param i el índice del nodo.
     * @return la cadena del contenido.
     */
    @Override protected String contenido(Integer elemento, int i) {
	int x = 265;
	x += i * 350;
	String s = "";
	s += "<text fill='black' font-family='sans-serif' ";
	s += "font-size='30' x='" + x + "' y='80' ";
	s += "text-anchor='middle'>" + elemento + "</text>\n";
	return s;
    }
}
