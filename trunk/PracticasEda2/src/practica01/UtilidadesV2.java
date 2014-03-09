package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import estructurasdedatos.Iterator;

public class UtilidadesV2 {

	static AVLTree<Usuario> usuarios;
	static LinkedList<String> datosCorruptos;
	static AVLTree<Usuario> candidatos;
	
	public static void calcularCandidatos(String file){
		
		usuarios = new AVLTree<Usuario>();
		datosCorruptos = new LinkedList<String>();
		candidatos = new AVLTree<Usuario>();
		int minimoCandidatos, minimo;
		
		try {
			leerFichero(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		minimoCandidatos = (usuarios.size()%100 == 0)? usuarios.size()/100: (usuarios.size()/100) + 1;
		
		while(candidatos.size() < minimoCandidatos){
			minimo = calcularMinimo();
			ArrayList<Usuario> nubeI = new ArrayList<Usuario>(minimo);
			ArrayList<Usuario> nubeD = new ArrayList<Usuario>(usuarios.size() - minimo);
			
		}
		
	}

	private static void leerFichero(String filei) throws IOException {
		int numDePuntos = 0;
		File file = new File(filei);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		numDePuntos = Integer.parseInt(br.readLine());

		int ce;
		int ice;
		String linea = "";
		Scanner sc;

		for (int i = 0; i < numDePuntos; i++) {
			try {
				linea = br.readLine();
				linea = linea.replaceAll(" ", "");
				sc = new Scanner(linea);
				sc.useDelimiter(",");

				ice = sc.nextInt();
				ce = sc.nextInt();

				usuarios.add(new Usuario(i, ce, ice));
			} catch (Exception e) {
				datosCorruptos.add("Id: " + i + " Datos: " + linea);
			}
		}

	}
	
	private static int calcularMinimo() {
		int CEminimo = Integer.MAX_VALUE;
		int minimo, i = 0;
		Iterator<Usuario> it = usuarios.inOrderIterator();
		while(it.hasNext()){
			if(CEminimo > it.next().getCe()){
				CEminimo = it.current().getCe();
				minimo = i;
			}
			i++;
		}
		return i;
	}

	private static void pareto() {
		int min = calcularMinimo();
		Usuario minimo;
	}

	
}
