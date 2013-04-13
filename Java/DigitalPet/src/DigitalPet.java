//DigitalPet
///////////////////////////////////////////////////////////////////////////////
//                   
// Title:            Digital Pet
// Files:            DigitalPet.java
// Semester:         Fall 2011
//
// Author:           Junrui Ruan jruan@wisc.edu (rjr130@gmail.com)
// CS Login:         junrui
// Lecturer's Name:  Jim Skrentny
// Lab Section:      328
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Dayna Hashemi (dhhashemi@wisc.edu)
// CS Login:         dayna
// Lecturer's Name:  Jim Skrentny
// Lab Section:      327
//
//                  
// Credits:          none
///////////////////////////////////////////////////////////////////////////////

//Class header comment.
/**
  * Simulates a simple digital pet in which the pet owner is the user.
  *
  * <p>Bugs: none know
  *
  * @author Junrui Ruan, Dayna Hashemi
  */

import java.util.Random;
import java.util.Scanner;

public class DigitalPet
{

	//Main method header comment.
	/**
	  * User chooses from 4 activities for their digital pet to perform. Each
	  * activity effects the health, hunger, and/or happiness of the pet. The
	  * pet does 5 activities a day unless otherwise stated and at the end of
	  * each day, a status report on the pet's hunger, health,and happiness
	  * from 0-10 is given. This continues on for 9 days in which afterwards
	  * a final report summarizes the pets status.
	  *
	  */

