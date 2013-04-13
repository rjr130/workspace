package practice;

import java.io.BufferedInputStream; 	// need this to get user input
import java.util.*;

public class StartHere {

	/* 
	 * A main is always "static". Anything you call directly from the main
	 * must also be static.
	 */
	public static void yourMethod() {
		System.out.println("Poor me, I haven't written any method here!");
	}
	
	/* after prompting the user for a number, calls the Fibonacci fn.
	 * need a helper function here b/c fib is recursive --
	 * don't want to ask the user for input each time
	 */
	public static void fibCaller() {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		System.out.println("What number do you want the fibonacci of?");
		int n = scanner.nextInt();
		scanner.nextLine(); 
		System.out.println("The fibonacci of "+n+" is "+fib(n)+"!");
	}
	
	/* returns value of Fibonacci
	 * @param n the number you want to calculate Fib of, must be >= 0
	 */
	public static int fib(int n) {
		if (n <= 1) 
			 return n;
		else 
			 return fib(n-1) + fib(n-2);
	}
	
	/*
	 * Want to play with code? Options:
	 * 	(1) create a few file with a new class, instantiate it and try out the methods
	 * 		See how this is done with Towers of Hanoi
	 *  (2) create a method in this file, and call it from the main
	 *  	See how this is done with the fibonnaci method
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		int choice = 0;

		System.out.println("What would you like to try out?");
		System.out.println("1: Towers of Hanoi");
		System.out.println("2: Fibonacci");
		System.out.println("3: Some method you need to write");
		choice = scanner.nextInt(); // the user types NUMBER then RETURN. nextInt gives you the NUMBER
		scanner.nextLine(); // nextLine throws away the RETURN which the user typed, but we don't care about
		
		switch(choice) {
			case 1:	TowersOfHanoi towerGame = new TowersOfHanoi();
					towerGame.playTowers();
			    	break;
			case 2: fibCaller();
					break;
			case 3: yourMethod();
			break;
		}
	}
}
