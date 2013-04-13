///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             Listnode.java
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
/**
 * Listnode implements a node for a singly-linked list.  Each Listnode 
 * keeps track of a key and the value associated with that key.
 * 
 * DO NOT CHANGE THIS FILE
 * 
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 * @param <V> class representing the value
 */

public class Listnode<K, V> {
	// Data members
    private K key;                      // the key for this node
    private V value;                    // the data value for this node
    private Listnode<K, V> next;  // the left and right children
 
  //*** constructors ***
    public Listnode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
    
    public Listnode(K key, V value, Listnode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
     
  //*** methods ***
    // access to fields
    /**
     * Returns the key for this BST node.
     * @return the key
     */
    public K getKey() { 
        return key; 
    }
    
    /**
     * Returns the value for this BST node
     * @return the value
     */
    public V getValue() {
        return value;
    }
 
    public Listnode<K, V> getNext() {
        return next;
    }
 
    /**
     * Changes the key for this node to the one given.
     * @param newK the new key  
     */
    public void setKey(K newK) { 
        key = newK; 
    }
    
    /**
     * Changes the value for this node to the one given.
     * @param newV the new value
     */
    public void setValue(V newV) {
        value = newV;
    }

    public void setNext(Listnode<K, V> n) {
        next = n;
    }
}
