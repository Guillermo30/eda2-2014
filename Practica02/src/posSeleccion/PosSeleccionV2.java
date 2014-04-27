package posSeleccion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import utilidades.Cliente;
import utilidades.ClienteMediaLocalComparador;
import utilidades.PosSeleccion;

/**
 * Clase que se encarga de elegir los "totalSospechosos" clientes más sospechosos de "candidatos".
 *
 */
public class PosSeleccionV2 extends PosSeleccion{
	
	/**
	 * Método constructor.
	 * @param nube
	 * @param candidatos
	 * @param totalSospechosos
	 */
	public PosSeleccionV2(ArrayList<Cliente> nube,	LinkedList<Cliente> candidatos, int totalSospechosos) {
		super(nube, candidatos, totalSospechosos);	
	}
	
	/**
	 * Método que te devuelve la media de CE de los 8 clientes mas cercanos por ICE al elemento "pos" de "nube" indices.
	 * @param pos
	 * @return
	 */
	public  int  mediaOchoCercanos(int pos) {
		int sum=0;
		int izq = pos - 1;
		int der = pos + 1;
		for (int i = 0; i < 8; i++) {
			
			if (izq < 0) {
				for(; i < 8; i++){
					sum +=nube.get(der).getCe();
					der++;
				}
				break;
			}
			
			if (der >= nube.size()) {
				for(; i < 8; i++){
					sum +=nube.get(izq).getCe();
					izq--;
				}
				break;
			}
			
			if (nube.get(der).getIce() - nube.get(pos).getIce() <= nube.get(pos).getIce() - nube.get(izq).getIce()) {
				sum += nube.get(der).getCe();
				der++;
			} else {
				sum += nube.get(izq).getCe();
				izq--;
			}
		}
		
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
