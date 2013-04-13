///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             LLBasicMap.java
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
public class LLBasicMap<K extends Comparable<K>, V> 
							implements BasicMapADT<K, V> {
	private Listnode<K, V> root;  // the root node
    private int numItems;        // the number of items in the basic map
    private Listnode<K, V> head;
	/**
     *  Constructs a new Map, defaults to empty
     */
    LLBasicMap() {
    	root = null;
    	head = root;
    	numItems = 0;
    }
    
    @Override
    public void put(K key, V value) throws DuplicateException {
    	root = put(root, key, value);
    	numItems++; //Increase size if success
    }
    /**
     * Recursive method for put
     * @param parentNode parentNode to assest
     * @param key Value of the key field
     * @param value Value of the value field
     * @throws DuplicateKeyException 
     */
    private Listnode<K, V> put(BSTnode<K, V> parentNode, K key, V value) throws 
    DuplicateException {
    	if (parentNode == null) { //If root is null
    		return new Listnode<K, V>(key, value);
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
    		numItems--;
    		return true;
    	}
        return false;
    }
    /**
     * 
     * @param parentNode
     * @param key
     * @return true if success false if fail
     */
    private BSTnode<K, V> remove(Listnode<K, V> parentNode, K key){ //Successor 
    															   //type
    	if (key.compareTo(parentNode.getKey()) == 0) {
            //parentNode is the node to be removed
    		if (parentNode.getNext() == null) {
                return null;
            }
            if (parentNode.getLeft() == null) {
                return parentNode.getRight();
            }
            if (parentNode.getRight() == null) {
                return parentNode.getLeft();
            }
           
            // if we get here, then n has 2 children
            // Get the smallest value
            BSTnode<K, V> sucessorNode = parentNode.getRight();
            while(sucessorNode.getLeft() != null){
            	sucessorNode = sucessorNode.getLeft();
            }
            K smallVal = sucessorNode.getKey();
            parentNode.setKey(smallVal);
            parentNode.setRight( remove(parentNode.getRight(), smallVal) );
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
    	return getNode(key).getValue();  	
    }
        
    public boolean containsKey(K key) {
    	BSTnode<K,V> tempNode = getNode(key);
    	if(tempNode == null){
    		return false;
    	}
        return true ;       
    }

    
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

    

    public boolean containsValue(V value) {
        // TODO Auto-generated method stub
    	BSTnode<K, V> currNode = root;
    	while(currNode != null){
    		if(currNode.equals(value)){
    			return true;
    		}else 
    			currNode = currNode.getRight();
    		}
    	return false;
    }

    public int size() {
       return numItems;
    }

    @Override
    public boolean isEmpty() {
        return (numItems <= 0);
    }
    
    @Override
    public List<K> keys() {
    	ArrayList<K> result = new ArrayList<K>();
    	//Iterate over each node
    	if (root != null){ 
    		Stack<BSTnode<K, V>> treeStack = new Stack<BSTnode<K,V>>();
    		treeStack.push(root);
    		while(!treeStack.isEmpty()){
    			BSTnode<K, V> x = treeStack.pop();
    			//In order traversal 
    			if (x.getLeft() != null) treeStack.push(x.getLeft());
    			x.getKey();
    			if (x.getRight() != null) treeStack.push(x.getRight());
    		}
    	}
    	return result;
    }

    public List<V> values() {
        // TODO Auto-generated method stub
        return null;
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
    	if (root.getLeft() != null) sumLength += totalPathLength(root.getLeft(),pathLength+1);
    	if (root.getRight() != null) sumLength += totalPathLength(root.getRight(),pathLength+1);
    	return sumLength+pathLength+1;
    }
    
    
}
