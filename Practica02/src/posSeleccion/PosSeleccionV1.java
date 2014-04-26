package posSeleccion;

import java.util.ArrayList;
import java.util.LinkedList;

import utilidades.Cliente;
import utilidades.PosSeleccion;

public class PosSeleccionV1 extends PosSeleccion
{
	//private ArrayList<Cliente> nube;
	//private LinkedList<Cliente> candidatos;
	//private int totalSospechosos;
	
	public PosSeleccionV1(ArrayList<Cliente> nube,	LinkedList<Cliente> candidatos, int totalSospechosos) {
		super(nube, candidatos, totalSospechosos);
		
	}
	
	public  int  mediaOchoCercanos(int pos) {
		int sum=0;
		int izq = 1, der = 1;
		for (int i = 0; i < 8; i++) {
			
			if (pos - izq < 0) {
				for(;i<8;i++){
					sum +=nube.get(pos + der).getCe();
					der++;
				}
				break;
			}
			
			if (pos + der >= nube.size()) {
				for(;i<8;i++){
					sum +=nube.get(pos - izq).getCe();
					izq++;
				}
				break;
			}
			
			if (nube.get(pos + der).getIce() - nube.get(pos).getIce() <= nube.get(pos).getIce() - nube.get(pos - izq).getIce()) {
				sum +=nube.get(pos + der).getCe();
				der++;
			} else {
				sum +=nube.get(pos - izq).getCe();
				izq++;
			}
		}
		
		return sum/8;
	}
	private ArrayList<Cliente> preSeleccionar(){
		ArrayList<Cliente> solucion = new ArrayList();
		int pos ;
		Cliente aux;
		for(int i = 0; i< candidatos.size(); i++){
			pos = nube.indexOf(candidatos.get(i));
			aux = (Cliente) candidatos.get(i).clone();
			aux.setDifMedia8(aux.getCe()-mediaOchoCercanos(pos));
			solucion.add(aux);
		}
		
		return solucion;
	}
	@Override
	public LinkedList<Cliente> seleccionar(){
		LinkedList<Cliente> solucion = new LinkedList<Cliente>();
		ArrayList<Cliente> preSol =  preSeleccionar();
		Cliente sol;
		for ( int i = 0; i<totalSospechosos/2;i++){
			sol = preSol.get(0);
			for(int j = 1; j<preSol.size();j++){
				if(preSol.get(j).getDifMedia8()>sol.getDifMedia8()) sol = preSol.get(j);
			}
			solucion.add(sol);
		}
		return solucion;
		
	}
	
	
	

}
