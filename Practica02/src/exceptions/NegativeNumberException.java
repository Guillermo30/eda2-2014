package exceptions;

/**
 *	Excepción que controla si algún número de entrada es negativo.
 */
public class NegativeNumberException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NegativeNumberException (String error){
		super(error);
	}
	
}
