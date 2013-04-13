package File;

public class Exceptions {
	public static void main (String[] args) {
		try {
			//throw new ArithmeticException();
			methodX ();
			//throw new NullPointerException();
		} catch (NullPointerException x) {
			System.out.print("Error 1,");
		} catch (IndexOutOfBoundsException x) {
			System.out.print("Error 2,");
		}
		System.out.print("main done");
	}
	
	public static void methodX () {
		methodY ();	
		try {
			//throw new IndexOutOfBoundsException();	
			
			//throw new IndexOutOfBoundsException();
		} catch (NullPointerException x) {
			System.out.print("Error 3,");
		}
		System.out.print("methodX done,");
	}
	
	public static void methodY () {
		try {
			throw new NullPointerException();
		} catch (ArithmeticException x) {
			System.out.print("Error 4,");
		}
		System.out.print("methodY done,");
	}
}
