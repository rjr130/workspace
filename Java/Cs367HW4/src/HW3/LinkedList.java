package HW3;

public class LinkedList {
	private DblListnode<String> items;
	private int numItems;
	
	public static void main (String[] args) {
		LinkedList ll = new LinkedList();
		ll.swap(0, 3);
	}
	
	public LinkedList() {
		/**/
		DblListnode<String> items = new DblListnode<String>();
		this.numItems = 4;
	}
	
	public void swap(int pos1, int pos2) {
		// check for bad position
		if (pos1 < 0 || pos2 < 0 || pos1 > numItems || pos2 > numItems) {
			throw new IndexOutOfBoundsException();
		}
		
		if (pos1 == pos2) {
			return;
		}
		
		int max = 0;
		int min = 0;
		
		if (pos1 > pos2) {
			max = pos1;
			min = pos2;
		}
		else {
			max = pos2;
			min = pos1;
		}
		
		DblListnode<String> n1 = items;
		DblListnode<String> n2 = items;
		
		for (int i = 0; i < min + 1; i++) {
			n1 = n1.getNext();
		}
		
		for (int i = 0; i < max + 1; i++) {
			n2 = n2.getNext();
		}
		
		// create two new nodes to have the same value w/ the original node.
		DblListnode<String> tmp1 = new DblListnode<String>(n2.getPrev(), n1.getData(), n2.getNext());
		DblListnode<String> tmp2 = new DblListnode<String>(n1.getPrev(), n2.getData(), n1.getNext());
		
		// remove the original nodes from the linked-list to finish swap
		n1.getPrev().setNext(tmp2);
		n2.getPrev().setNext(tmp1);
		n1.getNext().setPrev(tmp2);
		if (n2.getNext() != null) {
			n2.getNext().setPrev(tmp1);
		}
	}
	
	public void printList(LinkedList l) {
		
	}
	
}
