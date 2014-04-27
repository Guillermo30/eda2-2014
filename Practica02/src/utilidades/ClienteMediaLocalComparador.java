package utilidades;

import java.util.Comparator;

/**
 * Comparator para ordenar una estructura de clientes por diferencia de CE respecto a su media local.
 *
 */
public class ClienteMediaLocalComparador implements Comparator<Cliente>{

	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		return arg1.getDifMediaLocal() - arg0.getDifMediaLocal();
	}

}
