package utilidades;

import java.util.Comparator;

public class ClienteMediaLocalComparador implements Comparator<Cliente>{

	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		return arg1.getMediaLocal() - arg0.getMediaLocal();
	}

}
