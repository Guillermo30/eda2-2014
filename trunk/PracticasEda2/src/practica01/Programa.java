package practica01;

import java.io.File;
import java.io.IOException;

public class Programa {
	public static void main (String[] args) throws NumberFormatException, IOException{
		String FILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "practica01" + File.separator + "datos_eda_2.txt";
		Utilidades.solucion(FILE);
	}
}
