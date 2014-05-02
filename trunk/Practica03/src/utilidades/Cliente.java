package utilidades;



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
	 * CE del cliente.
	 */
	private int ice;
	/**
	 * Diferencia de su media de CE local con su CE.
	 */
	private int difMediaLocal;
	/**
	 * Tiempo que se tarda en inspeccionar el cliente, incluyendo el desplazamiento.
	 */
	private int tiempo;
	
	/**
	 * Marca su posicion en la nube original. 
	 */
	public Cliente(int id, int ice, int difMediaLocal){
		this.id=id;
		this.difMediaLocal = difMediaLocal;
		this.ice=ice;
		
		double tiempo = 30 *(Math.log10(ice/1500 + 1)/Math.log10(2)) + 9;
		tiempo += 15 - tiempo%15;
		this.tiempo = (int)tiempo +15;
		
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
	 * @return the difMediaLocal
	 */
	public int getDifMediaLocal() {
		return difMediaLocal;
	}
	
	/**
	 * @param difMediaLocal the difMediaLocal to set
	 */
	public void setDifMediaLocal(int difMediaLocal) {
		this.difMediaLocal = difMediaLocal;
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
		return "["+id+" "+ice+" "+difMediaLocal+"]";
		
	}
	
	@Override
	public boolean equals(Object o){
		if(((Cliente) o).getId()==this.getId()) return true;
		return false;
	}
	
}
