package practica01;

import org.junit.Before;
import org.junit.Test;

import programa.Programa;
import utilidades.Cliente;
import utilidades.Pareto;
import version04.ParetoV4;
import exceptions.EmptyFileException;
import exceptions.HeaderOutOfRangeException;
import exceptions.LinesNotEqualsHeaderException;
import exceptions.NegativeNumberException;
import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Practica01Test {
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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
			NegativeNumberException, IOException, LinesNotEqualsHeaderException, HeaderOutOfRangeException {

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
		assertEquals("Id: 5 Datos: hola (No ha sido posible parsear los datos a enteros.)", it.next());
		assertEquals("Id: 11 Datos: adios (No ha sido posible parsear los datos a enteros.)", it.next());

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
		assertEquals("Id: 2 Datos: 2000,-654 (" + Programa.ERROR_DATO_NEGATIVO + ")", it.next());
		assertEquals("Id: 6 Datos: 2990,-434 (" + Programa.ERROR_DATO_NEGATIVO + ")", it.next());
		assertEquals("Id: 12 Datos: 5340,-245 (" + Programa.ERROR_DATO_NEGATIVO + ")", it.next());

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
		assertEquals("Id: 1 Datos: 189,321 (" + Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Id: 3 Datos: -2101,212 (" + Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Id: 5 Datos: 784,232 (" + Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());

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
		assertEquals("Id: 3 Datos: 12101,212 (" + Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Id: 8 Datos: 13890,450 (" + Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());

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
		assertEquals("Id: 0 Datos: -1850,232 (" + Programa.ERROR_DATO_NEGATIVO + ")", it.next());
		assertEquals("Id: 2 Datos: 2000,-654 (" + Programa.ERROR_DATO_NEGATIVO + ")", it.next());
		assertEquals("Id: 3 Datos: 12101,212 (" + Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Id: 5 Datos: Efectivamente,noestoybienescrito (No ha sido posible parsear los datos a enteros.)", it.next());
		assertEquals("Id: 8 Datos: 13890,450 (" + Programa.ERROR_ICE_FUERA_DE_RANGO + ")", it.next());
		assertEquals("Id: 11 Datos: 4245,512 (" + Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());
		assertEquals("Id: 13 Datos: 3340,536 (" + Programa.ERROR_DATO_NO_ORDENADO + ")", it.next());

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
	public void testParetoSimple() {

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

		Pareto pareto = new ParetoV4(clientes);
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

	public void testFronteraRecta() {

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

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(4, resultados.size());
		assertTrue(resultados.contains(clientes.get(0)));
		assertTrue(resultados.contains(clientes.get(4)));
		assertTrue(resultados.contains(clientes.get(7)));
		assertTrue(resultados.contains(clientes.get(12)));

	}

	@Test
	public void testCombinacion1() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 6, 6));
		clientes.add(new Cliente(6, 7, 7));
		clientes.add(new Cliente(7, 8, 8));
		clientes.add(new Cliente(8, 9, 9));
		clientes.add(new Cliente(9, 10, 10));
		clientes.add(new Cliente(10, 11, 11));
		clientes.add(new Cliente(11, 12, 12));
		clientes.add(new Cliente(12, 13, 13));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(13, resultados.size());

	}

	@Test
	public void testCombinacion2() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 3));
		clientes.add(new Cliente(3, 4, 4));
		clientes.add(new Cliente(4, 5, 5));
		clientes.add(new Cliente(5, 6, 6));
		clientes.add(new Cliente(6, 7, 7));
		clientes.add(new Cliente(7, 5, 8));
		clientes.add(new Cliente(8, 6, 9));
		clientes.add(new Cliente(9, 7, 10));
		clientes.add(new Cliente(10, 8, 11));
		clientes.add(new Cliente(11, 9, 12));
		clientes.add(new Cliente(12, 10, 13));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(11, resultados.size());

	}

	@Test
	public void testCombinacion3() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 8, 2));
		clientes.add(new Cliente(2, 9, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 11, 5));
		clientes.add(new Cliente(5, 12, 6));
		clientes.add(new Cliente(6, 13, 7));
		clientes.add(new Cliente(7, 3, 8));
		clientes.add(new Cliente(8, 4, 9));
		clientes.add(new Cliente(9, 5, 10));
		clientes.add(new Cliente(10, 6, 11));
		clientes.add(new Cliente(11, 7, 12));
		clientes.add(new Cliente(12, 8, 13));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(7, resultados.size());

	}
	
	@Test
	public void testCombinacion4MismoICE() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 8, 2));
		clientes.add(new Cliente(2, 9, 3));
		clientes.add(new Cliente(3, 10, 4));
		clientes.add(new Cliente(4, 11, 5));
		clientes.add(new Cliente(5, 12, 6));
		clientes.add(new Cliente(6, 13, 7));
		clientes.add(new Cliente(7, 14, 7));
		clientes.add(new Cliente(8, 15, 9));
		clientes.add(new Cliente(9, 16, 10));
		clientes.add(new Cliente(10, 17, 11));
		clientes.add(new Cliente(11, 18, 12));
		clientes.add(new Cliente(12, 19, 13));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(12, resultados.size());

	}
	
	@Test
	public void testCasoBase1() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 3));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(3, resultados.size());

	}
	
	@Test
	public void testCasoBase2() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 3, 2));
		clientes.add(new Cliente(2, 2, 3));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(2, resultados.size());
		assertTrue(!resultados.contains(clientes.get(1)));

	}
	
	@Test
	public void testCasoBase3() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 3, 2));
		clientes.add(new Cliente(2, 2, 2));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(2, resultados.size());
		assertTrue(!resultados.contains(clientes.get(1)));

	}
	
	@Test
	public void testCasoBase4() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 3, 2));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(2, resultados.size());
		assertTrue(!resultados.contains(clientes.get(2)));

	}
	
	@Test
	public void testCasoBase5() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 2, 3));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(3, resultados.size());

	}
	
	@Test
	public void testCasoBase6() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 3));
		clientes.add(new Cliente(2, 2, 2));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(3, resultados.size());

	}
	
	@Test
	public void testCasoBase7() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>(13);
		clientes.add(new Cliente(0, 1, 1));
		clientes.add(new Cliente(1, 2, 2));
		clientes.add(new Cliente(2, 2, 2));

		Pareto pareto = new ParetoV4(clientes);
		LinkedList<Cliente> resultados = (LinkedList<Cliente>) pareto
				.paretoSolucion();

		assertEquals(3, resultados.size());

	}

}
