package exceptions;

/**
 *	Excepci�n que controla si una archivo est� vac�o.
 */
public class TallerAlredyIncludeException extends Exception{

	private static final long serialVersionUID = 1L;

	public TallerAlredyIncludeException (String error){
		super(error);
	}
}
