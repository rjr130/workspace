import java.util.*;

public class BinaryTreenode<T> {
	// fields
	private T data;
	private BinaryTreenode<T> left, right;
	 
	// methods
	public T getData()                { return data; }
	public BinaryTreenode<T> getLeft()        { return left; }
	public BinaryTreenode<T> getRight()       { return right; }
	public void setLeft(BinaryTreenode<T> newL)  { left  = newL; }
	public void setRight(BinaryTreenode<T> newR) { right = newR; }
	
	public static List<Integer> findInRange( BinaryTreenode<Integer> n, 
			int low, int high ) {
		List<Integer> list = new ArrayList<Integer>();
		if (n == null) {
			return list;
		}
		
		if (n.getData() > low && n.getData() < high) {
			if (!list.contains(n.getData())) {
				list.add(n.getData());
			}
		}
		
		if (n.getLeft() == null && n.getRight() == null) {
			return list;
		}
		
		if (n.getLeft() != null) {
			findInRange(n.getLeft(), low, high);
		}
		
		if (n.getRight() != null) {
			findInRange(n.getRight(), low, high);
		}
		
		return list;
	}
}
