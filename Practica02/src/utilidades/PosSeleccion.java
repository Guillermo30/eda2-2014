package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Superclase para la post-selecci�n.
 *
 */
public class PosSeleccion {
	
	/**
	 * Total de sospechosos a seleccionar.
	 */
	protected int totalSospechosos;
	/**
	 * Nube total de clientes.
	 */
	protected ArrayList<Cliente> nube;
	/**
	 * Candidatos seleccionados con Pareto.
	 */
	protected LinkedList<Cliente> candidatos;
	
	/**
	 * M�todo constructor.
	 * @param nube
	 * @param candidatos
	 * @param totalSospechosos
	 */
	public PosSeleccion(ArrayList<Cliente> nube, LinkedList<Cliente> candidatos, int totalSospechosos){
		
		this.nube = nube;
		this.candidatos = candidatos;
		this.totalSospechosos = totalSospechosos;
		
	}
	
	/**
	 * Selecciona selecciona los m�s sospechosos de la lista de candidatos.
	 * @return
	 */
	public LinkedList<Cliente> seleccionar(){
		return null;
	}

}
