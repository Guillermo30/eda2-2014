package utilidades;

import java.util.LinkedList;

public interface Mochila {
	
	/**
	 * Devuelve los elementos que se deben inspeccionar para obtener el máximo beneficio.
	 * @return
	 */
	public abstract LinkedList<Cliente> maxBeneficio();
	

}
