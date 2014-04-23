package utilidades;

import java.util.Comparator;

public class TareaConflictosComparador implements Comparator<Tarea>{

	@Override
	public int compare(Tarea a, Tarea b) {
		return a.getConflictos() - b.getConflictos();
	}

}
