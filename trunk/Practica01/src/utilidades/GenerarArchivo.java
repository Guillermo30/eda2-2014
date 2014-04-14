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
				if(tamano < 0 || tamano > 150000) System.out.println("Fallo");
				else correcto = true;
			}
			
			File archivo = new File(Programa.ENTRADA + tamano + "Usuarios.txt");
			FileWriter  fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			double toAdd = 10000.0/tamano;
			
			pw.println(tamano);
			double ice = toAdd;
			
			for(int i = 0; i < tamano; i++){
				pw.println((int)Math.floor(ice) + "," + generarCE());
				ice += toAdd;
			}
			
			System.out.println("Fin");
			
			pw.close();
			bw.close();
			fw.close();

		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	/**
	 * Método que genera un CE aleatorio.
	 * @return
	 */
	private static int generarCE(){
		return (int) Math.ceil(Math.random()*800);
	}

}
