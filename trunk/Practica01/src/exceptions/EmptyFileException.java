package exceptions;

/**
 *	Excepci�n que controla si una archivo est� vac�o.
 */
public class EmptyFileException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyFileException (String error){
		super(error);
	}
}
