package Version02;

import java.util.*;

import Utilidades.Pareto;
import Utilidades.Cliente;

public class ParetoV2 extends Pareto {

	public ParetoV2(Collection<Cliente> nube) {
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
		for (int i = 0; i <= min.getId() - 1; i++) {
			v1.add(in.get(i));
		}

		// cambio el problema creciente a uno decreciente
		for (int i = in.size() - 1; i > min.getId(); i--) {
			v2.add(in.get(i));
		}

		// impresion del estado de los dos paretos
		System.out.println("problema decreciente  " + v1);
		System.out.println("problema creciente invertido  " + v2);
		System.out.println("minimo " + min);

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
		long r;
		r= System.nanoTime();
		if(in.size()<=3){
			return casoBase(in,salida);
		}
		System.out.println((System.nanoTime()-r));
	
		
		
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
		int a,b,c;
		c=Integer.MAX_VALUE;
		a=in.get(0).getCe();
		b=in.get(1).getCe();
		if(in.size()==3) c=in.get(2).getCe();
		
		salida.add(in.get(0));
		if(b<a) salida.add(in.get(1));
		if(c<b&&c<a) salida.add(in.get(2));
		
		return salida;
	}

}
