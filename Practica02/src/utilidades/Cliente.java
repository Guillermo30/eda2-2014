package utilidades;



/**
 * Clase que representa el tipo de objeto con el que vamos a trabajar, en este caso Usuarios de la compa�ia electrica.
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
	 * Diferencia de su media de CE loca con su CE.
	 */
	private int difMediaLocal;
	
	/**
	 * Marca su posicion en la nube original. 
	 */
	private int indexOnNube;
	
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
	 * @return the indexOnNube
	 */
	public int getIndexOnNube() {
		return indexOnNube;
	}

	/**
	 * @param indexOnNube the indexOnNube to set
	 */
	public void setIndexOnNube(int indexOnNube) {
		this.indexOnNube = indexOnNube;
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
	
	@Override
	public Cliente clone(){
		Cliente sol = new Cliente(this.id, this.ce, this.ice);
		sol.setDifMediaLocal(difMediaLocal);
		return sol;
	}
	
}
