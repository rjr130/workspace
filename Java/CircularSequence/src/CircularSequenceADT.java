import java.util.*;

/**
 * A CircularSequence ADT is a circular sequence of items.  A CircularSequence 
 * has a current item and the ability to move forward or backwards.  A 
 * CircularSequence can be modified by removing the current item or by adding 
 * an item before the current item.
 * 
 * @author Beck Hasti (hasti@cs.wisc.edu) for CS 367, Spring 2012
 */
public interface CircularSequenceADT<E> extends Iterable<E> {
    
    /**
     * Adds the given <tt>item</tt> immediately <em>before</em> the current 
     * item.  After the new item has been added, the new item becomes the 
     * current item.
     * 
     * @param item the item to add
     */
    void insert(E item);
    
    /**
     * Returns the current item.  If the CircularSequence is empty, an 
     * <tt>EmptySequenceException</tt> is thrown.
     * 
     * @return the current item
     * @throws EmptySequenceException if the CircularSequence is empty
     */
    E getCurrent();
    
    /**
     * Removes and returns the current item.  The item immediately 
     * <em>after</em> the removed item then becomes the  current item.  
     * If the CircularSequence is empty initially, an 
     * <tt>EmptySequenceException</tt> is thrown.
     * 
     * @return the removed item
     * @throws EmptySequenceException if the CircularSequence is empty
     */
    E removeCurrent();
    
    /**
     * Advances the current item forward one item resulting in the item 
     * that is immediately <em>after</em> the current item becoming the  
     * current item.  If the CircularSequence is empty initially, an 
     * <tt>EmptySequenceException</tt> is thrown.
     *
     * @throws EmptySequenceException if the CircularSequence is empty
     */
    void next();
    
    /**
     * Moves the current item backwards one item resulting in the item 
     * that is immediately <em>before</em> the current item becoming the  
     * current item.  If the CircularSequence is empty initially, an 
     * <tt>EmptySequenceException</tt> is thrown.
     *
     * @throws EmptySequenceException if the CircularSequence is empty
     */
    void previous();
    
    /**
     * Returns the number of items in this CircularSequence.
     * @return the number of items in this CircularSequence
     */
    int size();

    /**
     * Returns true if this CircularSequence is empty and false otherwise.
     * @return true if this CircularSequence is empty; false otherwise.
     **/
    boolean isEmpty();
    
    /**
     * Returns an iterator for this CircularSequence.
     * @return an iterator for this CircularSequence
     */
    Iterator<E> iterator();
}
