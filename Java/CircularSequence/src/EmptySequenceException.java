///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  
// File:             EmptySequenceException.java
// Semester:         Spring 2012
//
// Author:           Chew Wei Lai clai9@wisc.edu
// CS Login:         clai
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Junrui Ruan jruan@wisc.edu
// CS Login:         junrui
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////
public class EmptySequenceException extends RuntimeException {
	/**
	 * Constructor
	 */
	public EmptySequenceException() {
        super();
    }
    
	/**
	 * Message version
	 * @param message String to output
	 */
    public EmptySequenceException(String message) {
        super(message);
    }
}
