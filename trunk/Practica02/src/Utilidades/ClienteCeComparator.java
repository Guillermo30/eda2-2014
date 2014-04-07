package Utilidades;

import java.util.Comparator;

public class ClienteCeComparator implements Comparator {
	
	public ClienteCeComparator(){
	}

	@Override
	public int compare(Object o1, Object o2) {
		return ((Cliente)o2).getCe() - ((Cliente)o1).getCe();
	}

}
