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
	    throw new ExcepcionEstructuraFaltante("La estructura" + s + "no es válida");
	Graficadora graficadora = graficadora(estructura, piezas);
	System.out.println(graficadora.grafica());
    }

    /**
     * Regresa la graficadora correspondiente a la estructura deseada.
     * @param estructura la estructura de datos que queremos graficar.
     * @param elementos la lista de elementos que estarán en la representación gráfica
     *        de la estructura.
     * @return un objeto instancia de una clase que extiende a {@link Graficadora}.
     */
     private Graficadora graficadora(Estructura estructura, Lista<String> elementos) {
	switch (estructura) {
	    case Estructura.LISTA : return new GraficadoraLista(elementos);
	    case Estructura.PILA : return new GraficadoraPila(elementos);
  	    case Estructura.COLA : return new GraficadoraCola(elementos);
	    case Estructura.GRAFICA : return new GraficadoraGrafica(elementos);
	    case Estructura.ABC : return new GraficadoraArbolCompleto(elementos);
	    case Estructura.ABO : return new GraficadoraArbolOrdenado(elementos);
	    case Estructura.ARN : return new GraficadoraArbolRojinegro(elementos);
	    case Estructura.AAVL : return new GraficadoraArbolAVL(elementos);
	    case default : return null;
     }	
     }
}
