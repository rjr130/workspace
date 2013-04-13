package HW2;

/**
 * This program allows the user to trace program execution under different
 * scenarios involving exceptions. To use this program, make sure to download
 * (or create) the following RuntimeExceptions (all should be in the same folder
 * as this program): RedException BlueException YellowException GreenException
 * OrangeException
 * 
 * To compile (from the command line): javac *.java
 * 
 * To run (from the command line): java ExceptionTester mmm ccc 
 * where mmm is c, d, or e and ccc is red, blue, yellow, green, or orange
 * 
 * @author Beck Hasti, copyright 2008-2012
 */
public class ExceptionTester {
	private static String color;  // holds the color from the command line
	private static String method; // holds the method from the command line

	public static void main(String[] args) {

		// Set up
		if (args.length != 2) {
			System.out.println("Need a method and a color as args");
			System.exit(1);
		}
		method = args[0];
		color = args[1];

		// This is where the interesting stuff starts to happen

		System.out.print("main[");

		try {
			methodA();
			System.out.print("after A,");

			methodE();
			System.out.print("after E,");

		} catch (RedException exc) {
			System.out.print("red,");

		} catch (GreenException exc) {
			System.out.print("green,");
		}

		System.out.println("]main");
	}

	/*
	 * A private method with a try and one catch
	 */
	private static void methodA() {
		System.out.print("\nA[");

		try {
			methodB();
			System.out.print("after B,");

		} catch (BlueException exc) {
			System.out.print("blue,");
		}

		System.out.println("]A");
	}

	/*
	 * A private method with a try, two catches, and a throw
	 */
	private static void methodB() {
		System.out.print("\nB[");

		try {
			methodC();
			System.out.print("after C,");

		} catch (YellowException exc) {
			System.out.print("yellow,");
			throw new GreenException();

		} catch (RedException exc) {
			System.out.print("red,");
		}

		methodD();
		System.out.print("after D,");

		System.out.println("]B");
	}

	/*
	 * A private exception generating method.
	 */
	private static void methodC() {
		if (method.equalsIgnoreCase("C")) {
			if (color.equalsIgnoreCase("red"))
				throw new RedException();
			else if (color.equalsIgnoreCase("blue"))
				throw new BlueException();
			else if (color.equalsIgnoreCase("green"))
				throw new GreenException();
			else if (color.equalsIgnoreCase("yellow"))
				throw new YellowException();
			else if (color.equalsIgnoreCase("orange"))
				throw new OrangeException();
		}
	}

	/*
	 * A private exception generating method.
	 */
	private static void methodD() {
		if (method.equalsIgnoreCase("D")) {
			if (color.equalsIgnoreCase("red"))
				throw new RedException();
			else if (color.equalsIgnoreCase("blue"))
				throw new BlueException();
			else if (color.equalsIgnoreCase("green"))
				throw new GreenException();
			else if (color.equalsIgnoreCase("yellow"))
				throw new YellowException();
			else if (color.equalsIgnoreCase("orange"))
				throw new OrangeException();
		}
	}

	/*
	 * A private exception generating method.
	 */
	private static void methodE() {
		if (method.equalsIgnoreCase("E")) {
			if (color.equalsIgnoreCase("red"))
				throw new RedException();
			else if (color.equalsIgnoreCase("blue"))
				throw new BlueException();
			else if (color.equalsIgnoreCase("green"))
				throw new GreenException();
			else if (color.equalsIgnoreCase("yellow"))
				throw new YellowException();
			else if (color.equalsIgnoreCase("orange"))
				throw new OrangeException();
		}
	}
}