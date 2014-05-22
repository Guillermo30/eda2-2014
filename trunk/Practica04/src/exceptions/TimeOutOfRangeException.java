package exceptions;

/**
 *	Excepci�n que controla si el ICE de alg�n cliente est� fuera de rango.
 */
public class TimeOutOfRangeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public TimeOutOfRangeException(String error){
		super(error);
	}

}
