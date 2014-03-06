package practica01;

public class Usuario {
	private int id;
	private int ce;
	private int ice;
	
	public Usuario(int id, int ce, int ice){
		this.id=id;
		this.ce=ce;
		this.ice=ice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCe() {
		return ce;
	}

	public void setCe(int ce) {
		this.ce = ce;
	}

	public int getIce() {
		return ice;
	}

	public void setIce(int ice) {
		this.ice = ice;
	}
	
	public String toString(){
		return "["+id+" "+ice+" "+ce+"]";
		
	}
	
	@Override
	public boolean equals(Object o){
		if(((Usuario) o).getId()==this.getId()) return true;
		return false;
	}
	
}
