package utilidades;

public class Tarea {

	private int ini;
	private int fin;
	private int duracion;
	
	private int conflictos;
	
	public Tarea(int ini, int fin){
		this.ini = ini;
		this.fin = fin;
		duracion = fin - ini;
		conflictos = -1;
	}
	
	public Tarea(int rango, int maxDuracion, int noUsado){
		int duracion = (int)(Math.random() * maxDuracion + 1);
		this.fin = (int) (Math.random() * (rango - maxDuracion + 1) + maxDuracion);
		this.ini = fin - duracion;
		this.duracion = duracion;
		conflictos = - 1;
	}
	
	public boolean solapaCon(Tarea t, int superposicion){
		if(this.ini >= (t.fin - ((t.duracion * superposicion)/100)) || t.ini >= (this.fin - ((this.duracion * superposicion)/100))) return false;
		return true;
	}
	
	public boolean solapaCon(Tarea t){
		if(this.ini >= t.fin || t.ini >= this.fin) return false;
		return true;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getIni() {
		return ini;
	}

	public void setIni(int ini) {
		this.ini = ini;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getConflictos() {
		return conflictos;
	}

	public void setConflictos(int confilctos) {
		this.conflictos = confilctos;
	}
	
	@Override
	public String toString(){
		String toReturn = ini + "-(" + duracion + ")->" + fin;
		if(conflictos >= 0) toReturn +=" (" + conflictos + ")";
		return toReturn;
	}
	
}
