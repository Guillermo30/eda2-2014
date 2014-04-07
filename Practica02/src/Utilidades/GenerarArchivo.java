package Utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import Programa.Programa;

public class GenerarArchivo {

	static int tamano;
	static Scanner scanner;

	public static void main(String[] args) {
		boolean correcto = false;
		try {
			while(!correcto){
				scanner = new Scanner(System.in);
				System.out.println("N� de usuarios: ");
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
	
	private static int generarCE(){
		return (int) Math.ceil(Math.random()*800);
	}

}
