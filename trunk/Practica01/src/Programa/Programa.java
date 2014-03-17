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

	final static String ENTRADA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Entradas"
			+ File.separator;
	final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Salidas"
			+ File.separator;

	private static LinkedList<String> datosCorruptos;
	private static LinkedList<Cliente> candidatos;
	private static LinkedList<Cliente> clientes;
	private static int uPC;
	private static Scanner scanner = new Scanner(System.in);
	private static Pareto pareto;
	private static File file;

	final static String ERROR_ARCHIVO_INEXISTENTE = "El archivo seleccionado no existe.";
	final static String ERROR_ARCHIVO_VACIO = "El archivo seleccionado esta vacio.";
	final static String ERROR_FORMATO_CABECERA = "El archivo seleccionado no contiene una cabecera con un entero.";
	final static String ERROR_CABECERA_NEGATIVA = "El archivo seleccionado contiene una cabecera negativa.";
	final static String ERROR_MENOS_DATOS_QUE_EN_LA_CABECERA = "El archivo de texto tiene menos datos de los que indica la cabecera.";

	public static void main(String[] args) {
		// try {

		try {
			System.out
					.println("Nombre del fichero (no hace falta poner la extensi�n de archivo): ");
			String fileName = scanner.nextLine();
			file = new File(ENTRADA + fileName + ".txt");
			if (!file.exists())
				throw new FileNotFoundException();
			datosCorruptos = new LinkedList<String>();
			candidatos = new LinkedList<Cliente>();
			clientes = leerArchivo();
			int version;
			System.out.println("Version? ");
			version = scanner.nextInt();
			if(version == 1)
				pareto = new ParetoV1(clientes);
			if(version == 2)
				pareto = new ParetoV2(clientes);
			if(version == 3){
				ArrayList<Cliente> a = new ArrayList<Cliente>(clientes.size());
				Iterator<Cliente> it = clientes.iterator();
				while(it.hasNext()){
					a.add(it.next());
				}
				pareto = new ParetoV3(a);
			}
			if(version == 4){
				ArrayList<Cliente> a = new ArrayList<Cliente>(clientes.size());
				Iterator<Cliente> it = clientes.iterator();
				while(it.hasNext()){
					a.add(it.next());
				}
				pareto = new ParetoV4(clientes);
			}

			do {
				clientes.removeAll(candidatos);
				long a = System.nanoTime();
				candidatos.addAll(pareto.paretoSolucion());
				System.out.println("Tiempo:" + (System.nanoTime() - a));
			} while (candidatos.size() < uPC);
			imprimirInforme();

		} catch (FileNotFoundException e) {
			System.out.println(ERROR_ARCHIVO_INEXISTENTE);
		} catch (EmptyFileException e) {
			System.out.println(ERROR_ARCHIVO_VACIO);
		} catch (NumberFormatException e) {
			System.out.println(ERROR_FORMATO_CABECERA);
		} catch (NegativeNumberException e) {
			System.out.println(ERROR_CABECERA_NEGATIVA);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LinesNotEqualsHeaderException e) {
			System.out.println(ERROR_MENOS_DATOS_QUE_EN_LA_CABECERA);
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

	private static LinkedList<Cliente> leerArchivo() throws EmptyFileException,
			NumberFormatException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException {

		LinkedList<Cliente> toReturn = new LinkedList<Cliente>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		do {
			linea = br.readLine();
			linea = linea.replaceAll(" ", "");
		} while (linea.equals(""));
		if (linea.equals(null))
			throw new EmptyFileException(ERROR_ARCHIVO_VACIO);
		int nClientes = Integer.parseInt(linea);
		if (nClientes <= 0)
			throw new NegativeNumberException(ERROR_CABECERA_NEGATIVA);
		int i = 0;
		int ice, ce;
		Scanner sc;
		linea = br.readLine();

		while (linea != null) {
			try {
				linea = linea.replaceAll(" ", "");
				if (linea.equals("")) {
					i++;
					linea = br.readLine();
					continue;
				}
				sc = new Scanner(linea);
				sc.useDelimiter(",");

				ice = sc.nextInt();
				ce = sc.nextInt();
				if (ice <= 0 || ce <= 0)
					throw new NegativeNumberException(ERROR_CABECERA_NEGATIVA);

				toReturn.add(new Cliente(i, ce, ice));
				i++;
				linea = br.readLine();
			} catch (Exception e) {
				datosCorruptos.add("Id: " + i + " Datos: " + linea);
				i++;
				linea = br.readLine();
				continue;
			}
		}
		if (i != nClientes)
			throw new LinesNotEqualsHeaderException(
					ERROR_MENOS_DATOS_QUE_EN_LA_CABECERA);
		uPC = ((nClientes) % 100 == 0) ? (nClientes) / 100
				: ((nClientes) / 100) + 1;
		br.close();
		fr.close();
		return toReturn;
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
