package utilidades;

import java.awt.Point;

/**
 * Clase que representa el tipo de objeto con el que vamos a trabajar, en este caso Usuarios de la compañia electrica.
 *
 */
public class Cliente {
	
	/**
	 * Numero por el que se identifica el cliente.
	 */
	private int id;
	/**
	 * Tiempo que se tarda en inspeccionar el cliente, incluyendo el desplazamiento.
	 */
	private int tiempo;
	/**
	 * Coordenadas de vivienda.
	 */
	private Point vivienda;
	
	/**
	 * Método constructor.
	 * @param id
	 * @param ice
	 * @param difMediaLocal
	 */
	public Cliente(int id, int tiempo, int i, int j){
		this.id=id;
		this.tiempo = tiempo;
		vivienda = new Point(i, j);
	}
	
	/**
	 * 
	 * @return ID del cliente.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the tiempo
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * Formatea un string que representa el cliente con sus tres atributos.
	 */
	public String toString(){
		return id+", "+ tiempo +", (" + vivienda.x +"," + vivienda.y + ")";
		
	}
	
	/**
	 * @return the vivienda
	 */
	public Point getVivienda() {
		return vivienda;
	}
	
	public int distanciaHasta(Cliente c){
		return (int)(Math.abs(vivienda.x - c.getVivienda().x) + Math.abs(vivienda.y - c.getVivienda().y)); 
	}

	@Override
	public boolean equals(Object o){
		if(((Cliente) o).getId()==this.getId()) return true;
		return false;
	}
	
}
