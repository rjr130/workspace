import java.util.*;

public class Treenode<T> {
	// fields
	private T data;
	private List<Treenode<T>> children;
		 
	// methods
	public T getData() { return data; }
	public List<Treenode<T>> getChildren() { return children; }
	
	public int countLeaves( Treenode<T> n ) {
		if (n == null) {
			return 0;
		}
		
		if (n.getChildren().size() == 0) {
			return 1;
		}
		
		int count = 0;
		Iterator<Treenode<T>> iter = n.getChildren().iterator();
		while (iter.hasNext()) {
			count = count + countLeaves(iter.next());
		}
		return count;
	}
}
