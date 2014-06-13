package utilidades;

import java.util.ArrayList;

/**
 * Clase que guarda la informaci�n de una ruta.
 * @author JuanAndres
 *
 */
public class Ruta {
	
	/**
	 * Clientes que se visitan en orden a exepci�n del elemento 0 que es el taller.
	 */
	private ArrayList<Cliente> ruta;
	
	/**
	 * N�mero de clientes a�adidos a la ruta.
	 */
	private int numClientes;
	
	/**
	 * Distancia total acumulada en la ruta.
	 */
	private int distancia;
	
	/**
	 * Tiempo total acumulado en la ruta.
	 */
	private int tiempo;
	
	/**
	 * Tiempo m�ximo que puede durar una jornada sin incluir el tiempo de vuelta al taller.
	 */
	private int jornada;
	
	/**
	 * M�todo constructor
	 * @param jornada
	 * @param a
	 * @param c
	 */
	public Ruta(int jornada, int a, int c){
		ruta = new ArrayList<Cliente>(14);
		ruta.add(new Cliente(-1, 0, a ,c));
		this.jornada=jornada;
		numClientes = 0;
		distancia =  0;
		tiempo = 15;
	}
	
	/**
	 * Se a�ade un cliente a la ruta si no se sobrepasa el tiempo de jornada.
	 * @param x
	 * @return
	 */
	public boolean addClient (Cliente x){
		if(tiempo+x.getTiempo() > (jornada + 15)) return false;
		ruta.add( x);
		++numClientes;
		tiempo += x.getTiempo();
		calcularDistancia();
		return true;
		
	}
	
	/**
	 * Calcula la distancia acumulada en la ruta en el instante actual.
	 */
	private void calcularDistancia(){
		distancia =0;
		
		for(int i =0; i < numClientes; i++){
			distancia += ruta.get(i).distanciaHasta(ruta.get(i+1));
		}
		distancia += ruta.get(ruta.size()-1).distanciaHasta(ruta.get(0));
	}
	
	/**
	 * Elimina el ultimo cliente a�adido si lo hay.
	 * @return
	 */
	public boolean deleteLast(){
		if(ruta.size() == 1) return false;
		ruta.remove(ruta.size()-1);
		numClientes--;
		return true;
	}
	
	/**
	 * Devuelve el �ltimo cliente.
	 * @return
	 */
	public Cliente getLastCliente(){
		return ruta.get(ruta.size() - 1);
	}
	
	//getters
	
	public ArrayList<Cliente> getRuta() {
		return ruta;
	}

	public int getNumClientes() {
		return numClientes;
	}

	public int getDistancia() {
		return distancia;
	}

	public int getTiempo() {
		return tiempo;
	}
	
	@Override
	public boolean equals(Object r){
		return ruta.equals(((Ruta)r).ruta);
	}
	
	@Override
	public String toString(){
		String toReturn = "(Taller --> ";
		for(int i = 1; i < ruta.size(); i++)
			toReturn += ruta.get(i).getId() + " --> ";
		return toReturn + "Taller)";
	}
	
}
