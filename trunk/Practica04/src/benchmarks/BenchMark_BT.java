package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import programa.Programa;
import utilidades.BacktrackingV1;
import utilidades.Cliente;

public class BenchMark_BT {

	/**
	 * Archivo de salida.
	 */
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmarks"
			+ File.separator + "Benchmark_BT.txt";
	
	/**
	 * Metodo principal de la prueba de rendimiento
	 * @param args
	 */
	public static void main(String[] args){
		int tamano = 5;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				Programa.setFile(new File(Programa.ENTRADA + tamano + "Usuarios.txt"));
				Programa.setEntry(new ArrayList<Cliente>());
				Programa.setTaller(null);
				Programa.leerArchivo();
				BacktrackingV1 bt = new BacktrackingV1(Programa.getEntry(), 405, Programa.getTaller().getVivienda().x, Programa.getTaller().getVivienda().y);
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b = System.nanoTime();
					bt.solucionBasica();
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("Nº de clientes: " + tamano + " Tiempo: " + media(times) + "ns\n");
				System.out.println("Done");
				tamano +=1;
			}while(tamano <= 10);
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que cacula la media de los 6 tiempos intermedios.
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
