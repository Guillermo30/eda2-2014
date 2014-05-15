package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import algoritmo.ABBOptimo;
import utilidades.PalabraProbabilidad;
import utilidades.SimpleABB;

public class Benchmark {
	
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_ABB_Optimo.txt";
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		
		int tamano = 100;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		SimpleABB<PalabraProbabilidad> arbol;
		ArrayList<PalabraProbabilidad> datos;
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				pw.println("Tamaño " + tamano + "\n");
				datos = PalabraProbabilidad.generarAleatorio(tamano);
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b =System.nanoTime();
					ABBOptimo.abbOpt(datos);
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("Algoritmo dinámico: " + media(times) + "ns\n");
				datos = PalabraProbabilidad.generarAleatorio(tamano);
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b =System.nanoTime();
					arbol = ABBOptimo.obtenerArbol();
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("Recuperación del árbol: " + media(times) + "ns\n");
				pw.println("-------------------------------------------------------------\n");
				System.out.println("Done");
				tamano += 100;
			}while(tamano <= 1000);
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static long media(PriorityQueue<Long> times) {
		long media = 0;
		
		times.poll();
		times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		return media/6;
	}
	
}
