package practica02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import paretoDivision.ParetoDivision;
import paretoNube.ParetoNube;
import posSeleccion.PosSeleccionV1;
import posSeleccion.PosSeleccionV2;
import programa.Programa;
import utilidades.Cliente;
import utilidades.Pareto;
import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import exceptions.NegativeNumberException;

public class Practica02Test {
	String directorioEntrada;
	String directorioEntrada1;
	String memsaje;

	@Before
	public void setUp() throws Exception {

		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datos_eda_2.txt";
	}

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

		Programa.setClientes(new ArrayList<Cliente>());
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

		Programa.setClientes(new ArrayList<Cliente>());
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

		Programa.setClientes(new ArrayList<Cliente>());
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
				"Id: 5 Datos: hola (No ha sido posible parsear los datos a enteros.)",
				it.next());
		assertEquals(
				"Id: 11 Datos: adios (No ha sido posible parsear los datos a enteros.)",
				it.next());

	}

	@Test
	public void testDatosEntradasNegativas() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setClientes(new ArrayList<Cliente>());
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
		assertEquals("Id: 2 Datos: 2000,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Id: 6 Datos: 2990,-434 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Id: 12 Datos: 5340,-245 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());

	}

	@Test
	public void testDatosIceNoOrdenado() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setClientes(new ArrayList<Cliente>());
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
		assertEquals("Id: 1 Datos: 189,321 (" + Programa.ERROR_DATO_NO_ORDENADO
				+ ")", it.next());
		assertEquals("Id: 3 Datos: -2101,212 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Id: 5 Datos: 784,232 (" + Programa.ERROR_DATO_NO_ORDENADO
				+ ")", it.next());

	}

	@Test
	public void testDatosIceFueraDeRango() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setClientes(new ArrayList<Cliente>());
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
		assertEquals("Id: 3 Datos: 12101,212 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Id: 8 Datos: 13890,450 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());

	}

	@Test
	public void testDatosCorruptosGeneral() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setClientes(new ArrayList<Cliente>());
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
		assertEquals("Id: 0 Datos: -1850,232 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Id: 2 Datos: 2000,-654 (" + Programa.ERROR_DATO_NEGATIVO
				+ ")", it.next());
		assertEquals("Id: 3 Datos: 12101,212 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals(
				"Id: 5 Datos: Efectivamente,noestoybienescrito (No ha sido posible parsear los datos a enteros.)",
				it.next());
		assertEquals("Id: 8 Datos: 13890,450 ("
				+ Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Id: 11 Datos: 4245,512 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Id: 13 Datos: 3340,536 ("
				+ Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());

	}

	@Test
	public void testLineasEnBlanco() throws NumberFormatException,
			EmptyFileException, NegativeNumberException, IOException,
			LinesNotEqualsHeaderException, HeaderOutOfRangeException {

		Programa.setClientes(new ArrayList<Cliente>());
		Programa.setDatosCorruptos(new LinkedList<String>());
		directorioEntrada1 = System.getProperty("user.dir");

		directorioEntrada1 = directorioEntrada1 + File.separator + "test"
				+ File.separator + "testFiles" + File.separator
				+ "datosLineasEnBlanco.txt";

		Programa.setFile(new File(directorioEntrada1));
		Programa.leerArchivo();

		assertEquals(0, Programa.getDatosCorruptos().size());
		assertEquals(5, Programa.getClientes().size());

	}

	@Test
	public void testParetoSimpleDiv() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 5, 1));
		clientes.add(new Cliente(1, 10, 2));
		clientes.add(new Cliente(2, 4, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 3, 5));
		clientes.add(new Cliente(5, 10, 6));
		clientes.add(new Cliente(6, 2, 7));
		clientes.add(new Cliente(7, 10, 8));
		clientes.add(new Cliente(8, 3, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 4, 11));
		clientes.add(new Cliente(11, 10, 12));
		clientes.add(new Cliente(12, 5, 13));

		Pareto pareto = new ParetoDivision(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(7, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(2)));
		assertTrue(resultados.contains(clientes.get(4)));
		assertTrue(resultados.contains(clientes.get(6)));
		assertTrue(resultados.contains(clientes.get(8)));
		assertTrue(resultados.contains(clientes.get(10)));
		assertTrue(resultados.contains(clientes.get(12)));

	}

	@Test
	public void testFronteraRectaDiv() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 10, 2));
		clientes.add(new Cliente(2, 4, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 1, 5));
		clientes.add(new Cliente(5, 10, 6));
		clientes.add(new Cliente(6, 2, 7));
		clientes.add(new Cliente(7, 1, 8));
		clientes.add(new Cliente(8, 3, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 4, 11));
		clientes.add(new Cliente(11, 10, 12));
		clientes.add(new Cliente(12, 1, 13));

		Pareto pareto = new ParetoDivision(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(4, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(4)));
		assertTrue(resultados.contains(clientes.get(7)));
		assertTrue(resultados.contains(clientes.get(12)));

	}
	
	@Test
	public void testTratamientoMismoIceDiv(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 3, 3));
		clientes.add(new Cliente(2, 2, 3));
		clientes.add(new Cliente(3, 4, 5));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 7, 7));
		clientes.add(new Cliente(6, 6, 7));
		clientes.add(new Cliente(7, 8, 9));
		clientes.add(new Cliente(8, 9, 9));
		clientes.add(new Cliente(9, 11, 11));
		clientes.add(new Cliente(10, 10, 11));
		clientes.add(new Cliente(11, 12, 13));
		clientes.add(new Cliente(12, 13, 13));
		
		Pareto pareto = new ParetoDivision(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();
		
		assertEquals(7, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(2)));
		assertTrue(resultados.contains(clientes.get(3)));
		assertTrue(resultados.contains(clientes.get(6)));
		assertTrue(resultados.contains(clientes.get(7)));
		assertTrue(resultados.contains(clientes.get(10)));
		assertTrue(resultados.contains(clientes.get(11)));
		
	}
	
	@Test
	public void testTratamientoMismoCeyMismoPuntoDiv(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 1, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 3, 5));
		clientes.add(new Cliente(5, 6, 6));
		clientes.add(new Cliente(6, 6, 6));
		clientes.add(new Cliente(7, 8, 8));
		clientes.add(new Cliente(8, 9, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 11, 11));
		clientes.add(new Cliente(11, 12, 12));
		clientes.add(new Cliente(12, 13, 13));
		
		Pareto pareto = new ParetoDivision(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();
		
		assertEquals(12, resultados.size());
		assertFalse(resultados.contains(clientes.get(3)));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParetoSimpleNube() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 5, 1));
		clientes.add(new Cliente(1, 10, 2));
		clientes.add(new Cliente(2, 4, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 3, 5));
		clientes.add(new Cliente(5, 10, 6));
		clientes.add(new Cliente(6, 2, 7));
		clientes.add(new Cliente(7, 10, 8));
		clientes.add(new Cliente(8, 3, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 4, 11));
		clientes.add(new Cliente(11, 10, 12));
		clientes.add(new Cliente(12, 5, 13));

		Pareto pareto = new ParetoNube((ArrayList<Cliente>) clientes.clone());
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(7, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(2)));
		assertTrue(resultados.contains(clientes.get(4)));
		assertTrue(resultados.contains(clientes.get(6)));
		assertTrue(resultados.contains(clientes.get(8)));
		assertTrue(resultados.contains(clientes.get(10)));
		assertTrue(resultados.contains(clientes.get(12)));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFronteraRectaNube() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 10, 2));
		clientes.add(new Cliente(2, 4, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 1, 5));
		clientes.add(new Cliente(5, 10, 6));
		clientes.add(new Cliente(6, 2, 7));
		clientes.add(new Cliente(7, 1, 8));
		clientes.add(new Cliente(8, 3, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 4, 11));
		clientes.add(new Cliente(11, 10, 12));
		clientes.add(new Cliente(12, 1, 13));

		Pareto pareto = new ParetoNube((ArrayList<Cliente>) clientes.clone());
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(4, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(4)));
		assertTrue(resultados.contains(clientes.get(7)));
		assertTrue(resultados.contains(clientes.get(12)));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTratamientoMismoIceNube(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 3, 3));
		clientes.add(new Cliente(2, 2, 3));
		clientes.add(new Cliente(3, 4, 5));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 7, 7));
		clientes.add(new Cliente(6, 6, 7));
		clientes.add(new Cliente(7, 8, 9));
		clientes.add(new Cliente(8, 9, 9));
		clientes.add(new Cliente(9, 11, 11));
		clientes.add(new Cliente(10, 10, 11));
		clientes.add(new Cliente(11, 12, 13));
		clientes.add(new Cliente(12, 13, 13));
		
		Pareto pareto = new ParetoNube((ArrayList<Cliente>) clientes.clone());
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();
		
		assertEquals(7, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(2)));
		assertTrue(resultados.contains(clientes.get(3)));
		assertTrue(resultados.contains(clientes.get(6)));
		assertTrue(resultados.contains(clientes.get(7)));
		assertTrue(resultados.contains(clientes.get(10)));
		assertTrue(resultados.contains(clientes.get(11)));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTratamientoMismoCeyMismoPuntoNube(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 1, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 3, 5));
		clientes.add(new Cliente(5, 6, 6));
		clientes.add(new Cliente(6, 6, 6));
		clientes.add(new Cliente(7, 8, 8));
		clientes.add(new Cliente(8, 9, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 11, 11));
		clientes.add(new Cliente(11, 12, 12));
		clientes.add(new Cliente(12, 13, 13));
		
		Pareto pareto = new ParetoNube((ArrayList<Cliente>) clientes.clone());
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();
		
		assertEquals(12, resultados.size());
		assertFalse(resultados.contains(clientes.get(3)));
		
	}
	
	@Test
	public void testDiferenciaConMediaLocalV1(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(17);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 16, 16));
		clientes.add(new Cliente(5, 17, 17));
		clientes.add(new Cliente(6, 18, 18));
		clientes.add(new Cliente(7, 19, 19));
		clientes.add(new Cliente(8, 20, 20));
		clientes.add(new Cliente(9, 21, 21));
		clientes.add(new Cliente(10, 22, 22));
		clientes.add(new Cliente(11, 23, 23));
		clientes.add(new Cliente(12, 24, 24));
		clientes.add(new Cliente(13, 54, 54));
		clientes.add(new Cliente(14, 55, 55));
		clientes.add(new Cliente(15, 56, 56));
		clientes.add(new Cliente(16, 57, 57));
		
		
		PosSeleccionV1 ps = new PosSeleccionV1(clientes, null, 1);
		
		assertEquals((ps.mediaOchoCercanos(8) - clientes.get(8).getCe()), 0);
		
	}
	
	@Test
	public void testDiferenciaConMediaLocalV2(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(17);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 16, 16));
		clientes.add(new Cliente(5, 17, 17));
		clientes.add(new Cliente(6, 18, 18));
		clientes.add(new Cliente(7, 19, 19));
		clientes.add(new Cliente(8, 20, 20));
		clientes.add(new Cliente(9, 21, 21));
		clientes.add(new Cliente(10, 22, 22));
		clientes.add(new Cliente(11, 23, 23));
		clientes.add(new Cliente(12, 24, 24));
		clientes.add(new Cliente(13, 54, 54));
		clientes.add(new Cliente(14, 55, 55));
		clientes.add(new Cliente(15, 56, 56));
		clientes.add(new Cliente(16, 57, 57));
		
		
		PosSeleccionV2 ps = new PosSeleccionV2(clientes, null, 1);
		
		assertEquals((ps.mediaOchoCercanos(8) - clientes.get(8).getCe()), 0);
		
	}
	
	@Test
	public void testPosSeleccionV1(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(17);
		LinkedList<Cliente> candidatos = new LinkedList<Cliente>();
		LinkedList<Cliente> resultado;
		
		clientes.add(new Cliente(0, 101, 1));
		clientes.add(new Cliente(1, 102, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 104, 4));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 106, 6));
		clientes.add(new Cliente(6, 7, 7));
		clientes.add(new Cliente(7, 8, 8));
		clientes.add(new Cliente(8, 109, 9));
		clientes.add(new Cliente(9, 1, 10));
		clientes.add(new Cliente(10, 11, 111));
		clientes.add(new Cliente(11, 2, 12));
		clientes.add(new Cliente(12, 3, 13));
		clientes.add(new Cliente(13, 114, 14));
		clientes.add(new Cliente(14, 5, 15));
		clientes.add(new Cliente(15, 116, 16));
		clientes.add(new Cliente(16, 117, 17));
		candidatos.addAll(clientes);
		
		PosSeleccionV1 ps = new PosSeleccionV1(clientes, candidatos, 8);
		resultado = ps.seleccionar();
		
		assertTrue(resultado.contains(clientes.get(2)));
		assertTrue(resultado.contains(clientes.get(4)));
		assertTrue(resultado.contains(clientes.get(6)));
		assertTrue(resultado.contains(clientes.get(7)));
		assertTrue(resultado.contains(clientes.get(9)));
		assertTrue(resultado.contains(clientes.get(11)));
		assertTrue(resultado.contains(clientes.get(12)));
		assertTrue(resultado.contains(clientes.get(14)));
		
	}
	
	@Test
	public void testPosSeleccionV2(){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(17);
		LinkedList<Cliente> candidatos = new LinkedList<Cliente>();
		LinkedList<Cliente> resultado;
		
		clientes.add(new Cliente(0, 101, 1));
		clientes.add(new Cliente(1, 102, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 104, 4));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 106, 6));
		clientes.add(new Cliente(6, 7, 7));
		clientes.add(new Cliente(7, 8, 8));
		clientes.add(new Cliente(8, 109, 9));
		clientes.add(new Cliente(9, 1, 10));
		clientes.add(new Cliente(10, 11, 111));
		clientes.add(new Cliente(11, 2, 12));
		clientes.add(new Cliente(12, 3, 13));
		clientes.add(new Cliente(13, 114, 14));
		clientes.add(new Cliente(14, 5, 15));
		clientes.add(new Cliente(15, 116, 16));
		clientes.add(new Cliente(16, 117, 17));
		candidatos.addAll(clientes);
		
		PosSeleccionV2 ps = new PosSeleccionV2(clientes, candidatos, 8);
		resultado = ps.seleccionar();
		
		assertTrue(resultado.contains(clientes.get(2)));
		assertTrue(resultado.contains(clientes.get(4)));
		assertTrue(resultado.contains(clientes.get(6)));
		assertTrue(resultado.contains(clientes.get(7)));
		assertTrue(resultado.contains(clientes.get(9)));
		assertTrue(resultado.contains(clientes.get(11)));
		assertTrue(resultado.contains(clientes.get(12)));
		assertTrue(resultado.contains(clientes.get(14)));
		
	}

}
