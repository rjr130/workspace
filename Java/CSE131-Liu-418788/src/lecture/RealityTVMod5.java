package lecture;

import java.io.BufferedInputStream; 	// need this to get user input
import java.util.*;

/**
 * Anne Bracy & 131 students
 * Running lecture examples for CSE 131, Spring 2012  
 */

/**
 * This is a program that simulates the elimination rounds of two 
 * reality television shows: Survivor and Dancing with the Stars. 
 * One will be eliminated according to the rules of the respective
 * shows.
 */
public class RealityTVMod5 {

	// start the class with your constants
	static final int NUM_VOTES_REQD = 4;
	static final int NUM_JUDGES = 3;
	static final int INVALID_CHOICE = -1;
	static final int SCORE_UPPER_BOUND = NUM_JUDGES * 10; // 3 judges x perfect score of 10
	
	// here are private instance variables
	private static List<RealityContestantMod5> playerList;
	
	// the scanner is a utility we will use to get input from the user.
	// it's okay if you don't understand this part just yet
	private static Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
	
	/*
	 * User decides how many players there are and also gives each player a name.
	 */
	public static void initPlayers() {
		int nPlayers;
		
		System.out.println("How many players are there?");
		nPlayers = scanner.nextInt();
		scanner.nextLine(); // throw away the "return"
		
		playerList = new LinkedList<RealityContestantMod5>();
		
		for (int i = 1; i <= nPlayers; i++) {
			System.out.println("What is the name of player #"+i+"?");
			String name = scanner.nextLine();
			playerList.add(new RealityContestantMod5(name));
		}
	}
	
	
	/* main()
	 * asks the user what they want to watch.
	 * calls the method for that show
	 */
	public static void main(String[] args) {
		int choice = INVALID_CHOICE;

		System.out.println("Welcome to the Reality Show Channel");
		initPlayers();
		
		while (choice == INVALID_CHOICE) {
			System.out.println("what show do you want to watch?");
			System.out.println("1: Survivor, 2: Dancing with the Stars");
			choice = scanner.nextInt(); // get 1,2,etc. from the user
			scanner.nextLine(); // ignore the "Return" button
		
			switch(choice) {
			case 1:	watchSurvivor();
			    	break;
			case 2: watchDWTS();
					break;
			default: System.out.println("Sorry, that's not a valid option");
					choice = INVALID_CHOICE;
			}		
		}
	}	

	/* Helper function that requires the user to Press Enter 
	 * before the program continues.
	 */
	public static void waitForEnter() {
		System.out.println("Press enter to continue");
		scanner.nextLine(); // throw away the "\n"
	}
	
	/* Prints out how many votes each survivor has. 
	 */
	public static void printPlayerVotes() {
		
		for (int i = 0; i < playerList.size(); i++) {
			int currScore = playerList.get(i).getScore();
			
			if (currScore > 0) {
				System.out.print(currScore+" for "+playerList.get(i).getName()+". ");
			}
		}
	}

	
	/* Goes through all the players and finds the highest number of votes.
	 */
	public static int getMaxVotes() {
		int maxVotes = 0;
		
		for (int i = 0; i < playerList.size(); i++) {
			int currScore = playerList.get(i).getScore();

			if (currScore > maxVotes) {
				maxVotes = currScore;
			}
		}
		return maxVotes;
	}	
	
	/* Goes through all the players and finds the first player with n votes.
	 */
	public static int getPlayerNVotes(int n) {
		int i = 0;
		
		for (i = 0; i < playerList.size(); i++) {
			int currScore = playerList.get(i).getScore();
			if (currScore == n) {
				break;
			}
		}
		if (i == playerList.size()) {
			System.out.println("getPlayerNVotes: Searched playerList for someone with "+n+" votes and didn't find anyone. You have been warned.");
		}
		return i;
	}	
	
	/* Randomly picks one of the players on the player list.
	 * Prints out their name & increments their score.
	 */
	public static void getRandomVote() {
		Random voter = new Random();   					// will give us a random integer
		int votee = voter.nextInt(playerList.size()); 	// from 0 up to # of players
		System.out.print("\t"+playerList.get(votee).getName()+": ");
		playerList.get(votee).incrementScore();
	}
	
	/* Goes through all the players and sets their scores to 0.
	 */
	public static void clearVotes() {
		
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).clearScore();
		}
	}			
	
	/* Collects votes until someone is officially voted off
	 */
	public static void watchSurvivor() {
		int maxVotes = 0;
		int numVotes = 0;
		
		int numPlayers = playerList.size();
		waitForEnter();
		clearVotes();
		System.out.println("There are "+numPlayers+" players "+playerList.toString()+".");
		
		if (numPlayers == 1) {
			System.out.println("Congratulations! You are the sole survivor.");
		} else {
		
			// done voting when someone gets just over the majority of votes or all votes are collected 
			while (maxVotes < ((playerList.size()+1)/2) && numVotes < playerList.size()) {
				getRandomVote();
				printPlayerVotes();
				maxVotes = getMaxVotes();
				numVotes++;
				System.out.print("\n");
			}
			int losingPlayer = getPlayerNVotes(maxVotes);
			System.out.println("I'm sorry, "+playerList.get(losingPlayer).getName()+", the tribe has spoken.");
			playerList.remove(losingPlayer);
			watchSurvivor();
		}
	}
	
	/* Collects scores from 3 judges and updates the player's score
	 * @param player the player whose scores will be updated
	 */
	public static void getJudgesScores(RealityContestantMod5 player) {
		
		System.out.print(player.getName()+" you're up! ");
		System.out.print("Press enter to see judges scores");
		scanner.nextLine();
		for (int i = 1; i <= NUM_JUDGES; i++) {

			int score = (int)(10*Math.random())+1; // convert random double into an int from 1-10
			System.out.print(score+"+ ");
			player.incrementScore(score);
		}
		System.out.println("= a total score of "+player.getScore());
	}
	
	/* Goes through the list of players, calling getJudgesScores for each
	 * then kicks out the dancer with the lowest total score
	 */
	public static void watchDWTS() {
		int worstDancer = 0;
		int minScore = SCORE_UPPER_BOUND;
		
		System.out.println("One dancer will be eliminated.");

		for (int i = 0; i < playerList.size(); i++) {
			getJudgesScores(playerList.get(i));
			if (i == 0) {
				minScore = playerList.get(i).getScore();
				worstDancer = 0;
			} else {
				int currScore = playerList.get(i).getScore();
				if (currScore < minScore) {
					minScore = currScore;
					worstDancer = i;
				}
			}
		}

		System.out.print(playerList.get(worstDancer).getName());
		System.out.println(", you have been eliminated from Dancing with the Stars.");
		playerList.remove(worstDancer); // remove the bad dancer from the list of players
	}
	
}
