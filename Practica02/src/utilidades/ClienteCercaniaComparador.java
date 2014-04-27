package utilidades;

import java.util.Comparator;

/**
 * Comparator para ordenar una estructura de clientes por su cercania al atributo ice.
 *
 */
public class ClienteCercaniaComparador implements Comparator<Cliente>{

	/**
	 * Ice del cliente a comparar.
	 */
	private int ice;
	
	/**
	 * Método constructor.
	 * @param ice
	 */
	public ClienteCercaniaComparador(int ice){
		this.ice = ice;
	}
	
	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		return Math.abs(ice - arg0.getIce()) - Math.abs(ice - arg1.getIce());
	}

}
