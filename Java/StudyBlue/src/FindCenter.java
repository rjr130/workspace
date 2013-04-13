/**
 * Class for the Question 1
 * 
 * Question1 from StudyBlue for summer internship position:
 * Given a Window's height and width and a Frame's height and width, construct a
 * method to determine the upper-left corner position of the Frame, such that 
 * the Frame would be centered in the Window.
 * 
 * @author Junrui Ruan
 *
 */
import java.util.*;

public class FindCenter {
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("If you want to quit the program, please input Q," +
				"\notherwise, press enter to input data:");
		while (!in.nextLine().equals("Q")) {
			Windows win = new Windows();
			Frames frame = new Frames();
			// Input the input values
			input(win, frame);	
			findCenterPosition(win, frame);
			print(frame);
			System.out.println("Please input the data, if you want to quit " +
					"the program, please input Q");
		}
		System.out.println("Thanks for testing. Question1 Ended.");
	}	
	
	/**Handle the data passed in and find the desired position for frame's
	 * upper-left point.
	 * 
	 * @param win Window object
	 * @param frame Frame object
	 */
	public static void findCenterPosition (Windows win, Frames frame) {
		double winHeight = win.getWinHeight();
		double winWidth = win.getWinWidth();
		double frameHeight = frame.getFrameHeight();
		double frameWidth = frame.getFrameWidth();
		if ((winHeight < frameHeight) || (winWidth < frameWidth)) {
			System.out.println("Frame is larger than Windows, please change " +
					"the input values\n\n");
			// Intentionally set two flag values for print.
			frame.setFrameX(-1);
			frame.setFrameY(-1);
		}
		else {
			// Set the frame's upper-left position
			double y = (winHeight + frameHeight) / 2;
			frame.setFrameY(y);
			double x = (winWidth - frameWidth) / 2;
			frame.setFrameX(x);
		}
	}
	
	/**Input the data
	 * 
	 * @param win Window object
	 * @param frame Frame object
	 */
	public static void input (Windows win, Frames frame) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please type in the Window's height");
		while (!in.hasNextDouble()) {
			System.out.println("Error! Your input value should be a number");
			in.nextLine();
		}
		win.setWinHeight(in.nextDouble());		
		in.nextLine();
		System.out.println("Please type in the Window's width");
		while (!in.hasNextDouble()) {
			System.out.println("Error! Your input value should be a number");
			in.nextLine();
		}
		win.setWinWidth(in.nextDouble());
		in.nextLine();
		System.out.println("Please type in the Frame's height");
		while (!in.hasNextDouble()) {
			System.out.println("Error! Your input value should be a number");
			in.nextLine();
		}
		frame.setFrameHeight(in.nextDouble());
		in.nextLine();
		System.out.println("Please type in the Frame's width");	
		while (!in.hasNextDouble()) {
			System.out.println("Error! Your input value should be a number");
			in.nextLine();
		}
		frame.setFrameWidth(in.nextDouble());
		in.nextLine();
	}
	
	/**Print the result
	 * 
	 * @param frame Frame object
	 */
	public static void print (Frames frame) {
		if (frame.getFrameX() != -1) {
		System.out.println("The centered frame's upper-left position's height" +
				" is: " + frame.getFrameY() + "\nand the width value is: " + 
					frame.getFrameX() + "\n\n");
		}
	}
}
