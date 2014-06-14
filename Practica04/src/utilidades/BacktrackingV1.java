package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Clase donde se encuentra el algoritmo de Backtracking.
 */
public class BacktrackingV1 {

	/**
	 * Entrada de datos del algoritmo.
	 */
	private ArrayList<Cliente> clientes;

	/**
	 * Duración de cada jornada en minutos sin contar los 15 min de regreso al
	 * taller.
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

	/**
	 * Variables para llevar calculos de distancia, tiempo y numero de rutas.
	 */
	private int solDist, solT, sumaDist, sumaT, numTalleres;

	/**
	 * Array para saber si un elemto ya esta en la solucion parcial
	 */
	private boolean[] cogido;

	/**
	 * Array para saber el siguiente hermano del nivel.
	 */
	private int[] siguienteHermano;

	/**
	 * Tiempo de relleno para completar una ruta
	 */
	private int relleno;

	/**
	 * Método constructor.
	 * 
	 * @param clientes
	 * @param jornada
	 * @param aTaller
	 * @param cTaller
	 */
	public BacktrackingV1(ArrayList<Cliente> clientes, int jornada,
			int aTaller, int cTaller) {
		this.clientes = clientes;
		this.jornada = jornada;
		this.aTaller = aTaller;
		this.cTaller = cTaller;
		taller = new Cliente(-1, 15, aTaller, cTaller);
	}

	// Solución mejorada.

	/**
	 * Método que encuentra una solucion de manera más eficiente.
	 * 
	 * @return
	 */
	public ArrayList<Ruta> solucionMejorada() {
		LinkedList<Cliente> solActual = new LinkedList<Cliente>();
		LinkedList<Cliente> solParcial = new LinkedList<Cliente>();
		ArrayList<Ruta> solucion = new ArrayList<Ruta>();
		cogido = new boolean[clientes.size()];
		siguienteHermano = new int[clientes.size()];
		for (int i = 0; i < cogido.length; i++)
			cogido[i] = false;
		for (int i = 0; i < siguienteHermano.length; i++)
			siguienteHermano[i] = 0;
		Ruta auxR = new Ruta(jornada, aTaller, cTaller);
		int nivel = 1;
		solDist = Integer.MAX_VALUE;
		solT = Integer.MAX_VALUE;
		sumaDist = 0;
		sumaT = 0;
		numTalleres = 0;
		int jornadaC = jornada + 15;
		do {
			generar(nivel, solParcial);
			if (solucion(nivel, solParcial)) {
				if (((sumaT / jornadaC) < solT)
						|| (((sumaT / jornadaC) == solT) && sumaDist < solDist)) {

					solT = sumaT / jornadaC;
					solDist = sumaDist;
					solActual.clear();
					solActual.addAll(solParcial);
				}
				sumaT -= relleno;
				sumaDist -= solParcial.getLast().distanciaHasta(taller);
			}

			if (criterio(nivel, solParcial))
				nivel++;
			else
				while (nivel > 0 && !masHermanos(nivel, solParcial)) {
					retroceder(nivel, solParcial);
					nivel--;
				}
		} while (nivel != 0);
		for (Cliente c : solActual) {
			if (c.equals(taller)) {
				solucion.add(auxR);
				auxR = new Ruta(jornada, aTaller, cTaller);
				continue;
			}
			auxR.addClient(c);
		}
		solucion.add(auxR);
		return solucion;
	}

	/**
	 * Método que retrocede un nivel.
	 * 
	 * @param nivel
	 * @param solParcial
	 */
	private void retroceder(int nivel, LinkedList<Cliente> solParcial) {
		Cliente aux = solParcial.removeLast();

		sumaT -= aux.getTiempo();
		if (!solParcial.isEmpty())
			sumaDist -= solParcial.getLast().distanciaHasta(aux);
		else
			sumaDist -= taller.distanciaHasta(aux);

		cogido[aux.getIndex()] = false;

		if (!solParcial.isEmpty() && solParcial.getLast().equals(taller)) {
			numTalleres--;
			aux = solParcial.removeLast();
			sumaT -= aux.getTiempo();
			sumaDist -= solParcial.getLast().distanciaHasta(taller);
		}

		siguienteHermano[nivel - 1] = 0;

	}

	/**
	 * Método que verifica si un nivel tiene nodos por investigar.
	 * 
	 * @param nivel
	 * @param solParcial
	 * @return
	 */
	private boolean masHermanos(int nivel, LinkedList<Cliente> solParcial) {
		if (siguienteHermano[nivel - 1] < clientes.size()) {
			Cliente aux = solParcial.removeLast();
			sumaT -= aux.getTiempo();
			if (!solParcial.isEmpty())
				sumaDist -= solParcial.getLast().distanciaHasta(aux);
			else
				sumaDist -= taller.distanciaHasta(aux);
			cogido[aux.getIndex()] = false;
			return true;
		}
		return false;
	}

