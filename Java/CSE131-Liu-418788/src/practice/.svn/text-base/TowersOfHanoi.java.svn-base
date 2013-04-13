package practice;

import java.io.BufferedInputStream; 	// need this to get user input
import java.util.*;

public class TowersOfHanoi {
	
	public void moveDiscs(int n, boolean moveLeft) {
		if (n == 0) {
			return;
		}
		moveDiscs(n-1, !moveLeft);
		System.out.print("Move disc "+n+ " to the ");
		if (moveLeft)
			System.out.print("left.\n");
		else
			System.out.print("right.\n");
		moveDiscs(n-1, !moveLeft);
	}

	public void playTowers() {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		System.out.println("How many discs are there?");
		int n = scanner.nextInt(); // the user types NUMBER then RETURN. nextInt gives you the NUMBER
		scanner.nextLine(); // nextLine throws away the RETURN which the user typed, but we don't care about
		
		moveDiscs(n, true);
	}
}
