package utilidades;



/**
 * Clase que representa el tipo de objeto con el que vamos a trabajar, en este caso Usuarios de la compañia electrica.
 *
 */
public class Cliente implements Comparable<Cliente>{
	
	/**
	 * Numero por el que se identifica el cliente.
	 */
	private int id;
	/**
	 * CE del cliente.
	 */
	private int ce;
	/**
	 * ICE del cliente.
	 */
	private int ice;
	
	/**
	 * Crear un nuevo cliente dada su ID, su CE y su ICe
	 * @param id 
	 * @param ce
	 * @param ice
	 */
	public Cliente(int id, int ce, int ice){
		this.id=id;
		this.ce=ce;
		this.ice=ice;
	}
	
	/**
	 * 
	 * @return ID del cliente.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return CE del cliente.
	 */
	public int getCe() {
		return ce;
	}
	/**
	 * Da al cliente un nuevo CE.
	 * @param ce
	 */
	public void setCe(int ce) {
		this.ce = ce;
	}
	/**
	 * 
	 * @return ICE del cliente.
	 */
	public int getIce() {
		return ice;
	}
	/**
	 * Da al cliente un nuevo ICE
	 * @param ice
	 */
	public void setIce(int ice) {
		this.ice = ice;
	}
	/**
	 * Formatea un string que representa el cliente con sus tres atributos.
	 */
	public String toString(){
		return "["+id+" "+ice+" "+ce+"]";
		
	}
	
	@Override
	public boolean equals(Object o){
		if(((Cliente) o).getId()==this.getId()) return true;
		return false;
	}
	@Override
	public int compareTo(Cliente arg0) {
		int comp = this.ce - arg0.ce;
		if(comp == 0) return this.ice - arg0.ice;
		return comp;
		
	}
	
}
