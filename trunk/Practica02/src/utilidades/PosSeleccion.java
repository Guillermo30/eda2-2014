package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;

public class PosSeleccion {
	
	/**
	 * Total de sospechosos a seleccionar.
	 */
	private int totalSospechosos;
	/**
	 * Nube total de clientes.
	 */
	private ArrayList<Cliente> nube;
	/**
	 * Candidatos seleccionados con Pareto.
	 */
	private LinkedList<Cliente> candidatos;
	
	/**
	 * Método constructor.
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
	 * Selecciona selecciona los más sospechosos de la lista de candidatos.
	 * @return
	 */
	public LinkedList<Cliente> seleccionar(){
		return null;
	}

}
