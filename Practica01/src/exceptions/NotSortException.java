package exceptions;

/**
 *	Excepción que controla si los ICEs de los clientes están ordenados de menor a mayor.
 */
public class NotSortException extends Exception{

	private static final long serialVersionUID = 1L;

	public NotSortException(String error){
		super(error);
	}
	
}
