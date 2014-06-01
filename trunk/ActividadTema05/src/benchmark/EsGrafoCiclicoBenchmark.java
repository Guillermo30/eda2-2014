package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

import algoritmo.GrafoCiclico;

public class EsGrafoCiclicoBenchmark {
	
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_GrafoCiclico.txt";
	
	public static void main(String[] args){
		int tamano = 5;
		TreeMap<Integer, TreeSet<Integer>> grafo;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				grafo = GrafoCiclico.generarGrafoAleatorio(tamano);
				
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b = System.nanoTime();
					GrafoCiclico.esGrafoCiclico(grafo);
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("Tamaño del grafo: " + tamano + " Tiempo: " + media(times) + "ns\n");
				System.out.println("Done");
				tamano +=5;
			}while(tamano <= 50);
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
