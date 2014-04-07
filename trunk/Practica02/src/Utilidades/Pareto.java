package Utilidades;

import java.util.ArrayList;
import java.util.Collection;

public class Pareto {
	
	protected ArrayList<Cliente> nube;
	
	public Pareto(ArrayList<Cliente> nube){
		this.nube=nube;
	}
	
	public  Collection<Cliente> paretoSolucion(){	
		return null;
	}
	
	public void removeAll(Collection<Cliente> c){
		nube.removeAll(c);
	}
	
}
