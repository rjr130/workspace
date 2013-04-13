///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             BSTBasicMap.java
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

public class BSTBasicMap<K extends Comparable<K>,  V> 
									implements BasicMapADT<K, V> {
    private BSTnode<K, V> root;  // the root node
    private int numItems;        // the number of items in the basic map
    
    /**
     *  Constructs a new Map, defaults to empty
     */
    BSTBasicMap(){
    	root = null;
    	numItems = 0;
    }
    
    @Override
    public void put(K key, V value) throws DuplicateException {
        root = put(root, key, value);
    	numItems++; //Increase size if successfully added
    }
    /**
     * Method for put
     * @param parentNode - root
     * @param key - Value of the key field
     * @param value - Value of the value field
     * @throws DuplicateException 
     */
    private BSTnode<K, V> put(BSTnode<K, V> parentNode, K key, V value) 
    								throws DuplicateException {
    	if (parentNode == null) { //If root is null
    		return new BSTnode<K, V>(key, value);
    	}
    	if (parentNode.getKey().equals(key)) { //If key found throw exception
    		throw new DuplicateException();
        }
        if (key.compareTo(parentNode.getKey()) < 0) {
            // add node to the left subtree
            parentNode.setLeft(put(parentNode.getLeft(), key, value));
        }else {
            // add node to the right subtree
            parentNode.setRight(put(parentNode.getRight(), key, value));
        }
        return parentNode;
    }

    @Override
    public boolean remove(K key) {
    	if(remove(root, key) != null){
    		numItems--;//decrease size if successfully removed
    		return true;
    	}
        return false;
    }
    /**
     * Method for remove
     * @param parentNode
     * @param key
     * @return true if success false if fail
     */
    private BSTnode<K, V> remove(BSTnode<K, V> parentNode, K key){
    	if (key.compareTo(parentNode.getKey()) == 0) {
            //parentNode is the node to be removed
    		if (parentNode.getLeft() == null && parentNode.getRight() == null) {
                return null;
            }
            if (parentNode.getLeft() == null) {
                return parentNode.getRight();
            }
            if (parentNode.getRight() == null) {
                return parentNode.getLeft();
            }
           
            // if n has 2 children, get the smallest value
            BSTnode<K, V> sucessorNode = parentNode.getRight();
            while(sucessorNode.getLeft() != null){
            	sucessorNode = sucessorNode.getLeft();
            }
            K smallestVal = sucessorNode.getKey();
            parentNode.setKey(smallestVal);
            parentNode.setRight( remove(parentNode.getRight(), smallestVal) );
            return parentNode;
        }else if (key.compareTo(parentNode.getKey()) < 0) {
            parentNode.setLeft( remove(parentNode.getLeft(), key) );
            return parentNode;
        }else {
            parentNode.setRight( remove(parentNode.getRight(), key) );
            return parentNode;
        }
    }
    
    @Override
    public V get(K key) {
    	BSTnode<K,V> tempNode = getNode(key);
    	if(tempNode == null){
    		return null;
    	}
        return getNode(key).getValue();
    }
    /**
     * Method for get
     * Searches for a node in the tree given the key
     * @param key Search key
     * @return The result node, null if not present
     */
    private BSTnode<K, V> getNode(K key){
    	//Check if tree is empty
    	BSTnode<K, V> currNode = root;
    	BSTnode<K, V> resultNode = null; //Not found by default
    	while(currNode != null){
    		int comparison = currNode.getKey().compareTo(key);
    		if(comparison == 0){
    			resultNode = currNode;
    			break; //Break loop if found
    		}else if(comparison < 0){ //Larger, navigate right
    			currNode = currNode.getRight();
    		}else { //Smaller, navigate left
    			currNode = currNode.getLeft();
    		}
    	}
    	return resultNode;
    }


	@Override
	public boolean containsKey(K key) {
		return containsKey(root, key);
	}
	private boolean containsKey(BSTnode<K,V> n, K key) {
		if (n == null){
    		return false;
    	}
    	if (n.getKey().equals(key)){
    		return true;
    	}
    	if (key.compareTo(n.getKey()) < 0) {
    	    return containsKey(n.getLeft(), key);
    	}
    	else {
    	    return containsKey(n.getRight(), key);
    	}
	}

	@Override
	public boolean containsValue(V value) {
		return containsValue(root, value);
	}

	private boolean containsValue(BSTnode<K,V> n, V value) {
		if (n == null){
    		return false;
    	}
		if (n.getValue().equals(value)){
    		return true;
    	}
		if (n.getLeft() != null) {
			containsValue(n.getLeft(), value);
		}
		if (n.getRight() != null) {
    	    containsValue(n.getRight(), value);
    	}
		return false;
}
    
    @Override
    public int size() {
        return numItems;
    }
    
    @Override
    public boolean isEmpty() {
        return (numItems <= 0);
    }
    public List<K> keys() {
    	return keys(root);
    }
    private List<K> keys(BSTnode<K, V> n) {
    	ArrayList<K> keyList = new ArrayList<K>();
    	if (n == null){ 
    		return keyList;
    	}
    	keyList.addAll(keys(n.getLeft()));
    	keyList.add(n.getKey());
    	keyList.addAll(keys(n.getRight()));
    	return keyList;
    }

    public List<V> values() {
        return values(root);
    }

    private List<V> values(BSTnode<K, V> n) {
    	ArrayList<V> valuesList = new ArrayList<V>();
    	//Iterate over each node
    	if (n == null){ 
    		return valuesList;
    	}
    	else{
    		valuesList.addAll(values(n.getLeft()));
    		valuesList.add(n.getValue());
    		valuesList.addAll(values(n.getRight()));
    	}
    	return valuesList;
    }

    @Override
    public int totalPathLength() {
    	if(root == null){
    		return 0;
    	}
    	return totalPathLength(root,0);
    }
    
    /**
     * Returns the totalPathLength
     * @param root Root Node
     * @param pathLength Path length to root
     * @return the total path length
     */
    private int totalPathLength(BSTnode<K,V> root, int pathLength){
    	int sumLength = 0;
    	//Post-order traversal
    	if (root.getLeft() != null) sumLength 
    	+= totalPathLength(root.getLeft(),pathLength+1);
    	if (root.getRight() != null) sumLength 
    	+= totalPathLength(root.getRight(),pathLength+1);
    	return sumLength+pathLength+1;
    }  
}
