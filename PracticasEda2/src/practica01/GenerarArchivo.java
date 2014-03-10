package practica01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GenerarArchivo {

	static int tamano;
	static Scanner scanner;
	static String ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "practica01" + File.separator;

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
			
			File archivo = new File(ruta + tamano + "Usuarios.txt");
			FileWriter  fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(tamano);
			int ice, ce;
			
			for(int i = 0; i < tamano; i++){
				
			}

		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
