package programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.TallerAlredyIncludeException;
import exceptions.TimeOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import exceptions.NegativeNumberException;
import utilidades.BacktrackingV1;
import utilidades.Cliente;
import utilidades.Ruta;

public class Programa {

	//Directorios de entrada y de salida de los ficheros.
		public final static String ENTRADA = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "entradas"
				+ File.separator;
		public final static String SALIDA = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "salidas"
				+ File.separator;
		
		/**
		 * Estructura de datos con el informe de clientes cuyos datos estan corruptos.
		 */
		private static LinkedList<String> datosCorruptos;
		
		/**
		 * Estructura de datos con el informe de clientes de entrada.
		 */
		private static ArrayList<Cliente> entry;
		
		/**
		 * Estructura de datos de salida.
		 */
		private static ArrayList<Ruta> out;
		
		/**
		 * Scanner para leer entradas por teclado.
		 */
		private static Scanner scanner = new Scanner(System.in);
		/**
		 * Archivo de entrada
		 */
		private static File file;
		
		/**
		 * Cliente dummie que indica el taller.
		 */
		private static Cliente taller;
		
		//Mensajes de error
		public final static String ERROR_ARCHIVO_INEXISTENTE = "El archivo seleccionado no existe.";
		public final static String ERROR_ARCHIVO_VACIO = "El archivo seleccionado esta vacio.";
		public final static String ERROR_FORMATO_CABECERA = "El archivo seleccionado no contiene una cabecera con un entero.";
		public final static String ERROR_DATOS_NO_COINCIDEN_CON_CABECERA = "El numero de clientes del archivo no coincide con el de la cabecera.";
		public final static String ERROR_DATO_NO_ORDENADO = "El dato no sigue un orden por ICE ascendente con respecto al dato anterior.";
		public final static String ERROR_DATO_NEGATIVO = "Algun dato es un entero negativo.";
		public final static String ERROR_CABECERA_FUERA_DE_RANGO = "El numero de datos se encuentra fuera de rango.";
		public final static String ERROR_TIEMPO_FUERA_DE_RANGO = "El tiempo del dato es demasiado alto.";
		public static final String ERROR_TALLER = "El taller ya ha sido incluido";
		
		/**
		 * Metodo de ejecución principal.
		 * @param args
		 */
		public static void main(String[] args) {

			try {
				System.out
						.println("Nombre del fichero (no hace falta poner la extensión de archivo): ");
				String fileName = scanner.nextLine();
				file = new File(ENTRADA + fileName + ".txt");
				if (!file.exists())
					throw new FileNotFoundException();
				
				datosCorruptos = new LinkedList<String>();
				entry = new ArrayList<Cliente>();
				out = new ArrayList<Ruta>();
				leerArchivo();
				
				int version;
				System.out.println("Version del algoritmo?");
				version = scanner.nextInt();
				
				if(version == 1 ){
					BacktrackingV1 bc = new BacktrackingV1(entry, 405, taller.getVivienda().x, taller.getVivienda().y);
					out = bc.solucion();
				}
				
				imprimirInforme();
				
			} catch (FileNotFoundException e) {
				System.out.println(ERROR_ARCHIVO_INEXISTENTE);
			} catch (EmptyFileException e) {
				System.out.println(ERROR_ARCHIVO_VACIO);
			} catch (NumberFormatException e) {
				System.out.println(ERROR_FORMATO_CABECERA);
			} catch (HeaderOutOfRangeException e) {
				System.out.println(ERROR_CABECERA_FUERA_DE_RANGO);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LinesNotEqualsHeaderException e) {
				System.out.println(ERROR_DATOS_NO_COINCIDEN_CON_CABECERA);
			} catch (TallerAlredyIncludeException e) {
				System.out.println(ERROR_TALLER);
			}
		}
		
