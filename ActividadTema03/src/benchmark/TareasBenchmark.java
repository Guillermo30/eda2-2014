package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import algoritmos.OrganizacionTareas;
import utilidades.Tarea;
import utilidades.TareaConflictosComparador;
import utilidades.TareaDuracionComparador;
import utilidades.TareaFinComparador;
import utilidades.TareaInicioComparador;

public class TareasBenchmark {
	
	public final static String SALIDA = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "benchmark"
			+ File.separator + "Benchmark_Tareas.txt";
	
	public static void main(String[] args){
		
		File out = new File(SALIDA);
		PriorityQueue<Long> times = new PriorityQueue<Long>();
		ArrayList<Tarea> five = new ArrayList<Tarea>(5);
		ArrayList<Tarea> ten = new ArrayList<Tarea>(10);
		ArrayList<Tarea> fifteen = new ArrayList<Tarea>(15);
		ArrayList<Tarea> fiveFin = new ArrayList<Tarea>(5);
		ArrayList<Tarea> fiveTm = new ArrayList<Tarea>(5);
		ArrayList<Tarea> fiveCf = new ArrayList<Tarea>(5);
		ArrayList<Tarea> tenFin = new ArrayList<Tarea>(10);
		ArrayList<Tarea> tenTm = new ArrayList<Tarea>(10);
		ArrayList<Tarea> tenCf = new ArrayList<Tarea>(10);
		ArrayList<Tarea> fifteenFin = new ArrayList<Tarea>(15);
		ArrayList<Tarea> fifteenTm = new ArrayList<Tarea>(15);
		ArrayList<Tarea> fifteenCf = new ArrayList<Tarea>(15);
		for(int i = 0; i < 15; i++){
			if(i < 5) five.add(new Tarea(50, 20, 0));
			if(i < 10) ten.add(new Tarea(100, 40, 0));
			fifteen.add(new Tarea(150,60, 0));
		}
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
			pw.println("Datos con los que se va a trabajar:\n");
			pw.println("-5: " + five.toString());
			pw.println("-10: " + ten.toString());
			pw.println("-15: " + fifteen.toString() + "\n");
			OrganizacionTareas.setConfictos(five);
			OrganizacionTareas.setConfictos(ten);
			OrganizacionTareas.setConfictos(fifteen);
			fiveFin.addAll(five);
			fiveTm.addAll(five);
			fiveCf.addAll(five);
			tenFin.addAll(ten);
			tenTm.addAll(ten);
			tenCf.addAll(ten);
			fifteenFin.addAll(fifteen);
			fifteenTm.addAll(fifteen);
			fifteenCf.addAll(fifteen);
			Collections.sort(five, new TareaInicioComparador());
			Collections.sort(ten, new TareaInicioComparador());
			Collections.sort(fifteen, new TareaInicioComparador());
			Collections.sort(fiveFin, new TareaFinComparador());
			Collections.sort(tenFin, new TareaFinComparador());
			Collections.sort(fifteenFin, new TareaFinComparador());
			Collections.sort(fiveTm, new TareaDuracionComparador());
			Collections.sort(tenTm, new TareaDuracionComparador());
			Collections.sort(fifteenTm, new TareaDuracionComparador());
			Collections.sort(fiveCf, new TareaConflictosComparador());
			Collections.sort(tenCf, new TareaConflictosComparador());
			Collections.sort(fifteenCf, new TareaConflictosComparador());
			pw.println("-Oredandos por inicio:\n");
			pw.println("\t-5: " + five.toString());
			pw.println("\t-10: " + ten.toString());
			pw.println("\t-15: " + fifteen.toString() + "\n");
			pw.println("-Oredandos por fin:\n");
			pw.println("\t-5: " + fiveFin.toString());
			pw.println("\t-10: " + tenFin.toString());
			pw.println("\t-15: " + fifteenFin.toString() + "\n");
			pw.println("-Oredandos por duracion:\n");
			pw.println("\t-5: " + fiveTm.toString());
			pw.println("\t-10: " + tenTm.toString());
			pw.println("\t-15: " + fifteenTm.toString() + "\n");
			pw.println("-Oredandos por confiltos:\n");
			pw.println("\t-5: " + fiveCf.toString());
			pw.println("\t-10: " + tenCf.toString());
			pw.println("\t-15: " + fifteenCf.toString() + "\n");
			pw.println("----------------------------------------------------------------------");
			pw.println("Nota:");
			pw.println("\tPrimero se muestra que tipo de ordenación tiene la entrada y luego");
			pw.println("los tamaños de las estructuras de datos seguido del su tiempo de eje-");
			pw.println("cución y su salida ordenada por inicio.");
			pw.println("----------------------------------------------------------------------\n");
			
			ArrayList<Tarea> resultado = null;
			long a, b;
			int superposicion = 10;
			pw.println("-Rendimiento y resultados con superposicion del: " + superposicion + "%\n");
			pw.println("\t-Oredandos por inicio:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(five, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(ten, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteen, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por fin:\n");
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fiveFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(tenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por duracion:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por confiltos:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			superposicion = 25;
			pw.println("----------------------------------------------------------------------\n");
			
			pw.println("-Rendimiento y resultados con superposicion del: " + superposicion + "%\n");
			pw.println("\t-Oredandos por inicio:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(five, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(ten, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteen, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por fin:\n");
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fiveFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(tenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por duracion:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por confiltos:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			superposicion = 40;
			pw.println("----------------------------------------------------------------------\n");
			
			pw.println("-Rendimiento y resultados con superposicion del: " + superposicion + "%\n");
			pw.println("\t-Oredandos por inicio:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(five, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(ten, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteen, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por fin:\n");
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fiveFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(tenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por duracion:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por confiltos:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			superposicion = 65;
			pw.println("----------------------------------------------------------------------\n");
			
			pw.println("-Rendimiento y resultados con superposicion del: " + superposicion + "%\n");
			pw.println("\t-Oredandos por inicio:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(five, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(ten, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteen, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por fin:\n");
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fiveFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(tenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por duracion:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por confiltos:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			superposicion = 80;
			pw.println("----------------------------------------------------------------------\n");
			
			pw.println("-Rendimiento y resultados con superposicion del: " + superposicion + "%\n");
			pw.println("\t-Oredandos por inicio:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(five, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(ten, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteen, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por fin:\n");
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fiveFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(tenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaFinalizacionOInicio(fifteenFin, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por duracion:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenTm, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.println("\n\t-Oredandos por confiltos:\n");
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fiveCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-5: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(tenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-10: " + media(times) + "ns " + resultado.toString());
			
			times.clear();
			for(int i = 0; i < 10; i++){
				a = System.nanoTime();
				resultado = OrganizacionTareas.heuristicaDuracionOConfictos(fifteenCf, superposicion);
				b = System.nanoTime();
				times.add(b - a);
			}
			Collections.sort(resultado, new TareaInicioComparador());
			pw.println("\t\t-15: " + media(times) + "ns " + resultado.toString());
			
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static long media(PriorityQueue<Long> times) {
		long media = 0;
		
		times.poll();
		times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		media += times.poll();
		return media/6;
	}

}
