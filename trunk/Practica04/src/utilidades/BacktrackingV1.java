package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 *	Clase donde se encuentra el algoritmo de Backtracking.
 */
public class BacktrackingV1 {
	
	/**
	 * Entrada de datos del algoritmo.
	 */
	private ArrayList<Cliente> clientes;
	
	/**
	 * Duraci�n de cada jornada en minutos sin contar los 15 min de regreso al taller.
	 */
	private int jornada;
	
	/**
	 * Avenida del taller.
	 */
	private int aTaller;
	
	/**
	 * Calle del taller.
	 */
	private int cTaller;
	
	/**
	 * Cliente falso usado para guardar los datos del taller.
	 */
	private Cliente taller;
	
	private int solDist, solT, sumaDist, sumaT, numTalleres;
	
	private boolean[] cogido;
	
	private int[] siguienteHermano;
	
	private int relleno;
	
	/**
	 * M�todo constructor.
	 * @param clientes
	 * @param jornada
	 * @param aTaller
	 * @param cTaller
	 */
	public BacktrackingV1(ArrayList<Cliente> clientes, int jornada, int aTaller, int cTaller){
		 this.clientes = clientes;
		 this.jornada = jornada;
		 this.aTaller = aTaller;
		 this.cTaller = cTaller;
		 taller = new Cliente(-1, 15, aTaller, cTaller);
	}
	
