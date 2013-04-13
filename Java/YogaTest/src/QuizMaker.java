///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Quiz Maker
// Files:            (list of source files)
// Semester:         CS302 Spring 2013
//
// Author:           Yujia Zhang
// CS Login:         yujia
// Lecturer's Name:  Deb Deppeler
// Lab Section:      318
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     (name of your pair programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Scanner;
import java.util.Random;
public class QuizMaker {

	public static void main(String[] args){

		// Declare constants and variables that will be used throughout
		// your program
		int questionNumber = 3;
		int minOperand = 1;
		int maxOperand = 10;
		int i = 0; // Menu number
		int n = 1; //Number of questions that have been completed
		int c = 0; //Number of questions that have been answered correctly
		String operator = "+";

		// Declare a Scanner attached to System.in. This should be the only
		// Scanner created in the entire program.
		Scanner in = new Scanner(System.in);
		String newLine = System.getProperty("line.separator");

		// Declare a Random object, used for generating random numbers
		// in the quiz. This should be the only Random created in the entire
		// program.
		java.util.Random rng = new java.util.Random();
		//TODO (code will be similar to how the Scanner was created)



		// DISPLAY WELCOME MESSAGE
		System.out.println("Welcome to the Quiz Maker 3000!"  + newLine);

		// MAIN PROGRAM LOOP
		while (i <= 5) { 
			// PRINT QUIZ SETTINGS
			System.out.println("Quiz Settings:" + newLine +
					"	Number of Questions: " + questionNumber + newLine +
					"	Minimum Operand Value: " + minOperand + newLine +
					"	Maximum Operand Value: " + maxOperand + newLine +
					"	Set Operator: " + operator + newLine);

			// (insert code here that will print the Quiz Maker's settings)

			//DISPLAY MENU
			System.out.println("Main Menu"); 
			System.out.println("1) Set Number of Questions");
			System.out.println("2) Set Minimum Operand Value");
			System.out.println("3) Set Maximum Operand Value");
			System.out.println("4) Set Operator");
			System.out.println("5) Take Quiz");
			System.out.println("6) Quit");
			System.out.println("What would you like to do? " + newLine); 


			//PROCESS USER'S MENU CHOICE
			// USER CHOSE TO SET NUMBER OF QUESTIONS
			in.hasNextInt();
			while (!in.hasNextInt()){
				System.out.println("Error: Invalid command.");    
				in.next();
			}
			i = in.nextInt();
			while (i>=7 || i<1){
				System.out.println("Error: Invalid command.");  
				i = in.nextInt();
			}
			if (i == 1) { 
				System.out.println("Enter a length for the quiz. ");
				questionNumber = in.nextInt();
				boolean hasPosInt = false;
				while (!hasPosInt) { 
					if(questionNumber > 0) hasPosInt = true;
					else{
						System.out.println("Error: Number of questions must be a positive integer.");
						questionNumber = in.nextInt();
					}
				}	
			}				// USER CHOSE TO SET MINIMUM OPERAND VALUE
			else if (i == 2) { 
				System.out.println("Enter a value for the minimum possible operand.");
				in.hasNextInt();
				while (!in.hasNextInt()){
					System.out.println("Error: Minimum operand must be an integer.");    
					in.next();
				}
				if(minOperand >= maxOperand) System.out.println("Error: The minimum operand must be strictly less than the maximum operand.");
				else minOperand = in.nextInt();	
			}			//(insert code here that will handle this menu option)


			// USER CHOSE TO SET MAXIMUM OPERAND VALUE
			else if (i == 3) { 
				System.out.println("Enter a value for the maximum possible operand.");
				in.hasNextInt();
				while (!in.hasNextInt()){
					System.out.println("Error: Maximum operand must be an integer.");    
					in.next();
				}
				if(minOperand >= maxOperand) System.out.println("Error: The maximum operand must be strictly greater than the minimum operand.");
				else minOperand = in.nextInt();			
			}//(insert code here that will handle this menu option)

			// USER CHOSE TOSET OPERATOR
			else if (i == 4) {
				System.out.println("Select an operator: (+,-,*,/,%)");
				operator = in.next();
				boolean validInput = false; 
				while (!validInput){
					if (operator.equals("+")) validInput = true;
					else if (operator.equals("-")) validInput = true;
					else if (operator.equals("*")) validInput = true;
					else if (operator.equals("/")) validInput = true;
					else if (operator.equals("%")) validInput = true;
					else {
						System.out.println("Error: Invalid operator choice.");
						operator = in.next();
					}

				}
			}//(insert code here that will handle this menu option)

			// USER CHOSE TO TAKE QUIZ
			else if (i == 5) {
				while (n <= questionNumber){
					int aRandomNumber = rng.nextInt(maxOperand)+ minOperand;
					int bRandomNumber = rng.nextInt(maxOperand)+ minOperand;
					

					if (operator.equals("+")) {
						System.out.println("What is " + aRandomNumber + " " + operator + " " + bRandomNumber + "?" );
						int answer = aRandomNumber + bRandomNumber;
						in.hasNextDouble();
						while (!in.hasNextDouble()){
							System.out.println("Error: Invalid answer.");    
							in.next();
						}
						double input = in.nextDouble();
						if (input == answer) {
							System.out.println ("Correct!");
							c =c+1;
						}
						else System.out.println ("Incorrect!");//Tell User if they are correct or incorrect

					}
					else if (operator.equals("-")) {
						System.out.println("What is " + aRandomNumber + " " + operator + " " + bRandomNumber + "?" );
						int answer = aRandomNumber - bRandomNumber;
						in.hasNextDouble();
						while (!in.hasNextDouble()){
							System.out.println("Error: Invalid answer.");    
							in.next();
						}
						double input = in.nextDouble();
						if (input == answer) {
							System.out.println ("Correct!");
							c =c+1;
						}
						else System.out.println ("Incorrect!");//Tell User if they are correct or incorrect

					}
					else if (operator.equals("*")) {
						System.out.println("What is " + aRandomNumber + " " + operator + " " + bRandomNumber + "?" );
						int answer = aRandomNumber * bRandomNumber;
						in.hasNextDouble();
						while (!in.hasNextDouble()){
							System.out.println("Error: Invalid answer.");    
							in.next();
						}
						double input = in.nextDouble();
						if (input == answer) {
							System.out.println ("Correct!");
							c =c+1;
						}
						else System.out.println ("Incorrect!");//Tell User if they are correct or incorrect
					}
					else if (operator.equals("/")) {
						while (bRandomNumber == 0) {
							bRandomNumber = rng.nextInt(maxOperand)+ minOperand;
						}
						double answer1 = (double) aRandomNumber / bRandomNumber;
						System.out.println("What is " + aRandomNumber + " " + operator + " " + bRandomNumber + "?" );
						in.hasNextDouble();
						while (!in.hasNextDouble()){
							System.out.println("Error: Invalid answer.");    
							in.next();
						}
						double input = in.nextDouble();
						if (answer1-0.005 <= input && input <= answer1+0.005) {
							System.out.println ("Correct!");
							c =c+1;
						}
						else System.out.println ("Incorrect!");//Tell User if they are correct or incorrect

					}					

					else {
						while (bRandomNumber == 0) {
							bRandomNumber = rng.nextInt(maxOperand)+ minOperand;
						}
						int answer = aRandomNumber % bRandomNumber ;
						System.out.println("What is " + aRandomNumber + " " + operator + " " + bRandomNumber + "?" );
						in.hasNextDouble();
						while (!in.hasNextDouble()){
							System.out.println("Error: Invalid answer.");    
							in.next();
						}
						double input = in.nextDouble();
						if (input == answer) {
							System.out.println ("Correct!");
							c =c+1;
						}
						else System.out.println ("Incorrect!");//Tell User if they are correct or incorrect

					}
					n++;
				}
				System.out.println ("Quiz Complete! Your Score: "+ c + " out of " +  questionNumber);// PRINT POST-QUIZ MESSAGES
			}

			//(insert code here that will handle this menu option)
			// FOR EACH QUESTION TO BE POSED TO USER

			//Select Two Random Operands

			//Calculate Answer

			//Prompt the user with the problem

			//Process their response





			// USER CHOSE TO QUIT PROGRAM
			else System.out.println("Goodbye!");
		}

	
	//(insert code here that will handle this menu option)


}
//PRINT END MESSAGE

}




