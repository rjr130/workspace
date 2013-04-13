///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TestHash.java
// File:             HashTable.java
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

import java.io.*;
import java.util.LinkedList;

/**
 * This class implements a hashtable that using chaining for collision handling.
 * Any non-<tt>null</tt> item may be added to a hashtable.  Chains are 
 * implemented using <tt>LinkedList</tt>s.  When a hashtable is created, its 
 * initial size, maximum load factor, and (optionally) maximum chain length are 
 * specified.  The hashtable can hold arbitrarily many items and resizes itself 
 * whenever it reaches its maximum load factor or whenever it reaches its 
 * maximum chain length (if a maximum chain length has been specified).
 * 
 * Note that the hashtable allows duplicate entries.
 */
public class HashTable<T> {
	private int initSize; // For constructor
	private double loadFactor; // For constructor
	private double currLoadFactor;
	private int maxChainLength;	// For constructor
	private int largestChainLength;	// To record the longest chain length
	private double numItems; // To record the items in hash
	private LinkedList<T>[] hashTable;	// The hashTable
	private LinkedList<T> list;	// To record the items
								// added in hashTable
	private boolean flag = false;	// To record the state whether we need to 
									// consider about the maxChainLength

	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and no maximum chain length.  The load factor should be a real 
	 * number greater than 0.0 (not a percentage).  For example, to create a 
	 * hash table with an initial size of 10 and a load factor of 0.85, one 
	 * would use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
	 *
	 * @param initSize the initial size of the hashtable.
	 * @param loadFactor the load factor expressed as a real number.
	 * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
	 *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0
	 **/
	public HashTable(int initSize, double loadFactor) {
		if (initSize <= 0 || loadFactor <= 0.0) {
			throw new IllegalArgumentException();
		}
		this.initSize = initSize;
		this.loadFactor = loadFactor;
		this.currLoadFactor=0;
		this.numItems = 0;
		this.hashTable = (LinkedList<T>[])(new LinkedList[this.initSize]);
		for (int i = 0; i < hashTable.length; i++){
			hashTable[i] = new LinkedList<T>();
		}
		list =  new LinkedList<T>();
	}


	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and maximum chain length.  The load factor should be a real 
	 * number greater than 0.0 (and not a percentage).  For example, to create 
	 * a hash table with an initial size of 10, a load factor of 0.85, and a 
	 * maximum chain length of 20, one would use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85, 20);</tt></dir>
	 *
	 * @param initSize the initial size of the hashtable.
	 * @param loadFactor the load factor expressed as a real number.
	 * @param maxChainLength the maximum chain length.
	 * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
	 *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0 
	 *         or if <tt>maxChainLength</tt> is less than or equal to 0.
	 **/
	public HashTable(int initSize, double loadFactor, int maxChainLength) {
		if (initSize <= 0 || loadFactor <= 0.0 || maxChainLength <= 0) {
			throw new IllegalArgumentException();
		}
		this.initSize = initSize;
		this.loadFactor = loadFactor;
		this.currLoadFactor = 0;
		this.maxChainLength = maxChainLength;
		this.flag = true;
		this.numItems = 0;
		this.hashTable = (LinkedList<T>[])(new LinkedList[this.initSize]);

		for (int i = 0; i < hashTable.length; i++){
			hashTable[i] = new LinkedList<T>();
		}
		list = new LinkedList<T>();
	}

	/**
	 * Determines if the given item is in the hashtable and returns it if 
	 * present.  If more than one copy of the item is in the hashtable, the 
	 * first copy encountered is returned.
	 *
	 * @param item the item to search for in the hashtable.
	 * @return the item if it is found and <tt>null</tt> if not found.
	 **/
	public T lookup(T item) {
		for (int i = 0; i < this.initSize; i++) {
			if (hashTable[i].contains(item)) {
				for (int j = 0; j < hashTable[i].size(); j++) {
					if (hashTable[i].get(j).equals(item)) {
						return hashTable[i].get(j);
					}
				}
			}
		}
		return null;
	}


	/**
	 * Inserts the given item into the hashtable.  The item cannot be 
	 * <tt>null</tt>.  If there is a collision, the item is added to the end of
	 * the chain.
	 * <p>
	 * If the load factor of the hashtable after the insert would exceed 
	 * (not equal) the maximum load factor (given in the constructor), then the 
	 * hashtable is resized.  
	 * 
	 * If the maximum chain length of the hashtable after insert would exceed
	 * (not equal) the maximum chain length (given in the constructor), then the
	 * hashtable is resized.
	 * 
	 * When resizing, to make sure the size of the table is reasonable, the new 
	 * size is always 2 x <i>old size</i> + 1.  For example, size 101 would 
	 * become 203.  (This guarantees that it will be an odd size.)
	 * </p>
	 * <p>Note that duplicates <b>are</b> allowed.</p>
	 *
	 * @param item the item to add to the hashtable.
	 * @throws NullPointerException if <tt>item</tt> is <tt>null</tt>.
	 **/
	public void insert(T item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		int k = item.hashCode() % initSize;
		if (k < 0) {
			k = k + initSize;
		}
		hashTable[k].add(item);
		if (this.largestChainLength < hashTable[k].size()) {
			this.largestChainLength = hashTable[k].size();
		}
		numItems++;
		//calculate load factor after the addition
		currLoadFactor = ((double)(numItems)) / initSize;
		if (currLoadFactor > loadFactor) {
			rehash();
		}
		if (this.largestChainLength < hashTable[k].size()) {
			this.largestChainLength = hashTable[k].size();
		}
		//calculate maxChainLength after the addition
		if(flag) {	
			if (maxChainLength != 0) {
				if (this.largestChainLength > maxChainLength) {	
					rehash();
				}
			}
		}
		 
	}

	/**
	 * Removes and returns the given item from the hashtable.  If the item is 
	 * not in the hashtable, <tt>null</tt> is returned.  If more than one copy 
	 * of the item is in the hashtable, only the first copy encountered is 
	 * removed and returned.
	 *
	 * @param item the item to delete in the hashtable.
	 * @return the removed item if it was found and <tt>null</tt> if not found.
	 **/
	public T delete(T item) {
		for (int i = 0; i < this.initSize; i++) {
			if (hashTable[i].contains(item)) {
				for (int j = 0; j < hashTable[i].size(); j++) {
					if (hashTable[i].get(j).equals(item)) {
						T tmp = hashTable[i].get(j);
						hashTable[i].remove(j);
						numItems--;
						return tmp;
					}
				}
			}
		}
		return null;  
	}

	/**
	 * Rehash the table if the HashTable needs to be resized
	 * 
	 * @return the resized hashtable
	 */
	public void rehash() {
		LinkedList<T>[] temp = hashTable;
		numItems = 0;
		currLoadFactor = 0;
		this.largestChainLength = 0;
		initSize= 2*initSize+1;
		hashTable = (LinkedList<T>[])(new LinkedList[initSize]);
		for (int i = 0; i < hashTable.length; i++){
			hashTable[i] = new LinkedList<T>();
		}
		for (int i = 0; i < temp.length; i++) {
			while(!temp[i].isEmpty()) {
				insert(temp[i].removeFirst());
			}
		}		
	}
	/**
	 * Prints all the items in the hashtable to the <tt>PrintStream</tt> 
	 * supplied.  The items are printed in the order determined by the index of
	 * the hashtable where they are stored (starting at 0 and going to 
	 * (table size - 1)).  The values at each index are printed according 
	 * to the order in the <tt>LinkedList</tt> starting from the beginning. 
	 *
	 * @param out the place to print all the output.
	 **/
	public void dump(PrintStream out) {
		out.println("Hashtable contents:");
		for (int i = 0; i < this.initSize; i++) {
			if (!hashTable[i].isEmpty()) {
				out.println(i + ":" + hashTable[i]);
			}
		}
	}

	/**
	 * Prints statistics about the hashtable to the <tt>PrintStream</tt> 
	 * supplied.  The statistics displayed are: 
	 * <ul>
	 * <li>the current table size
	 * <li>the number of items currently in the table 
	 * <li>the current load factor
	 * <li>the length of the largest chain
	 * <li>the number of chains of length 0
	 * <li>the average length of the chains of length > 0
	 * </ul>
	 *
	 * @param out the place to print all the output.
	 **/
	public void displayStats(PrintStream out) {
		out.println("Hashtable statistics:");
		out.println("  current table size:       " + hashTable.length);
		out.println("  # items in table:         " + this.numItems);
		out.println("  current load factor:      "
				+ (double)this.numItems / initSize);
		out.println("  longest chain length:     " + this.largestChainLength);
		int count = 0;
		double sum = 0;
		for (int i = 0; i < this.initSize; i++) {
			if (hashTable[i].isEmpty()) {
				count++;
			}
			else { 
				sum += hashTable[i].size();
			}
		}
		out.println("  # 0-length chains:        " + count);
		out.println("  avg (non-0) chain length: " + 
				sum / (initSize - count));
	}
}
