package practica04;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import programa.Programa;
import utilidades.*;
import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import exceptions.NegativeNumberException;
import exceptions.TallerAlredyIncludeException;

public class Practica03Test {
	
	String directorioEntrada1;
	
	@Test(expected = EmptyFileException.class)
	public void testExcepcionArchivoVacio() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
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
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
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
		assertEquals("Cliente en fichero nº: 2 Datos: 3,20,1,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 6 Datos: 7,29,90,-434 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 12 Datos: 13,53,40,-245 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());

	}

	@Test
	public void testDatosCorruptosGeneral() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosCorruptosGenerales.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(3, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 0 Datos: 1,-18,50,232 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 2 Datos: 3,2,1,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Cliente en fichero nº: 5 Datos: Efectivamente,noestoybienescrito (No ha sido posible parsear los datos a enteros.)", it.next());

	}

	@Test
	public void testLineasEnBlanco() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosLineasEnBlanco.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		assertEquals(0, Programa.getDatosCorruptos().size());
		assertEquals(5, Programa.getEntry().size());

	}
	
	@Test
	public void testTiempoFueraDeRango() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosTiempofueraDeRango.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		LinkedList<String> datosCorruptos = Programa.getDatosCorruptos();
		Iterator<String> it = datosCorruptos.iterator();
		assertEquals(1, datosCorruptos.size());
		assertEquals("Cliente en fichero nº: 1 Datos: 2,1890,1,321 (" + Programa.ERROR_TIEMPO_FUERA_DE_RANGO
				+ ")", it.next());

	}
	
	@Test(expected = TallerAlredyIncludeException.class)
	public void testTallerYaAnadido() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException, TallerAlredyIncludeException {

		Programa.setEntry(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosDosTalleres.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();
	}
	
	@Test
	public void testBackTrackingSimple(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(0,45,5,8));
		clientes.add(new Cliente(1,60,6,9));
		clientes.add(new Cliente(2,60,0,3));
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 8, 4);
		
		ArrayList<Ruta> sol = bt.solucionBasica();
		
		Ruta ruta = sol.get(0);
		assertEquals(28, ruta.getDistancia());
	}
	
	@Test
	public void testRutaDistanciaCorrecta(){
		Ruta r = new Ruta(405, 0, 0);
		r.addClient(new Cliente(1, 30, 2, 3));
		r.addClient(new Cliente(1, 30, 0, 1));
		r.addClient(new Cliente(1, 30, 3, 2));
		assertEquals(18, r.getDistancia());
	}
	
	@Test
	public void testRutaTiempoCorrecta(){
		Ruta r = new Ruta(405, 0, 0);
		r.addClient(new Cliente(1, 30, 2, 3));
		r.addClient(new Cliente(1, 120, 0, 1));
		r.addClient(new Cliente(1, 60, 3, 2));
		assertEquals(225, r.getTiempo());
	}
	
	@Test
	public void testDistanciaHastaClienteCorrecta(){
		Cliente c, d;
		c = new Cliente(0, 30, 8, 3);
		d = new Cliente(0, 30, 2, 6);
		assertEquals(9, c.distanciaHasta(d));
		assertEquals(9, d.distanciaHasta(c));
	}
	
	@Test
	public void testRutaAddCliente(){
		Ruta r = new Ruta(405, 0, 0);
		r.addClient(new Cliente(1, 120, 2, 3));
		r.addClient(new Cliente(1, 120, 0, 1));
		r.addClient(new Cliente(1, 120, 3, 2));
		assertEquals(false, r.addClient(new Cliente(1, 120, 3, 2)));
		assertEquals(true, r.addClient(new Cliente(1, 30, 3, 2)));
	}
	
	@Test
	public void testRutaDeleteLast(){
		Ruta r = new Ruta(405, 0, 0);
		r.addClient(new Cliente(1, 120, 2, 3));
		assertEquals(true, r.deleteLast());
		assertEquals(false, r.deleteLast());
	}
	
	@Test
	public void testIndexCorrecto(){
		Programa.setEntry(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosEntradasNegativas.txt";

		Programa.setFile(new File(directorioEntrada1));
		try {
			Programa.leerArchivo();
			int i = 0;
			for(Cliente c: Programa.getEntry()){
				assertEquals(i, c.getIndex());
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBackTrackingSimpleMasDeUnaRuta(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(0,45,3,1));
		clientes.add(new Cliente(1,60,6,5));
		clientes.add(new Cliente(2,120,17,2));
		clientes.add(new Cliente(3,105,15,10));
		clientes.add(new Cliente(4,120,18,19));
		clientes.add(new Cliente(5,90,15,18));
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 14, 17);
		
		ArrayList<Ruta> sol = bt.solucionBasica();
		
		Ruta ruta = sol.get(0);
		assertEquals(60, ruta.getDistancia());
		assertEquals(345, ruta.getTiempo());
		ruta = sol.get(1);
		assertEquals(12, ruta.getDistancia());
		assertEquals(225, ruta.getTiempo());
	}
	
	@Test
	public void testBackTrackingComplejo(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(0,105,16,24));
		clientes.add(new Cliente(1,45,1,6));
		clientes.add(new Cliente(2,120,24,7));
		clientes.add(new Cliente(3,105,28,5));
		clientes.add(new Cliente(4,60,16,7));
		clientes.add(new Cliente(5,105,3,23));
		clientes.add(new Cliente(6,120,22,10));
		clientes.add(new Cliente(7,45,24,8));
		clientes.add(new Cliente(8,105,4,5));
		clientes.add(new Cliente(9,75,6,10));
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 2, 6);
		
		ArrayList<Ruta> sol = bt.solucionBasica();
		
		Ruta ruta = sol.get(0);
		assertEquals(70, ruta.getDistancia());
		assertEquals(360, ruta.getTiempo());
		ruta = sol.get(1);
		assertEquals(62, ruta.getDistancia());
		assertEquals(405, ruta.getTiempo());
		ruta = sol.get(2);
		assertEquals(8, ruta.getDistancia());
		assertEquals(165, ruta.getTiempo());
	}
	
	@Test
	public void testBackTrackingSimpleMejorado(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c = new Cliente(0,45,5,8);
		c.setIndex(0);
		clientes.add(c);
		
		c = new Cliente(1,60,6,9);
		c.setIndex(1);
		clientes.add(c);
		
		c = new Cliente(2,60,0,3);
		c.setIndex(2);
		clientes.add(c);
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 8, 4);
		
		ArrayList<Ruta> sol = bt.solucionBasica();
		
		Ruta ruta = sol.get(0);
		assertEquals(28, ruta.getDistancia());
	}
	
	@Test
	public void testBackTrackingSimpleMasDeUnaRutaMejorado(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c = new Cliente(0,45,3,1);
		c.setIndex(0);
		clientes.add(c);
		
		c = new Cliente(1,60,6,5);
		c.setIndex(1);
		clientes.add(c);
		
		c = new Cliente(2,120,17,2);
		c.setIndex(2);
		clientes.add(c);
		
		c = new Cliente(3,105,15,10);
		c.setIndex(3);
		clientes.add(c);
		
		c = new Cliente(4,120,18,19);
		c.setIndex(4);
		clientes.add(c);
		
		c = new Cliente(5,90,15,18);
		c.setIndex(5);
		clientes.add(c);
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 14, 17);
		
		ArrayList<Ruta> sol = bt.solucionBasica();
		
		Ruta ruta = sol.get(0);
		assertEquals(60, ruta.getDistancia());
		assertEquals(345, ruta.getTiempo());
		ruta = sol.get(1);
		assertEquals(12, ruta.getDistancia());
		assertEquals(225, ruta.getTiempo());
	}
	
	@Test
	public void testBackTrackingComplejoMejorado(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c = new Cliente(0,105,16,24);
		c.setIndex(0);
		clientes.add(c);
		
		c = new Cliente(1,45,1,6);
		c.setIndex(1);
		clientes.add(c);
		
		c = new Cliente(2,120,24,7);
		c.setIndex(2);
		clientes.add(c);
		
		c = new Cliente(3,105,28,5);
		c.setIndex(3);
		clientes.add(c);
		
		c = new Cliente(4,60,16,7);
		c.setIndex(4);
		clientes.add(c);
		
		c = new Cliente(5,105,3,23);
		c.setIndex(5);
		clientes.add(c);
		
		c = new Cliente(6,120,22,10);
		c.setIndex(6);
		clientes.add(c);
		
		c = new Cliente(7,45,24,8);
		c.setIndex(7);
		clientes.add(c);
		
		c = new Cliente(8,105,4,5);
		c.setIndex(8);
		clientes.add(c);
		
		c = new Cliente(9,75,6,10);
		c.setIndex(9);
		clientes.add(c);
		
		BacktrackingV1 bt = new BacktrackingV1(clientes, 405, 2, 6);
		
		ArrayList<Ruta> sol = bt.solucionMejorada();
		
		Ruta ruta = sol.get(0);
		assertEquals(72, ruta.getDistancia());
		assertEquals(405, ruta.getTiempo());
		ruta = sol.get(1);
		assertEquals(62, ruta.getDistancia());
		assertEquals(405, ruta.getTiempo());
		ruta = sol.get(2);
		assertEquals(6, ruta.getDistancia());
		assertEquals(120, ruta.getTiempo());
	}
	
}
