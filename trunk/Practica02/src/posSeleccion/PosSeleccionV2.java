package posSeleccion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import utilidades.Cliente;
import utilidades.ClienteMediaLocalComparador;
import utilidades.PosSeleccion;

public class PosSeleccionV2 extends PosSeleccion
{
	//private ArrayList<Cliente> nube;
	//private LinkedList<Cliente> candidatos;
	//private int totalSospechosos;
	
	public PosSeleccionV2(ArrayList<Cliente> nube,	LinkedList<Cliente> candidatos, int totalSospechosos) {
		super(nube, candidatos, totalSospechosos);
		
	}
	
	private  int  mediaOchoCercanos(int pos) {
		int sum=0;
		int izq = pos - 1;
		int der = pos + 1;
		for (int i = 0; i < 8; i++) {
			
			if (izq < 0) {
				for(; i < 8; i++){
					sum +=nube.get(pos + der).getCe();
					der++;
				}
				break;
			}
			
			if (der >= nube.size()) {
				for(; i < 8; i++){
					sum +=nube.get(pos - izq).getCe();
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
	
	private PriorityQueue<Cliente> preSeleccionar(){
		PriorityQueue<Cliente> preSol = new PriorityQueue<Cliente>(candidatos.size(), new ClienteMediaLocalComparador());
		Cliente aux;
		for(int i = 0; i < candidatos.size(); i++){
			aux = candidatos.get(i);
			aux.setDifMediaLocal(aux.getCe() - mediaOchoCercanos(aux.getIndexOnNube()));
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
