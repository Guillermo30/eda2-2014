package utilidades;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mochila {
	
	/**
	 * Peso límite
	 */
	private int maxPeso;
	
	/**
	 * Tamaño de cada bloque.
	 */
	private int tamanoBloque;
	
	/**
	 * Objetos candidatos a escoger.
	 */
	protected ArrayList<Cliente> fraudes;
	
	/**
	 * Array para el tratamiento dinámico.
	 */
	protected int[][] datos;
	
	/**
	 * Método constructor.
	 * @param fraudes
	 * @param tamanoBloque
	 */
	public Mochila(ArrayList<Cliente> fraudes, int tamanoBloque){
		this.fraudes = fraudes;
		this.tamanoBloque = tamanoBloque;
		this.maxPeso = 9900/tamanoBloque;
		datos = new int[fraudes.size() + 1][maxPeso + 1];
	}
	
	/**
	 * Devuelve los elementos que se deben inspeccionar para obtener el máximo beneficio.
	 * @return
	 */
	public LinkedList<Cliente> maxBeneficio() {
		rellenarDatos();
		return obtenerSeleccion();
	}

	/**
	 * Utiliza un método recursivo para sacar los clientes a inspeccionar a través de la tabla "datos".
	 * @return
	 */
	private LinkedList<Cliente> obtenerSeleccion() {
		LinkedList<Cliente> toReturn = new LinkedList<Cliente>();
		int elemento;
		elemento = fraudes.size();
		seleccionar(toReturn, elemento, maxPeso);
		return toReturn;
	}

	/**
	 * Método recursivo utilizado para sacar los clientes a inspeccionar a través de la tabla "datos".
	 * @param toReturn
	 * @param elemento
	 * @param peso
	 */
	private void seleccionar(LinkedList<Cliente> toReturn, int elemento, int peso) {
		Cliente cliente = fraudes.get(elemento - 1); 
		int pesoElemento = cliente.getTiempo()/tamanoBloque;
		int beneficio = cliente.getDifMediaLocal();
		if(peso < pesoElemento) seleccionar(toReturn, elemento - 1, peso);
		else{
			if(datos[elemento - 1][peso - pesoElemento] + beneficio > datos[elemento -1][peso]){
				seleccionar(toReturn, elemento - 1, peso - pesoElemento);
				toReturn.add(cliente);
			}else seleccionar(toReturn, elemento - 1, peso);
		}
		
	}

	/**
	 * Rellena la tabla "datos".
	 */
	private void rellenarDatos() {
		int peso, beneficio;
		for(int i = 0; i < datos[0].length; i++) datos[0][i] = 0;
		for(int i = 0; i < datos.length; i++) datos[i][0] = 0;
		for(int k = 0; k < fraudes.size(); k++ ){
			peso = fraudes.get(k).getTiempo()/tamanoBloque;
			beneficio = fraudes.get(k).getDifMediaLocal();
			for(int w = 1; w < peso - 1; w++) datos[k][w] = datos[k - 1][w];
			for(int w = peso; w <= maxPeso; w++) datos[k][w] = max(datos[k-1][w - peso] + beneficio, datos[k - 1][w]);
		}
	}

	/**
	 * Devuelve el mayor de los 2 enteros que se le pasan por parámetro.
	 * @param i
	 * @param j
	 * @return
	 */
	private int max(int i, int j) {
		if (i > j) return i;
		return j;
	}	

}
