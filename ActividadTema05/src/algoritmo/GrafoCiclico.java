package algoritmo;

import java.util.TreeMap;
import java.util.TreeSet;

public class GrafoCiclico {

	public static boolean esGrafoCiclico(TreeMap<Integer, TreeSet<Integer>> grafo){
		
		boolean[] explorados = new boolean[grafo.size()];;
		for(boolean b: explorados) b = false;
		
		return esGrafoCiclico(grafo, grafo.firstKey(), grafo.firstKey(), explorados, 1, false);
	}
	
	private static boolean esGrafoCiclico(
			TreeMap<Integer, TreeSet<Integer>> grafo, Integer nodoActual, Integer nodoInicial,
			boolean[] explorados, int nivel, boolean esciclo) {
		explorados[nodoActual] = true;
		if(nivel == grafo.size()) return grafo.get(nodoActual).contains(nodoInicial);
		for(int i: grafo.get(nodoActual)) if(!explorados[i]) esciclo = (esciclo || esGrafoCiclico(grafo, i, nodoInicial, explorados.clone(), nivel + 1, esciclo));
		return esciclo;
	}

	public static TreeMap<Integer, TreeSet<Integer>> generarGrafoAleatorio(int tamano){
		
		tamano--;
		TreeMap<Integer, TreeSet<Integer>> toReturn = new TreeMap<Integer, TreeSet<Integer>>();
		for(int i = 0; i <= tamano; i++) toReturn.put(i, new TreeSet<Integer>());
		for(int i = 0; i <= tamano; i++){
			int adyacentes = (int)Math.floor(Math.random() * ((tamano > 15)?7:tamano));
			int k = (int)Math.floor(Math.random() * (tamano - adyacentes + 1) + adyacentes);
			for(int j = 1 ; j <= adyacentes; j++){
				toReturn.get(i).add(k);
				k--;
			}
		}
		return toReturn;
	}
	
}
