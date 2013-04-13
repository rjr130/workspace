import java.util.Scanner;

/**Convert the input number into Ordinal Number String
 * 
 * Question2 from StudyBlue for summer internship position:
 * Construct a method that takes any positive integer as an input and returns 
 * the Ordinal Number String. For example: Input = 14, Output = "14th"
 * 
 * @author Junrui Ruan
 *
 */
public class OrdinalNumberString {
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("If you want to quit the program, please input Q," +
				"\notherwise, press enter to input data:");
		while (!in.nextLine().equals("Q")) {
			input();
			System.out.println("If you want to quit the program, " +
					"please input Q," +
					"\notherwise, press enter to input data:");
		}
		System.out.println("Thanks for testing. Question2 Ended.");
	}
	
	/**
	 * input the data and make sure the input would only be integers
	 */
	public static void input () {
		Scanner in = new Scanner(System.in);
		System.out.println("Please type in an integer number");
		while (!in.hasNextInt()) {
			System.out.println("Error! Your input value should be an integer " +
					"which value should be between 1 to 2147483647");
			in.nextLine();
		}
		int input = in.nextInt();
		print(input);
	}
	
	/**
	 * Generate the ordinal number string and print 
	 * @param value
	 */
	public static void print (int value) {
		int hundredRemainder = value % 100;
		int tenRemainder = value % 10;
		// For the xx11th, xx12th exceptions
		if(hundredRemainder - tenRemainder == 10) {
			System.out.println(value + "th");
		}
		
		// Normal Cases
		else {
			switch (tenRemainder) {
				case 1:
					System.out.println(value + "st");
					break;
				case 2:
					System.out.println(value + "nd");
					break;
				case 3:
					System.out.println(value + "rd");
					break;
				default:
					System.out.println(value + "th");
			}
		}
		System.out.println("\n");
	}
}
