import java.util.*;

public class Calculate {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		double weight = 1;
		int gender = 1;
		int activityLevel = 1;
		double calories = 1;
		
		System.out.println("Please input your weight in pounds");
		weight = in.nextDouble();
		while(weight <= 0){
			System.out.println("Weight must be a positive number!");
			weight = in.nextDouble();
		}
		//weight = in.nextDouble();
		
		System.out.println("Please input your gender. 1 for male and 2 for female.");
		gender = in.nextInt();
		while(gender != 1 && gender != 2){
			System.out.println("Please choose from 1 or 2, 1 for male and 2 for female.");
			gender = in.nextInt();
		}
		//gender = in.nextInt();
		
		System.out.println("Please input your activity level. 1 for low, 2 for medium, 3 for high.");
		activityLevel = in.nextInt();
		while(activityLevel != 1 && activityLevel != 2 && activityLevel != 3){
			System.out.println("Please choose from 1, 2 or 3, 1 for low, 2 for medium, 3 for high.");
			activityLevel = in.nextInt();
		}
		//activityLevel = in.nextInt();
		
		if(gender == 2){
			weight = weight / 1.6;
		}
		
		if(activityLevel == 1){
			calories = calories * 0.9;
		}
		else if(activityLevel == 2){
			calories = calories * 1.05;
		}
		else if(activityLevel == 3){
			calories = calories * 1.2;
		}
		
		System.out.println("The calories you should consume in one day is " + weight * 15 * calories);
	}
	
}
