package paretoNube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

import utilidades.Cliente;
import utilidades.Pareto;

/**
 * Clase que saca la frontera inferior de la nube de forma boraz usando como entrada la nube ordenada por CE.
 */
public class ParetoNube extends Pareto{
	
	/**
	 * Nube ordenada por CE.
	 */
	private PriorityQueue<Cliente> eAuxiliar;
	
	/**
	 * Método constructor.
	 */
	public ParetoNube(ArrayList<Cliente> nube){
		super(nube);
	}
	
	@Override
	public  Collection<Cliente> paretoSolucion(){	
		
		//Inicialización
		eAuxiliar = new PriorityQueue<Cliente>();
		eAuxiliar.addAll(nube);
		LinkedList<Cliente> toReturn = new LinkedList<Cliente>();
		if(eAuxiliar.isEmpty()) return toReturn;
		Cliente cur = eAuxiliar.poll();
		int iceIzLimit = nube.get(0).getIce();
		int iceDerLimit = nube.get(nube.size() - 1).getIce();
		int iceIzMin = cur.getIce();
		int iceDerMin = cur.getIce();
		
		//Selección
		toReturn.add(cur);
		while(!eAuxiliar.isEmpty() && (!(iceIzLimit == iceIzMin) || !(iceDerLimit == iceDerMin))){
			cur = eAuxiliar.poll();
			if(cur.getIce() < iceIzMin){
				toReturn.add(cur);
				iceIzMin = cur.getIce();
				continue;
			}
			if(cur.getIce() > iceDerMin){
				toReturn.add(cur);
				iceDerMin = cur.getIce();
			}
		}
		
		return toReturn;
	}

}
