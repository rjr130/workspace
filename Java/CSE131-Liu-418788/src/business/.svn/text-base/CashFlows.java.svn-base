package business;

public class CashFlows {
	
	/**
  	 * Constructs a CashFlows object with however many flows are supplied.
 	 * @param f - a variable amount of supplied cash flows (Money objects).
  	 */
	public CashFlows(Money ... f) {
		flows = f;
	}
	
	/**
  	 * Creates an empty CashFlows object.
 	 * 	 	 
 	 */
	public CashFlows() {
		flows = new Money[0];
	}
	
	/**
  	 * Returns the value for the specified year, checking to make sure it exists
  	 * @param year - the desired year to retrieve
  	 * @return - a Money object containing the cash flow for the supplied year
 	 */
	public Money getFlow(int year) {
		if(year < 1 || year > flows.length)
			throw new IllegalArgumentException("The year provided was not valid");
		return flows[year-1];
	}
	
	public int getNumberOfFlows() {
		return flows.length;
	}
	
	private Money[] flows;
}
