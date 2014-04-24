package paretoNube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import utilidades.Cliente;
import utilidades.Pareto;

/**
 * Clase que saca la frontera inferior de la nube de forma boraz usando como entrada la nube ordenada por CE.
 */
public class ParetoNube extends Pareto{
	
	/**
	 * Método constructor.
	 */
	public ParetoNube(ArrayList<Cliente> nube){
		super(nube);
		Collections.sort(this.nube);
	}
	
	@Override
	public  Collection<Cliente> paretoSolucion(){	
		
		//Inicialización
		LinkedList<Cliente> toReturn = new LinkedList<Cliente>();
		if(nube.isEmpty()) return toReturn;
		Iterator<Cliente> it = nube.iterator();
		int iceIzLimit = Integer.MAX_VALUE;
		int iceDerLimit = Integer.MIN_VALUE;
		while(it.hasNext()){
			int ice = it.next().getIce();
			if(ice < iceIzLimit) iceIzLimit = ice;
			if(ice > iceDerLimit) iceDerLimit = ice;
		}
		it = nube.iterator();
		Cliente cur = it.next();
		Cliente anteriorAceptadoI = cur;
		Cliente anteriorAceptadoD = cur;
		
		int iceIzMin = cur.getIce();
		int iceDerMin = cur.getIce();
		int iceIgualCe = cur.getIce();
		int iceIgualCeAnterior = cur.getIce();
		
		//Selección
		toReturn.add(cur);
		while(it.hasNext() && (!(iceIzLimit == iceIzMin) || !(iceDerLimit == iceDerMin))){
			cur = it.next();
			if(cur.getIce() > iceDerMin || (cur.getIce() >= iceDerMin && cur.getCe() == anteriorAceptadoD.getCe())){
				toReturn.add(cur);
				iceDerMin = cur.getIce();
				anteriorAceptadoD = cur;
				continue;
			}
			if(cur.getIce() < iceIzMin){
				toReturn.add(cur);
				iceIgualCeAnterior = iceIgualCe;
				iceIzMin = cur.getIce();
				iceIgualCe = cur.getIce();
				anteriorAceptadoI = cur;
			}else if(cur.getCe() == anteriorAceptadoI.getCe() && cur.getIce() < iceIgualCeAnterior) toReturn.add(cur);
			
		}
		
		return toReturn;
	}

}
