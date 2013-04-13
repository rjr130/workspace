///////////////////////////////////////////////////////////////////////////////
// Main Class File:  P1.java
// File:             Symbol.java
// Semester:         Spring 2013
//
// Author:           Junrui Ruan jruan@wisc.edu
// CS Login:         junrui
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////

public class Symbol {
	
	/** type of the Symbol, currently using String type */
	private String type;
	
	/**
	 * Constructor of the Symbol Class
	 * @param type
	 */
	Symbol (String type) {
		this.type = type;
	}
	
	/**
	 * Get and return the type
	 * @return type
	 */
	public String getType () {
		return this.type;
	}
	
	/**
	 * Change the Type to String
	 * @return type in String
	 */
	public String toString () {
		return this.type;
	}
}