		/**
		 * Método que genera un archivo con las salidas del programa.
		 */
		private static void imprimirInforme() {
			try {
				FileWriter fw = new FileWriter(new File(SALIDA + "Salida-"
						+ file.getName()));
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(fw);

				pw.println("Rutas:\n\n");
				Iterator<Ruta> it = out.iterator();
				int i = 1;
				while (it.hasNext())
					pw.println(i +": " + it.next().toString());
				pw.println("\nClientes con datos corruptos:\n\n");
				Iterator<String> it2 = datosCorruptos.iterator();
				while (it2.hasNext())
					pw.println(it2.next());
				pw.close();
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Método encargado de leer los datos del archivo de entrada.
		 * @throws EmptyFileException
		 * @throws NumberFormatException
		 * @throws IOException
		 * @throws LinesNotEqualsHeaderException
		 * @throws HeaderOutOfRangeException
		 * @throws TallerAlredyIncludeException 
		 */
		@SuppressWarnings({ "resource", "unused" })
		public static void leerArchivo() throws EmptyFileException,
				NumberFormatException, IOException,
				LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			if (linea == null)
				throw new EmptyFileException(ERROR_ARCHIVO_VACIO);
			linea = linea.replaceAll(" ", "");
			while (linea.equals("")) {
				linea = linea.replaceAll(" ", "");
				linea = br.readLine();
			}
			if (linea == null)
				throw new EmptyFileException(ERROR_ARCHIVO_VACIO);
			int nClientes = Integer.parseInt(linea);
			if (nClientes <= 0 || nClientes > 330)
				throw new HeaderOutOfRangeException(ERROR_CABECERA_FUERA_DE_RANGO);
			
			int i = 0;
			int id, tiempo, a, b;
			Scanner sc;
			linea = br.readLine();
			Cliente toAdd = null;

			while (linea != null) {
				try {
					linea = linea.replaceAll(" ", "");
					if (linea.equals("")) {
						linea = br.readLine();
						continue;
					}
					sc = new Scanner(linea);
					sc.useDelimiter(",");

					id = sc.nextInt();
					tiempo = sc.nextInt();
					a = sc.nextInt();
					b = sc.nextInt();
					if (tiempo <= 0 || a < 0 || b < 0)
						throw new NegativeNumberException(ERROR_DATO_NEGATIVO);
					if(tiempo > 120)
						throw new TimeOutOfRangeException(ERROR_TIEMPO_FUERA_DE_RANGO);
					if(id == -1){
						if(taller != null) throw new TallerAlredyIncludeException(ERROR_TALLER);
						taller = new Cliente(id, tiempo, a, b);
					}
					else{
					      toAdd = new Cliente(id, tiempo, a, b);
					      entry.add(toAdd);
					     }
					i++;
					linea = br.readLine();
				} catch(TallerAlredyIncludeException e){
					throw e;
				} catch (Exception e) {
					if(e.getMessage() == null)
						datosCorruptos.add("Cliente en fichero nº: " + i + " Datos: " + linea
								+ " (No ha sido posible parsear los datos a enteros.)");
					else
						datosCorruptos.add("Cliente en fichero nº: " + i + " Datos: " + linea
							+ " (" + e.getMessage() + ")");
					i++;
					linea = br.readLine();
					continue;
				}
			}
			if (i != nClientes + 1)
				throw new LinesNotEqualsHeaderException(
						ERROR_DATOS_NO_COINCIDEN_CON_CABECERA);
			br.close();
			fr.close();
		}

		/**
		 * @return the datosCorruptos
		 */
		public static LinkedList<String> getDatosCorruptos() {
			return datosCorruptos;
		}

		/**
		 * @param datosCorruptos the datosCorruptos to set
		 */
		public static void setDatosCorruptos(LinkedList<String> datosCorruptos) {
			Programa.datosCorruptos = datosCorruptos;
		}

		/**
		 * @return the entry
		 */
		public static ArrayList<Cliente> getEntry() {
			return entry;
		}

		/**
		 * @param entry the entry to set
		 */
		public static void setEntry(ArrayList<Cliente> entry) {
			Programa.entry = entry;
		}

		/**
		 * @return the out
		 */
		public static ArrayList<Ruta> getOut() {
			return out;
		}

		/**
		 * @param out the out to set
		 */
		public static void setOut(ArrayList<Ruta> out) {
			Programa.out = out;
		}

		/**
		 * @return the file
		 */
		public static File getFile() {
			return file;
		}

		/**
		 * @param file the file to set
		 */
		public static void setFile(File file) {
			Programa.file = file;
		}

		/**
		 * @return the taller
		 */
		public static Cliente getTaller() {
			return taller;
		}

		/**
		 * @param taller the taller to set
		 */
		public static void setTaller(Cliente taller) {
			Programa.taller = taller;
		}
		
}
