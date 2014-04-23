package utilidades;

import java.util.Comparator;

public class TareaInicioComparador  implements Comparator<Tarea>{

	@Override
	public int compare(Tarea a, Tarea b) {
		return a.getIni() - b.getIni();
	}

}
