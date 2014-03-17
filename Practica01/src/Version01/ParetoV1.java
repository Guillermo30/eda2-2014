package Version01;

import java.util.*;

import Utilidades.Pareto;
import Utilidades.Cliente;

public class ParetoV1 extends Pareto {

	public ParetoV1(Collection<Cliente> nube) {
		super(nube);
	}

	@Override
	public LinkedList<Cliente> paretoSolucion() {
		
		LinkedList<Cliente> in = (LinkedList<Cliente>) nube;
		LinkedList<Cliente> salida = new LinkedList<Cliente>();

		// calculo del minimo
		Cliente min = in.get(0);
		for (int i = 0; i < in.size(); i++) {
			if (in.get(i).getCe() < min.getCe()) {
				min = in.get(i);
			}
		}

		// dos problemas separados, decreciente y creciente
		LinkedList<Cliente> v1 = new LinkedList<Cliente>();
		LinkedList<Cliente> v2 = new LinkedList<Cliente>();

		// adjunta el problema decreciente
		for (int i = 0; i < min.getId() - 1; i++) {
			v1.add(in.get(i));
		}

		// cambio el problema creciente a uno decreciente
		for (int i = in.size() - 1; i > min.getId(); i--) {
			v2.add(in.get(i));
		}

		// adicion de la primera parte
		salida.addAll(paretoRecur(v1));
		// adicion del minimo
		salida.add(min);
		// adicion de la segunda parte
		salida.addAll(paretoRecur(v2));

		// devuelvo todo
		return salida;

	}

	private LinkedList<Cliente> paretoRecur(LinkedList<Cliente> in) {
		LinkedList<Cliente> salida = new  LinkedList<Cliente>();
		//caso base
		
		if(in.size()<=3){
			return casoBase(in,salida);
		}
		
	
		
		
		//division del problema
		LinkedList<Cliente> prob1 = new LinkedList<Cliente>();
		LinkedList<Cliente> prob2 = new LinkedList<Cliente>();
		
		for(int i = 0; i< in.size(); i++){
			if(i < in.size()/2){
				prob1.add(in.get(i));
			}else{
				prob2.add(in.get(i));
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

	private LinkedList<Cliente> casoBase(LinkedList<Cliente> in, LinkedList<Cliente> salida) {
		Cliente min, izq;
		min= in.get(0);
		izq=in.get(0);
		for(int i =0;i<in.size();i++){
			if(in.get(i).getCe()<min.getCe()){
				min=in.get(i);
			}
		}
		if(izq.getId()==min.getId()){
			salida.add(min);
			return salida;
		}
		else if(in.get(1).equals(min)){
			salida.add(izq);
			salida.add(min);
			return salida;
		}
		else{
			if(in.get(1).getCe()<izq.getCe()){
				salida.addAll(in);
				return salida;
			}else
				salida.add(izq);
				salida.add(min);
				return salida;
		}
	}

}
