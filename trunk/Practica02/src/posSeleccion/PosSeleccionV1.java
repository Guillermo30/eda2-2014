package posSeleccion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import utilidades.Cliente;
import utilidades.ClienteCercaniaComparador;
import utilidades.ClienteMediaLocalComparador;
import utilidades.PosSeleccion;

/**
 * Clase que se encarga de elegir los "totalSospechosos" clientes más sospechosos de "candidatos".
 *
 */
public class PosSeleccionV1 extends PosSeleccion{
	
	/**
	 * Método constructor.
	 * @param nube
	 * @param candidatos
	 * @param totalSospechosos
	 */
	public PosSeleccionV1(ArrayList<Cliente> nube,	LinkedList<Cliente> candidatos, int totalSospechosos) {
		super(nube, candidatos, totalSospechosos);
		
	}
	
	/**
	 * Método que te devuelve la media de CE de los 8 clientes mas cercanos por ICE al elemento "pos" de "nube" usando una cola de prioridad.
	 * @param pos
	 * @return
	 */
	public int mediaOchoCercanos(int pos) {
		int sum=0;
		PriorityQueue<Cliente> clientesCercanos = new PriorityQueue<Cliente>(16, new ClienteCercaniaComparador(nube.get(pos).getIce()));
		for(int i = 1; i <= 8; i++){
			if((pos - i) >= 0) clientesCercanos.add(nube.get(pos - i));
			if((pos + i)  < nube.size()) clientesCercanos.add(nube.get(pos + i));
		}
		for(int i = 0; i < 8; i++) sum += clientesCercanos.poll().getCe();
		return sum/8;
	}
	
	/**
	 * Método encargado de dar a todos los clientes de "nube" la diferencia de la media de CE de sus 8 clientes más cercanos por ICE y su CE, y 
	 * meterlos en una cola de prioridad.
	 * @return
	 */
	private PriorityQueue<Cliente> preSeleccionar(){
		PriorityQueue<Cliente> preSol = new PriorityQueue<Cliente>(candidatos.size(), new ClienteMediaLocalComparador());
		Cliente aux;
		for(int i = 0; i < candidatos.size(); i++){
			aux = candidatos.get(i);
			aux.setDifMediaLocal(mediaOchoCercanos(aux.getIndexOnNube()) - aux.getCe());
			preSol.add(aux);
		}
		return preSol;

	}
	
	@Override
	public LinkedList<Cliente> seleccionar(){
		LinkedList<Cliente> solucion = new LinkedList<Cliente>();
		PriorityQueue<Cliente> preSol = preSeleccionar();
		for ( int i = 0; i < totalSospechosos; i++) solucion.add(preSol.poll());
		return solucion;
		
	}

}
