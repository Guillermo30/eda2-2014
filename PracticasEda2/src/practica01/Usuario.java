package practica01;
/**
 * Clase que representa el tipo de objeto con el que vamos a trabajar, en este caso Usuarios de la compañia electrica
 * @author 
 *
 */
public class Usuario {
	private int id;
	private int ce;
	private int ice;
	/**
	 * Crear un nuevo usuario dada su ID, su CE y su ICe
	 * @param id 
	 * @param ce
	 * @param ice
	 */
	public Usuario(int id, int ce, int ice){
		this.id=id;
		this.ce=ce;
		this.ice=ice;
	}
	/**
	 * 
	 * @return ID del usuario.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Da al usuario un nuevo id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return CE del usuario.
	 */
	public int getCe() {
		return ce;
	}
	/**
	 * Da al usuario un nuevo CE.
	 * @param ce
	 */
	public void setCe(int ce) {
		this.ce = ce;
	}
	/**
	 * 
	 * @return ICE del usuario.
	 */
	public int getIce() {
		return ice;
	}
	/**
	 * Da al usuario un nuevo ICE
	 * @param ice
	 */
	public void setIce(int ice) {
		this.ice = ice;
	}
	/**
	 * Formatea un string que representa el usuario con sus tres atributos.
	 */
	public String toString(){
		return "["+id+" "+ice+" "+ce+"]";
		
	}
	
	@Override
	public boolean equals(Object o){
		if(((Usuario) o).getId()==this.getId()) return true;
		return false;
	}
	
}
