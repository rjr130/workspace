///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GraphAnalyser.java
// File:             BasicGraph.java
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

/**
 * BasicGraph class is used to use the Graphnode to represent a whole graph. It 
 * will be used by the GraphAnalyser to analyse the data.
 * 
 * @author Chew Wei Lai
 * @author Junrui Ruan
 *
 */
public class BasicGraph implements Iterable <String> {
	//NodeMap
	private TreeMap<String, Graphnode<String>> nodeMap = new TreeMap<String, 
			Graphnode<String>>(); // stores all the graph nodes
	private int numEdges = 0; // number of edges

	/**
	 * Adds a node with the given label to the graph.
	 * @param label label of newly added node
	 * @return false if a node with the given label is already in the graph, 
	 * true otherwise
	 * @throws IllegalArgumentException if label is null
	 */
	public boolean addNode(String label) throws IllegalArgumentException {
		//if first node is null, throw exception
		if(label == null) throw new IllegalArgumentException();
		//return false if a node with the given label is already in the graph
		if(nodeMap.containsKey(label)){ 
			return false;
		}
		//else add node
		else{ nodeMap.put(label, new Graphnode<String>(label));
		return true;
		}
	}
	/**
	 * Return true if and only if a node with the given label is in the graph.
	 * @param label label of node to check
	 * @return true if and only if there is a node with the given label 
	 * in the graph
	 * @throws IllegalArgumentException if label is null
	 */
	public boolean hasNode(String label) throws IllegalArgumentException {
		//return true iff a node with the given label is in the graph.
		if(nodeMap.containsKey(label)) {
			return true;	
		}
		else {
			return false;
		}
	}
	/**
	 * Return labels of immediate successors of the given node in 
	 * alphabetical order.
	 * @param label label of the start node
	 * @return labels of immediate neighbors of the start node in 
	 * alphabetical order
	 * @throws IllegalArgumentException if label is null or if there is 
	 * no node in the graph with the given label
	 */
	public List<String> Successors(String label) throws 
	IllegalArgumentException {
		ArrayList<String> result = new ArrayList<String>();
		if(label == null) {
			throw new IllegalArgumentException();
		}
		Graphnode<String> startNode = nodeMap.get(label);
		if(startNode == null) {
			throw new IllegalArgumentException();
		}
		for(Graphnode<String> node : startNode.Successors()){
			result.add(node.toString());
		}
		return result;
	}

	/**
	 * Return a list of node labels in the order they are visited using a 
	 * depth-first search starting at the node with the given label.
	 * @param label label of the start node
	 * @return list of the node labels in DFS order
	 * @throws IllegalArgumentException if label is null or there is no 
	 * node with this label in the graph
	 */

	public List<String> dfs(String label) throws IllegalArgumentException {
		if(label == null) throw new IllegalArgumentException();
		Graphnode<String> startNode = nodeMap.get(label);
		//return an empty list 
		if(startNode == null) return new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		dfs(startNode,result);
		return result;
	}
	/**
	 * Return a set of nodes in the order they are visited using a depth-first 
	 * search starting at the node with the given label.
	 * @param startNode start node
	 * @param visited list of name of nodes
	 * @throws IllegalArgumentException if label is null or there is no node 
	 * with this label in the graph
	 */
	private void dfs(Graphnode<String> startNode, ArrayList<String> visited) 
			throws IllegalArgumentException {
		visited.add(startNode.toString());
		for (Graphnode<String> node : startNode.Successors()) {
			if (!visited.contains(node.getValue())) {
				dfs(node,visited);
			}
		}
	}
	/**
	 * Return a list of node labels in the order they are visited using a 
	 * breadth-first search starting at the node with the given label.
	 * @param label label of the start node
	 * @return list of the node labels in BFS order
	 * @throws IllegalArgumentException
	 */
	public List<String> bfs(String label) throws IllegalArgumentException {
		if(label == null) throw new IllegalArgumentException();
		Graphnode<String> startNode = nodeMap.get(label);
		//return an empty list
		if(startNode == null) return new ArrayList<String>(); 
		LinkedList<Graphnode<String>> queue = 
				new LinkedList<Graphnode<String>>(); //used as a queue
		ArrayList<String> result = new ArrayList<String>();
		TreeSet<Graphnode<String>> visited = new TreeSet<Graphnode<String>>();
		//Start of breath first search
		visited.add(startNode);
		queue.add(startNode);
		// end loop while queue not empty
		while (!queue.isEmpty()) {
			Graphnode<String> current = queue.remove();
			// end for every successor k
			for (Graphnode<String> k : current.Successors()) {
				// end if k not visited
				if (! visited.contains(k)){
					visited.add(k);
					queue.add(k);
				}
			} 
			result.add(current.toString());
		} 
		return result;
	}
	/**
	 * Add to the graph the specified directed edge from the node with the label
	 * label1 to the node with the label label2.
	 * @param label1 label of source node of the edge
	 * @param label2 label of target node of the edge
	 * @return true if and only if there is an edge from the node labeled label1
	 *  to the node labeled label2
	 * @throws IllegalArgumentException if either label is null or if there are
	 * no nodes in the graph with the given labels
	 */
	public boolean addEdge(String label1, String label2) throws 
	IllegalArgumentException {
		//Handle illegal arguments
		if(label1 == null || label2 == null || label1.equals(label2)) {
			throw new IllegalArgumentException(); 
		}
		//Handle self edges
		Graphnode<String> sourceNode = nodeMap.get(label1);
		Graphnode<String> targetNode = nodeMap.get(label2);
		if(sourceNode == null || targetNode == null) 
			throw new IllegalArgumentException();
		sourceNode.Successors().add(targetNode);
		numEdges++;
		return true;
	}
	/**
	 * Return true iff the graph contains an edge from the node 
	 * with the label label1 to the node with the label label2.
	 * @param label1 label of source node of the edge
	 * @param label2 label of target node of the edge
	 * @return true if and only if there is an edge from the node labeled 
	 * label1 to the node labeled label2
	 * @throws IllegalArgumentException
	 */
	public boolean hasEdge(String label1, String label2) 
			throws IllegalArgumentException {
		//Handle illegal arguments
		if(label1 == null || label2 == null || label1.equals(label2)) 
			throw new IllegalArgumentException(); 
		//Handle self edges
		Graphnode<String> sourceNode = nodeMap.get(label1);
		Graphnode<String> targetNode = nodeMap.get(label2);
		if(sourceNode == null || targetNode == null) 
			throw new IllegalArgumentException();
		return sourceNode.Successors().contains(targetNode);
	}

