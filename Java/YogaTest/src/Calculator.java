import java.math.*;

public class Calculator {
	public static void main(String args[]) {
		double[] array = {15, 9, 18,10,5,12,8,5,8,10,7,2,1,5,3,5,15,10,15,9,8,18,1,2,11};
		double mean = 0;
		double deviation = 0;
		double sd = 0;
		double sum = 0;
		
		for (int i = 0; i < 25; i++) {
			sum += array[i];
		}
		mean = sum/25;
		for (int i = 0; i < 25; i++) {
			deviation += ( (array[i] - mean) * (array[i] - mean) );
		}
		deviation = deviation/24;
		sd = Math.sqrt(deviation);
		System.out.println(mean + "\n" + deviation + "\n" + sd);
		
	}
}
