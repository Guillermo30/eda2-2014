package exceptions;

/**
 *	Excepci�n que controla si alg�n n�mero de entrada es negativo.
 */
public class NegativeNumberException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NegativeNumberException (String error){
		super(error);
	}
	
}
