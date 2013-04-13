///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GraphAnalyser.java
// File:             GraphAnalyser.java
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
import java.io.File;
import java.util.*;

/**
 * The GraphAnalyser is the main class for the Programming 5
 * It analyse the data as the assignment requirement. Read in the file, and
 * print out the results. 
 * 
 * @author Junrui Ruan
 * @author Chew Wei Lai
 *
 */

public class GraphAnalyser {
	public static Integer minutes = 0;	//for counting the minutes

	public static void main(String[] args) {
		// Obtain file input
		if (args.length != 1) { 
			System.out.println("Usage: java GraphAnalyser fileName");
			System.exit(0);
		}
		// **Import users into memory**
		
		//	the Graph
		BasicGraph directedGraph = new BasicGraph();
		ArrayList<String> allUserName = new ArrayList<String>(); 
		try {
			Scanner fileIn = new Scanner(new File(args[0]));
			while (fileIn.hasNext()) {
				String[] tokens = fileIn.nextLine().split("[:]+");
				String user = tokens[0];
				allUserName.add(user);
				String[] followers = tokens[1].split("[,]+");

				if (!directedGraph.hasNode(user)) {
					directedGraph.addNode(user);
				}
				for (int i = 0; i < followers.length; i++) {
					if (!directedGraph.hasNode(followers[i])) {
						directedGraph.addNode(followers[i]);
					}
					directedGraph.addEdge(user, followers[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}

		// Start to analysis the data
		
		// the list of users with no followers
		ArrayList<String> noFollowers = new ArrayList<String>();
		// contains all the information about users
		TreeMap<String, Integer> mostTweeted = new TreeMap<String, Integer>();
		// the list for counting the mostTweeted
		ArrayList<String> mostTweetedList = new ArrayList<String>();
		// the list of the most user reached (using an ArrayList in case of 
		// two of more things has the same number of reached)
		ArrayList<String> mostReach = new ArrayList<String>();
		// the list for counting and storing tweet which reaches themselves
		ArrayList<String> reachSelf = new ArrayList<String>();
		// a tree contains all the node
		TreeSet<String> allNodes = new TreeSet<String>(); 
		// the list for counting and storing the most followers
		ArrayList<String> mostFollowers = new ArrayList<String>();
		
		
		int maxFollower = 0; // number of followers
		int maxTweeted = 0;
		int numMostReach = 0; // number of most reached

		for (String nodeKey : directedGraph) {
			// nodeKey's sucesssors list, will be used afterwards
			List<String> sucessors = directedGraph.Successors(nodeKey);
			
			// Search for graph node with the most followers
			if (sucessors.size() > maxFollower) {
				mostFollowers.clear();
				maxFollower = sucessors.size();
				mostFollowers.add(nodeKey);
			} 
			else if (sucessors.size() == maxFollower) {
				mostFollowers.add(nodeKey);
			}
			
			// For mostTweeted
			for (String follower : sucessors) {
				if (!mostTweeted.containsKey(follower)) {
					mostTweeted.put(follower, 0);
				}
				mostTweeted.put(follower, mostTweeted.get(follower) + 1);
			}

			// Search for the no followers ones
			if (sucessors.size() == 0) {
				noFollowers.add(nodeKey);
			}
			if (!allNodes.contains(nodeKey)) {
				allNodes.add(nodeKey);
			}

			// Count for the number of most reached Tweet
			if (directedGraph.bfs(nodeKey).size() > numMostReach) {
				mostReach.clear();
				mostReach.add(nodeKey);			
				numMostReach = directedGraph.bfs(nodeKey).size();
			}
			if (directedGraph.bfs(nodeKey).size() == numMostReach) {
				if (!mostReach.contains(nodeKey)) {
					mostReach.add(nodeKey);	
				}
				numMostReach = directedGraph.bfs(nodeKey).size();
			}
			
			// Check the number of reachSelf node
			for (String succ : sucessors) {
				if (directedGraph.dfs(succ).contains(nodeKey)) {
					if (!reachSelf.contains(nodeKey))
					reachSelf.add(nodeKey);
				}
			}
		}

		// Search for most tweeted
		for (String username : mostTweeted.keySet()) {
			if (mostTweeted.get(username) > maxTweeted) {
				mostTweetedList.clear();
				maxTweeted = mostTweeted.get(username);
				mostTweetedList.add(username);
			} else if (mostTweeted.get(username) == maxTweeted) {
				mostTweetedList.add(username);
			}
		}

		// Search who has no followers
		for (String nodeKey : allUserName) {
			for (String nodes : directedGraph.Successors(nodeKey)) {
				if (allNodes.contains(nodes)) {
					allNodes.remove(nodes);
				}
			}
		}
		
		
		// Work out the running time
		Collections.sort(mostFollowers);
		int minutes = 0;
		String startNode = mostFollowers.get(0);
		for (String nodeKey : directedGraph) {
			if(!nodeKey.equals(startNode)){
				List<String> path = directedGraph.shortestPath(
						startNode, nodeKey);
				if(path != null && path.size() > minutes){
					minutes = path.size();
				}
			}
		}
		
		// Printout the results
		System.out.println("Number of users: " + directedGraph.size());
		System.out.println("Number of follows links: " + 
				directedGraph.numEdges());
		System.out.println("DFS visit order: " + 
				directedGraph.dfs(directedGraph.iterator().next()));
		System.out.println("BFS visit order: " + 
				directedGraph.bfs(directedGraph.iterator().next()));
		System.out.println("Most followers: " + mostFollowers);
		System.out.println("No followers: " + noFollowers.size());
		System.out.println("Don't follow anyone: " + allNodes.size());
		System.out.println("Receive most tweets: " + mostTweetedList);
		System.out.println("Tweet reaches self: " + 
				reachSelf.size() + " " +reachSelf);
		if (mostReach.size() == 0) {
			System.out.println("Reach everyone: none");
		}
		else {
			System.out.println("Most users reached: " + 
					numMostReach + " " + mostReach);
		}
		System.out.println("Minutes to get tweet: " + (minutes - 1)); 
	}
}