	public static void main(String[] args)
	{
		//Declare and initialize a Scanner.
		Scanner input = new Scanner(System.in);
		
		//Get seed value from user.
		System.out.println("Please enter a seed value [integer]:");
		int seed = input.nextInt();
		input.nextLine();
		//Use value entered to make the pseudo random number generator.
		Random rand = new Random(seed);

		// The 3 major attributes.
		int happiness = 5;
		int health = 5;
		int hunger = 5;     
		
		// The 3 major attributes backups.
		int happiness0 = 5;
		int health0 = 5;
		int hunger0 = 5;    
		
		// Create temporary variables for use if necessary.
		int temp = 0;                                                
		int temp2 = 0;                                               
		int temp3 = 0;                                               
		//Begin day loop.
		for (int day = 1; day <= 9; ++day)							
		{			
			//Print daily report.
			System.out.println();
			System.out.println("Good morning.");                    
			
			System.out.println("Today is day: " + day);              

			System.out.println("Here's how your pet is doing:");     
			System.out.println("Happiness: " + happiness);
			System.out.println("Health: " + health);
			System.out.println("Hunger: " + hunger);                
			
			//Begin menu loop.
			for (int activity = 1; activity <= 5; ++activity)      
			{
				//Prompt user for their decision.
				System.out.println("What would you like to do?");    
			
				System.out.println("1: Feed");
				System.out.println("2: Play");
				System.out.println("3: Heal");
				System.out.println("4: Sleep");                    
				
				//Tells the user to input new value if user does not enter an
				//integer value.
				while (!input.hasNextInt()){
					System.out.println("Please enter a number 1-4.");
					System.out.println("What would you like to do?"); 
					System.out.println("1: Feed");
					System.out.println("2: Play");
					System.out.println("3: Heal");
					System.out.println("4: Sleep"); 
					input.nextLine();								 
																	
				}
				// Use temp to get the choice number.
				temp = input.nextInt();
				input.nextLine();	
				
				//If user chose feed.
				if (temp == 1)
				{
					//Prompt for which food to eat.
					System.out.println("What would you like to feed your " +
							"pet?");
					System.out.println("1: Broccoli");
					System.out.println("2: Candy");      
					
					//Tells the user to input new value if user does not enter
					//an integer value.
					while (!input.hasNextInt())
					{
						System.out.println("Please enter 1 or 2.");
						System.out.println("What would you like to feed " +
								"your pet?");
						System.out.println("1: Broccoli");
						System.out.println("2: Candy"); 
						input.nextLine();							
					}
					temp = input.nextInt();
					input.nextLine();
					//Tells the user to input new value if user does not
					//enter integer 1 or 2.
					while (temp > 2 || temp < 1)
					{
						System.out.println("Please enter 1 or 2.");
						System.out.println("What would you like to feed " +
								"your pet?");
						System.out.println("1: Broccoli");
						System.out.println("2: Candy"); 
						temp = input.nextInt();
						input.nextLine();							 
																	
					}
					// If feed Broccoli.
					if (temp == 1)
					{
						//update attributes accordingly.
						// 50% chance that happiness = happiness-1.                 
						happiness = happiness - rand.nextInt(2);               
						health++;
						hunger++;
					}
					// If feed Candy.
					else if (temp == 2) 
					{
						//Updates attributes acccordingly.
						// 50% chance that health = health-1.					
						health = health - rand.nextInt(2);
						hunger++;
						happiness++;							
					}
					//If on 5th activity, updates correct attributes.
					if (activity == 5)								
					{
						health--;
					}
				}
				//If user chose play.
				else if (temp == 2)
				{
					//Update attributes accordingly.
					happiness++;
					hunger--;
					//If on 5th activity, updates correct attributes.
					if (activity == 5)								
					{
						health--;
					}
				}	
				//If user chose heal.
				else if (temp == 3)
				{
					//Update attributes accordingly and ends day.
					health = health + 5;
					hunger--;
					happiness--;
					System.out.println("UW Hospital is nice! I feel " +
							"healthier, but I'm exhausted - good night!");
					break;                                           
				}
				//If user chose sleep.
				else if (temp == 4)
				{
					//Update attributes accordingly and ends day.
					if (activity < 4)
					{
						happiness--;
					}
					else
					{
						happiness++;
					}
					break;                                           
				}	
				else
				{
					//Tells the user to reenter a value if user enters a
					//number not 1-4.
					System.out.println("Please enter a number 1-4.");
					activity--;										
				}
			//End of menu loop.
			}
			//Randomly update happiness, health, and hunger.
			happiness = happiness + rand.nextInt(5)-2;                        
			health = health + rand.nextInt(2);                       
			hunger = hunger - rand.nextInt(3);    
			
			//Print today's changes report.
			System.out.println("Your pet's condition changed as follows " +
					"today");
			temp = happiness - happiness0;
			temp2 = health - health0;
			temp3 = hunger - hunger0;
			if (temp > 0)
			{
				System.out.println("Happiness: +" + temp);
			}
			else if (temp == 0)
			{
				System.out.println("Happiness: No Change");
			}
			else 
			{
				System.out.println("Happiness: " + temp);
			}
			
			if (temp2 > 0)
			{
				System.out.println("Health: +" + temp2);
			}
			else if (temp2 == 0)
			{
				System.out.println("Health: No Change");
			}
			else 
			{
				System.out.println("Health: " + temp2);
			}
			
			if (temp3 > 0)
			{
				System.out.println("Hunger: +" + temp3);
			}
			else if (temp3 == 0)
			{
				System.out.println("Hunger: No Change");
			}
			else 
			{
				System.out.println("Hunger: " + temp3);
			}
			
			//Updates overall happiness, health, and hunger.
			//Adjust happiness, health, and hunger values to be within bounds.
			if (happiness <= 0)
			{
				System.out.println("I am very unhappy now!!!");
				happiness = 0;
			}
			if (happiness >= 10)
			{
				System.out.println("I feel great!");
				happiness = 10;
			}
			if (health <= 0)
			{
				System.out.println("I feel very sick now!");
				health = 0;
			}
			if (health >= 10)
			{
				System.out.println("I feel very healthy now!");
				health = 10;
			}
			if (hunger <= 0)
			{
				System.out.println("I really want to have something to eat!");
				hunger = 0;
			}
			if (hunger >= 10)
			{
				System.out.println("Too much food...");
				hunger = 10;
			}
			happiness0 = happiness;
			health0 = health;
			hunger0 = hunger;
		//End of day loop.
		}
		//Print end-of-simulation report.
		System.out.println();
		System.out.println("Here's how your pet did:");
		System.out.println("Happiness: " + happiness);
		System.out.println("Health: " + health);
		System.out.println("Hunger: " + hunger);
	}
}