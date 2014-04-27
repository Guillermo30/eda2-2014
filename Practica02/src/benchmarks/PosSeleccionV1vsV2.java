package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import paretoDivision.ParetoDivision;
import paretoNube.ParetoNube;
import posSeleccion.*;
import programa.Programa;
import utilidades.Cliente;
import utilidades.Pareto;

public class PosSeleccionV1vsV2 {
	public final static String ENTRADA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "entradas"
			+ File.separator;
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "salidas"
			+ File.separator + "Benchmark_PosSeleccion.txt";

	/**
	 * Método que ejecuta este benchmark
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int numDatos = 10000;
		Programa.setClientes(new ArrayList<Cliente>());
		Programa.setFile(new File(ENTRADA + numDatos + "Usuarios.txt"));
		try {
			Programa.leerArchivo();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LinesNotEqualsHeaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeaderOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PosSeleccionV1 pos1 = new PosSeleccionV1 (Programa.getClientes(),null,0);
		PosSeleccionV2 pos2 = new PosSeleccionV2 (Programa.getClientes(),null,0);
		long[] s1 = new long[1000];
		long[] s2 = new long[1000];
		long t1 = 0;
		long t2 = 0;
		for(int i =0 ; i<1000;i++){
			t1 =System.nanoTime();
			pos1.mediaOchoCercanos(5);
			s1[i] = System.nanoTime()-t1;
			t2 = System.nanoTime();
			pos2.mediaOchoCercanos(5);
			s2[i] = System.nanoTime()-t2;
		}
		t1 = 0;
		t2 = 0;
		for(int i =2; i<998;i++){
			t1 += s1[i];
			t2 += s2[i];
		}
		System.out.println("V1= "+t1);
		System.out.println("V2= "+t2);
		//añadido para resubir
	}
}
