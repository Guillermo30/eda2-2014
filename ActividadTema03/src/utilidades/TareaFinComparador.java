package utilidades;

import java.util.Comparator;

public class TareaFinComparador implements Comparator<Tarea>{

	@Override
	public int compare(Tarea a, Tarea b) {
		return a.getFin() - b.getFin();
	}

}
