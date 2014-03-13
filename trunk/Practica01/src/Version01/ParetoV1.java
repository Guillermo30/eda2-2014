package Version01;

import java.util.*;

import Utilidades.Pareto;
import Utilidades.Cliente;

public class ParetoV1 implements Pareto{
	public ArrayList<Cliente> nube;
	
	public ParetoV1(){
		
	}
	
	public LinkedList<Cliente> paretoSolucion(){
		paretoRecur();
		casoBase();
		return null;
		
	}
	private LinkedList<Cliente> paretoRecur(){
		return null;
	}
	private LinkedList<Cliente> casoBase(){
		return null;
	}
	
}