	/**
	 * Criterio que dice si subir o no de nivel.
	 * 
	 * @param nivel
	 * @param solParcial
	 * @return
	 */
	private boolean criterio(int nivel, LinkedList<Cliente> solParcial) {
		if (nivel == clientes.size())
			return false;
		if (((sumaT / (jornada + 15)) + ((sumaT % (jornada + 15) == 0) ? 0 : 1)) > solT
				|| (sumaT == solT && sumaDist > solDist))
			return false;
		return true;
	}

	/**
	 * Método que determina si se ha llegado a una solución.
	 * 
	 * @param nivel
	 * @param solParcial
	 * @return
	 */
	private boolean solucion(int nivel, LinkedList<Cliente> solParcial) {
		if (nivel == clientes.size()) {
			sumaDist += solParcial.getLast().distanciaHasta(taller);
			relleno = (jornada + 15) - (sumaT % (jornada + 15));
			sumaT += relleno;
			return true;
		}
		return false;
	}

	/**
	 * Método que genera al siguiente hermano del nivel.
	 * 
	 * @param nivel
	 * @param solParcial
	 */
	private void generar(int nivel, LinkedList<Cliente> solParcial) {
		while (cogido[siguienteHermano[nivel - 1]])
			siguienteHermano[nivel - 1]++;
		Cliente c = clientes.get(siguienteHermano[nivel - 1]);

		sumaT += c.getTiempo();

		if (((sumaT - 1) / jornada) - numTalleres > 0) {
			int tiempoRelleno = (jornada + 15)
					- ((sumaT - c.getTiempo()) % (jornada + 15));
			sumaDist += solParcial.getLast().distanciaHasta(taller);
			solParcial.add(new Cliente(-1, tiempoRelleno, aTaller, cTaller));
			numTalleres++;
			sumaT += tiempoRelleno;
		}
		if (solParcial.size() != 0)
			sumaDist += solParcial.getLast().distanciaHasta(c);
		else
			sumaDist += taller.distanciaHasta(c);
		solParcial.add(c);
		cogido[siguienteHermano[nivel - 1]] = true;
		while (siguienteHermano[nivel - 1] < cogido.length
				&& cogido[siguienteHermano[nivel - 1]])
			siguienteHermano[nivel - 1]++;
	}

	// Solución básica

/**
 * Método que encuentra una solucion de manera más eficiente.
 * @return
 */
	public ArrayList<Ruta> solucionBasica() {
		// Inicialización
		Stack<Cliente> pila = new Stack<Cliente>();
		LinkedList<Cliente> solParcial = new LinkedList<Cliente>();
		ArrayList<Ruta> solucion = new ArrayList<Ruta>();
		ArrayList<Ruta> solucionFinal = new ArrayList<Ruta>();
		Ruta auxR = new Ruta(jornada, aTaller, cTaller);
		int solDist = Integer.MAX_VALUE;
		int sumaDist = 0;

		// Inicializar pila con el primer nivel
		for (Cliente c : clientes)
			pila.push(c);

		// Exploración del arbol
		while (!pila.isEmpty()) {

			solParcial.add(pila.peek());

			// Si hemos llegado a una hoja.
			if (solParcial.size() == clientes.size()) {

				solucion.clear();
				auxR = new Ruta(jornada, aTaller, cTaller);
				for (Cliente c : solParcial) {
					if (!auxR.addClient(c)) {
						solucion.add(auxR);
						auxR = new Ruta(jornada, aTaller, cTaller);
						auxR.addClient(c);
					}
				}
				solucion.add(auxR);
				sumaDist = 0;
				for (Ruta r : solucion)
					sumaDist += r.getDistancia();
				if (solucionFinal.isEmpty()
						|| solucion.size() < solucionFinal.size()
						|| (solucion.size() == solucionFinal.size() && sumaDist < solDist)) {
					solucionFinal.clear();
					solucionFinal.addAll(solucion);
					solDist = sumaDist;
				}

				// Retroceder

				pila.pop();
				solParcial.removeLast();

				do {
					pila.pop();
					solParcial.removeLast();

				} while (!solParcial.isEmpty()
						&& pila.peek() == solParcial.getLast());
			} else {
				// Generar Nivel
				for (Cliente c : clientes)
					if (!solParcial.contains(c))
						pila.push(c);
			}

		}
		return solucionFinal;
	}

}
