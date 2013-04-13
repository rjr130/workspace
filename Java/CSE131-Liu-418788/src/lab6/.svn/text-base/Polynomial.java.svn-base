package lab6;

import java.util.Iterator;

import java.util.LinkedList;

public class Polynomial {

	final private LinkedList<Double> list;

	/**
	 * Constructs a Polynomial with no terms.
	 */
	public Polynomial() {
		list = new LinkedList<Double>();
	}

	public String toString() {
		Iterator <Double> i = getIterator();
		int power=0;
		String s="";
		while(i.hasNext()){
			s= s+i.next()+"x"+"^"+power+"+";
			++power;
		}
		return s; 
	}

	public Polynomial addTerm(double coeff) {
		// implement this method
		list.add(coeff);
		return this;  // required by lab spec
	}

	/**
	 * returning a random double so that
	 *    almost all assertEquals will fail on this method
	 *    until it is implemented.  Be sure to implement this
	 *    method recursively as specified in the lab documentation.
	 * @return the evaluation of this polynomial at x
	 i*/
	public double evaluateHelper(double x, Iterator <Double> i1){
		if (!i1.hasNext())
			return 0;
		else
			return i1.next()+x*evaluateHelper(x,i1);
	}

	public double evaluate(double x) {
		if (list == null){
			return 0;
		} else {
			Iterator<Double> iterator = list.iterator();
			return evaluateHelper(x, iterator);
		} 

	}

	/**
	 * This method does return a new Polynomial that is the
	 *    derivative of the current one.
	 * @return a new Polynomial that is the derivative of this one
	 */
	public Polynomial derivative() {
		Polynomial deri= new Polynomial();
		for (int i=1; i<list.size();i++){
			deri.addTerm(i*list.get(i));
		}
		return deri;
	}		
	/**
	 * Helper method for derivative
	 * @param iterator the list's iterator
	 * @return desired polynomial
	 */
	public Polynomial derivativeHelper(Iterator<Double> iterator) {
		Polynomial a = new Polynomial();
		while (iterator.hasNext()) {
			double x = 1;iterator.next(); 
			while (iterator.hasNext()) {
				a.addTerm(iterator.next()*x);
				++x;
			}
		}
		return a;
	}


	/**
	 * Compute the sum of this and the other Polynomial, returning
	 *    a new Polynomial that represents that sum.
	 * @return a new Polynomial that is the some of this and another
	 */
	public Polynomial sum(Polynomial another) {
		Iterator<Double> ite = list.iterator();Iterator<Double> ite1 = another.getIterator();
		Polynomial result = new Polynomial();
		while (ite.hasNext() || ite1.hasNext()) {
			int tem = 0;
			if (ite.hasNext())
				tem += ite.next();
			if (ite1.hasNext())
				tem += ite1.next();
			result.addTerm(tem);
		}
		return result;
	}

	/**
	 * This is the "equals" method that is called by
	 *    assertEquals(...) from your JUnit test code.
	 *    It must be prepared to compare this Polynomial
	 *    with any other kind of Object (obj).  Eclipse
	 *    automatically generated the code for this method,
	 *    after I told it to use the contained list as the basis
	 *    of equality testing.  I have annotated the code to show
	 *    what is going on.
	 */

	public boolean equals(Object obj) {
		// If the two objects are exactly the same object,
		//    we are required to return true.  The == operator
		//    tests whether they are exactly the same object.
		if (this == obj)
			return true;
		// "this" object cannot be null (or we would have
		//    experienced a null-pointer exception by now), but
		//    obj can be null.  We are required to say the two
		//    objects are not the same if obj is null.
		if (obj == null)
			return false;

		//  The two objects must be Polynomials (or better) to
		//     allow meaningful comparison.
		if (!(obj instanceof Polynomial))
			return false;

		// View the obj reference now as the Polynomial we know
		//   it to be.  This works even if obj is a BetterPolynomial.
		Polynomial other = (Polynomial) obj;

		//
		// If we get here, we have two Polynomial objects,
		//   this and other,
		//   and neither is null.  Eclipse generated code
		//   to make sure that the Polynomial's list references
		//   are non-null, but we can prove they are not null
		//   because the constructor sets them to some object.
		//   I cleaned up that code to make this read better.


		// A LinkedList object is programmed to compare itself
		//   against any other LinkedList object by checking
		//   that the elements in each list agree.

		return this.list.equals(other.list);
	}


	protected Iterator<Double> getIterator() {
		return list.iterator();
	}

}
