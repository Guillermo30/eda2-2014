package utilidades;

import java.util.ArrayList;
import java.util.Stack;

public class BacktrackingV1 {
	private ArrayList<Cliente> clientes;
	private int jornada;
	private int aTaller;
	private int cTaller;
	
	public BacktrackingV1(ArrayList<Cliente> clientes, int jornada, int aTaller, int cTaller){
		 this.clientes = clientes;
		 this.jornada = jornada;
		 this.aTaller = aTaller;
		 this.cTaller = cTaller;
		
		 
	}
	
	public ArrayList<Ruta> solucion(){
		int nivel=1;
		ArrayList<Ruta> sol = new ArrayList<Ruta>();
		int solDist=Integer.MAX_VALUE;
		ArrayList<Ruta> solParcial = new ArrayList<Ruta>();
		Stack<Cliente> pila = new Stack<Cliente>();
		
		while(nivel!=0){
			
			if(nivel == clientes.size() &&  dist(solParcial)<solDist){
				sol = solParcial;
				solDist = dist(solParcial);
				solParcial = new ArrayList<Ruta>();
			}
		}
		
		
		return sol;
	}
	
	private int dist (ArrayList<Ruta> x){
		int dist =0;
		for(int i =0; i<x.size();i++){
			dist+=x.get(i).getDistancia();
		}
		return dist;
	}
	private void generarNivel (Stack<Cliente> pila, ArrayList<Ruta> sol){
		for(int i =0; i<clientes.size();i++){
			if(sol.size()==0){
				pila.add(clientes.get(i));
			} 
			for (int j = 0; j<sol.size();j++){
				sol.get(j).getRuta().contais()
			}
			
		}
		
	}
}
