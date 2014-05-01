package exceptions;

/**
 *	Excepci�n que controla si el ICE de alg�n cliente est� fuera de rango.
 */
public class IceOutOfRangeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public IceOutOfRangeException(String error){
		super(error);
	}

}
