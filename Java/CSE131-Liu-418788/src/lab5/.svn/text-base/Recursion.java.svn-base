package lab5;
/**
 * Name: Ruiqi,Liu	
 * Lab Section: A
 * Date:02/26/2012
 * Email:ruiqi.liu@wustl.edu
 * Recursion.java
 * CSE 131 Lab 5
 */

public class Recursion {
	
	// Example:
	static int factorial(int k) {
		if (k == 0)
			return 1;
		else
			return k * factorial(k-1);
	}
	
	// Your methods go here.
	
	/**
	 * @param n  
	 * the sum of the positive integers n + (n-2) + (n-4) + ...
	 */
	static int sumDownBy2(int n){ 
		if (n<=1)
			return 0;
		else
			return n+ sumDownBy2(n-2);
	}
	/**
	 * @param n
	 * the sum 1 + 1/2 + 1/3 + ... + 1/(n-1) + 1/n
	 */
	static double harmonicSum(int n){
		if (n>0)
			return (1.0/n) + harmonicSum(n-1);
		else return 0;
	}
	
	/**
	 * @param k
	 * the sum 1 + 1/2 + 1/4 + 1/8 + ... + 1/Math.pow(2,k)
	 */
	static double geometricSum(int k){ 
		if (k == 0)
			return 0;
		else
			return 1/Math.pow(2,k) + geometricSum(k-1); 
		}
	
	/**
	 * @param j
	 * @param k
	 * @return the product j*k
	 */
	static int multHelper(int j, int k){ //assume j and k are non-negative
		if (k==1)
			return j;
		else return j+multHelper(j, k-1); 
	}
	static int mult(int j, int k){
		if (j>=0 && k>=0)
			return multHelper(j,k);
		else if (j>=0 && k<0)
			return -multHelper(j,-k);
		else if (j<0 && k>=0)
			return -multHelper(-j,k);
		else
			return multHelper(-j,-k);
	}		
	
	
	/**
	 * 
	 * @param n
	 * @param k
	 * @return  the value of n to the power k
	 */
	static int exptHelper(int n, int k){
		if (k == 1)
			return n;
		else return n*exptHelper(n,k-1);
	}
	static int expt(int n, int k){
		if (k == 0)
			return 1;
		else 
			return exptHelper(n,k);
}
	
	
	static int lcmHelper(int j, int k, int m){
		if (m%k == 0)
			return m;
		else
		    return lcmHelper(j,k,m+j);
	}
	/**
	 * 
	 * @param j
	 * @param k
	 * @return the least common multiple (LCM) of j and k
	 */
	static int lcm(int j, int k){	
		return lcmHelper(j,k,j);
		}
	
	
	static int loanLengthHelper(double loanAmount, double r, double payment, int m){
		if (loanAmount<=0){
			return m;
		}
		else 
			System.out.println("Month" + m + ":"+"="+ (int)loanAmount);
			return loanLengthHelper(loanAmount+loanAmount*(r/12)-payment, r, payment, m+1);
	}
	/**
	 * 
	 * @param loanAmount
	 * @param r
	 * @param payment
	 * @return  the number of months until the loan is paid off, assuming that the interest is compounded monthly
	 */
	static int loanLength(double loanAmount, double r, double payment){
		if (loanAmount<=0)
			return 0;
		else  return loanLengthHelper(loanAmount, r, payment,0); 
	}
}	
	

