
public class Q1 {
	public static void main (String args[]) {
		//System.out.println(power(4,2));
		boolean[][] pic = new boolean[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				pic[i][j] = false;
			}
		}
		pic[1][2] = true;
		pic[0][2] = true;
		pic[2][1] = true;
		pic[3][4] = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(pic[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		erase(pic, 2, 2);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(pic[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int product( int K, int M ) {
		if (M == 1) {
			return K;
		}
		K = K + product(K, M - 1);
		return K;
	}
	
	/**
	 * 
	 * b^0 = 1
	 * b^e = (b ¡Á b)^e/2	if e is positive and even
	 * b^e = b ¡Á (b ¡Á b)^(e - 1)/2	if e is positive and odd
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 */
	public static double power( double base, int exponent ) {
		//basic step:
		if (exponent == 0) {
			return 1;
		}
		
		//recursive steps:
		base = base * power(base, exponent - 1);
		return base;
	}
	
	public static void erase( boolean[][] pic, int row, int col ) {
		if (row >= pic.length || col >= pic[row].length) {
			 return;
		}
		
		if (row == 0 && col == 0) {
			pic[0][0] = false;
		}
		
		if (row > 0 && col == 0) {
			for (int i = 0; i <= row; i++) {
				pic[row - i][col] = false;
			}
		}
		
		if (col > 0 && row == 0) {
			for (int i = 0; i <= col; i++) {
				pic[row][col - i] = false;
			}
		}
		
		if (row > 0 && col > 0) {
			for (int i = 0; i <= row; i++) {
				pic[row - i][col] = false;
			}
			for (int i = 0; i <= col; i++) {
				pic[row][col - i] = false;
			}
			erase(pic, row - 1, col - 1);
		}		
	}
}
