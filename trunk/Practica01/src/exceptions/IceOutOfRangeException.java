package exceptions;

/**
 *	Excepción que controla si el ICE de algún cliente está fuera de rango.
 */
public class IceOutOfRangeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public IceOutOfRangeException(String error){
		super(error);
	}

}
