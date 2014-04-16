package exceptions;

/**
 *	Excepción que controla si una archivo está vacío.
 */
public class EmptyFileException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyFileException (String error){
		super(error);
	}
}
