package lecture;

import java.io.*; 	// need this to get user input
import java.util.*;

/**
 * Anne Bracy & 131 students
 * Running lecture examples for CSE 131, Spring 2012  
 */

/**
 * This is a program that uses a multi-dimensional array to wish
 * my son a happy birthday.
 */
public class TimoBirthdayArray {

	private static TimoFriend[] friends;
		
	public static void bestowGifts() {
		
		for (int i = 0; i < friends.length; i++) {
			TimoFriend f = friends[i];
			System.out.println(f.getName()+" gives Timo: "+f.getPresents());
		}	
	}
	
	/* main()
	 * asks the user some questions to generate a birthday wish for Timo
	 */
	public static void main(String[] args) {

		System.out.println("It's March 7th. That's Timo's Birthday!");
		int age = HelperMethods.getInteger("How old is Timo today?");
		
		System.out.println("Then let's invite "+age+" friends to Timo's party.");
		
		friends = new TimoFriend[age];
		
		for (int i = 0; i < age; i++) {
			String friendName = HelperMethods.getString("What's the name of Timo's friend #"+(i+1)+"?");
			int friendAge = HelperMethods.getInteger("How old is "+friendName+"?");
			friends[i] = new TimoFriend(friendName, friendAge);
		}
		
		System.out.println("Let the party begin.");
		bestowGifts();
		
	}	

}
