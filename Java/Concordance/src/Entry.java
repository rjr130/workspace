///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             Entrys.java
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
import java.util.*;

/*
 * Entry
 */
public class Entry implements Comparable<Entry>{
	private String word;
	public ArrayList<Integer> lineNoList;
	/**
	 * Constructs an Entry with the given word (converted to lower-case) and 
	 * an empty list of line numbers. If the word is null or an empty string, 
	 * an IllegalArgumentException is thrown.
	 * @param the word for this Entry
	 * @throw IllegalArgumentException if word is null or empty
	 */
	public Entry(String word) {
		if (word == null){
			throw new IllegalArgumentException();
		}
		
		this.word = word.toLowerCase(); 
		//Just in case, forces all characters to lowercase
		
		lineNoList = new ArrayList<Integer>();
	}
	/**
	 * Compares the Entry with the one given. Two Entries are compared by 
	 * comparing the word associated with the two Entries, ignoring case 
	 * differences in the words.
	 * @param other the Entry with which to compare this Entry
	 * @return a negative integer, zero, or a positive integer as this object 
	 * is less than, equal to, or greater than the specified object.
	 * 
	 */
	public int compareTo(Entry other) {
		if (other == null) {
			throw new NullPointerException();
		}
		return other.getWord().compareToIgnoreCase(word);
	}
	/**
	 * Returns the word for this Entry.
	 * @return the word for this Entry
	 */
	public String getWord(){
		return word;
	}
	/**
	 * Returns the list of line numbers for this Entry.
	 * @return the list of line numbers for this Entry
	 */
	public List<Integer> getLines(){
		return lineNoList;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) {
			throw new NullPointerException();
		}
		
		if (other instanceof Entry) {
			Entry e = (Entry) other;
			return (e.getWord().compareToIgnoreCase(word) == 0);
		}
		
		else {
			throw new ClassCastException();
		}
	}
	
	@Override
	/**
	 * Returns a String representation of this Entry in the format:
		word(N): line1, line2, line3, ..., lineN
		where N is the number of line numbers in the list, e.g., 
		madison(5): 1, 18, 22, 23, 55
		Note that there is a space after the colon (:) and each comma (,) and 
		that there is no comma at the end of the list of line numbers.
	 * @see java.lang.Object#toString()
	 * @return a String representation of this Entry
	 */
	public String toString(){
		String lines = ""; //initial value
		//Iterate and output lines
		for(Integer lineNo: lineNoList){
			lines += ", "+lineNo;
		}
		return word + "(" + lineNoList.size() + "): " + lines.substring(2); 
		//Removes the initial comma 
	}
	
}
