package paretoNube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import Utilidades.Cliente;
import Utilidades.ClienteCeComparator;
import Utilidades.Pareto;

public class ParetoNube extends Pareto{
	
	private PriorityQueue<Cliente> eAuxiliar;
	
	public ParetoNube(ArrayList<Cliente> nube){
		super(nube);
		eAuxiliar = new PriorityQueue<Cliente>();
		
		Iterator<Cliente> it = nube.iterator();
		while(it.hasNext()) eAuxiliar.add(it.next());
	}
	
	@Override
	public  Collection<Cliente> paretoSolucion(){	
		
		LinkedList<Cliente> toReturn = new LinkedList<Cliente>();
		int iceIzLimit = nube.get(0).getIce();
		int iceDerLimit = nube.get(nube.size() - 1).getIce();
		int iceIzMin = eAuxiliar.peek().getIce();
		int iceDerMin = eAuxiliar.peek().getIce();
		Cliente cur;
		
		Iterator<Cliente> it = eAuxiliar.iterator();
		if(it.hasNext()) toReturn.add(it.next());
		while(it.hasNext() && (!(iceIzLimit == iceIzMin) || !(iceDerLimit == iceDerMin))){
			cur = it.next();
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
	
	@Override
	public void removeAll(Collection<Cliente> c){
		eAuxiliar.removeAll(c);
		nube.removeAll(c);
	}

}
