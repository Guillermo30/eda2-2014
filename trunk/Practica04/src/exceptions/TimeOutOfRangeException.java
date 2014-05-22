package exceptions;

/**
 *	Excepción que controla si el ICE de algún cliente está fuera de rango.
 */
public class TimeOutOfRangeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public TimeOutOfRangeException(String error){
		super(error);
	}

}
