package Utilidades;

import java.util.Collection;

import estructurasdedatos.AVLTree;

public class Pareto {
	
	protected Collection<Cliente> nube;
	
	public Pareto(Collection<Cliente> nube){
		this.nube=nube;
	}
	
	public  Collection<Cliente> paretoSolucion(){	
		return null;
	}
	
	public void removeAll(Collection<Cliente> c){
		nube.removeAll(c);
	}
	
}
