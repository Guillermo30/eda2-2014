package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
			+ File.separator + "Benchmark_Pareto_Voraz";

	public static void main(String[] args) {
		int numDatos = 1000;
		Pareto paretoNube;
		Pareto paretoDivision;
		File out = new File(SALIDA);
		Collection<Cliente> resultadoParcial = new LinkedList<Cliente>();
		LinkedList<Cliente> resultado = new LinkedList<Cliente>();
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				pw.println("Datos: " + numDatos +"\n\tNube:\n");
				Programa.setClientes(new ArrayList<Cliente>());
				Programa.setCandidatos(new LinkedList<Cliente>());
				Programa.setFile(new File(ENTRADA + numDatos + "Usuarios.txt"));
				try {
					Programa.leerArchivo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				paretoNube = new ParetoNube(Programa.getClientes());
				paretoDivision = new ParetoDivision(Programa.getClientes());
				
				long a = System.nanoTime();
				long b, c;
				resultadoParcial = paretoNube.paretoSolucion();
				b = System.nanoTime();
				resultado.addAll(resultadoParcial);
				pw.println("\t\tIteración: " + (b - a) + "\n");
				while (resultado.size() < Programa.getuPC()) {
					paretoNube.removeAll(resultadoParcial);
					b = System.nanoTime();
					resultadoParcial = paretoNube.paretoSolucion();
					c = System.nanoTime();
					pw.println("\t\tIteración: " + (c - b) + "\n");
					resultado.addAll(resultadoParcial);
				}
				b = System.nanoTime();
				pw.println("\t\tTiempo total: " + (b - a) + "\n");
				
				pw.println("\tDivisión:\n");
				
				a = System.nanoTime();
				resultadoParcial = paretoDivision.paretoSolucion();
				b = System.nanoTime();
				resultado.addAll(resultadoParcial);
				pw.println("\t\tIteración: " + (b - a) + "\n");
				while (resultado.size() < Programa.getuPC()) {
					paretoDivision.removeAll(resultadoParcial);
					b = System.nanoTime();
					resultadoParcial = paretoDivision.paretoSolucion();
					c = System.nanoTime();
					pw.println("\t\tIteración: " + (c - b) + "\n");
					resultado.addAll(resultadoParcial);
				}
				b = System.nanoTime();
				pw.println("\t\tTiempo total: " + (b - a) + "\n");
				
				numDatos += 1000;
				
			}while(numDatos <= 10000);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
