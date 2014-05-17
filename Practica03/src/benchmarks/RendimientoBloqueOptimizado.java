package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import programa.Programa;
import utilidades.Cliente;
import utilidades.Mochila;

public class RendimientoBloqueOptimizado {

	//Directorio de salida de los ficheros.
			public final static String SALIDA = System.getProperty("user.dir")
					+ File.separator + "src" + File.separator + "salidas"
					+ File.separator + "Benchmark_TamñoBloque.txt";

			/**
			 * Método que ejecuta este benchmark
			 * @param args
			 */
			public static void main(String[] args) {
				Mochila mochila;
				File out = new File(SALIDA);
				int tBloque;
						
						LinkedList<Integer> mcd = new LinkedList<Integer>();
						mcd.add(15);
						mcd.add(18);
						mcd.add(20);
						mcd.add(25);
						mcd.add(30);
						mcd.add(45);
						mcd.add(60);
						mcd.add(75);
						mcd.add(110);
						Iterator<Integer> it = mcd.iterator();
				PriorityQueue<Long> times = new PriorityQueue<Long>();
				try {
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
					do{
						
						
						ArrayList<Cliente> fraudes = generarAList(it.next());
						Programa.setFraudes(fraudes);
						tBloque = Programa.bloqueOptimo();
						
						mochila = new Mochila(fraudes, tBloque);
						
						times.clear();
						for(int i = 0; i < 10; i++){
							long a, b = System.nanoTime();
							tBloque = Programa.bloqueOptimo();
							mochila.maxBeneficio();
							a = System.nanoTime();
							times.add(a - b);
						}
						pw.println("\t  Tamaño de bloque: " + tBloque + " Media: " + media(times) + "\n");
						
					}while(it.hasNext());
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			/**
			 * Método que calcula la media deshechando los 2 dos mejores y peores tiempos.
			 * @param times
			 * @return
			 */
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
			
			private static ArrayList<Cliente> generarAList(int tBloque){			
				ArrayList<Cliente> toReturn =  new ArrayList<Cliente>(750);
				
				for(int i = 0; i < 750; i++){
					Cliente c = new Cliente(i, 1, 100);
					c.setTiempo(tBloque);
					toReturn.add(c);
				}
				
				return toReturn;
			}
	
}
