import java.util.*;

public class BSTnode<K> {
	// fields
	private K key;
	private BSTnode<K> left, right;
	 
	// methods
	public K getKey() { return key; }
	public BSTnode<K> getLeft()  { return left; }
	public BSTnode<K> getRight() { return right; }
	public void setLeft(BSTnode<K> newL)  { left  = newL; }
	public void setRight(BSTnode<K> newR) { right = newR; }
	
	public K secondLargest(BSTnode<K> n) {
		// no node
		if (n == null) {
			return null;
		}
		// one node
		if (n.getLeft() == null && n.getRight() == null) {
			return null;
		}
		
		if (n.getLeft() != null && n.getRight() == null) {
			n = n.getLeft();
			while (n.getRight() != null) {
				n = n.getRight();
			}
			return n.getKey();
		}
		
		// if n.getRight() != null
		else {
			BSTnode<K> last = n;
			n = n.getRight();
			BSTnode<K> curr = n;
			while (curr.getRight() != null) {
				last = last.getRight();
				curr = curr.getRight();
			}
			
			// if last has leftChild
			if (curr.getLeft() == null) {
				return last.getKey();
			}
			else {
				last = curr;
				curr = curr.getLeft();
				if (curr.getRight() == null) {
					return curr.getKey();
				}
				if (curr.getRight() != null) {
					last = curr;
					curr = curr.getRight();
				}
				while (curr.getRight() != null) {
					last = last.getRight();
					curr = curr.getRight();
				}
				return curr.getKey();
			}
			
		}
	}
		
}
