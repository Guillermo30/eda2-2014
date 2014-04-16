package exceptions;

/**
 *	Excepción que controla si hay tantos clientes como dice la cabecera.
 */
public class LinesNotEqualsHeaderException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LinesNotEqualsHeaderException(String error){
		super(error);
	}

}
