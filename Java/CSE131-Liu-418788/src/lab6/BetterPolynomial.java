package lab6;

import java.util.Iterator;

public class BetterPolynomial extends Polynomial {
	
	// DO NOT redefine the LinkedList here!
	// DO NOT change the access of the LinkedList in Polynomial!
	// It is available privately in Polynomial, but
	//   you do not have direct access to it here.
	// You are therefore forced to compute the product
	//   using methods you already have available from
	//   Polynomial, but NOT access to the list contained therein
	
	/**
	 * Java generates the following constructor automatically,
	 * but I'll put it in here in case you worry about it being
	 * missing.  A BetterPolynomial is made by having Polynomial
	 * do its usual work. Nothing extra is needed for the extension.
	 */
	public BetterPolynomial() {
		super();
	}
	
	/**
	 * Copies a Polynomial as a BetterPolynomial. The better
	 *   one can do everything a Polynomial can, as well as perform
	 *   the product method.
	 * @param p A Polynomial
	 */
	public BetterPolynomial(Polynomial p) {
		super();
		Iterator<Double> i = p.getIterator();
		while (i.hasNext()) {
			double d = i.next();
			addTerm(d);
		}
	}

	
	public Polynomial product(Polynomial another) {
		return new Polynomial(); // FIXME
	}

}
