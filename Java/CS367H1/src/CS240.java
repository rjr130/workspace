import java.math.*;
public class CS240 {
	public static void main (String args[]) {
		double j = 0; 
		int limit = 8;
		double sum = 0;
		for (int i = 0; i <= limit; i++, j++) {
			sum = sum + Math.pow(2.0, j+1) - Math.pow(2.0, j);
		}
		System.out.println(sum);
		System.out.println(Math.pow(3.0, 8.0));
	}
}
