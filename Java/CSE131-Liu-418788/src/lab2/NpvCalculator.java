package lab2;

import business.*;
/**
 * Name:
 * Lab Section:
 * Date:
 * NpvCalculator.java
 * CSE 131
 */
public class NpvCalculator {
	
	/**
  	 * Constructs an NpvCalculator object by storing the necessary objects.
  	 * @param flows - a CashFlows object with the expected yearly cash flows
  	 * @param investment - a Money object representing the initial investment amount
  	 * @param discount - the discount rate
  	 	 	 	 	 */
	public NpvCalculator(CashFlows flows, Money investment, double discount)
	{
		//FIXME - your code here
	}
	
	/**
  	 * Calculates the Net Present Value for the supplied year using the formula provided in the
  	 * extension information
  	 * @param year - the year being calculated for
  	 * @return - a Money object containing the NPV for the supplied year
  	 */
	public Money calculateNPV(int year) 
	{
		//FIXME - your code here
		return new Money(0);		
	}
	/**
  	 * Finds the maximum Net Present Value for all of the years contained in the cash flow
  	 * @return - a Money object containing the maximum Net Present value with the supplied information
  	 */
	public Money findMaxNPV() 
	{
		//FIXME - your code here
		return new Money(0);		
	}
	
	/**
  	 * Calculates what the total NPV will be for all of the CashFlows supplied.
  	 * @return - a Money object representing the total NPV for the supplied cash flows
  	 */
	public Money calculateTotalNPV()
	{
		//FIXME - your code here
		return new Money(0);
	}
	
	/**
  	 * Calculates what the present value is (different from the NET present value -- see Extension
  	 * write up) for the supplied year.
  	 * @param year - the year being calculated for
  	 * @return - a Money object representing the present value of the supplied year
  	 */
	public Money calculatePV(int year) 
	{
		//FIXME - your code here
		return new Money(0);	
	}
	
	private CashFlows flows;
	private Money investment;
	private double discount;
	
	/**
  	 * The following main method shows how to create new Money objects, CashFlows objects, and executes
  	 * the example included in the extension write up.
  	 * @param args
  	 */
	public static void main(String args[]) {
		CashFlows cf = new CashFlows(new Money(10000), new Money(10000), new Money(10000));
		NpvCalculator npvCalc = new NpvCalculator(cf, new Money(30000), 0.1);
		System.out.println("Present Value, year 1: " + npvCalc.calculatePV(1));
		System.out.println("Present Value, year 2: " + npvCalc.calculatePV(2));
		System.out.println("Present Value, year 3: " + npvCalc.calculatePV(3));
		System.out.println("Total Net Present Value (all 3 years): " + npvCalc.calculateTotalNPV());
	}
}
