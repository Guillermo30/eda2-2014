package Version05;

import java.util.*;

import estructurasdedatos.AVLTree;
import Utilidades.Pareto;
import Utilidades.Cliente;

public class ParetoV5 extends Pareto {

	public ParetoV5(Collection<Cliente> clientes) {
		super(clientes);
	}

	@Override
public LinkedList<Cliente> paretoSolucion() {
		
		AVLTree<Cliente> in = (AVLTree<Cliente>)nube;
		LinkedList<Cliente> salida = new LinkedList<Cliente>();

		// calculo del minimo
		Iterator<Cliente> it = in.iterator();
		Iterator<Cliente> it2 = in.reverseInOrderIterator();
		Cliente min = new Cliente(-1, Integer.MAX_VALUE, -1);
		Cliente minCandidato;
		int nMin = 0, m = 0;
		while(it.hasNext()){
			minCandidato = it.next();
			m++;
			if(minCandidato.getCe() < min.getCe()){
				min = minCandidato;
				nMin = m;
			}
		}

		// dos problemas separados, decreciente y creciente
		ArrayList<Cliente> v1 = new ArrayList<Cliente>(nMin - 1);
		ArrayList<Cliente> v2 = new ArrayList<Cliente>(nube.size() - nMin);

		// adjunta el problema decreciente
		it = nube.iterator();
		for(int i = 1; i < nMin; i++) v1.add(it.next());

		// cambio el problema creciente a uno decreciente
		for (int i = nube.size(); i > nMin; i--) v2.add(it2.next());

		// adicion de la primera parte
		salida.addAll(paretoRecur(v1));
		// adicion del minimo
		salida.add(min);
		// adicion de la segunda parte
		salida.addAll(paretoRecur(v2));

		// devuelvo todo
		return salida;

	}

	private LinkedList<Cliente> paretoRecur(ArrayList<Cliente> in) {
		LinkedList<Cliente> salida = new  LinkedList<Cliente>();
		
		//caso base
		if(in.size()<=3){
			return casoBase(in,salida);
		}
	
		
		
		//division del problema
		ArrayList<Cliente> prob1 = new ArrayList<Cliente>();
		ArrayList<Cliente> prob2 = new ArrayList<Cliente>();
		int min=0;
		
		for(int i = 0; i< in.size(); i++){
			if(i < in.size()/2){
				prob1.add(in.get(i));
				if(i==(in.size()/2)-1){
					for(int j=0; j< prob1.size(); j++){
						if(j==0) min = prob1.get(0).getCe();
						else if(prob1.get(j).getCe()<min)
							min = prob1.get(j).getCe();
					}
				}
				
				
			}else{
				if(in.get(i).getCe()<min) prob2.add(in.get(i));
			}
		}
		
		
		//combinacion
		//añadir la parte de la izquierda completa
		LinkedList<Cliente> previoP1 = new LinkedList<Cliente>();
		previoP1 = paretoRecur(prob1);
		salida.addAll(previoP1);
		Cliente minComb = previoP1.get(previoP1.size()-1); //obtener el minimo de la parte de la izquierda
		
		//calculo de la parte de la derecha
		LinkedList<Cliente> previoP2 = new LinkedList<Cliente>();
		previoP2= paretoRecur(prob2);

		//recorrer la segunda parte y elimina todos los mayores que el minimo
		// una vez se encuentre uno por debajo añadir el resto de la estructura y salir
		Iterator<Cliente> it = previoP2.iterator();
		while(it.hasNext()){
			if(it.next().getCe()>minComb.getCe()) it.remove();
			else break;
		}
		salida.addAll(previoP2);
		
		//devuelve la solucion completa
		return salida;
	}

	private LinkedList<Cliente> casoBase(ArrayList<Cliente> in, LinkedList<Cliente> salida) {
		int a,b,c;
		a=Integer.MAX_VALUE;
		b=Integer.MAX_VALUE;
		c=Integer.MAX_VALUE;
		
		if(in.size()>=1) a=in.get(0).getCe();
		if(in.size()>=2) b=in.get(1).getCe();
		if(in.size()==3) c=in.get(2).getCe();
		
		if(in.size()>=1)salida.add(in.get(0));
		if(b<a) salida.add(in.get(1));
		if(c<b&&c<a) salida.add(in.get(2));
		
		return salida;
	}

}


