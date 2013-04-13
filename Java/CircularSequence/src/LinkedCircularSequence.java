///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  
// File:             LinkedCircularSequenceIterator.java
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

/**
 * The LinkedCircularSequence class implements the CircularSequenceADT
 * using DblListnodes internally to hold the items in the sequence.
 * 
 * 
 * 
 * @author Junrui Ruan (jruan@wisc.edu) for CS 367, Spring 2012
 */
public class LinkedCircularSequence<E> implements CircularSequenceADT<E> {
	private DblListnode<E> head;
    private DblListnode<E> tail;
    private DblListnode<E> curr;
    private E tmp;
    private int numItems;

    public LinkedCircularSequence(){
            head = new DblListnode<E>(tmp);
            numItems = 0;
    }
    
    public void insert(E data){
    	
            DblListnode<E> tmp1 = new DblListnode<E>(data);
            if(isEmpty()){
            	curr = tmp1;
            }
            else if(size() == 1){
            	tail = curr;
            	curr = tmp1;
            	tail.setPrev(curr);
            	tail.setNext(curr);
            	curr.setNext(tail);
            	curr.setPrev(tail);
            }
            else{
            	DblListnode<E> tmp2 = curr;
            	curr = tmp1;
            	tmp2.getPrev().setNext(curr);
            	curr.setNext(tmp2);
            	curr.setPrev(tmp2.getPrev());
            	tmp2.setPrev(curr);
            }
            numItems++;
    }
   
    public E getCurrent() {
            if(isEmpty()) {
            	throw new EmptySequenceException();
            }
            else {
            	return curr.getData();
            }
    }
   
    public boolean isEmpty() {
            if(curr == null) {
            	return true;
            }
            else {
            	return false;
            }
    }
   
    public Iterator<E> iterator(){
            return new LinkedCircularSequenceIterator<E>(curr);
    }
   
    public void next() {
            if(isEmpty()) {
            	throw new EmptySequenceException();
            }
            else if(size() == 1) {
            	return;
            }
            else {
            	curr = curr.getNext();
            }
    }
   
    public void previous() {
            if(isEmpty()) {
            	throw new EmptySequenceException();
            }
            else if(size() == 1) {
            	return;
            }
            else {
            	curr = curr.getPrev();
            }
    }
   
    public E removeCurrent() {
            if(isEmpty()) {
            	throw new EmptySequenceException();
            }
            else if(size() == 1) {
            	numItems--;
                return curr.getData();
            }
            else {
            	DblListnode<E> tmp = curr;
                curr = tmp.getNext();
                tmp.getPrev().setNext(curr);
                curr.setPrev(tmp.getPrev());
                numItems--;
                return tmp.getData();
            }
    }
   
    public int size() {
    		return numItems;
    }
}