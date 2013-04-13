///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GraphAnalyser.java
// File:             Graphnode.java
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
import java.util.Set;
import java.util.TreeSet;


/**
 * The Graphnode class is used to represent the nodes in the graph
 *  
 * @author Chew Wei Lai
 * @author Junrui Ruan
 *
 * @param <T>
 */
class Graphnode<T extends Comparable<T>> implements Comparable<Graphnode<T>> {
    private Set<Graphnode<T>> successors = new TreeSet<Graphnode<T>>();
    private T value;
 
    /**
     * Constructor for graph node
     * @param value value to putin
     */
    public Graphnode(T value) {
        this.value = value;
    }
    
    /**
     * Retrieves the value stored
     * @return the value
     */
    public T getValue() {
        return value;
    }
    
    /**
     * Retrieves a set of successors
     * @return the set of successors
     */
    public Set<Graphnode<T>> Successors() {
    	return successors;
    }
    
    @Override
    public String toString(){
    	return value.toString();
    }

	@Override
	public int compareTo(Graphnode<T> other) {
		return value.compareTo(other.getValue());
	}
}
