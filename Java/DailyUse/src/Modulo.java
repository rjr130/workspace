
public class Modulo {
	public static void main (String args[]) {
		//System.out.println(modulo (4, 6, 1));
		program();
	}
	
	static int modulo (int n, int m, int product) {
		if (n == 1) {
			return (product * n) % m;
		}
		
		/*if (n > m) {
			return 0;
		}
		
		if ((product % m) == 0) {
			return 0;
		}*/
		
		else {
			product = ( product * n ) % m;
			return modulo (n-1, m, product);
		}
		
		//return product % m;
	}
	
	void program () {
		Integer a = 100;
		int b = a.hashCode();
		System.out.println(b);
	}
}
