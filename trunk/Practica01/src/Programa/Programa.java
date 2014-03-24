package Programa;

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

import Utilidades.Cliente;
import Utilidades.Pareto;
import Version01.ParetoV1;
import Version02.ParetoV2;
import Version03.ParetoV3;
import Version04.ParetoV4;
import estructurasdedatos.AVLTree;
import Execptions.*;

public class Programa {

	public final static String ENTRADA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Entradas"
			+ File.separator;
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Salidas"
			+ File.separator;

	private static LinkedList<String> datosCorruptos;
	private static LinkedList<Cliente> candidatos;
	private static Collection<Cliente> clientes;

	public static LinkedList<String> getDatosCorruptos() {
		return datosCorruptos;
	}

	private static int uPC;
	private static Scanner scanner = new Scanner(System.in);
	private static Pareto pareto;
	private static File file;

	final static String ERROR_ARCHIVO_INEXISTENTE = "El archivo seleccionado no existe.";
	final static String ERROR_ARCHIVO_VACIO = "El archivo seleccionado esta vacio.";
	final static String ERROR_FORMATO_CABECERA = "El archivo seleccionado no contiene una cabecera con un entero.";
	final static String ERROR_DATOS_NO_COINCIDEN_CON_CABECERA = "El numero de clientes del archivo no coincide con el de la cabecera.";
	final static String ERROR_DATO_NO_ORDENADO = "El dato no sigue un orden por ICE ascendente con respecto al dato anterior.";
	final static String ERROR_DATO_NEGATIVO = "Algun dato es un entero negativo.";
	final static String ERROR_CABECERA_FUERA_DE_RANGO = "El numero de datos se encuentra fuera de rango.";
	final static String ERROR_ICE_FUERA_DE_RANGO = "El ICE del dato es demasiado alto.";

	public static void main(String[] args) {
		// try {

		try {
			System.out
					.println("Nombre del fichero (no hace falta poner la extensión de archivo): ");
			String fileName = scanner.nextLine();
			file = new File(ENTRADA + fileName + ".txt");
			if (!file.exists())
				throw new FileNotFoundException();
			datosCorruptos = new LinkedList<String>();
			candidatos = new LinkedList<Cliente>();
			int version;
			System.out.println("Version? ");
			version = scanner.nextInt();
			if (version == 1) {
				clientes = new LinkedList<Cliente>();
				leerArchivo();
				pareto = new ParetoV1(clientes);
			}
			if (version == 2) {
				clientes = new LinkedList<Cliente>();
				leerArchivo();
				pareto = new ParetoV2(clientes);
			}
			if (version == 3) {
				clientes = new ArrayList<Cliente>();
				leerArchivo();
				pareto = new ParetoV3(clientes);
			}
			if (version == 4) {
				clientes = new ArrayList<Cliente>();
				leerArchivo();
				pareto = new ParetoV4(clientes);
			}
			if (version != 4) {
				System.out.println("No existe esa version de algoritmo.");
				System.exit(0);
			}
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
		if (nClientes <= 0 || nClientes > 10000)
			throw new HeaderOutOfRangeException(ERROR_CABECERA_FUERA_DE_RANGO);
		
		int i = 0;
		int ice, prevIce = Integer.MIN_VALUE, ce;
		Scanner sc;
		linea = br.readLine();

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

				clientes.add(new Cliente(i, ce, ice));
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

	public static LinkedList<Cliente> getCandidatos() {
		return candidatos;
	}

	public static void setCandidatos(LinkedList<Cliente> candidatos) {
		Programa.candidatos = candidatos;
	}

	public static Collection<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(Collection<Cliente> clientes) {
		Programa.clientes = clientes;
	}

	public static void setFile(File entrada) {
		Programa.file = entrada;
	}

	public static void setDatosCorruptos(LinkedList<String> datosCorruptos) {
		Programa.datosCorruptos = datosCorruptos;
	}

	/*
	 * private static LinkedList<Cliente> leerArchivo() throws
	 * NumberFormatException, IOException{ LinkedList<Cliente> struc= new
	 * LinkedList<Cliente>(); int numDePuntos=0; FileReader fr = new FileReader
	 * (ARCHIVO); BufferedReader br = new BufferedReader(fr); numDePuntos =
	 * Integer.parseInt(br.readLine());
	 * 
	 * int ce; int ice; String linea = ""; Scanner sc; int i = 0;
	 * 
	 * for(; i< numDePuntos; i++){ try{ linea = br.readLine(); linea =
	 * linea.replaceAll(" ", ""); sc = new Scanner (linea);
	 * sc.useDelimiter(",");
	 * 
	 * ice = sc.nextInt(); ce = sc.nextInt();
	 * 
	 * struc.add(new Cliente(i, ce, ice)); }catch(Exception e){
	 * datosCorruptos.add("Id: " + i + " Datos: " + linea); } } uPC = ((i+1)%100
	 * == 0)? (i+1)/100 : ((i+1)/100) + 1; return struc; }
	 */

}
