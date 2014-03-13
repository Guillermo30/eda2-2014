package Execptions;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Extension;

public class EmptyFileException extends Exception{

	public EmptyFileException (String error){
		super(error);
	}
}
