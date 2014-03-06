package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Utilidades {
	public static LinkedList<Usuario> pareto (LinkedList<Usuario> in){
		LinkedList<Usuario> salida = new LinkedList<Usuario>();
		//no recursiva
		//calculo del minimo
		Usuario min = in.get(0);
		for(int i =0;i<in.size();i++){
			if(in.get(i).getCe()<min.getCe()){
				min = in.get(i);
			}
		}
		System.out.println(min);
		
		LinkedList<Usuario> v1 = new LinkedList<Usuario>();
		LinkedList<Usuario> v2 = new LinkedList<Usuario>();
		for(int i =0;i<min.getId()-1;i++){
			v1.add(in.get(i));
		}
		
		for(int i =in.size()-1; i>=min.getId();i--){
			in.get(i).setIce(10000-in.get(i).getIce());
			v2.add(in.get(i));
		}
		
		salida.addAll(paretoRecur(v1));
		System.out.println(salida);
		salida.add(min);
		System.out.println(salida);
		salida.addAll(paretoRecur(v2));
		System.out.println(salida);
	
		return salida;
	}
	public static LinkedList<Usuario> paretoRecur (LinkedList<Usuario> in){
		//caso base
		LinkedList<Usuario> salida = new  LinkedList<Usuario>();
		if(in.size()<=3){
			Usuario min, izq;
			min= in.get(0);
			izq=in.get(0);
			if(izq.getId()==min.getId()){
				salida.add(min);
				return salida;
			}
			else if(in.size()==2){
				salida.add(min);
				salida.add(izq);
				return salida;
			}
			else{
				if(in.get(1).getCe()<izq.getCe()||in.get(1).getIce()<min.getIce()){
					salida.addAll(in);
					return salida;
				}
			}
		}
		//fin del caso base
		
		//division del problema
		
		//combinacion
		
		
		
		return salida;
	}
	
	public static LinkedList solucion(String filei) throws NumberFormatException, IOException{
		LinkedList<Usuario> struc= new LinkedList<Usuario>();
		int numDePuntos=0;
		File file = new File (filei);
		FileReader fr = new FileReader (file);
		BufferedReader br = new BufferedReader(fr);
		numDePuntos = Integer.parseInt(br.readLine());
		
		int ce;
		int ice;
		String linea;
		Scanner sc;
		
		for(int i =0 ; i< numDePuntos; i++){
			linea = br.readLine();
			sc = new Scanner (linea);
			sc.useDelimiter(", ");
			
			ice = sc.nextInt();
			ce = sc.nextInt();
			
			struc.add(new Usuario(i, ce, ice));
			
		}
		
		System.out.println(struc);
		pareto(struc);
		return null;
	}
}
