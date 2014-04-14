package exceptions;

/**
 *	Excepción que controla si la cabecera del archivo está fuera de reango.
 */
public class HeaderOutOfRangeException extends Exception{

	private static final long serialVersionUID = 1L;

	public HeaderOutOfRangeException(String error){
		super(error);
	}

}
