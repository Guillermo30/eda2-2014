package paretoDivision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import utilidades.Cliente;
import utilidades.Pareto;

/**
 * Clase que saca la frontera inferior de la nube de forma voraz usando como entrada la nube ordenada por ICE.
 */
public class ParetoDivision extends Pareto{

	/**
	 * Método constructor.
	 * @param nube
	 */
	public ParetoDivision(ArrayList<Cliente> nube){
		super(nube);
	}
	
	@Override
	public  Collection<Cliente> paretoSolucion(){
		
		//Inicialización
		int iceMin = 0;
		int minIndex = 0;
		Cliente min = nube.get(0);
		for (int i = 0; i < nube.size(); i++) {
			if (nube.get(i).getCe() < min.getCe()) {
				min = nube.get(i);
				iceMin = min.getIce();
				minIndex = i;
			}
		}
		
		ArrayList<Cliente> mitadIzq = new ArrayList<Cliente>(minIndex + 1);
		ArrayList<Cliente> mitadDch = new ArrayList<Cliente>(nube.size() - (minIndex + 1));
		
		for(int i = 0; i < minIndex; i++) mitadIzq.add(nube.get(i));
		for(int i = nube.size() - 1; i >= minIndex; i--) mitadDch.add(nube.get(i));
		
		//Resolución
		
		LinkedList<Cliente> resultado = new LinkedList<Cliente>();
		
		resultado.addAll(voraz(mitadIzq));
		LinkedList<Cliente> resultadoDch = voraz(mitadDch);
		
		if(!resultadoDch.isEmpty() && (resultadoDch.getFirst().getIce() == iceMin)) resultadoDch.removeFirst();
		
		resultado.addAll(resultadoDch);
		
		return resultado;
		
	}

	/**
	 * Método voraz.
	 * @param in
	 * @return
	 */
	private LinkedList<Cliente> voraz(ArrayList<Cliente> in) {
		LinkedList<Cliente> resultado = new LinkedList<Cliente>();
		
		if(in.isEmpty()) return resultado;
		Cliente candidato;
		Cliente anterior = in.get(0);
		
		resultado.add(anterior);
		
		for(int i = 1; i < in.size(); i++){
			
			candidato = in.get(i);
			
			if(candidato.getCe() <= anterior.getCe()){
				
				if((candidato.getIce() == anterior.getIce()) && !(candidato.getCe() == anterior.getCe())) resultado.removeLast();
				resultado.add(candidato);
				anterior = candidato;
				
			}
			
		}
		
		return resultado;
	}
	
}
