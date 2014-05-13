package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.TreeMap;

import floyd.Floyd;

public class Benchmark {
	
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_Floyd_VS_FBruta.txt";
	
	public static void main(String[] args){
		
		int tamano = 5;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		TreeMap<Integer, TreeMap<Integer, Integer>> grafo;
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				pw.println("Grafo de tamaño " + tamano + "\n");
				grafo = Floyd.generarGrafoAleatorio(tamano);
				Floyd.setGrafo(grafo);
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b =System.nanoTime();
					Floyd.fBruta();
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("Fuerza Bruta: " + media(times) + "ns\n");
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b =System.nanoTime();
					Floyd.dinamicaFloyd();
					a = System.nanoTime();
					times.add(a - b);
				}
				System.out.println("Done");
				pw.println("Floyd: " + media(times) + "ns\n");
				pw.println("----------------------------------------------------------------------------\n");
				tamano += 5;
			}while(tamano <= 15);
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
