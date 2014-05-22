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
		Ruta aux = new Ruta(jornada, aTaller, cTaller);
		
		while(nivel!=0){
			generarNivel(pila,solParcial);
			if(nivel == clientes.size() &&  dist(solParcial)<solDist){
				sol = solParcial;
				solDist = dist(solParcial);
				solParcial = new ArrayList<Ruta>();
			}
			if(!aux.addClient(pila.pop())){
				solParcial.add(aux);
				aux = new Ruta(jornada, aTaller, cTaller);
				
			}
			
			nivel++;
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
				continue;
			} 
			for (int j = 0; j<sol.size();j++){
				if(sol.get(j).getRuta().contains(clientes.get(i))){
					break;
				}
				if(j==sol.size()-1) pila.add(clientes.get(i));
			}
			
			
		}
		
	}
}
