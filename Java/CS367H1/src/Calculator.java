
public class Calculator {
	public static void main (String args[]) {
		/*int result = 1;
		/*for (int i = 0; i < 18; i++) {
			result = result * 2;
		}
		System.out.println(result);*/
		/*result = 987255335 % 97;
		System.out.println(result);*/
		LCG(3);
	}
	
	public static void LCG(int x) {
		int x0 = x;
		int x1 = 0;
		if (x1 > 10000) {
			System.out.println("end");
			return;
		}
		x1 = (4 * x0 + 1) % 7;
		System.out.println(x1);
		LCG(x1);
	}
}
