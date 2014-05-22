package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import programa.Programa;

/**
 * Clase pensada para crear archivos de texto para ejecutar las pruebas de rendimiento.
 *
 */
public class GenerarArchivo {

	/**
	 * Tamaño del archivo.
	 */
	static int tamano;
	/**
	 * Scanner para leer entradas por teclado.
	 */
	static Scanner scanner;
	/**
	 * Rago de viviendas para calles.
	 */
	static int rangoI;
	/**
	 * Rago de viviendas para avenidas.
	 */
	static int rangoJ;

	/**
	 * Método principal del programa para generar un archivo.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean correcto = false;
		try {
			while(!correcto){
				scanner = new Scanner(System.in);
				System.out.println("Nº de usuarios: ");
				tamano = scanner.nextInt();
				System.out.println("Rango calles: ");
				rangoI = scanner.nextInt();
				System.out.println("Rango avenidas: ");
				rangoJ = scanner.nextInt();
				if(tamano < 0 || tamano > 330) System.out.println("Fallo");
				else correcto = true;
			}
			
			File archivo = new File(Programa.ENTRADA + tamano + "Usuarios.txt");
			FileWriter  fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			if(rangoI*rangoJ < tamano){
				System.out.println("Viviendas insuficientes.");
				System.exit(0);
			}
			boolean[][] ocupado = new boolean[rangoI][rangoJ];
			for(int i = 0; i < ocupado.length; i++) for(int j = 0; j < ocupado[i].length; j++) ocupado[i][j] = false;
			pw.println(tamano);
			
			for(int i = -1; i < tamano; i++){
				int a,b,c;
				a = (int)Math.floor(Math.random()*rangoI);
				b = (int)Math.floor(Math.random()*rangoJ);
				c = (int) Math.floor(Math.random() * 91 + 30);
				if(c%15 != 0) c += (15 - c%15);
				pw.println(i + "," + c + "," + a + "," + b);
			}
			
			System.out.println("Fin");
			
			pw.close();
			bw.close();
			fw.close();

		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
