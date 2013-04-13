import java.util.*;

public class EggGrade {
	public static void main(String[] args){
		double weight = 1;
		String color = "a";
		Scanner in = new Scanner(System.in);
		int choice = 1;
		
		System.out.println("Please select what would you like to do. \n1. Grade a egg.(press 1)\n" +
				"2. Quit Program.(Press 2)\n");
		choice = in.nextInt();
		while(choice != 1 && choice !=2){
			System.out.println("Choice should be 1 or 2.");
			choice = in.nextInt();
		}
		
		while(choice != 2){			
			System.out.println("Please input the weight of eggs in ounces.");
			weight = in.nextDouble();
			while(weight <= 0){
				System.out.println("weight must be a positive number");
				weight = in.nextInt();
			}
			
			System.out.println("Please choose the color of the egg.");
			color = in.next();
			while(!color.equals("w") && !color.equals("b") && !color.equals("o")){
				System.out.println("Please xxxxxxxxxx");
				color = in.next();
			}
			
			if(weight >= 30){
				if(color.equals("w")){
					System.out.println("Jumbo");
				}
			}
			else if(weight >= 27){
				System.out.println("Extra Large");
			}
			else if(weight >= 24){
				System.out.println("Large");
			}
			else{
				System.out.println("Commercial");
			}
			
			System.out.println("Please select what would you like to do. \n1. Grade a egg.(press 1)\n" +
					"2. Quit Program.(Press 2)\n");
			choice = in.nextInt();
			while(choice != 1 && choice !=2){
				System.out.println("Choice should be 1 or 2.");
				choice = in.nextInt();
			}
		}
		if(choice == 2){
			System.out.println("Quit Program!");
		}
	}
}
