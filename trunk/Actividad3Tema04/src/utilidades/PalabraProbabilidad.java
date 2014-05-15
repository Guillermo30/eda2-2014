package utilidades;

import java.util.ArrayList;
import java.util.Collections;

public class PalabraProbabilidad implements Comparable<PalabraProbabilidad>{
	
	public String palabra;
	public double probabilidad;
	
	public PalabraProbabilidad(String palabra, double probabilidad){
		this.palabra = palabra;
		this.probabilidad = probabilidad;
	}
	
	@Override
	public int compareTo(PalabraProbabilidad arg0) {
		return palabra.compareTo(arg0.palabra);
	}
	
	@Override
	public String toString() {
		return "[palabra=" + palabra + ", probabilidad="
				+ probabilidad + "]";
	}

	public static ArrayList<PalabraProbabilidad> generarAleatorio(int tamano){
		ArrayList<PalabraProbabilidad> toReturn = new ArrayList<PalabraProbabilidad>(tamano);
		while(toReturn.size() < tamano){
			String nextWord = gernerarPalabra();
			if(!toReturn.contains(nextWord))toReturn.add(new PalabraProbabilidad(nextWord, Math.random()));
		}
		Collections.sort(toReturn);
		return toReturn;
	}

	private static String gernerarPalabra() {
		int tamano = (int)Math.floor(Math.random() * 11 + 1);
		String toReturn = "";
		for(int i = 0; i < tamano; i++){
			toReturn += ((char)(int)Math.floor(Math.random() * 26 + 97));
		}
		return toReturn;
	}

}
