package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class BacktrackingV1 {
	private ArrayList<Cliente> clientes;
	private int jornada;
	private int aTaller;
	private int cTaller;
	private Cliente taller;
	
	public BacktrackingV1(ArrayList<Cliente> clientes, int jornada, int aTaller, int cTaller){
		 this.clientes = clientes;
		 this.jornada = jornada;
		 this.aTaller = aTaller;
		 this.cTaller = cTaller;
		 taller = new Cliente(-1, 15, aTaller, cTaller);
	}
	
	public ArrayList<Ruta> solucionBuena(){
		
		//Inicialización
		Stack<Cliente> pila = new Stack<Cliente>();
		LinkedList<Cliente> solActual = new LinkedList<Cliente>();
		LinkedList<Cliente> solParcial = new LinkedList<Cliente>();
		ArrayList<Ruta> solucion = new ArrayList<Ruta>();
		Ruta auxR = new Ruta(jornada, aTaller, cTaller);
		Cliente auxC = null, auxC2 = null;
		int nivel=1;
		int solDist = Integer.MAX_VALUE;
		int solT = Integer.MAX_VALUE;
		int sumaDist = 0, sumaT = 0, numTalleres = 0;
		
		//Inicializar pila con el primer nivel
		for(Cliente c : clientes) pila.push(c);
		
		//Exploración del arbol
		while(!pila.isEmpty()){
			
			auxC = pila.peek();
			
			sumaT += auxC.getTiempo();
		
			if(((sumaT-1)/405) - numTalleres > 0){
				solParcial.add(taller);
				numTalleres++;
				sumaT += taller.getTiempo();
				sumaDist += solParcial.getLast().distanciaHasta(taller);
			}
			if(solParcial.size() != 0) sumaDist += solParcial.getLast().distanciaHasta(auxC);
			solParcial.add(auxC);
			
			//Si hemos llegado a una hoja.
			if(nivel == clientes.size()){
				sumaDist += solParcial.getLast().distanciaHasta(taller);
				sumaT +=15;
				
				if(solT > sumaT){
					solT = sumaT;
					solActual.clear();
					solActual.addAll(solParcial);
				}else if(solT == sumaT && sumaDist < solDist){
					solDist = sumaDist;
					solActual.clear();
					solActual.addAll(solParcial);
				}
				
				//Retroceder
				sumaT -= 15;
				sumaDist -= solParcial.getLast().distanciaHasta(taller);
				
				pila.pop();
				auxC2 = solParcial.removeLast();
				
				sumaT -= auxC2.getTiempo();
				sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
				do{
					if(solParcial.getLast().equals(taller)){
						numTalleres--;
						solParcial.removeLast();
						sumaT -= 15;
						sumaDist -= solParcial.getLast().distanciaHasta(taller);
					}
					
					pila.pop();
					auxC2 = solParcial.removeLast();
					
					sumaT -= auxC2.getTiempo();
					if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
					
					nivel--;
				}while(!solParcial.isEmpty() && pila.peek() == solParcial.getLast());
			}else if(sumaT > solT || (sumaT == solT && sumaDist > solDist)){
				//Poda
				if(solParcial.getLast().equals(taller)){
					numTalleres--;
					solParcial.removeLast();
					sumaT -= 15;
					sumaDist -= solParcial.getLast().distanciaHasta(taller);
				}
				
				pila.pop();
				auxC2 = solParcial.removeLast();
				
				sumaT -= auxC2.getTiempo();
				if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
				
				if(solParcial.isEmpty() && pila.peek() == solParcial.getLast()) nivel--;
			}else{
				//Generar Nivel
				nivel++;
				for(Cliente c : clientes) if(!solParcial.contains(c)) pila.push(c);
			}
			
		}
		for(Cliente c: solActual){
			if(c.equals(taller)){
				solucion.add(auxR);
				auxR = new Ruta(jornada, aTaller, cTaller);
				continue;
			}
			auxR.addClient(c);
		}
		solucion.add(auxR);
		return solucion;
	}
	
	public ArrayList<Ruta> solucionDep(){
		
		//Inicialización
		Stack<Cliente> pila = new Stack<Cliente>();
		ArrayList<Ruta> solActual = new ArrayList<Ruta>();
		ArrayList<Ruta> solParcial = new ArrayList<Ruta>();
		Ruta aux = new Ruta(jornada, aTaller, cTaller);
		Cliente auxC = null;
		int nivel=1;
		int prevLvl=0;
		int solDist = Integer.MAX_VALUE;
		int solT = Integer.MAX_VALUE;
		int sumaDist, sumaT;
		
		//Inicializar pila con el primer nivel
		for(Cliente c : clientes) pila.push(c);
		
		//Exploración del arbol
		while(nivel!=0){
			auxC = pila.peek();
			
			//Si la rutaActual esta llena...
			if(!aux.addClient(auxC)){
				solParcial.add(aux);
				aux = new Ruta(jornada, aTaller, cTaller);
				aux.addClient(auxC);
			}
				
			//Si hemos llegado a un nodo hoja...
			if(nivel==clientes.size()){
				if(!solParcial.contains(aux))solParcial.add(aux);
				sumaT = 0;
				sumaDist = 0;
				for(Ruta r: solParcial){
					sumaT += r.getTiempo();
					r.getDistancia();
				}
				if(sumaT <= solT){
					if(sumaT < solT){
						solT = sumaT;
						solDist = sumaDist;
						solActual = (ArrayList<Ruta>) solParcial.clone();
					}else if(sumaDist < solDist){
						solDist = sumaDist;
						solActual = (ArrayList<Ruta>) solParcial.clone();
					}
				}
				//Retroceder
				pila.pop();
				nivel--;
				if(!solParcial.get(solParcial.size()-1).deleteLast()){
					solParcial.remove(solParcial.size()-1);
					solParcial.get(solParcial.size()-1).deleteLast();
				}
				do{
					nivel--;
					pila.pop();
					if(!solParcial.get(solParcial.size()-1).deleteLast()){
						solParcial.remove(solParcial.size()-1);
						solParcial.get(solParcial.size()-1).deleteLast();
					}
					if(nivel == 0) break;
				}while(pila.peek().equals(solParcial.get(solParcial.size()-1).getLastCliente()));
				if(solParcial.get(solParcial.size()-1).getRuta().size() == 1)
					solParcial.remove(solParcial.size()-1);
				aux = solParcial.get(solParcial.size()-1);
				solParcial.remove(solParcial.size()-1);
			}else{
				//generar Nivel
				nivel++;
				for(Cliente c : clientes){
					if(aux.getRuta().contains(c)) continue;
					boolean anadir = true;
					for(Ruta r: solParcial){
						if(r.getRuta().contains(c)){
							anadir = false;
							break;
						}					
					}
					if(anadir) pila.push(c);
				}
			}
		}
		
		return solActual;
		
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
