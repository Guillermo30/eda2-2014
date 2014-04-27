package utilidades;

import java.util.Comparator;

public class ClienteCercaniaComparador implements Comparator<Cliente>{

	private int ice;
	
	public ClienteCercaniaComparador(int ice){
		this.ice = ice;
	}
	
	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		return Math.abs(ice - arg0.getIce()) - Math.abs(ice - arg1.getIce());
	}

}
