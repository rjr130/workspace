package lecture;


/**
 * Anne Bracy & 131 students
 * Running lecture examples for CSE 131, Spring 2012  
 * RealityContestantMod3.java
 */

/**
 * This is a class for the Reality Show Contestant
 * Each contestant has a name and a score 
 */
public class RealityContestantMod3 {
	private String name;
	private int score;
	
	/*
	 * Constructor needs the person's name
	 */
	public RealityContestantMod3(String name){
		this.name = name;
		score = 0;
	}
	//getters
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementScore() {
		score++;
	}
	/* Method updates a contestants score by the amount given
	 * @param update amount you want to increase score by
	 */
	public void incrementScore(int update) {
		score += update;
	}
	
}
