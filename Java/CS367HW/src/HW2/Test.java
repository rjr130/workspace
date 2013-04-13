/*
 * public E getWithDefault(int pos, E defaultVal) {
    // Return the value at position pos of the list, just as the
    // ListADT.get method would do.  However, if the call to get 
    // throws an IndexOutOfBoundsException, return defaultVal instead.     
	}
 */

package HW2;

public class Test {
	public static void main () {
		
	}
	
	public E getWithDefault (int pos, E defaultVal) {
		try {
			ListADT.get(pos);
		} catch (IndexOutOfBoundsExceptions e) {
			return defaultVal;
		}
		return ListADT.get(pos);
	}
}
