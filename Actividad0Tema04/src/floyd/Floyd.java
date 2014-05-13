package floyd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Floyd {

	private static int[][] lastFloydMidNodesResult;

	private static final int INFINITE = Integer.MAX_VALUE;

	private static LinkedList<Integer> result;
	private static int peso;
	private static int pesoAux;
	private static TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> caminos;
	private static TreeMap<Integer, TreeMap<Integer, Integer>> grafo;

	public static void setGrafo(TreeMap<Integer, TreeMap<Integer, Integer>> graf) {
		grafo = graf;
	}

	public static TreeMap<Integer, TreeMap<Integer, Integer>> generarGrafoAleatorio(
			int tamano) {

		tamano--;
		TreeMap<Integer, TreeMap<Integer, Integer>> toReturn = new TreeMap<Integer, TreeMap<Integer, Integer>>();
		for (int i = 0; i <= tamano; i++)
			toReturn.put(i, new TreeMap<Integer, Integer>());
		for (int i = 0; i <= tamano; i++) {
			int adyacentes = (int) Math.floor(Math.random() * tamano);
			int k = (int) Math.floor(Math.random() * (tamano - adyacentes + 1)
					+ adyacentes);
			for (int j = 1; j <= adyacentes; j++) {
				toReturn.get(i).put(k, (int) Math.floor(Math.random() * 100));
				k--;
			}
		}
		return toReturn;
	}

	// F.Bruta
	public static TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> fBruta() {
		caminos = new TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>>();
		Iterator<Integer> it, it2;
		int origen;
		it = grafo.keySet().iterator();
		while (it.hasNext()) {
			origen = it.next();
			caminos.put(origen, new TreeMap<Integer, LinkedList<Integer>>());
			it2 = grafo.get(origen).keySet().iterator();
			while (it2.hasNext()) {
				obtenerCaminoMin(origen, it.next());
			}
		}
		return caminos;
	}

	private static void obtenerCaminoMin(int origen, int destino) {
		result = new LinkedList<Integer>();
		peso = INFINITE;
		pesoAux = 0;
		obtenerCaminoMinAux(origen, destino);
	}

	private static void obtenerCaminoMinAux(int cur, int destino) {
		result.add(cur);
		int pesoAuxAnterior = pesoAux;

		if (cur == destino && pesoAux < peso) {
			LinkedList<Integer> resultAux = new LinkedList<Integer>();
			resultAux.addAll(result);
			caminos.get(result.getFirst()).put(cur, resultAux);

		} else {
			TreeMap<Integer, Integer> adyacentes = grafo.get(cur);
			for (Map.Entry<Integer, Integer> entry : adyacentes.entrySet()) {
				int proximo = entry.getKey();
				if (!result.contains(proximo))
					pesoAux += grafo.get(cur).get(proximo);
				obtenerCaminoMinAux(proximo, destino);
			}
		}
		result.removeLast();
		pesoAux = pesoAuxAnterior;
	}

	// Floyd
	public static TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> dinamicaFloyd() {
		floyd();
		caminos = new TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>>();
		Iterator<Integer> it, it2;
		int origen;
		it = grafo.keySet().iterator();
		while (it.hasNext()) {
			origen = it.next();
			caminos.put(origen, new TreeMap<Integer, LinkedList<Integer>>());
			it2 = grafo.get(origen).keySet().iterator();
			while (it2.hasNext()) {
				camino(origen, it.next());
			}
		}
		return caminos;
	}

	private static void floyd() {

		int size = grafo.size();

		int[][] d = new int[size][size];
		int[][] p = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					d[i][j] = 0;
					p[i][j] = 0;
				} else {
					d[i][j] = INFINITE;
					if (grafo.get(i).containsKey(j))
						p[i][j] = 0;
					else
						p[i][j] = INFINITE;
				}
			}
		}

		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (d[i][k] + +d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + +d[k][j];
						p[i][j] = k;
					}
				}
			}
		}

		lastFloydMidNodesResult = p;

	}

	private static LinkedList<Integer> camino(int origen, int destino) {
		LinkedList<Integer> toReturn = new LinkedList<Integer>();
		int k = lastFloydMidNodesResult[origen][destino];
		if (k != INFINITE) {
			camino(toReturn, origen, destino);
		}
		return toReturn;
	}

	private static void camino(LinkedList<Integer> toReturn, int origen,
			int destino) {
		int k = lastFloydMidNodesResult[origen][destino];
		if (k != 0) {
			camino(toReturn, origen, k);
			toReturn.add(k);
			camino(toReturn, k, destino);
		}
	}

}
