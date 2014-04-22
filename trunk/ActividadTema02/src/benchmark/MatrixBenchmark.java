package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;

import multiplicacionDeMatrices.Multiplicacion;
import utilidades.SquareMatrix;

public class MatrixBenchmark {
	
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_Pareto_Voraz.txt";
	
	public static void main(String[] args){
		SquareMatrix m, o;
		m = SquareMatrix.generarMatrizAleatoria(2, 100);
		o = SquareMatrix.generarMatrizAleatoria(2, 100);
		SquareMatrix z = Multiplicacion.fBruta(m, o);
		SquareMatrix x = Multiplicacion.dyV(m, o);
		SquareMatrix c = Multiplicacion.strassen(m, o);
		int exp = 1;
		int n;
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			do{
				n = (int) Math.pow(2, exp);
				m = SquareMatrix.generarMatrizAleatoria(n, 10);
				o = SquareMatrix.generarMatrizAleatoria(n, 10);
				pw.println("Matriz de " + n + "x" + n + "\n");
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b = System.nanoTime();
					Multiplicacion.fBruta(m, o);
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("\tFuerza bruta: " + media(times) + "\n");
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b = System.nanoTime();
					Multiplicacion.dyV(m, o);
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("\tDivide y vencerás: " + media(times) + "\n");
				times.clear();
				for(int i = 0; i < 10; i++){
					long a, b = System.nanoTime();
					Multiplicacion.strassen(m, o);
					a = System.nanoTime();
					times.add(a - b);
				}
				pw.println("\tStrassen: " + media(times) + "\n\n---------------------------------------------------------------\n");
				exp += 1;
			}while(exp <= 13);
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
