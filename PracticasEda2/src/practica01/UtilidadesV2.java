package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import estructurasdedatos.BSTree;
import estructurasdedatos.Iterator;

public class UtilidadesV2 {

	static AVLTree<Usuario> usuarios;
	static LinkedList<String> datosCorruptos;
	static BSTree<Usuario> candidatos;
	
	public static void calcularCandidatos(String file){
		
		usuarios = new AVLTree<Usuario>();
		datosCorruptos = new LinkedList<String>();
		candidatos = new BSTree<Usuario>();
		int minimoCandidatos;
		int minimo;
		
		try {
			leerFichero(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		minimoCandidatos = (usuarios.size()%100 == 0)? usuarios.size()/100: (usuarios.size()/100) + 1;
		
		while(candidatos.size() < minimoCandidatos){
			minimo = calcularMinimo();
			ArrayList<Usuario> nubeI = new ArrayList<Usuario>(minimo);
			ArrayList<Usuario> nubeD = new ArrayList<Usuario>(usuarios.size() - (minimo-1));
			Iterator<Usuario> itI = usuarios.inOrderIterator();
			Iterator<Usuario> itD = usuarios.reverseInOrderIterator();
			for(int i = 0; i != minimo; i++){
				nubeI.add(itI.next());
			}
			for(int i = usuarios.size() - 1; i >= minimo; i--){
				nubeD.add(itD.next());
			}
			//candidatos.addAll(pareto(nubeI));
			//candidatos.addAll(nubeD);
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
				if(linea.equals("")){
					i--;
					continue;
				}
				sc = new Scanner(linea);
				sc.useDelimiter(",");

				ice = sc.nextInt();
				ce = sc.nextInt();
				if(ice < 0 || ce < 0){
					datosCorruptos.add("Id: " + i + " Datos: " + linea);
					continue;
				}

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

	private static BSTree<Usuario> pareto() {
		return null;
	}

	
}
