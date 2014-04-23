package utilidades;

import java.util.Comparator;

public class TareaDuracionComparador implements Comparator<Tarea>{

	@Override
	public int compare(Tarea a, Tarea b) {
		return a.getDuracion() - b.getDuracion();
	}

}
