package algoritmo;

import java.util.ArrayList;

import utilidades.PalabraProbabilidad;
import utilidades.SimpleABB;

public class ABBOptimo {
	
	private static int[][]result;
	private static double[][] c;
	private static double[][] m;
	private static ArrayList<PalabraProbabilidad> dats;
	
	public static void abbOpt(ArrayList<PalabraProbabilidad> datos){
		dats = datos;
		int size = datos.size();
		double aux, min;
		result = new int[size + 1][size + 1];
		c = new double[size + 1][size + 1];
		m = new double[size + 1][size + 1];
		
		for(int i = 0; i <= size; i++){
			c[i][i] = 0;
			m[i][i] = 0;
			for(int j = i + 1; j <= size; j++)
				m[i][j] = m[i][j - 1] + datos.get(j - 1).probabilidad;
		}
		for(int i = 1; i <= size; i++){
			c[i - 1][i] = m[i - 1][i];
			result[i - 1][i] = i;
		}
		for(int d = 2; d <= size; d++)
			for(int j = d; j <= size; j++){
				int i = j - d;
				min = Double.POSITIVE_INFINITY;
				for(int k = i + 1; k <= j; k++){
					aux = c[i][k - 1] + c[k][j];
					if(aux < min){
						min = aux;
						result[i][j] = k;
					}
				}
				c[i][j] = m[i][j] + min;
			}
		/*for(int i = 0;i < m.length; i++)for(int j = 0;j < m[i].length; j++){
			System.out.print(m[i][j] + "\t");
			if( j == m[0].length - 1)System.out.print("\n");
		}
		System.out.println("\n");
		for(int i = 0;i < c.length; i++)for(int j = 0;j < c[i].length; j++){
			System.out.print(c[i][j] + "\t");
			if( j == c[0].length - 1)System.out.print("\n");
		}
		System.out.println("\n");
		for(int i = 0; i < result.length; i++)for(int j = 0;j < result[i].length; j++){
			System.out.print(result[i][j] + "\t");
			if( j == result[0].length - 1)System.out.print("\n");
		}*/
	}
	
	public static SimpleABB<PalabraProbabilidad> obtenerArbol(){
		SimpleABB<PalabraProbabilidad> toReturn = new SimpleABB<PalabraProbabilidad>();
		obtenerArbol(toReturn, 0, dats.size());
		return toReturn;
	}

	private static void obtenerArbol(SimpleABB<PalabraProbabilidad> arbol,
			int i, int j) {
		int next = result[i][j];
		if(next == 0) return;
		arbol.add(dats.get(next - 1));
		obtenerArbol(arbol, i, next - 1);
		obtenerArbol(arbol, next, j);
	}
	
}
