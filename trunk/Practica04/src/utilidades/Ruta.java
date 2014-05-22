package utilidades;

public class Ruta {
	
	private Cliente[] ruta;
	private int numClientes;
	private int distancia;
	private int tiempo;
	private int jornada;
	
	public Ruta(int jornada, int a, int c){
		ruta = new Cliente[14];
		ruta[0] = new Cliente(-1, 0, a ,c);
		this.jornada=jornada;
		numClientes = 0;
		distancia =  0;
		tiempo = 15;
	}
	
	public boolean addClient ( Cliente x){
		if(tiempo+x.getTiempo() > jornada) return false;
		ruta[++numClientes] =  x;
		tiempo += x.getTiempo();
		calcularDistancia();
		return true;
		
	}
	private void calcularDistancia(){
		distancia =0;
		for(int i =0; i<numClientes;i++){
			if(i+1 > numClientes){
				distancia += ruta[i].distanciaHasta(ruta[0]);
			}else{
				distancia += ruta[i].distanciaHasta(ruta[i+1]);
			}
		}
	}

	public Cliente[] getRuta() {
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
	
	
	
	
}
