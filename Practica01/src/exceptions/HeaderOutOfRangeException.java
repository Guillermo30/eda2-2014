package exceptions;

/**
 *	Excepci�n que controla si la cabecera del archivo est� fuera de reango.
 */
public class HeaderOutOfRangeException extends Exception{

	private static final long serialVersionUID = 1L;

	public HeaderOutOfRangeException(String error){
		super(error);
	}

}
