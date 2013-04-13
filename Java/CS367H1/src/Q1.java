import java.util.*;

public class Q1 {
	public static void main (String args[]) {
		System.out.println("Hello ListADT");
	}
	
	public static ListADT<String> union(ListADT<String> list1, ListADT<String> list2) 
		    throws BadListException {
		// If list1 or list2 (or both list1 and list2) is null, throw a BadListException. 
		// If list1 and list2 are both empty, return a new empty list.
		// If list1 is empty (but not list2), return a new list containing the strings in
		// list1 list2 with all duplicates removed.
		// If list2 is empty (but not list1), return a new list containing the strings in
		// list2 list1 with all duplicates removed.
		// Otherwise, create and return a new list that contains the strings in list1 and
		// the strings in list2 with all duplicates removed.
		//
		// Examples:
		//  list1: "a","b","c"          list2: "d","e","f"      result: "a","b","c","d","e","f"
		//  list1: "a","c","b","d"      list2: "e","d","a","f"  result: "a","c","b","d","e","f"
		//  list1: "a","b","c","b","a"  list2: "c","a","b"      result: "a","b","c"
		//
		// Note: the list returned does not need to be in any particular order
		if (list1 == null || list2 == null) {
			throw BadListException;
		}
		
		if (list1.isEmpty && list2.isEmpty) {
			ListADT<String> list3 = new ArrayList<String>();
			return list3;
		}
		
		if (list1.isEmpty && !list2.isEmpty) {
			ListADT<String> list3 = new ArrayList<String>();			
			for(int i = 0; i < list2.size(); i++) {
				int flag = 0;
				Iterator<String> itr = list3.iterator();
				while (itr.hasNext()) {
					String nextWord = itr.next();
					if (nextWord == list2.get(i)) {
						flag = 1;
						continue;
					}
				}
				if (flag == 0) {
					list3.add(list2.get(i));
				}
			}
			return list3;
		}
		
		if (list2.isEmpty && !list1.isEmpty) {
			int flag = 0;
			ListADT<String> list3 = new ArrayList<String>();			
			for(int i = 0; i < list1.size(); i++) {
				int flag = 0;
				Iterator<String> itr = list3.iterator();
				while (itr.hasNext()) {
					String nextWord = itr.next();
					if (nextWord == list1.get(i)) {
						flag = 1;
						continue;
					}
				}
				if (flag == 0) {
					list3.add(list1.get(i));
				}
			}
			return list3;
		}
		
		else {
			ListADT<String> list3 = new ArrayList<String>();
			for (int i = 0; i < list1.size(); i++) {
				int flag = 0;
				Iterator<String> itr = list3.iterator();
				while (itr.hasNext()) {
					String nextWord = itr.next();
					if (nextWord == list1.get(i)) {
						flag = 1;
						continue;
					}
				}
				if (flag == 0) {
					list3.add(list1.get(i));
				}
			}
			for(int i = 0; i < list2.size(); i++) {
				int flag = 0;
				Iterator<String> itr2 = list3.iterator();
				while (itr2.hasNext()) {
					String nextWord = itr2.next();
					if (nextWord == list2.get(i)) {
						flag = 1;
						continue;
					}
				}
				if (flag == 0) {
					list3.add(list2.get(i));
				}
			}
			return list3;
		}
	}
}
