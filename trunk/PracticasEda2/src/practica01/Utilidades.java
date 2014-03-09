package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Utilidades {
	public static LinkedList<Usuario> pareto (LinkedList<Usuario> in){
		LinkedList<Usuario> salida = new LinkedList<Usuario>();
		
		//calculo del minimo
		Usuario min = in.get(0);
		for(int i =0;i<in.size();i++){
			if(in.get(i).getCe()<min.getCe()){
				min = in.get(i);
			}
		}
		
		//dos problemas separados, decreciente y creciente
		LinkedList<Usuario> v1 = new LinkedList<Usuario>();
		LinkedList<Usuario> v2 = new LinkedList<Usuario>();
		
		//adjunta el problema decreciente
		for(int i =0;i<=min.getId()-1;i++){
			v1.add(in.get(i));
		}
		
		//cambio el problema creciente a uno decreciente
		for(int i =in.size()-1; i>min.getId();i--){
			v2.add(in.get(i));
		}
		
		
		//impresion del estado de los dos paretos 
		System.out.println("problema decreciente  "+v1);
		System.out.println("problema creciente invertido  "+v2);
		System.out.println("minimo " +min);
		
		//adicion de la primera parte
		salida.addAll(paretoRecur(v1));
		//adicion del minimo
		salida.add(min);
		//adicion de la segunda parte
		salida.addAll(paretoRecur(v2));
		 
		//devuelvo todo
		return salida;
	}
	public static LinkedList<Usuario> paretoRecur (LinkedList<Usuario> in){
		
		LinkedList<Usuario> salida = new  LinkedList<Usuario>();
		//caso base
		long r;
		r= System.nanoTime();
		if(in.size()<=3){
			return casoBaseMejorado(in,salida);
		}
		System.out.println((System.nanoTime()-r));
	
		
		
		//division del problema
		LinkedList<Usuario> prob1 = new LinkedList<Usuario>();
		LinkedList<Usuario> prob2 = new LinkedList<Usuario>();
		
		for(int i = 0; i< in.size(); i++){
			if(i < in.size()/2){
				prob1.add(in.get(i));
			}else{
				prob2.add(in.get(i));
			}
		}
		
		
		//combinacion
		//añadir la parte de la izquierda completa
		LinkedList<Usuario> previoP1 = new LinkedList<Usuario>();
		previoP1 = paretoRecur(prob1);
		salida.addAll(previoP1);
		Usuario minComb = previoP1.get(previoP1.size()-1); //obtener el minimo de la parte de la izquierda
		
		//calculo de la parte de la derecha
		LinkedList<Usuario> previoP2 = new LinkedList<Usuario>();
		previoP2= paretoRecur(prob2);

		//recorrer la segunda parte y elimina todos los mayores que el minimo
		// una vez se encuentre uno por debajo añadir el resto de la estructura y salir
		Iterator<Usuario> it = previoP2.iterator();
		while(it.hasNext()){
			if(it.next().getCe()>minComb.getCe()) it.remove();
			else break;
		}
		salida.addAll(previoP2);
		
		//devuelve la solucion completa
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
			try{
				linea = br.readLine();
				linea = linea.replaceAll(" ", "");
				sc = new Scanner (linea);
				sc.useDelimiter(",");
			
				ice = sc.nextInt();
				ce = sc.nextInt();
			
				struc.add(new Usuario(i, ce, ice));
			}catch(Exception e){
				
			}
		}
		
		System.out.println("estrcutura de la que partimos" +struc);
		System.out.println("puntos del pareto" +pareto(struc));
		return null;
	}
	
	private static LinkedList<Usuario> casoBase(LinkedList<Usuario> in, LinkedList<Usuario> salida){
		Usuario min, izq;
		min= in.get(0);
		izq=in.get(0);
		for(int i =0;i<in.size();i++){
			if(in.get(i).getCe()<min.getCe()){
				min=in.get(i);
			}
		}
		if(izq.getId()==min.getId()){
			salida.add(min);
			return salida;
		}
		else if(in.get(1).equals(min)){
			salida.add(izq);
			salida.add(min);
			return salida;
		}
		else{
			if(in.get(1).getCe()<izq.getCe()){
				salida.addAll(in);
				return salida;
			}else
				salida.add(izq);
				salida.add(min);
				return salida;
		}
	}
	private static LinkedList<Usuario> casoBaseMejorado(LinkedList<Usuario> in, LinkedList<Usuario> salida){
		
		int a,b,c;
		c=Integer.MAX_VALUE;
		a=in.get(0).getCe();
		b=in.get(1).getCe();
		if(in.size()==3) c=in.get(2).getCe();
		
		salida.add(in.get(0));
		if(b<a) salida.add(in.get(1));
		if(c<b&&c<a) salida.add(in.get(2));
		
		return salida;
	}
	
}
