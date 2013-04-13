package lecture;

import java.util.Random;


/**
 * Anne Bracy & 131 students
 * Running lecture examples for CSE 131, Spring 2012  
 */

/**
 * This is a class for Timo's friends. 
 */
public class TimoFriend {
	private String name;
	private int age;
	private String[] gifts;

	/*
	 * Constructor with the person's name
	 */
	public TimoFriend(String name, int age){
		this.name = name;
		this.age = age;
		gifts = new String[age];
		for (int i = 0; i < age; i++) {
			gifts[i] = HelperMethods.getPresent();
		}
	}
	
	//getters
	public String getName() {
		return name;
	}

	public String getPresents() {
		String presents = "";
		
		for (String s : gifts) {
			presents = s+", "+presents;	
		}
		presents = presents.substring(0, presents.length() -2);
		return presents;
	}
	
	public String toString() {
		return name;
	}
}
