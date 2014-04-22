package utilidades;

public class SquareMatrix {
	
	private int size;
	private int[][] matrixData;
	
	public SquareMatrix(int size){
		this.size = size;
		matrixData = new int[size][size];
	}
	
	public SquareMatrix(int[][] mData){
		this.matrixData = mData;
		size = mData.length;
	}
	
	public void setData(int i, int j, int data){
		matrixData[i][j] = data;
	}
	
	public int getData(int i, int j){
		return matrixData[i][j];
	}
	
	public SquareMatrix getSubMatrix(int i, int j, int size){
		int[][] sMData = new int[size][size];
		for(int a = i, c = 0; c < size; a++, c++)
			for(int b = j, d = 0; d < size; b++, d++)
				sMData[c][d] = matrixData[a][b];
		return new SquareMatrix(sMData);
	}
	
	public void setSubMatrix(int i, int j, SquareMatrix matrix){
		int size = matrix.size;
		int[][] sMData = matrix.matrixData;
		for(int a = i, c = 0; c < size; a++, c++)
			for(int b = j, d = 0; d < size; b++, d++)
				  matrixData[a][b] = sMData[c][d];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getMatrixData() {
		return matrixData;
	}

	public void setMatrixData(int[][] matrixData) {
		this.matrixData = matrixData;
	}
	
	public static SquareMatrix sumar(SquareMatrix a, SquareMatrix b){
		if(a.size != b.size) return null;
		int size = a.size;
		SquareMatrix c = new SquareMatrix(size);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				c.setData(i, j, a.getData(i, j) + b.getData(i, j));
		return c;
	}
	
	public static SquareMatrix restar(SquareMatrix a, SquareMatrix b){
		if(a.size != b.size) return null;
		int size = a.size;
		SquareMatrix c = new SquareMatrix(size);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				c.setData(i, j, a.getData(i, j) - b.getData(i, j));
		return c;
	}
	
	public static SquareMatrix generarMatrizAleatoria(int size, int maxData){
		int[][] data = new int[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) data[i][j] = (int)Math.round(Math.random() * maxData);
		return new SquareMatrix(data);
	}

}
