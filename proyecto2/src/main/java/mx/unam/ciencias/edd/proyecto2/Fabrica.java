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
    public void crea() throws IllegalArgumentException {
	if (piezas.esVacia())
	    throw new ExcepcionEstructuraNoValida("No se recibió ninguna estructura");
	String s = piezas.eliminaPrimero();
	estructura = Estructura.getEstructura(s);
	if (estructura == null)
	    throw new ExcepcionEstructuraNoValida("La estructura " + s + " no es válida");
	GraficadoraEstructura graficadora = graficadora(estructura, piezas);
	System.out.println("<?xml version='1.0' encoding='UTF-8' ?>");
	System.out.print("<svg width='" + graficadora.anchoLienzo());
	System.out.println("' height='" + graficadora.altoLienzo() + "'>");
	System.out.println("<g>");
	System.out.print(graficadora.grafica());
	System.out.println("</g>");
	System.out.println("</svg>");
    }

    /**
     * Regresa la graficadora correspondiente a la estructura deseada.
     * @param estructura la estructura de datos que queremos graficar.
     * @param elementos la lista de elementos que estarán en la representación gráfica
     *        de la estructura.
     * @return un objeto instancia de una clase que extiende a {@link Graficadora}.
     */
     private GraficadoraEstructura graficadora(Estructura estructura, Lista<String> elementos) {
	switch (estructura) {
	case LISTA : return new GraficadoraLista(elementos);
	case PILA : return new GraficadoraPila(elementos);
	case COLA : return new GraficadoraCola(elementos);
	case GRAFICA : return new GraficadoraGrafica(elementos);
	case ABC : return new GraficadoraArbolCompleto(elementos);
	case ABO : return new GraficadoraArbolOrdenado(elementos);
	case ARN : return new GraficadoraArbolRojinegro(elementos);
	case AAVL : return new GraficadoraArbolAVL(elementos);
	default : return null;
	}
     }
}
