package lab5;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Name: Ruiqi,Liu	
 * Lab Section: A
 * Date: 02/07/2012
 * Email: ruiqi.liu@wustl.edu
 * RecursionTest.java
 * CSE 131 Lab 5
 */

public class RecursionTest {

	    // Example:
		@Test
		public void testFactorial() {
			assertEquals(1, Recursion.factorial(0));
			assertEquals(24, Recursion.factorial(4));
		}
		
		// Your test methods go here.@Test
		
		@Test
		public void testSumDownBy2(){
				assertEquals(0,Recursion.sumDownBy2(1));
				assertEquals(8,Recursion.sumDownBy2(5));
		}
		@Test
		public void testHarmonicSum(){
				assertEquals(1.00,Recursion.harmonicSum(1), .01);
				assertEquals(1.83,Recursion.harmonicSum(3),.01);
		}
		@Test
		public void testGeometricSum(){
				assertEquals(0.5,Recursion.geometricSum(1),.01);
				assertEquals(0.875,Recursion.geometricSum(3),.01);
		}
		@Test
		public void testMult(){
				assertEquals(18,Recursion.mult(3,6));
				assertEquals(-8,Recursion.mult(-2, 4));
				assertEquals(8,Recursion.mult(-2, -4));
				assertEquals(-8,Recursion.mult(2, -4));
				assertEquals(0,Recursion.mult(0, 4));

		}
		@Test
		public void testExpt(){
				assertEquals(1,Recursion.expt(2, 0));
				assertEquals(3,Recursion.expt(3,1));
				assertEquals(27,Recursion.expt(3,3));
		}
		@Test
		public void testLcm(){
				assertEquals(6,Recursion.lcm(1,6));
				assertEquals(30,Recursion.lcm(5,6));
				assertEquals(24,Recursion.lcm(6,8));

		}
		@Test
		public void testloanLength(){
				assertEquals(5,Recursion.loanLength(1000,0.10,250));
				assertEquals(0,Recursion.loanLength(0,0.90,50));
		
		}
}






