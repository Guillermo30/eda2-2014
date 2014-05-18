package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

import programa.Programa;
import utilidades.Cliente;
import utilidades.Mochila;

public class RendimientoMochilaIt {

	//Directorios de entrada y de salida de los ficheros.
		public final static String ENTRADA = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "entradas"
				+ File.separator;
		public final static String SALIDA = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "salidas"
				+ File.separator + "Benchmark_Mochila_Iterativo.txt";

		/**
		 * Método que ejecuta este benchmark
		 * @param args
		 */
		public static void main(String[] args) {
			int numDatos = 165;
			Mochila mochila;
			File out = new File(SALIDA);
			PriorityQueue<Long> times = new PriorityQueue<Long>();
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
				do{
					pw.println("Datos: " + numDatos + "\n");
					Programa.setFraudes(new ArrayList<Cliente>());
					Programa.setFile(new File(ENTRADA + numDatos + "Usuarios.txt"));
					try {
						Programa.leerArchivo();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ArrayList<Cliente> fraudes = Programa.getFraudes();
					
					mochila = new Mochila(fraudes, 15);
					
					times.clear();
					for(int i = 0; i < 10; i++){
						long a, b = System.nanoTime();
						mochila.maxBeneficioIt();
						a = System.nanoTime();
						times.add(a - b);
					}
					pw.println("\t  Media: " + media(times) + "\n");
					
					numDatos += 65;
					
				}while(numDatos <= 750);
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
	
}
