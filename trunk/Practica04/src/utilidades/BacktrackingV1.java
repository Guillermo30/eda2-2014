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
		int prevLvl=0;
		ArrayList<Ruta> sol = new ArrayList<Ruta>();
		int solDist=Integer.MAX_VALUE;
		ArrayList<Ruta> solParcial = new ArrayList<Ruta>();
		Stack<Cliente> pila = new Stack<Cliente>();
		Ruta aux = new Ruta(jornada, aTaller, cTaller);
		Cliente auxi = null;
		
		while(nivel!=0){
			if((nivel-prevLvl) == 1) generarNivel(pila,solParcial);
			auxi = pila.pop();
			if(!aux.addClient(auxi)){
				solParcial.add(aux);
				aux = new Ruta(jornada, aTaller, cTaller);
				aux.addClient(pila.pop());
				
			} else if(nivel==clientes.size()){
				solParcial.add(aux);
			}
			if(nivel == clientes.size() &&  dist(solParcial)<solDist){
				sol = solParcial;
				solDist = dist(solParcial);
			}
			prevLvl = nivel;
			if(nivel!= clientes.size() || (auxi.getId() >= pila.peek().getId())){
				nivel++;
			} else if(pila.isEmpty() || (auxi.getId() >= pila.peek().getId() && nivel > 0)){
				retroceder(nivel,solParcial);
				nivel--;
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
	
	private void retroceder (int nivel, ArrayList<Ruta> sol){
		sol.get(sol.size()-1).deleteLast();
		if(nivel != 1) sol.get(sol.size()-1).deleteLast();
	}
}
