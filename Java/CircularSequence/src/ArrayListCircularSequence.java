import java.util.*;

/**
 * The ArrayListCircularSequence class implements the CircularSequenceADT
 * using an ArrayList internally to hold the items in the sequence.
 * 
 * You can use this implementation of a CircularSequence to develop and test
 * your MessageSequenceEditor independently of your LinkedCircularSequence 
 * class. 
 * 
 * Note: this class does NOT meet all the specifications of the 
 * CircularSequenceADT.  In particular, it does not throw 
 * EmptySequenceExceptions.
 * 
 * @author Beck Hasti (hasti@cs.wisc.edu) for CS 367, Spring 2012
 */
public class ArrayListCircularSequence<E> implements CircularSequenceADT<E> {
    private ArrayList<E> loop;
    private int curr;  // index of current item
    
    /**
     * Constructs a new empty circular sequence.
     */
    public ArrayListCircularSequence() {
        loop = new ArrayList<E>();
        curr = -1;
    }
    
    public E getCurrent() {
        return loop.get(curr);
    }

    public void insert(E item) {
        if (loop.isEmpty()) {
            loop.add(0, item);
            curr = 0;
        } else {
            loop.add(curr, item);
        }    
    }

    public boolean isEmpty() {
        return loop.isEmpty();
    }

    public Iterator<E> iterator() {
        int size = loop.size();
        ArrayList<E> copy = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            copy.add(loop.get((curr + i)%size));
        }
        return copy.iterator();
    }

    public void next() {
        curr = (curr == loop.size()-1) ? 0 : curr + 1;
    }

    public void previous() {
        curr = (curr == 0) ? loop.size() - 1 : curr - 1;
    }

    public E removeCurrent() {
        E item = loop.remove(curr);
        if (loop.isEmpty()) {
            curr = -1;
        }
        return item;
    }

    public int size() {
        return loop.size();
    }
    
}
