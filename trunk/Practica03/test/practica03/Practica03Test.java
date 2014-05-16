package practica03;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import programa.Programa;
import utilidades.Cliente;
import utilidades.Mochila;
import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import exceptions.NegativeNumberException;

public class Practica03Test {
	
	String directorioEntrada1;
	
	@Test(expected = EmptyFileException.class)
	public void testExcepcionArchivoVacio() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datos_eda.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test(expected = HeaderOutOfRangeException.class)
	public void testExcepcionArchivoCaberceraFueraDeRangoPorDebajo()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCabeceraNegativa.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test(expected = HeaderOutOfRangeException.class)
	public void testExcepcionArchivoCaberceraFueraDeRangoPorArriba()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCabecera10001.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test(expected = FileNotFoundException.class)
	public void testExcepcionArchivoNoEncontrado()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCabeceraXXX.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();
	}

	@Test(expected = NumberFormatException.class)
	public void testExcepcionArchivoCaberceraIcorrecta()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCabeceraIncorrecta.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test(expected = LinesNotEqualsHeaderException.class)
	public void testExcepcionArchivoMenosDatosQueCabecera()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosMenosDatosQueCabecera.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test(expected = LinesNotEqualsHeaderException.class)
	public void testExcepcionArchivoMasDatosQueCabecera()
			throws NumberFormatException, EmptyFileException,
			NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosMasDatosQueCabecera.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

	}

	@Test
	public void testDatosNoPudueronSerParsedos() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosEntradasCorruptas.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(2, datosCorruptos.size());
		assertEquals(
				"Cliente en fichero nº: 5 Datos: hola (No ha sido posible parsear los datos a enteros.)",
				it.next());
		assertEquals(
				"Cliente en fichero nº: 11 Datos: adios (No ha sido posible parsear los datos a enteros.)",
				it.next());

	}

	@Test
	public void testDatosEntradasNegativas() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosEntradasNegativas.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(3, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 2 Datos: 3,2000,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 6 Datos: 7,2990,-434 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 12 Datos: 13,5340,-245 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());

	}

	@Test
	public void testDatosIceNoOrdenado() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosIceNoOrdenado.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(3, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 1 Datos: 2,189,321 (" + Programa.ERROR_DATO_NO_ORDENADO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 3 Datos: 4,-2101,212 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Cliente en fichero nº: 5 Datos: 6,784,232 (" + Programa.ERROR_DATO_NO_ORDENADO
				+ ")", it.next());

	}

	@Test
	public void testDatosIceFueraDeRango() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosIceFueraDeRango.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(2, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 3 Datos: 4,12101,212 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Cliente en fichero nº: 8 Datos: 9,13890,450 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());

	}

	@Test
	public void testDatosCorruptosGeneral() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCorruptosGenerales.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(7, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 0 Datos: 1,-1850,232 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 2 Datos: 3,2000,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 3 Datos: 4,12101,212 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals(
				"Cliente en fichero nº: 5 Datos: Efectivamente,noestoybienescrito (No ha sido posible parsear los datos a enteros.)",
				it.next());
		assertEquals("Cliente en fichero nº: 8 Datos: 8,13890,450 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Cliente en fichero nº: 11 Datos: 11,4245,512 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Cliente en fichero nº: 13 Datos: 13,3340,536 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());

	}

	@Test
	public void testLineasEnBlanco() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosLineasEnBlanco.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		assertEquals(0, Programa.getDatosCorruptos().size());
		assertEquals(5, Programa.getFraudes().size());

	}
	
	@Test
	public void testMochilaSimple(){
		
		ArrayList<Cliente> fraudes = new ArrayList<Cliente>();
		Cliente toAdd = new Cliente(1, 1, 50);
		toAdd.setTiempo(1980*2);
		fraudes.add(toAdd);
		toAdd = new Cliente(2, 1, 70);
		toAdd.setTiempo(1980*4);
		fraudes.add(toAdd);
		toAdd = new Cliente(3, 1, 10);
		toAdd.setTiempo(1980);
		fraudes.add(toAdd);
		toAdd = new Cliente(4, 1, 80);
		toAdd.setTiempo(1980*2);
		fraudes.add(toAdd);
		toAdd = new Cliente(5, 1, 100);
		toAdd.setTiempo(1980*3);
		fraudes.add(toAdd);
		
		Mochila m = new Mochila(fraudes, 1980);
		LinkedList<Cliente> r = m.maxBeneficio();
		assertTrue(r.contains(fraudes.get(3)));
		assertTrue(r.contains(fraudes.get(4)));
	}
	
	@Test
	public void testMochilaElementosEquivalesntes(){
		
		ArrayList<Cliente> fraudes = new ArrayList<Cliente>();
		Cliente toAdd = new Cliente(1, 1, 10);
		toAdd.setTiempo(1980);
		fraudes.add(toAdd);
		toAdd = new Cliente(2, 1, 10);
		toAdd.setTiempo(1980);
		fraudes.add(toAdd);
		toAdd = new Cliente(3, 1, 10);
		toAdd.setTiempo(1980*4);
		fraudes.add(toAdd);
		toAdd = new Cliente(4, 1, 10);
		toAdd.setTiempo(1980*4);
		fraudes.add(toAdd);
		toAdd = new Cliente(5, 1, 20);
		toAdd.setTiempo(1980*3);
		fraudes.add(toAdd);
		
		Mochila m = new Mochila(fraudes, 1980);
		LinkedList<Cliente> r = m.maxBeneficio();
		assertTrue(r.contains(fraudes.get(0)));
		assertTrue(r.contains(fraudes.get(1)));
		assertTrue(r.contains(fraudes.get(4)));
	}
	
	@Test
	public void testNoSeNecesitaMochila(){
		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "82Usuarios.txt";

		Programa.setFile(new File(directorioEntrada1));
		try {
			assertTrue(Programa.leerArchivo2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNoSeNecesitaMochila2(){
		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "100Usuarios.txt";

		Programa.setFile(new File(directorioEntrada1));
		try {
			assertTrue(Programa.leerArchivo2());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSeNecesitaMochila(){
		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "400Usuarios.txt";

		Programa.setFile(new File(directorioEntrada1));
		try {
			assertFalse(Programa.leerArchivo2());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testNoMCD(){
		Programa.setFraudes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "400Usuarios.txt";

		Programa.setFile(new File(directorioEntrada1));
		try {
			Programa.leerArchivo();
			assertEquals(Programa.bloqueOptimo(), 15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMCD1(){
		
		ArrayList<Cliente> fraudes = new ArrayList<Cliente>();
		Cliente toAdd = new Cliente(1, 1, 50);
		toAdd.setTiempo(30);
		fraudes.add(toAdd);
		toAdd = new Cliente(2, 1, 70);
		toAdd.setTiempo(60);
		fraudes.add(toAdd);
		toAdd = new Cliente(3, 1, 10);
		toAdd.setTiempo(90);
		fraudes.add(toAdd);
		toAdd = new Cliente(4, 1, 80);
		toAdd.setTiempo(120);
		fraudes.add(toAdd);
		toAdd = new Cliente(5, 1, 100);
		toAdd.setTiempo(150);
		fraudes.add(toAdd);
		
		Programa.setFraudes(fraudes);;
		assertEquals(Programa.bloqueOptimo(), 30);
		
	}
	
	@Test
	public void testMCD2(){
		
		ArrayList<Cliente> fraudes = new ArrayList<Cliente>();
		Cliente toAdd = new Cliente(1, 1, 50);
		toAdd.setTiempo(75);
		fraudes.add(toAdd);
		toAdd = new Cliente(2, 1, 70);
		toAdd.setTiempo(75*2);
		fraudes.add(toAdd);
		toAdd = new Cliente(3, 1, 10);
		toAdd.setTiempo(75*3);
		fraudes.add(toAdd);
		toAdd = new Cliente(4, 1, 80);
		toAdd.setTiempo(75*4);
		fraudes.add(toAdd);
		toAdd = new Cliente(5, 1, 100);
		toAdd.setTiempo(75*5);
		fraudes.add(toAdd);
		
		Programa.setFraudes(fraudes);;
		assertEquals(Programa.bloqueOptimo(), 75);
		
	}
	
	@Test
	public void tiempocorrecto(){
		
		Cliente c = new Cliente(1, 1, 1);
		assertEquals(c.getTiempo(), 30);
		
		c = new Cliente(1, 2500, 1);
		assertEquals(c.getTiempo(), 60);
		
		c = new Cliente(1, 7500, 1);
		assertEquals(c.getTiempo(), 105);
		
		c = new Cliente(1, 5028, 1);
		assertEquals(c.getTiempo(), 90);
		
		c = new Cliente(1, 10000, 1);
		assertEquals(c.getTiempo(), 120);
	}
	
}
