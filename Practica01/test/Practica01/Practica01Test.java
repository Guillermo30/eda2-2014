package Practica01;

import org.junit.Before;
import org.junit.Test;
import Execptions.EmptyFileException;
import Execptions.LinesNotEqualsHeaderException;
import Execptions.NegativeNumberException;
import Programa.Programa;
import static org.junit.Assert.*;

import java.io.*;

public class Practica01Test {
	String directorioEntrada;
	String directorioEntrada1;
	String memsaje;
	
@Before
	public void setUp() throws Exception {
		
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datos_eda_2.txt";	
	}
	
	@Test(expected=EmptyFileException.class)
	public void testExcepcionArchivoVacio() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datos_eda.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();
			
		
	}
	
	@Test(expected=NegativeNumberException.class)
	public void testExcepcionArchivoCaberceraNegativa() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datosCabeceraNegativa.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();
				
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testExcepcionArchivoNoEncontrado() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datosCabeceraXXX.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();		
	}
	
	@Test(expected=NumberFormatException.class)
	public void testExcepcionArchivoCaberceraIcorrecta() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datosCabeceraIncorrecta.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();
				
	}
	
	@Test(expected=LinesNotEqualsHeaderException.class)
	public void testExcepcionArchivoMenosDatosQueCabecera() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datosMenosDatosQueCabecera.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();
				
	}
	
	@Test
	public void testExcepcionArchivoDatosNegativos() throws NumberFormatException, EmptyFileException, NegativeNumberException, IOException, LinesNotEqualsHeaderException{
		
		directorioEntrada1 = System.getProperty("user.dir");
		
		directorioEntrada1 = directorioEntrada1 
				+ File.separator + "test"
				+ File.separator + "TestFiles" 
				+ File.separator + "datosEntradaNegativos.txt";
			
			Programa.setFile(new File(directorioEntrada1));
			Programa.leerArchivo();
		
			boolean comprobar = true;
			boolean encontrado=false;
			for(int i=0;i<Programa.getDatosCorruptos().size();i++){
				encontrado =Programa.getDatosCorruptos().get(i).contains("-");	
			}
			
			
			assertEquals(comprobar,encontrado); 
				
	}
	
	
	
	
	
	
	


}