	public ArrayList<Ruta> solucionPuta(){
		LinkedList<Cliente> solActual = new LinkedList<Cliente>();
		LinkedList<Cliente> solParcial = new LinkedList<Cliente>();
		ArrayList<Ruta> solucion = new ArrayList<Ruta>();
		cogido = new boolean[clientes.size()];
		siguienteHermano = new int[clientes.size()];
		for(int i = 0; i < cogido.length; i++) cogido[i] = false;
		for(int i = 0; i < siguienteHermano.length; i++) siguienteHermano[i] = 0;
		Ruta auxR = new Ruta(jornada, aTaller, cTaller);
		Cliente auxC = null, auxC2 = null;
		int nivel=1;
		solDist = Integer.MAX_VALUE;
		solT = Integer.MAX_VALUE;
		sumaDist = 0;
		sumaT = 0;
		numTalleres = 0;
		int jornadaC = jornada + 15;
		do{
			generar(nivel, solParcial);
			if(solucion(nivel, solParcial) && (((sumaT/jornadaC) < solT) || (((sumaT/jornadaC) == solT) && sumaDist < solDist))){
								
				solT = sumaT/jornadaC;
				solDist = sumaDist;
				solActual.clear();
				solActual.addAll(solParcial);
				
				sumaT -= relleno;
				sumaDist -= solParcial.getLast().distanciaHasta(taller);
			}
			if (criterio(nivel, solParcial)) nivel++;
			else while(nivel > 0 && !masHermanos(nivel, solParcial)){
				retroceder(nivel, solParcial);
				nivel--;
			}
		}while(nivel != 0);
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

	private void retroceder(int nivel, LinkedList<Cliente> solParcial) {
		Cliente aux = solParcial.removeLast();
		
		sumaT -= aux.getTiempo();
		if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(aux);
		else sumaDist -= taller.distanciaHasta(aux);
		
		cogido[aux.getIndex()] = false;
		
		if(!solParcial.isEmpty() && solParcial.getLast().equals(taller)){
			numTalleres--;
			aux = solParcial.removeLast();
			sumaT -= aux.getTiempo();
			sumaDist -= solParcial.getLast().distanciaHasta(taller);
		}
		
		siguienteHermano[nivel - 1] = 0;
		
	}

	private boolean masHermanos(int nivel, LinkedList<Cliente> solParcial) {
		if(siguienteHermano[nivel - 1] < clientes.size()){
			Cliente aux = solParcial.removeLast();
			sumaT -= aux.getTiempo();
			if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(aux);
			else sumaDist -= taller.distanciaHasta(aux);
			cogido[aux.getIndex()] = false;
			return true;
		}
		return false;
	}

	private boolean criterio(int nivel, LinkedList<Cliente> solParcial) {
		if(nivel == clientes.size()) return false;
		if(((sumaT/(jornada + 15)) + ((sumaT%(jornada + 15) == 0)?0:1)) > solT || (sumaT == solT && sumaDist > solDist)) return false;
		return true;
	}

	private boolean solucion(int nivel, LinkedList<Cliente> solParcial) {
		if(nivel == clientes.size()){
			sumaDist += solParcial.getLast().distanciaHasta(taller);
			relleno =  (jornada + 15) - (sumaT % (jornada + 15));
			sumaT += relleno;
			return true;
		}
		return false;
	}

	private void generar(int nivel, LinkedList<Cliente> solParcial) {
		while(cogido[siguienteHermano[nivel - 1]]) siguienteHermano[nivel - 1]++;
		Cliente c = clientes.get(siguienteHermano[nivel - 1]);
		
		sumaT += c.getTiempo();
		
		if(((sumaT-1)/jornada) - numTalleres > 0){
			int tiempoRelleno = (jornada + 15) - ((sumaT - c.getTiempo()) % (jornada + 15));
			sumaDist += solParcial.getLast().distanciaHasta(taller);
			solParcial.add(new Cliente(-1, tiempoRelleno, aTaller, cTaller));
			numTalleres++;
			sumaT += tiempoRelleno;
		}
		if(solParcial.size() != 0) sumaDist += solParcial.getLast().distanciaHasta(c);
		else sumaDist += taller.distanciaHasta(c);
		solParcial.add(c);
		cogido[siguienteHermano[nivel - 1]] = true;
		while(siguienteHermano[nivel - 1] < cogido.length && cogido[siguienteHermano[nivel - 1]]) siguienteHermano[nivel - 1]++;
	}
	
	public ArrayList<Ruta> solucionBasica(){
		//Inicializaci�n
				Stack<Cliente> pila = new Stack<Cliente>();
				LinkedList<Cliente> solParcial = new LinkedList<Cliente>();
				ArrayList<Ruta> solucion = new ArrayList<Ruta>();
				ArrayList<Ruta> solucionFinal = new ArrayList<Ruta>();
				Ruta auxR = new Ruta(jornada, aTaller, cTaller);
				int solDist = Integer.MAX_VALUE;
				int sumaDist = 0;
				
				//Inicializar pila con el primer nivel
				for(Cliente c : clientes) pila.push(c);
				
				//Exploraci�n del arbol
				while(!pila.isEmpty()){
					
					solParcial.add(pila.peek());
					
					//Si hemos llegado a una hoja.
					if(solParcial.size() == clientes.size()){
						
						solucion.clear();
						auxR = new Ruta(jornada, aTaller, cTaller);
						for(Cliente c: solParcial){
							if(!auxR.addClient(c)){
								solucion.add(auxR);
								auxR = new Ruta(jornada, aTaller, cTaller);
								auxR.addClient(c);
							}
						}
						solucion.add(auxR);
						sumaDist = 0;
						for(Ruta r: solucion) sumaDist += r.getDistancia(); 
						if(solucionFinal.isEmpty() || solucion.size() < solucionFinal.size() || (solucion.size() == solucionFinal.size() && sumaDist < solDist)){
							solucionFinal.clear();
							solucionFinal.addAll(solucion);
							solDist = sumaDist;
						}
						
						//Retroceder
						
						pila.pop();
						solParcial.removeLast();
						
						do{
							pila.pop();
							solParcial.removeLast();

						}while(!solParcial.isEmpty() && pila.peek() == solParcial.getLast());
					}else{
						//Generar Nivel
						for(Cliente c : clientes) if(!solParcial.contains(c)) pila.push(c);
					}
					
				}
				return solucionFinal;
	}

	/**
	 * Algoritmo que devuelve el minimo n�mero de rutas con minima distancia.
	 * @return
	 */
	public ArrayList<Ruta> solucion(){
		//Inicializaci�n
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
				
				//Exploraci�n del arbol
				while(!pila.isEmpty()){
					
					auxC = pila.peek();
					
					sumaT += auxC.getTiempo();
				
					if(((sumaT-1)/jornada) - numTalleres > 0){
						int tiempoRelleno = (jornada + 15) - ((sumaT - auxC.getTiempo()) % (jornada + 15));
						sumaDist += solParcial.getLast().distanciaHasta(taller);
						solParcial.add(new Cliente(-1, tiempoRelleno, aTaller, cTaller));
						numTalleres++;
						sumaT += tiempoRelleno;
					}
					if(solParcial.size() != 0) sumaDist += solParcial.getLast().distanciaHasta(auxC);
					else sumaDist += taller.distanciaHasta(auxC);
					solParcial.add(auxC);
					
					//Si hemos llegado a una hoja.
					if(nivel == clientes.size()){
						sumaDist += solParcial.getLast().distanciaHasta(taller);
						int relleno =  (jornada + 15) - (sumaT % (jornada + 15));
						sumaT += relleno;
						
						if(solT > sumaT/(jornada +15)){
							solT = sumaT/(jornada +15);
							solDist = sumaDist;
							solActual.clear();
							solActual.addAll(solParcial);
						}else if(solT == sumaT/(jornada +15) && sumaDist < solDist){
							solDist = sumaDist;
							solActual.clear();
							solActual.addAll(solParcial);
						}
						
						//Retroceder
						sumaT -= relleno;
						sumaDist -= solParcial.getLast().distanciaHasta(taller);
						
						pila.pop();
						auxC2 = solParcial.removeLast();
						
						sumaT -= auxC2.getTiempo();
						sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
						
						do{
							pila.pop();
							auxC2 = solParcial.removeLast();
							
							sumaT -= auxC2.getTiempo();
							if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
							else sumaDist -= taller.distanciaHasta(auxC2);
							
							nivel--;
							
							if(!solParcial.isEmpty() && solParcial.getLast().equals(taller)){
								numTalleres--;
								auxC2 = solParcial.removeLast();
								sumaT -= auxC2.getTiempo();
								sumaDist -= solParcial.getLast().distanciaHasta(taller);
							}
						}while(!solParcial.isEmpty() && pila.peek() == solParcial.getLast());
					}else if(((sumaT/420) + ((sumaT%420 == 0)?0:1)) > solT || (sumaT == solT && sumaDist > solDist)){
						//Poda
						
						pila.pop();
						auxC2 = solParcial.removeLast();
						
						sumaT -= auxC2.getTiempo();
						if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
						else sumaDist -= taller.distanciaHasta(auxC2);
						
						if(!solParcial.isEmpty() && solParcial.getLast().equals(taller)){
							numTalleres--;
							auxC2 = solParcial.removeLast();
							sumaT -= auxC2.getTiempo();
							sumaDist -= solParcial.getLast().distanciaHasta(taller);
						}
						
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
				/*Ruta r = new Ruta(jornada, aTaller, cTaller);
				r.addClient(clientes.get(2));
				r.addClient(clientes.get(3));
				r.addClient(clientes.get(1));
				r.addClient(clientes.get(0));*/
				return solucion;
	}
	
	public ArrayList<Ruta> solucionIntento3(){
		
		//Inicializaci�n
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
		
		//Exploraci�n del arbol
		while(!pila.isEmpty()){
			
			auxC = pila.peek();
			
			sumaT += auxC.getTiempo();
		
			if(((sumaT-1)/405) - numTalleres > 0){
				int tiempoRelleno = 420 - ((sumaT - auxC.getTiempo()) % 420);
				solParcial.add(new Cliente(-1, tiempoRelleno, aTaller, cTaller));
				numTalleres++;
				sumaT += tiempoRelleno;
				sumaDist += solParcial.getLast().distanciaHasta(taller);
			}
			if(solParcial.size() != 0) sumaDist += solParcial.getLast().distanciaHasta(auxC);
			else sumaDist += taller.distanciaHasta(auxC);
			solParcial.add(auxC);
			
			//Si hemos llegado a una hoja.
			if(nivel == clientes.size()){
				sumaDist += solParcial.getLast().distanciaHasta(taller);
				sumaT += 420 - (sumaT % 420);
				
				if(solT/420 > sumaT/420){
					solT = sumaT;
					solDist = sumaDist;
					solActual.clear();
					solActual.addAll(solParcial);
				}else if(solT/420 == sumaT/420 && sumaDist < solDist){
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
					pila.pop();
					auxC2 = solParcial.removeLast();
					
					sumaT -= auxC2.getTiempo();
					if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
					else sumaDist -= taller.distanciaHasta(auxC2);
					
					nivel--;
					
					if(!solParcial.isEmpty() && solParcial.getLast().equals(taller)){
						numTalleres--;
						auxC2 = solParcial.removeLast();
						sumaT -= auxC2.getTiempo();
						sumaDist -= solParcial.getLast().distanciaHasta(taller);
					}
				}while(!solParcial.isEmpty() && pila.peek() == solParcial.getLast());
			}else if(sumaT > solT || (sumaT == solT && sumaDist > solDist)){
				//Poda
				
				pila.pop();
				auxC2 = solParcial.removeLast();
				
				sumaT -= auxC2.getTiempo();
				if(!solParcial.isEmpty())sumaDist -= solParcial.getLast().distanciaHasta(auxC2);
				else sumaDist -= taller.distanciaHasta(auxC2);
				
				if(!solParcial.isEmpty() && solParcial.getLast().equals(taller)){
					numTalleres--;
					auxC2 = solParcial.removeLast();
					sumaT -= auxC2.getTiempo();
					sumaDist -= solParcial.getLast().distanciaHasta(taller);
				}
				
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
		Ruta r = new Ruta(jornada, aTaller, cTaller);
		r.addClient(clientes.get(2));
		r.addClient(clientes.get(3));
		r.addClient(clientes.get(1));
		r.addClient(clientes.get(0));
		return solucion;
	}
	
	public ArrayList<Ruta> solucionIntento2(){
		
		//Inicializaci�n
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
		
		//Exploraci�n del arbol
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
	
	public ArrayList<Ruta> solucionIntento1(){
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
