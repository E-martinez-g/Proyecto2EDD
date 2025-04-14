package mx.unam.ciencias.edd.proyecto2;

import java.io.IOException;

/**
 * Proyecto 2: Representaciones gráficas.
 */
public class Proyecto2 {
    
    public static void main(String[] args) {
	if (args.length > 1) {
	    System.err.println("No es posible utilizar más de un archivo a la vez.");
	    System.exit(1);
	}
	Entrada entrada;
	if (args.length == 0) {
	    try {
		entrada = new Entrada();
	    } catch (IOException ioe) {
		System.err.println("No se pudo acceder a la entrada estándar.");
		System.exit(2);
	    }
	} else {
	    try {
		entrada = new Entrada(args[0]);
	    } catch (IOException ioe) {
		System.err.println("No se pudo acceder al archivo" + args[0]);
		System.exit(2);
	    }
	}
	new Fabrica(entrada.recibe()).crea();
    }
    
}
