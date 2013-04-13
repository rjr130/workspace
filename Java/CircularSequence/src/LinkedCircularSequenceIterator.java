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


public class LinkedCircularSequenceIterator<E> implements Iterator<E> {
	private DblListnode<E> current;
	private DblListnode<E> head;
	private boolean flag = false;

	/**
	 * constructor of LinkedCircularSequenceIterator 
	 * @param node
	 */
	public LinkedCircularSequenceIterator(DblListnode<E> node){
		current = node;
		head = node;
	}
	
	public boolean hasNext(){
			if(flag){
				if(head==current){
					return false;
				}
			}
		return true;
	}

	public E next() {
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		if(current==head){ 
			flag=true;
		}
		E result = current.getData();
		current = current.getNext();
		return result;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}


