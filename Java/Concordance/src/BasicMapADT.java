import java.util.List;

 /**
 * The BasicMap ADT represents a basic Map ADT that stores (key, value) pairs
 * where the keys are unique.  Operations are provided to add, remove, and
 * find information as well as to return lists of all the keys or of all the
 * values in the Map.  Additionally, the BasicMap ADT provides some methods
 * that give insight about the relative efficiency of a particular map: the
 * size (# of (key, value) pairs) and the total path length.  The total path
 * length is the sum of the lengths of the paths to each (key, value) pair.  
 * Thus, one measure of the average lookup time is (total path length)/size.
 * 
 * The basic Map does not allow null keys or null values to be added.
 * The ordering of keys is determined by the compareTo method.
 * 
 * DO NOT CHANGE THIS FILE
 *  
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 * @param <V> class representing the value
 */
public interface BasicMapADT<K extends Comparable<K>, V> {
    
    /**
     * Adds the given (key, value) pair to the Basic Map if the key is not
     * already in the Basic Map.  If the key is already in the Basic Map, a 
     * DuplicateKeyException is thrown.
     * @param key the key to insert into the Basic Map
     * @param value the value associated with the key
     * @throws DuplicateException if the key is already in the Basic Map
     * @throws NullPointerException if the key or value is null
     */
    void put(K key, V value) throws DuplicateException;
    
    /**
     * Removes the (key, value) pair from the Basic Map that corresponds with 
     * the given key.  If the key is in the Basic Map, the (key, value) pair 
     * is deleted and true is returned.  If the key is not in the Basic Map, 
     * the Basic Map is unchanged and false is returned. 
     * @param key the key for the (key, value) pair to delete from the Basic 
     *        Map
     * @return true if the removal is successful (i.e., the key was in the 
     *         Basic Map and has been removed), false otherwise (i.e., the key 
     *         was not in the Basic Map)
     * @throws NullPointerException if the key is null
     */
    boolean remove(K key);
    
    /**
     * Searches for the given key in the Basic Map and returns the value 
     * associated with it.  If the key is not in the Basic Map, null is 
     * returned.
     * @param key the key to search for
     * @return the value from the Basic Map corresponding to the key, if the
     *         key is in the Basic Map; null if the key is not in the Basic Map
     * @throws NullPointerException if the key is null
     */
    V get(K key);
    
    /**
     * Returns true if and only if the given key is in the Basic Map.
     * @param key the key to look for
     * @return true if and only if the given key is in the Basic Map
     * @throws NullPointerException if the key is null
     */
    boolean containsKey(K key);
    
    /**
     * Returns true if and only if the given value is in the Basic Map.
     * More specifically, returns true if and only if there is at least one
     * key k in the Basic Map whose associated value is v such that 
     * value.equals(v)
     * @param value the value to look for
     * @return true if and only if the given value is in the Basic Map
     * @throws NullPointerException if the value is null
     */
    boolean containsValue(V value);
    
    /**
     * Returns the number of (key, value) pairs in the Basic Map.
     * @return the number of (key, value) pairs in the Basic Map
     */
    int size();
    
    /**
     * Returns true if and only if the Basic Map is empty.
     * @return true if the Basic Map is empty, false otherwise
     */
    boolean isEmpty();
   
    /**
     * Returns a list of the keys in the Basic Map in order from smallest to
     * largest.
     * @return a list of the keys in the Basic Map in order
     */
    List<K> keys();
    
    /**
     * Returns a list of the values in the Basic Map in order of the associated
     * keys from smallest to largest.
     * @return a list of the values in the Basic Map in key order
     */
    List<V> values();
    
    /**
     * Returns the total path length.  The total path length is the sum of the 
     * lengths of the paths to each (key, value) pair.
     * @return the total path length
     */
    int totalPathLength();
}