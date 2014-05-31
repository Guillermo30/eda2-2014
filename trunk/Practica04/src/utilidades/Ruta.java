package utilidades;

import java.util.ArrayList;

public class Ruta {
	
	private ArrayList<Cliente> ruta;
	private int numClientes;
	private int distancia;
	private int tiempo;
	private int jornada;
	
	public Ruta(int jornada, int a, int c){
		ruta = new ArrayList<Cliente>(14);
		ruta.add(new Cliente(-1, 0, a ,c));
		this.jornada=jornada;
		numClientes = 0;
		distancia =  0;
		tiempo = 15;
	}
	
	public boolean addClient (Cliente x){
		if(tiempo+x.getTiempo() > (jornada + 15)) return false;
		ruta.add( x);
		++numClientes;
		tiempo += x.getTiempo();
		calcularDistancia();
		return true;
		
	}
	private void calcularDistancia(){
		distancia =0;
		for(int i =0; i<numClientes;i++){
			if(i+1 > numClientes){
				distancia += ruta.get(i).distanciaHasta(ruta.get(0));
			}else{
				distancia += ruta.get(i).distanciaHasta(ruta.get(i+1));
			}
		}
	}
	public boolean deleteLast(){
		if(ruta.size() == 1) return false;
		ruta.remove(ruta.size()-1);
		numClientes--;
		return true;
	}
	
	public Cliente getLastCliente(){
		return ruta.get(ruta.size() - 1);
	}
	
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
