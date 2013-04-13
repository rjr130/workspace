///////////////////////////////////////////////////////////////////////////////
// Main Class File:  P1.java
// File:             SymbolTable.java
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

import java.util.*;
import java.io.*;

public class SymbolTable {
	/** A HashMap used to represent the Symbol Table */
	private List<HashMap<String, Symbol>> symbolList;
	
	/** 
	 * Constructor for the SymbolTable, will use LinkedList to implement
	 * the List  
	 */
	SymbolTable () {
		this.symbolList = new LinkedList<HashMap<String, Symbol>>();
		HashMap<String, Symbol> map = new HashMap<String, Symbol>();
		this.symbolList.add(map);
	}
	
	/**
	 * add the given name and symbol to the first HashMap in the list.
	 * @param name
	 * @param sym
	 * @throws DuplicateException
	 * @throws EmptySymbolTableException
	 */
	void insert (String name, Symbol sym) throws DuplicateException,
		EmptySymbolTableException {
		if (sym == null || name == null) {
			throw new NullPointerException();
		}
		if (this.symbolList.size() == 0) {
			throw new EmptySymbolTableException();
		}
		HashMap<String, Symbol> tmpMap = this.symbolList.get(0);
		if (tmpMap.containsKey(name)) {
			throw new DuplicateException();
		}		
		tmpMap.put(name, sym);
	}
	
	/**
	 * Add a new, empty HashMap to the front of the list.
	 */
	void addMap () {
		HashMap<String, Symbol> map = new HashMap<String, Symbol>();
		this.symbolList.add(0, map);
	}
	
	/**
	 * Return the associated Symbol in the first HashMap in the list, else 
	 * return null
	 * @param name
	 * @return null or the associated Symbol
	 */
	Symbol localLookup (String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}
		if (this.symbolList == null || this.symbolList.size() == 0) {
			return null;
		}
		if (this.symbolList.get(0).containsKey(name)) {
			return this.symbolList.get(0).get(name);
		}
		return null;	
	}
	
	/**
	 * Return the first associated Symbol in all the HashMap in the list, else 
	 * return null
	 * @param name
	 * @return null or the associated Symbol
	 */
	Symbol globalLookup (String name) {
		for (HashMap<String, Symbol> map : this.symbolList) {
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}
		return null;
	}
	
	/**
	 * If this SymbolTable's list is empty, throw an EmptySymbolTableException;
	 * otherwise, remove the HashMap from the front of the list.
	 * 
	 * @throws EmptySymbolTableException
	 */
	void removeMap () throws EmptySymbolTableException {
		if (this.symbolList.isEmpty()) {
			throw new EmptySymbolTableException();
		}
		else {
			this.symbolList.remove(0);
		}
	}
	
	/**
	 *  This method is for debugging.
	 *  
	 *  First, print "\nSymbol Table\n". Then, for each HashMap M in the list, 
	 *  print M.toString() followed by a newline. 
	 *  Finally, print one more newline. All output should go to System.out.
	 */
	void print () {
		System.out.println("\nSymbol Table");
		for (HashMap<String, Symbol> map : this.symbolList) {
			System.out.println(map.toString());
		}
		System.out.println();
	}	
}
