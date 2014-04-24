package utilidades;

public class test {

	public static void main(String[] args) {
		int[][] vector = new int[9][2];
		vector[0][0] = 1;
		vector[0][1] = 3;

		vector[1][0] = 2;
		vector[1][1] = 6;

		vector[2][0] = 3;
		vector[2][1] = 7;

		vector[3][0] = 10;
		vector[3][1] = 17;

		vector[4][0] = 11;
		vector[4][1] = 25;

		vector[5][0] = 12;
		vector[5][1] = 32;

		vector[6][0] = 13;
		vector[6][1] = 45;

		vector[7][0] = 14;
		vector[7][1] = 56;

		vector[8][0] = 128;
		vector[8][1] = 67;
		
		
		System.out.println(mediaOchoCercanos(3, vector));

	}

	public static int  mediaOchoCercanos(int pos, int[][] vector) {
		int sum=0;
		int izq = 1, der = 1;
		for (int i = 0; i < 8; i++) {
			
			if (pos - izq < 0) {
				for(;i<8;i++){
					sum +=vector[pos + der][1];
					der++;
				}
				break;
			}
			
			if (pos + der >= vector.length) {
				for(;i<8;i++){
					sum +=vector[pos - izq][1];
					izq++;
				}
				break;
			}
			
			if (vector[pos + der][0] - vector[pos][0] <= vector[pos][0] - vector[pos - izq][0]) {
				sum +=vector[pos + der][1];
				der++;
			} else {
				sum +=vector[pos - izq][1];
				izq++;
			}
		}
		
		return sum/8;
	}

}
