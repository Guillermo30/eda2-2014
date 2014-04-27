package posSeleccion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import utilidades.Cliente;
import utilidades.ClienteCercaniaComparador;
import utilidades.ClienteMediaLocalComparador;
import utilidades.PosSeleccion;

public class PosSeleccionV1 extends PosSeleccion{
	
	public PosSeleccionV1(ArrayList<Cliente> nube,	LinkedList<Cliente> candidatos, int totalSospechosos) {
		super(nube, candidatos, totalSospechosos);
		
	}
	
	public  int  mediaOchoCercanos(int pos) {
		int sum=0;
		PriorityQueue<Cliente> clientesCercanos = new PriorityQueue<Cliente>(16, new ClienteCercaniaComparador(nube.get(pos).getIce()));
		for(int i = 1; i <= 8; i++){
			if((pos - i) >= 0) clientesCercanos.add(nube.get(pos - i));
			if((pos + i)  < nube.size()) clientesCercanos.add(nube.get(pos + i));
		}
		for(int i = 0; i < 8; i++) sum += clientesCercanos.poll().getCe();
		return sum/8;
	}
	
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
