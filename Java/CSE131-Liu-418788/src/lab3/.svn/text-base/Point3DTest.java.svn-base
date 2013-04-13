package lab3;


import static org.junit.Assert.*;
import org.junit.Test;


public class Point3DTest {
	@Test
	public void Point3D() {
		Point3D p = new Point3D(5, -3, 4);
		assertEquals(5.0, p.getX(), .01);assertEquals(-3.0, p.getY(), .01);assertEquals(4.0, p.getZ(), .01);
	}
	
	public void compareDoubles(double one, double other) {
		assertEquals(one, other, 0.01);
	}


	public void comparePoint3Ds(Point3D M, Point3D N) {
		compareDoubles(M.getX(), N.getX());compareDoubles(M.getY(), N.getY());compareDoubles(M.getZ(), N.getZ());
	}


	@Test
	public void plus() {
		Point3D p = new Point3D(5, -3, 1);
		Vector3D v = new Vector3D(8, 6, 1);
		Point3D pPlusV = p.plus(v);


		comparePoint3Ds(new Point3D(5, -3, 1), p);
		comparePoint3Ds(new Point3D(13, 3, 2), pPlusV);


		System.out.println(v);
		System.out.println(pPlusV);
	}


	@Test
	public void minus(){
		Point3D p= new Point3D(1,4,2);
		Point3D q= new Point3D(3,5,1);
		assertEquals(2.0, q.minus(p).getDeltaX(),0.1);
		assertEquals(1.0, q.minus(p).getDeltaY(),0.1);
		assertEquals(-1.0, q.minus(p).getDeltaZ(),0.1);
	}


	@Test
	public void distance(){
		Point3D p= new Point3D (2,3,0);
		Point3D q= new Point3D (5,4,1);
		assertEquals(Math.sqrt(11.0), p.distance(q),0.1);
	}
}
