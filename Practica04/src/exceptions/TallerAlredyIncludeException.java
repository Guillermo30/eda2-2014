package exceptions;

/**
 *	Excepción que controla si una archivo está vacío.
 */
public class TallerAlredyIncludeException extends Exception{

	private static final long serialVersionUID = 1L;

	public TallerAlredyIncludeException (String error){
		super(error);
	}
}
