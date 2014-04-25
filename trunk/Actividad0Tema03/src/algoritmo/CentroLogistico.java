package algoritmo;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class CentroLogistico {
	
	//Estructura del grafo = Treemap<Nodo, nodos adyacentes<Nodo, distancia>>.
	public static int centroLogistico(TreeMap<Integer, TreeMap<Integer, Integer>> grafo){
		Iterator<Integer>  it = grafo.keySet().iterator();
		int nodoActual, distanciaActual, distanciaMin = Integer.MAX_VALUE, nodoElegido = -1;
		while (it.hasNext()){
			nodoActual = it.next();
			if(grafo.get(nodoActual).isEmpty()) continue;
			distanciaActual = dijkstra(grafo, nodoActual);
			if (distanciaActual < distanciaMin) nodoElegido = nodoActual;
		}
		return nodoElegido;		
	}

	private static int dijkstra(
			TreeMap<Integer, TreeMap<Integer, Integer>> grafo, int nodoActual) {
		
		int[] distancia = new int[grafo.size()];
		PriorityQueue<ParNodoPeso> cola = new PriorityQueue<ParNodoPeso>();
		int nodo, adyacente, distancia2, suma = 0;
		Iterator<Integer> it;
		
		for(int i = 0; i < distancia.length; i++) distancia[i] = Integer.MAX_VALUE;
		distancia[nodoActual] = 0;
		cola.add(new ParNodoPeso(nodoActual, 0));
		
		
		while(!cola.isEmpty()){
			nodo = cola.poll().getNodo();
			it = grafo.get(nodo).keySet().iterator();
			while (it.hasNext()){
				adyacente = it.next();
				distancia2 = distancia[nodo] + grafo.get(nodo).get(adyacente);
				if(distancia[adyacente] > distancia2){
					distancia[adyacente] = distancia2;
					cola.add(new ParNodoPeso(adyacente, distancia2));
				}
			}
			
			for (int i = 0; i < distancia.length; i++) suma += distancia[i];
			
		}
		return suma;
	}
	
	private static class ParNodoPeso implements Comparable<ParNodoPeso>{
		
		private int nodo;
		private int peso;
		
		public ParNodoPeso(int nodo, int peso){
			this.nodo = nodo;
			this.peso = peso;
		}

		@Override
		public int compareTo(ParNodoPeso arg0) {
			return this.peso - arg0.peso;
		}

		public int getNodo() {
			return nodo;
		}

		@SuppressWarnings("unused")
		public void setNodo(int nodo) {
			this.nodo = nodo;
		}

		@SuppressWarnings("unused")
		public int getPeso() {
			return peso;
		}

		@SuppressWarnings("unused")
		public void setPeso(int peso) {
			this.peso = peso;
		}
		
	}
	
	public static TreeMap<Integer, TreeMap<Integer, Integer>> generarGrafoAleatorio(int tamano){
		
		tamano--;
		TreeMap<Integer, TreeMap<Integer, Integer>> toReturn = new TreeMap<Integer, TreeMap<Integer, Integer>>();
		for(int i = 0; i <= tamano; i++) toReturn.put(i, new TreeMap<Integer, Integer>());
		for(int i = 0; i <= tamano; i++){
			int adyacentes = (int)Math.floor(Math.random() * tamano);
			int k = (int)Math.floor(Math.random() * (tamano - adyacentes + 1) + adyacentes);
			for(int j = 1 ; j <= adyacentes; j++){
				toReturn.get(i).put(k, (int)Math.floor(Math.random() * 100));
				k--;
			}
		}
		return toReturn;
	}

}
