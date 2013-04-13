package lab3;
//TA: delete old print statements.
import static org.junit.Assert.*
;

import org.junit.Test;

import junit.framework.TestCase;

//public class PointTest extends TestCase {
public class PointTest{



	@Test
	public void init () {
		Point p= new Point(5, -3);
		assertEquals(5.0,  p.getX(), .01);
		assertEquals(-3.0, p.getY(), .01);
	}
	@Test
	public void testToString(){
		Point p2= new Point(5, -3);
		//System.out.println("string "+  p2.toString());
		assertEquals("(5.0,-3.0)",p2.tostring());
	}			

	@Test
	public void arith() {	
		Point p = new Point(5, -3);
		Vector v=new Vector(5,-3);
		Point pPlusV = p.plus(v);//

		comparePoints(new Point(5, -3), p);

		comparePoints(new Point(10, -6), pPlusV);
		compareDoubles(10, pPlusV.getX());
		compareDoubles(-6, pPlusV.getY());
		//
		// Test toString visually
		System.out.println("TA check p:      " + p);
		System.out.println("TA check pplusP: " + pPlusV);

		Vector v1 = new Vector(2, -3);

		Vector pminusP = p.minus(p);
		compareVectors(new Vector(2, -3), v1);

		compareVectors(new Vector(0,0), pminusP);
		compareDoubles(0, pminusP.getDeltaX());
		compareDoubles(0, pminusP.getDeltaY());
		//
		// Test toString visually
		System.out.println("TA check v:      " + v);
		System.out.println("TA check vplusV: " + pminusP);




	}
	public void comparePoints(Point one, Point two) {
		//(one.getX(), two.getX());
		compareDoubles(one.getY(), two.getY());
	}
	public void compareDoubles(double one, double other) {
		System.out.println("one other:" + one+" "+other);
		assertEquals(one, other, 0.01);
	}

	public void compareVectors(Vector one, Vector two) {

		System.out.println("x,y "+one.getDeltaX()+" "+one.getDeltaY());
		System.out.println("x,y "+two.getDeltaX()+" "+two.getDeltaY());

		compareDoubles(one.getDeltaX(), two.getDeltaX());
		compareDoubles(one.getDeltaY(), two.getDeltaY());
	}		
	@Test
	public void DistanceTest(){

		Point p3=new Point(0,0);
		Point p4=new Point(3,4);
		System.out.println("distance"+p3.distance(p4));

		assertEquals(5.0,p3.distance(p4),0.01);

	}
}


