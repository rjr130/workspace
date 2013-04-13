package julia;

/**
 * Complex number, an immutable class
 * @author cytron
 *
 */
public class Complex {
	

	/**
	 * A complex number
	 * @param real part
	 * @param imaginary part
	 */
	public Complex(double real, double imaginary) {

	}

	public double getReal() {
		return 0.0; // FIXME
	}

	public double getImaginary() {
		return 0.0; // FIXME
	}
	
	/**
	 * Return a new Complex number that is the sum of this
	 *   and the other one
	 * @param other
	 * @return sum of this and the other Complex numbers
	 */
	public Complex plus (Complex other) {
		return null; // FIXME
	}

	/**
	 * Return the difference of this and the other Complex numbers
	 * @param other
	 * @return this - other
	 */
	public Complex minus(Complex other) {
		return null; // FIXME
	}
	
	/**
	 * Return a new Complex number that is the product of this
	 *   and the other number.
	 * @param other
	 * @return this * other
	 */
	public Complex times(Complex other) {
		return null; // FIXME
	}
	
	/**
	 * Return the distance between (0,0) and this Complex number
	 * @return distance from (0,0)
	 */
	public double abs() { 
		return 0.0; // FIXME
	}
	
	public String toString() {
		return "("+ getReal() + " + " + getImaginary() + "i)";
	}
}
