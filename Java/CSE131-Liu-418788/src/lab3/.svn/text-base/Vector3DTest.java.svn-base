package lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Vector3DTest {

	@Test
	public void init() {
		Vector3D v = new Vector3D(5, -3, 4);
		assertEquals(5.0,  v.getDeltaX(), .01);
		assertEquals(-3.0, v.getDeltaY(), .01);
		assertEquals(4, v.getDeltaZ(), .01);
	}

	@Test
	public void arith() {
		Vector3D v = new Vector3D(5, -3, 4);
		Vector3D vPlusV = v.plus(v);
		//
		// Make sure the old vector did not change
		//
		compareVectors3D(new Vector3D(5, -3,4), v);
		//
		// Make sure the new vector is right
		//
		compareVectors3D(new Vector3D(10, -6,8), vPlusV);
		compareDoubles3D(10, vPlusV.getDeltaX());
		compareDoubles3D(-6, vPlusV.getDeltaY());
		compareDoubles3D(8, vPlusV.getDeltaZ());
		//
		// Test toString visually
		System.out.println("TA check v:      " + v);
		System.out.println("TA check vplusV: " + vPlusV);
	}

	/**
	 * Compare two Vectors JUnit-style, failing if they do not
	 * agree on their x and y deltas.
	 * @param one
	 * @param two
	 */
	public void compareVectors3D(Vector3D one, Vector3D two) {
		compareDoubles3D(one.getDeltaX(), two.getDeltaX());
		compareDoubles3D(one.getDeltaY(), two.getDeltaY());
		compareDoubles3D(one.getDeltaZ(), two.getDeltaZ());

	}


	/**
	 * Why did I write this method?
	 * @param one    one of two doubles to compare
	 * @param other  other of two doubles to compare
	 */
	public void compareDoubles3D(double one, double other) {
		assertEquals(one, other, 0.01);
	}



	@Test
	public void scale() {
		Vector3D v = new Vector3D(5, -3, 4);
		Vector3D bigger = v.scale(1.5);
		Vector3D smaller = v.scale(0.75);
		compareDoubles3D( 7.5,   gx(bigger));
		compareDoubles3D(-4.5,   gy(bigger));
		compareDoubles3D(6.0,    gz(bigger));
		compareDoubles3D(3.75,  gx(smaller));
		compareDoubles3D(-2.25, gy(smaller));
		compareDoubles3D( 2.25,  gy(smaller.deflectY()));
		compareDoubles3D(-2.25,  gy(smaller.deflectX()));
		compareDoubles3D(-3.0, gz(smaller.deflectZ()));
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's x component
	 */
	public double gx(Vector3D v) {
		return v.getDeltaX();
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's y component
	 */
	public double gy(Vector3D v) {
		return v.getDeltaY();
	}

	public double gz(Vector3D v){
		return v.getDeltaZ();
	}
	
	@Test
	public void rescale() {
		Vector3D v = new Vector3D(2, 4,4);
		compareDoubles3D(6.0, v.magnitude());
		compareDoubles3D(4, gx(v.rescale(12)));
		compareDoubles3D(8, gy(v.rescale(12)));
		compareDoubles3D(8, gz(v.rescale(12)));
	}

	@Test
	public void specialCases() {
		Vector3D v = new Vector3D(0, 0,0);
		Vector3D r = v.rescale(5);
		compareDoubles3D(0, v.magnitude());
		compareDoubles3D(5, r.magnitude());
		compareDoubles3D(5, gx(r));
		compareDoubles3D(0, gy(r));
		compareDoubles3D(0,gz(r));
	}

}
