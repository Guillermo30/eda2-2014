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

import exceptions.*;
import utilidades.Cliente;
import utilidades.Mochila;

/**
 * Clase usada para cargar y leer los datos de un archivo y procesarlos mediante el algoritmo.
 */
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
	 * Estructura de datos con el informe de clientes sospechosos.
	 */
	private static LinkedList<Cliente> elegidos;
	/**
	 * Estructura de datos con el informe de clientes de entrada.
	 */
	private static ArrayList<Cliente> fraudes;

	/**
	 * Tamaño en minutos de cada bloque.
	 */
	private static int tamanoBloque;
	/**
	 * Scanner para leer entradas por teclado.
	 */
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * Archivo de entrada
	 */
	private static File file;
	
	//Mensajes de error
	public final static String ERROR_ARCHIVO_INEXISTENTE = "El archivo seleccionado no existe.";
	public final static String ERROR_ARCHIVO_VACIO = "El archivo seleccionado esta vacio.";
	public final static String ERROR_FORMATO_CABECERA = "El archivo seleccionado no contiene una cabecera con un entero.";
	public final static String ERROR_DATOS_NO_COINCIDEN_CON_CABECERA = "El numero de clientes del archivo no coincide con el de la cabecera.";
	public final static String ERROR_DATO_NO_ORDENADO = "El dato no sigue un orden por ICE ascendente con respecto al dato anterior.";
	public final static String ERROR_DATO_NEGATIVO = "Algun dato es un entero negativo.";
	public final static String ERROR_CABECERA_FUERA_DE_RANGO = "El numero de datos se encuentra fuera de rango.";
	public final static String ERROR_ICE_FUERA_DE_RANGO = "El ICE del dato es demasiado alto.";

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
			fraudes = new ArrayList<Cliente>();
			
			int version;
			System.out.println("Version del algoritmo?");
			version = scanner.nextInt();
			
			Mochila mochila = null;
			
			if(version == 1){
				leerArchivo();
				tamanoBloque = 15;
				mochila = new Mochila(fraudes, tamanoBloque);
			}
			if(version == 2){
				leerArchivo();
				tamanoBloque = bloqueOptimo();
				mochila = new Mochila(fraudes, tamanoBloque);
			}
			if(version == 3){
				if(leerArchivo2()){
					System.out.println("Dado el número de clientes o el tiempo total que implica la inspección de todos ellos,"
							+ " se pueden inspeccionar todos los clientes sospechos.");
					System.exit(0);
				}
				tamanoBloque = bloqueOptimo();
				mochila = new Mochila(fraudes, tamanoBloque);
			}
			
			elegidos = mochila.maxBeneficio();
			System.out.println(elegidos.size());
			System.out.println("El programa se ejecuto correctamente.");
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

			pw.println("Clientes a inspeccionar:\n\n");
			Iterator<Cliente> it = elegidos.iterator();
			while (it.hasNext())
				pw.println(it.next().toString());
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
	 */
	@SuppressWarnings({ "resource", "unused" })
	public static void leerArchivo() throws EmptyFileException,
			NumberFormatException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
		if (nClientes <= 0 || nClientes > 750)
			throw new HeaderOutOfRangeException(ERROR_CABECERA_FUERA_DE_RANGO);
		
		int i = 0;
		int ice, prevIce = Integer.MIN_VALUE, beneficio, id;
		Scanner sc;
		linea = br.readLine();
		Cliente toAdd;

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
				ice = sc.nextInt();
				if(ice < prevIce) throw new NotSortException(ERROR_DATO_NO_ORDENADO);
				beneficio = sc.nextInt();
				if (ice <= 0 || beneficio <= 0)
					throw new NegativeNumberException(ERROR_DATO_NEGATIVO);
				if(ice > 10000)
					throw new IceOutOfRangeException(ERROR_ICE_FUERA_DE_RANGO);

				toAdd = new Cliente(id, ice, beneficio);
				fraudes.add(toAdd);
				i++;
				prevIce = ice;
				linea = br.readLine();
				
			} catch (Exception e) {
				if(e.getMessage() == null)
					datosCorruptos.add("Cliente en fichero nº: " + i + " Datos: " + linea
							+ " (No ha sido posible parsear los datos a enteros.)");
				else
					datosCorruptos.add("Id: " + i + " Datos: " + linea
						+ " (" + e.getMessage() + ")");
				i++;
				linea = br.readLine();
				continue;
			}
		}
		if (i != nClientes)
			throw new LinesNotEqualsHeaderException(
					ERROR_DATOS_NO_COINCIDEN_CON_CABECERA);
		br.close();
		fr.close();
	}
	
	/**
	 * Método encargado de leer los datos del archivo de entrada y devuelve true si no hace falta ejecutar la mochila o false si hace falta..
	 * @throws EmptyFileException
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws LinesNotEqualsHeaderException
	 * @throws HeaderOutOfRangeException
	 */
	@SuppressWarnings({ "resource", "unused" })
	public static boolean leerArchivo2() throws EmptyFileException,
			NumberFormatException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
		if (nClientes <= 0 || nClientes > 750)
			throw new HeaderOutOfRangeException(ERROR_CABECERA_FUERA_DE_RANGO);
		
		if(nClientes <= 82){
			br.close();
			fr.close();
			return true;
		}
		
		int tiempoTotal = 0;
		int i = 0;
		int ice, prevIce = Integer.MIN_VALUE, beneficio, id;
		Scanner sc;
		linea = br.readLine();
		Cliente toAdd;

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
				ice = sc.nextInt();
				if(ice < prevIce) throw new NotSortException(ERROR_DATO_NO_ORDENADO);
				beneficio = sc.nextInt();
				if (ice <= 0 || beneficio <= 0)
					throw new NegativeNumberException(ERROR_DATO_NEGATIVO);
				if(ice > 10000)
					throw new IceOutOfRangeException(ERROR_ICE_FUERA_DE_RANGO);

				toAdd = new Cliente(id, ice, beneficio);
				fraudes.add(toAdd);
				tiempoTotal += toAdd.getTiempo();
				i++;
				prevIce = ice;
				linea = br.readLine();
				
			} catch (Exception e) {
				if(e.getMessage() == null)
					datosCorruptos.add("Cliente en fichero nº: " + i + " Datos: " + linea
							+ " (No ha sido posible parsear los datos a enteros.)");
				else
					datosCorruptos.add("Id: " + i + " Datos: " + linea
						+ " (" + e.getMessage() + ")");
				i++;
				linea = br.readLine();
				continue;
			}
		}
		if (i != nClientes)
			throw new LinesNotEqualsHeaderException(
					ERROR_DATOS_NO_COINCIDEN_CON_CABECERA);
		br.close();
		fr.close();
		if(tiempoTotal <= 9900) return true;
		return false;
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
	 * @return the elegidos
	 */
	public static LinkedList<Cliente> getElegidos() {
		return elegidos;
	}

	/**
	 * @param elegidos the elegidos to set
	 */
	public static void setElegidos(LinkedList<Cliente> elegidos) {
		Programa.elegidos = elegidos;
	}

	/**
	 * @return the fraudes
	 */
	public static ArrayList<Cliente> getFraudes() {
		return fraudes;
	}

	/**
	 * @param fraudes the fraudes to set
	 */
	public static void setFraudes(ArrayList<Cliente> fraudes) {
		Programa.fraudes = fraudes;
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
	 * @return the tamanobloque
	 */
	public static int getTamanoBloque() {
		return tamanoBloque;
	}
	
	public static void setTamanoBolque(int tBloque){
		tamanoBloque = tBloque;
	}
	
	public static int bloqueOptimo(){
		LinkedList<Integer> mcd = new LinkedList<Integer>();
		mcd.add(18);
		mcd.add(20);
		mcd.add(25);
		mcd.add(30);
		mcd.add(45);
		mcd.add(60);
		mcd.add(75);
		mcd.add(110);
		Iterator<Integer> it;
		Cliente cur;
		for (int i = 0; i < fraudes.size(); i++){
			cur = fraudes.get(i);
			it = mcd.iterator();
			while(it.hasNext()) if(cur.getTiempo()%it.next() != 0) it.remove();
			if(mcd.isEmpty()) return 15;
		}
		return mcd.getLast();
	}

}
