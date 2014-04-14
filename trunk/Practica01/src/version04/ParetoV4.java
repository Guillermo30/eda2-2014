package version04;

import java.util.*;

import utilidades.Cliente;
import utilidades.Pareto;

/**
 * Clase que implementa la cuarta versión del algoritmo.
 *
 */
public class ParetoV4 extends Pareto {

	/**
	 * Método constructor.
	 * @param nube
	 */
	public ParetoV4(Collection<Cliente> nube) {
		super(nube);
	}

	@Override
	public LinkedList<Cliente> paretoSolucion() {
		
		ArrayList<Cliente> in = (ArrayList<Cliente>) nube;
		LinkedList<Cliente> salida = new LinkedList<Cliente>();

		// calculo del minimo
		Cliente min = in.get(0);
		for (int i = 0; i < in.size(); i++) {
			if (in.get(i).getCe() < min.getCe()) {
				min = in.get(i);
			}
		}

		// dos problemas separados, decreciente y creciente
		ArrayList<Cliente> v1 = new ArrayList<Cliente>();
		ArrayList<Cliente> v2 = new ArrayList<Cliente>();

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

	/**
	 * Implementación del algoritmo divide y vencerás.
	 * @param in Entrada
	 * @return Salida
	 */
	private LinkedList<Cliente> paretoRecur(ArrayList<Cliente> in) {
		LinkedList<Cliente> salida = new  LinkedList<Cliente>();
		
		//caso base
		if(in.size()<=2){
			return casoBase(in,salida);
		}
		
		//division del problema
		ArrayList<Cliente> prob1 = new ArrayList<Cliente>();
		ArrayList<Cliente> prob2 = new ArrayList<Cliente>();
		int min=Integer.MAX_VALUE;
		
		int i = 0;
		for(; i< in.size()/2; i++){
			prob1.add(in.get(i));
			if(min > in.get(i).getIce()) min = in.get(i).getCe();
		}
		
		for(; i< in.size(); i++){
			if(in.get(i).getCe() <= min) prob2.add(in.get(i));
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
		if(!previoP2.isEmpty() && (salida.getLast().getIce() == previoP2.getFirst().getIce())) salida.removeLast();
		salida.addAll(previoP2);
		
		//devuelve la solucion completa
		return salida;
	}

	/**
	 * Implementación del caso base.
	 * @param in Entrada
	 * @param salida
	 * @return Salida
	 */
	private LinkedList<Cliente> casoBase(ArrayList<Cliente> in, LinkedList<Cliente> salida) {
		if(in.size() == 0) return salida;
		salida.add(in.get(0));
		if(in.size() == 2){
			if(in.get(0).getCe() >= in.get(1).getCe()){
				if ((in.get(0).getIce() == in.get(1).getIce()) && !(in.get(0).getCe() == in.get(1).getCe())) salida.clear();
				salida.add(in.get(1));
			}
		}
		
		return salida;
	}

}