	/**
	 * Find the shortest path from a start node to a finish node.
	 * @param startLabel label of the start node
	 * @param finishLabel label of the finish node
	 * @return sequence of nodes along the path, or null if there is 
	 * no such path
	 * @throws IllegalArgumentException if either label is null or if 
	 * there are no nodes in the graph with the given labels
	 */
	public List<String> shortestPath(String startLabel, String finishLabel) 
			throws IllegalArgumentException {
		//handle illegal argument
		if(startLabel == null || finishLabel == null) 
			throw new IllegalArgumentException(); 
		//handle self edges
		Graphnode<String> startNode = nodeMap.get(startLabel);
		Graphnode<String> targetNode = nodeMap.get(finishLabel);
		if(startNode == null || targetNode == null) 
			throw new IllegalArgumentException();

		LinkedList<Graphnode<String>> queue = 
				new LinkedList<Graphnode<String>>(); //used as a queue
		ArrayList<String> result = null;
		TreeMap<Graphnode<String>,Graphnode<String>> breadcrumbs = 
				new TreeMap<Graphnode<String>,Graphnode<String>>();
		//bfs
		breadcrumbs.put(startNode,null);
		queue.add(startNode);
		// end while queue not empty
		outerLoop : while (!queue.isEmpty()) {
			Graphnode<String> current = queue.remove();
			for (Graphnode<String> k : current.Successors()) {
				// end if k not visited
				if (!breadcrumbs.containsKey(k)){
					breadcrumbs.put(k,current);
					queue.add(k);
				} 
				if(k.equals(targetNode)){
					break outerLoop;
				}
			} 
		} 
		if(breadcrumbs.containsKey(targetNode)){ 
			result = new ArrayList<String>();
			Graphnode<String> current = targetNode;
			while(current != null){
				result.add(current.getValue());
				current = breadcrumbs.get(current);
			}
			//Reverse List
			Collections.reverse(result); 
		}
		return result;
	}
	/**
	 * Return the number of edges in the graph.
	 * @return number of edges in the graph
	 */
	public int numEdges() {
		return numEdges;
	}
	/**
	 * Return the number of nodes in the graph.
	 * @return number of nodes in the graph
	 */
	public int size() {
		return nodeMap.size();
	}
	/**
	 * Return true if and only if the graph has size 0 (i.e., no nodes or edges).
	 * @return true if and only if the graph is empty
	 */
	public boolean isEmpty() {
		if(nodeMap.size() == 0) {
			return true;
		}
		else return false;
	}
	/**
	 * Return an iterator over the node labels in the graph.
	 * @return iterator over the node labels in the graph (in alphabetical order).
	 */
	public Iterator<String> iterator() {
		return nodeMap.keySet().iterator();
	}
}