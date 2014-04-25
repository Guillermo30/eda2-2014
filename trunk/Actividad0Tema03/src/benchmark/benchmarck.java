package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.TreeMap;

import algoritmo.CentroLogistico;



public class benchmarck {

	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_Grafo.txt";
	
	public static void main(String[] args){
		
		int tamano = 20;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		TreeMap<Integer, TreeMap<Integer, Integer>> grafo;
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				grafo = CentroLogistico.generarGrafoAleatorio(tamano);
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b =System.nanoTime();
					CentroLogistico.centroLogistico(grafo);
					a = System.nanoTime();
					times.add(a - b);
				}
				System.out.println("Done");
				pw.println("Grafo de tamaño " + tamano + ": " + media(times) + "ns\n");
				tamano += 20;
			}while(tamano <=200);
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
