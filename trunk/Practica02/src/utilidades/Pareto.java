package utilidades;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Superclase dise�ada para implementar las distintas verciones del algoritmo.
 *
 */
public class Pareto {
	
	/**
	 * Nube de puntos sobre la que se trabaja.
	 */
	protected ArrayList<Cliente> nube;
	
	/**
	 * M�tos constructor.
	 * @param nube
	 */
	public Pareto(ArrayList<Cliente> nube){
		this.nube=nube;
	}
	
	/**
	 * Devuelve los resultados tras procesar la nube.
	 * @return
	 */
	public  Collection<Cliente> paretoSolucion(){	
		return null;
	}
	
	/**
	 * Elimina los puntos de la nube que se encuentren en el par�metro c.
	 * @param c
	 */
	public void removeAll(Collection<Cliente> c){
		nube.removeAll(c);
	}
	
}
