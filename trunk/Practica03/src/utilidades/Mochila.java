package utilidades;

import java.util.LinkedList;

public interface Mochila {
	
	/**
	 * Devuelve los elementos que se deben inspeccionar para obtener el m�ximo beneficio.
	 * @return
	 */
	public abstract LinkedList<Cliente> maxBeneficio();
	

}
