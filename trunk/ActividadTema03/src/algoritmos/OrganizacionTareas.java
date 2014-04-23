package algoritmos;

import java.util.ArrayList;
import java.util.Iterator;
import utilidades.Tarea;

public class OrganizacionTareas {
	
	public static ArrayList<Tarea> heuristicaFinalizacionOInicio(ArrayList<Tarea> tareas, int superposicion){
		
		ArrayList<Tarea> solucion = new ArrayList<Tarea>();
		if(tareas.isEmpty()) return solucion;
		Iterator<Tarea> it = tareas.iterator();
		
		Tarea actual = it.next();
		solucion.add(actual);
		Tarea anterior = actual;
		
		while(it.hasNext()){
			
			actual = it.next();
			if(actual.getIni() >= (anterior.getFin() - ((anterior.getDuracion() * superposicion)/100))) solucion.add(actual);
			anterior = actual;
			
		}
			
		return solucion;
	}
	
	public static ArrayList<Tarea> heuristicaDuracionOConfictos(ArrayList<Tarea> tareas, int superposicion){
		
		ArrayList<Tarea> solucion = new ArrayList<Tarea>();
		boolean anadir;
		Iterator<Tarea> it = tareas.iterator();
		Iterator<Tarea> it2;
		Tarea actual = it.next();
		solucion.add(actual);
		int i = 0, j;
		
		while(it.hasNext()){
			anadir = true;
			actual = it.next();
			it2 = solucion.iterator();
			j = 0;
			while(it2.hasNext()){
				if(i == j){
					j++;
					continue;
				}
				if(actual.solapaCon(it2.next(), superposicion)){
					anadir = false;
					j++;
					break;
				}
			}	
			if(anadir) solucion.add(actual);
			i++;
		}
		
		return solucion;
	}
	
	public static void setConfictos(ArrayList<Tarea> tareas){
		Iterator<Tarea> it = tareas.iterator();
		Iterator<Tarea> it2;
		int i = 0, j, conflictos;
		Tarea actual;
		while(it.hasNext()){
			actual = it.next();
			it2 = tareas.iterator();
			j = 0;
			conflictos = 0;
			while(it2.hasNext()){
				if(i == j){
					it2.next();
					j++;
					continue;
				}
				if(actual.solapaCon(it2.next())) conflictos++;
				j++;
			}
			actual.setConflictos(conflictos);
			i++;
		}
	}
	
}
