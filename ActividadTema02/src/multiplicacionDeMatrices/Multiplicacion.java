package multiplicacionDeMatrices;

import utilidades.SquareMatrix;

public class Multiplicacion{
	
	public static SquareMatrix fBruta(SquareMatrix a, SquareMatrix b) {
		
		int size = a.getSize();
		if(a.getSize() != b.getSize()) return null;
		SquareMatrix c = new SquareMatrix(size);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				int suma = 0;
				for(int k = 0; k < size; k++) suma += a.getData(i, k) * b.getData(k, j);
				c.setData(i, j, suma);
			}
		return c;
	}
	
	public static SquareMatrix dyV(SquareMatrix a, SquareMatrix b) {
		int size = a.getSize();
		if(a.getSize() != b.getSize()) return null;
		return dyV(a, b, size);
	}

	private static SquareMatrix dyV(SquareMatrix a, SquareMatrix b, int size) {
		SquareMatrix c = new SquareMatrix(size);
		if(size == 1){
			c.setData(0, 0, a.getData(0, 0) * b.getData(0, 0));
			return c;
		}
		size = size/2;
		SquareMatrix m1 = dyV(a.getSubMatrix(0, 0, size), b.getSubMatrix(0, 0, size), size);
		SquareMatrix m2 = dyV(a.getSubMatrix(0, size, size), b.getSubMatrix(size, 0, size), size);
		SquareMatrix m3 = dyV(a.getSubMatrix(0, 0, size), b.getSubMatrix(0, size, size), size);
		SquareMatrix m4 = dyV(a.getSubMatrix(0, size, size), b.getSubMatrix(size, size, size), size);
		SquareMatrix m5 = dyV(a.getSubMatrix(size, 0, size), b.getSubMatrix(0, 0, size), size);
		SquareMatrix m6 = dyV(a.getSubMatrix(size, size, size), b.getSubMatrix(size, 0, size), size);
		SquareMatrix m7 = dyV(a.getSubMatrix(size, 0, size), b.getSubMatrix(0, size, size), size);
		SquareMatrix m8 = dyV(a.getSubMatrix(size, size, size), b.getSubMatrix(size, size, size), size);
		
		c.setSubMatrix(0, 0, SquareMatrix.sumar(m1, m2));
		c.setSubMatrix(0, size, SquareMatrix.sumar(m3, m4));
		c.setSubMatrix(size, 0, SquareMatrix.sumar(m5, m6));
		c.setSubMatrix(size, size, SquareMatrix.sumar(m7, m8));
		
		return c;
	}
	
	public static SquareMatrix strassen(SquareMatrix a, SquareMatrix b) {
		int size = a.getSize();
		if(a.getSize() != b.getSize()) return null;
		return strassen(a, b, size);
	}

	private static SquareMatrix strassen(SquareMatrix a, SquareMatrix b, int size) {
		SquareMatrix c = new SquareMatrix(size);
		if(size == 1){
			c.setData(0, 0, a.getData(0, 0) * b.getData(0, 0));
			return c;
		}
		size = size/2;
		SquareMatrix m1 = strassen(SquareMatrix.restar(a.getSubMatrix(0, size, size), a.getSubMatrix(size, size, size)), SquareMatrix.sumar(b.getSubMatrix(size, 0, size), b.getSubMatrix(size, size, size)), size);
		SquareMatrix m2 = strassen(SquareMatrix.sumar(a.getSubMatrix(0, 0, size), a.getSubMatrix(size, size, size)), SquareMatrix.sumar(b.getSubMatrix(0, 0, size), b.getSubMatrix(size, size, size)), size);
		SquareMatrix m3 = strassen(SquareMatrix.restar(a.getSubMatrix(size, 0, size), a.getSubMatrix(0, 0, size)), SquareMatrix.sumar(b.getSubMatrix(0, 0, size), b.getSubMatrix(0, size, size)), size);
		SquareMatrix m4 = strassen(SquareMatrix.sumar(a.getSubMatrix(0, 0, size), a.getSubMatrix(0, size, size)), b.getSubMatrix(size, size, size), size);
		SquareMatrix m5 = strassen(a.getSubMatrix(0, 0, size), SquareMatrix.restar(b.getSubMatrix(0, size, size), b.getSubMatrix(size, size, size)), size);
		SquareMatrix m6 = strassen(a.getSubMatrix(size, size, size), SquareMatrix.restar(b.getSubMatrix(size, 0, size), b.getSubMatrix(0, 0, size)), size);
		SquareMatrix m7 = strassen(SquareMatrix.sumar(a.getSubMatrix(size, 0, size), a.getSubMatrix(size, size, size)), b.getSubMatrix(0, 0, size), size);
		
		
		c.setSubMatrix(0, 0, SquareMatrix.sumar(SquareMatrix.sumar(m1, SquareMatrix.restar(m2, m4)), m6));
		c.setSubMatrix(0, size, SquareMatrix.sumar(m4, m5));
		c.setSubMatrix(size, 0, SquareMatrix.sumar(m6, m7));
		c.setSubMatrix(size, size, SquareMatrix.sumar(SquareMatrix.restar(m2, m7), SquareMatrix.sumar(m5, m3)));
		
		return c;
	}

}
