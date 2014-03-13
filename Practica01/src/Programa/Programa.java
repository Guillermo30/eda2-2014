package Programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	final static File ARCHIVO = new File(System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Programa"
			+ File.separator + "datos_eda_2.txt");
	final static File SALIDA = new File(System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "Programa"
			+ File.separator + "datos_eda_2 Salida.txt");

	private static LinkedList<String> datosCorruptos;
	private static LinkedList<Cliente> candidatos;
	private static LinkedList<Cliente> clientes;
	private static int uPC;
	private static Scanner scanner = new Scanner(System.in);
	private static Pareto pareto;

	final static String ERROR_ARCHIVO_INEXISTENTE = "El archivo seleccionado no existe";
	final static String ERROR_ARCHIVO_VACIO = "El archivo seleccionado esta vacio";

	public static void main(String[] args) {
		//try {
			
			try {
				datosCorruptos = new LinkedList<String>();
				candidatos = new LinkedList<Cliente>();
				clientes = leerArchivo();
				int version;
				System.out.println("Version? ");
				version = scanner.nextInt();
				switch(version){
					case 1: pareto = new ParetoV1(clientes);
					case 2: pareto = new ParetoV2(clientes);
					//case 3: pareto = new ParetoV3(clientes);
					//case 4: pareto = new ParetoV4(clientes);
				}
				
				do{
					long a = System.nanoTime();
					candidatos.addAll(pareto.paretoSolucion());
					System.out.println("Tiempo:" + (System.nanoTime() - a));
					clientes.removeAll(candidatos);
				}while (candidatos.size() < uPC);
				imprimirInforme();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		/*} catch (FileNotFoundException e) {
			System.out.println(ERROR_ARCHIVO_INEXISTENTE);
		} catch (EmptyFileException e) {
			System.out.println(e.getMessage());
		}*/
	}

	private static void imprimirInforme() {
		try{
			FileWriter fw = new FileWriter(SALIDA);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println("Clientes sospechosos:\n\n");
			Iterator<Cliente> it = candidatos.iterator();
			while(it.hasNext()) pw.println(it.next().toString());
			pw.println("\nClientes con datos corruptos:\n\n");
			Iterator<String> it2 = datosCorruptos.iterator();
			while(it2.hasNext()) pw.println(it2.next());
			pw.close();
			bw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*private static void leerArchivo() throws EmptyFileException,
			FileNotFoundException {
		try {
			FileReader fr = new FileReader(ARCHIVO);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			if (linea.equals(null))
				throw new EmptyFileException(ERROR_ARCHIVO_VACIO);
			do{
				linea = linea.replaceAll(" ", "");
			}while(linea.equals(""));
			while(linea)
			
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	private static LinkedList<Cliente> leerArchivo() throws NumberFormatException, IOException{
		LinkedList<Cliente> struc= new LinkedList<Cliente>();
		int numDePuntos=0;
		FileReader fr = new FileReader (ARCHIVO);
		BufferedReader br = new BufferedReader(fr);
		numDePuntos = Integer.parseInt(br.readLine());
		
		int ce;
		int ice;
		String linea = "";
		Scanner sc;
		int i = 0;
		
		for(; i< numDePuntos; i++){
			try{
				linea = br.readLine();
				linea = linea.replaceAll(" ", "");
				sc = new Scanner (linea);
				sc.useDelimiter(",");
			
				ice = sc.nextInt();
				ce = sc.nextInt();
			
				struc.add(new Cliente(i, ce, ice));
			}catch(Exception e){
				datosCorruptos.add("Id: " + i + " Datos: " + linea);
			}
		}
		uPC = ((i+1)%100 == 0)? (i+1)/100 : ((i+1)/100) + 1;
		return struc;
	}

}
