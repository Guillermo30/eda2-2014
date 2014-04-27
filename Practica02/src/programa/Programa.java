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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import exceptions.*;
import paretoDivision.ParetoDivision;
import paretoNube.ParetoNube;
import posSeleccion.PosSeleccionV1;
import posSeleccion.PosSeleccionV2;
import utilidades.Cliente;
import utilidades.Pareto;

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
	private static LinkedList<Cliente> candidatos;
	/**
	 * Estructura de datos con el informe de clientes sospechosos tras la post-selección.
	 */
	private static LinkedList<Cliente> posCandidatos;
	/**
	 * Estructura de datos con el informe de clientes de entrada.
	 */
	private static ArrayList<Cliente> clientes;

	/**
	 * Cantidad correspondiente al 1% de los datos.
	 */
	private static int uPC;
	/**
	 * Scanner para leer entradas por teclado.
	 */
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * Objeto que incluye los métodos de ejecución del algoritmo principal.
	 */
	private static Pareto pareto;
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
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {
			System.out
					.println("Nombre del fichero (no hace falta poner la extensión de archivo): ");
			String fileName = scanner.nextLine();
			file = new File(ENTRADA + fileName + ".txt");
			if (!file.exists())
				throw new FileNotFoundException();
			datosCorruptos = new LinkedList<String>();
			candidatos = new LinkedList<Cliente>();
			clientes = new ArrayList<Cliente>();
			int version;
			System.out.println("Version? ");
			version = scanner.nextInt();
			leerArchivo();
			if(version == 1) pareto = new ParetoNube((ArrayList<Cliente>) clientes.clone());
			if(version == 2) pareto = new ParetoDivision((ArrayList<Cliente>) clientes.clone());
			long a = System.nanoTime();
			long b;
			Collection<Cliente> paretoTemp = pareto.paretoSolucion();
			candidatos.addAll(paretoTemp);
			System.out.println("Tiempo pareto: " + (System.nanoTime() - a));
			while (candidatos.size() < uPC) {
				b = System.nanoTime();
				pareto.removeAll(paretoTemp);
				System.out.println("Tiempo eliminación: "
						+ (System.nanoTime() - b));
				b = System.nanoTime();
				paretoTemp = pareto.paretoSolucion();
				System.out.println("Tiempo pareto: " + (System.nanoTime() - b));
				candidatos.addAll(paretoTemp);
			}
			System.out.println("Tiempo total: " + (System.nanoTime() - a));
			System.out.println(candidatos.size());
			
			int mitadUPC = uPC/2 + (uPC%2); 
			PosSeleccionV2 ps = new PosSeleccionV2(clientes, candidatos, mitadUPC );
			ps.mediaOchoCercanos(1);
			posCandidatos = ps.seleccionar();
			System.out.println(posCandidatos.toString());
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

			pw.println("Clientes sospechosos:\n\n");
			Iterator<Cliente> it = candidatos.iterator();
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
		if (nClientes <= 0 || nClientes > 150000)
			throw new HeaderOutOfRangeException(ERROR_CABECERA_FUERA_DE_RANGO);
		
		int i = 0, j = 0;
		int ice, prevIce = Integer.MIN_VALUE, ce;
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

				ice = sc.nextInt();
				if(ice < prevIce) throw new NotSortException(ERROR_DATO_NO_ORDENADO);
				ce = sc.nextInt();
				if (ice <= 0 || ce <= 0)
					throw new NegativeNumberException(ERROR_DATO_NEGATIVO);
				if(ice > 10000)
					throw new IceOutOfRangeException(ERROR_ICE_FUERA_DE_RANGO);

				toAdd = new Cliente(i, ce, ice);
				toAdd.setIndexOnNube(j);
				clientes.add(toAdd);
				j++;
				i++;
				prevIce = ice;
				linea = br.readLine();
				
			} catch (Exception e) {
				if(e.getMessage() == null)
					datosCorruptos.add("Id: " + i + " Datos: " + linea
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
		uPC = ((nClientes) % 100 == 0) ? (nClientes) / 100
				: ((nClientes) / 100) + 1;
		br.close();
		fr.close();
	}

	//Getters y setters
	
	public static LinkedList<Cliente> getCandidatos() {
		return candidatos;
	}

	public static void setCandidatos(LinkedList<Cliente> candidatos) {
		Programa.candidatos = candidatos;
	}

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<Cliente> clientes) {
		Programa.clientes = clientes;
	}

	public static void setFile(File entrada) {
		Programa.file = entrada;
	}

	public static void setDatosCorruptos(LinkedList<String> datosCorruptos) {
		Programa.datosCorruptos = datosCorruptos;
	}
	
	public static LinkedList<String> getDatosCorruptos() {
		return datosCorruptos;
	}

	public static int getuPC() {
		return uPC;
	}

}
