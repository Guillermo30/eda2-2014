package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

import paretoDivision.ParetoDivision;
import paretoNube.ParetoNube;
import programa.Programa;
import utilidades.Cliente;
import utilidades.Pareto;

/**
 * Clase para el calculo del rendimiento de los algoritmos que resuelven pareto de forma voraz.
 */
public class NubeVsDivision {
	
	//Directorios de entrada y de salida de los ficheros.
	public final static String ENTRADA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "entradas"
			+ File.separator;
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "salidas"
			+ File.separator + "Benchmark_Pareto_Voraz.txt";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int numDatos = 10000;
		Pareto paretoNube;
		Pareto paretoDivision;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				pw.println("Datos: " + numDatos +"\n\n\tNube:\n");
				Programa.setClientes(new ArrayList<Cliente>());
				Programa.setCandidatos(new LinkedList<Cliente>());
				Programa.setFile(new File(ENTRADA + numDatos + "Usuarios.txt"));
				try {
					Programa.leerArchivo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<Cliente> clientes = Programa.getClientes();
				
				
				times.clear();
				for(int i = 0; i < 10; i++){
					paretoNube = new ParetoNube((ArrayList<Cliente>) clientes.clone());
					times.add(iteracion(pw, paretoNube));
					pw.println("--------------------------------------------------------");
				}
				pw.println("\t  Media: " + media(times) + "\n");
				
				pw.println("\tDivision:\n");
				
				times.clear();
				for(int i = 0; i < 10; i++){
					paretoDivision = new ParetoDivision((ArrayList<Cliente>) clientes.clone());
					times.add(iteracion(pw, paretoDivision));
					pw.println("--------------------------------------------------------");
				}
				pw.println("\t  Media: " + media(times) + "\n");
				
				numDatos += 10000;
				
			}while(numDatos <= 100000);
			pw.close();
		} catch (IOException e) {
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
		media += times.poll();
		media += times.poll();
		return media/8;
	}

	private static long iteracion(PrintWriter pw, Pareto pareto){
		Collection<Cliente> resultadoParcial = new LinkedList<Cliente>();
		LinkedList<Cliente> resultado = new LinkedList<Cliente>();
		
		long a = System.nanoTime();
		long b, c;
		resultadoParcial = pareto.paretoSolucion();
		b = System.nanoTime();
		resultado.addAll(resultadoParcial);
		pw.println("\t\tIteración: " + (b - a) + "\n");
		while (resultado.size() < Programa.getuPC()) {
			pareto.removeAll(resultadoParcial);
			b = System.nanoTime();
			resultadoParcial = pareto.paretoSolucion();
			c = System.nanoTime();
			pw.println("\t\tIteración: " + (c - b) + "\n");
			resultado.addAll(resultadoParcial);
		}
		b = System.nanoTime();
		pw.println("\t\tTiempo total: " + (b - a) + "\n");
		return (b - a);
	}

}
