package lecture;

import java.io.*; 	// need this to get user input
import java.util.*;

/**
 * Anne Bracy & 131 students
 * Running lecture examples for CSE 131, Spring 2012  
 */

/**
 * This is a program that uses a multi-dimensional array to wish
 * my son a happy birthday.
 */
public class HelperMethods {

	private static String[] presentList = {"legos", "doll", "puzzle", "gummibears", "stickers", "playdough", 
		"bike", "beads", "mask", "iPod", "iPad", "bathrobe", "CD", "stuffed rhino", "easel", "markers", 
		"walkie talkies"};
	
	// the scanner is a utility we will use to get input from the user.
	// it's okay if you don't understand this part just yet
	private static Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");

	/* gets an integer from the user
	 * that's it!
	 */
	public static int getInteger(String s) {
		System.out.println(s);
		int i = scanner.nextInt();
		scanner.nextLine(); // throw away the "return"
		return i;
	}
	
	/* gets a String from the user
	 * that's it!
	 */
	public static String getString(String s) {
		System.out.println(s);
		return scanner.nextLine(); // return the string
	}
	
	/* returns a present from the present list
	 */
	public static String getPresent() {
		Random picker = new Random();   	// will give us a random integer
		int i = picker.nextInt(presentList.length);
		return presentList[i];
	}
}
