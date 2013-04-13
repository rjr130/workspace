import java.util.*;
import java.io.*;

public class PhotoOrganizer {
    public static void main(String[] args) {

        // *** Add code for steps 1 - 3 of the main method ***
    	PhotoDatabase photoDB = new PhotoDatabase();
		Iterator<Photo> photoIter;

		//Check command-line argument
//		if (args.length == 0 || args.length >= 2){
//			System.out.println("Usage: java PhotoDB FileName");
//			System.exit(0);
//		}
		//Check process the argument
		try{
			File srcFile = new File(args[0]);
			Scanner fileIn = new Scanner(srcFile);
			while (fileIn.hasNext()){
				//Get and separate
				String[] tokens = fileIn.nextLine().split("[:]+");
				//Process photo
				photoDB.addPhoto(tokens[0]); //Add name
				//Process ingredients
				for (int i = 1; i < tokens.length; i++){
					photoDB.addPhoto(tokens[i]);
				}
			}
			fileIn.close();
		}catch(Exception e){
			System.out.println("Error: Cannot access input Photo");
			System.exit(0);
		}
        Scanner stdin = new Scanner(System.in);  // for reading console input
        printOptions();
        boolean done = false;
        while (!done) {
            System.out.print("Enter option ( dfhilqr ): ");
            String input = stdin.nextLine();
            input = input.toLowerCase();  // convert input to lower case

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) {
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                switch (choice) {
				
				case 'd':
                    // *** Add code to implement this option ***
					
                    break;


                case 'f':
                    // *** Add code to implement this option ***
                	
                	if(photoDB.containsPhoto(remainder)){ // Check if contains photo
						System.out.println(remainder+": "+ joinArray(photoDB.getPhotos(remainder),", ")); //Gets the ingredients and formats it
					}else{
						System.out.println("Photo not found");
					}
                    break;

                case 'h': 
                    printOptions();
                    break;

                case 'i':
                    // *** Add code to implement this option ***
                	//Initialize variables
					//Stats holders for ingredients/photo
					int mostPhotos = -1;
					int leastPhotos = -1;
					double sumOfPhotos = 0;
					//Temporary holders for photos/ingredient to collect unique ingredients
					ArrayList<String> ingredientNameList = new ArrayList<String>();
					ArrayList<String> mostIngredientList = new ArrayList<String>();
					ArrayList<Integer> photoCountList = new ArrayList<Integer>();
					
					
					photoIter = photoDB.iterator();
					while (photoIter.hasNext()){
						Photo cPhoto = photoIter.next();
						//Get photo stats
						int n = cPhoto.getSlideshows().size();
						if(n > mostPhotos || mostPhotos == -1){ //Filters out the mode
							mostPhotos = n;
						}

						if(n < leastPhotos || leastPhotos == -1){ //Filters out the lowest mode
							leastPhotos = n;
						}
						sumOfPhotos += n;

						//Collecting unique ingredients
						Iterator<String> ingredientIter = cPhoto.getSlideshows().iterator();
						while (ingredientIter.hasNext()){
							String ingredientName = ingredientIter.next();
							if(ingredientNameList.contains(ingredientName)){
								//Increment count of the ingredient
								photoCountList.set(ingredientNameList.indexOf(ingredientName), photoCountList.get(ingredientNameList.indexOf(ingredientName))+1);
							}else{
								//Add new ingredient
								ingredientNameList.add(ingredientName);
								photoCountList.add(1);
							}
						}
					}

					Iterator<String> photoNameIter = ingredientNameList.iterator();
					while (photoNameIter.hasNext()){
						//Get ingredient stats
						String photoName = photoNameIter.next();
						int n = photoCountList.get(ingredientNameList.indexOf(photoName));
						if(n > mostPhotos || mostPhotos == -1){ //Filters out the mode
							mostPhotos = n;
							mostIngredientList.clear();//Clear out list since there is a new winner
						}
						if(n < leastPhotos || leastPhotos == -1){ //Filters out the lowest mode
							leastPhotos = n;
						}
						if(mostPhotos == n){ //If they share first place, add them to the pile
							mostIngredientList.add(photoName);
						}
						sumOfPhotos+=n;
					}
					//Output data
					System.out.println("Photos: " + photoDB.size() + ", Photos: "+ingredientNameList.size());
					System.out.println("# of ingredients/photo: most "+mostPhotos+", least "+leastPhotos+", average "+Math.round(sumOfPhotos/photoDB.size()));
					System.out.println("# of photos/ingredient: most "+mostPhotos+", least "+leastPhotos+", average "+Math.round(sumOfPhotos/ingredientNameList.size()));
					System.out.println("Most Common: "+joinArray(mostIngredientList, ", ")+" ["+mostPhotos+"]");
					break;
                	
                case 'l':
                    // *** Add code to implement this option ***
                	done = true;
					System.out.println("quit");
                    break;

                case 'q':
                    done = true;
                    System.out.println("quit");
                    break;

                case 'r':
                    // *** Add code to implement this option ***
                	//Check if the last time
					if(photoDB.size() > 1){
						if(photoDB.removePhoto(remainder)){
							System.out.println("recipe removed");
						}else{
							System.out.println("recipe not found");
						}
					}else{
						System.out.println("there must be at least 1 recipe");
					}
                    break;

                default:  // ignore any unknown commands
                    break;
                }
            }
        }

    }

    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() {
		System.out.println("d <slideshow> - delete the given <slideshow>");
        System.out.println("f <photo> - find the given <photo>");
        System.out.println("h - display this help menu");
        System.out.println("i - display information about this photo database");
		System.out.println("l <slideshow> - lookup the given <slideshow>");
        System.out.println("q - quit");
        System.out.println("r <photo> - remove the given <photo>");
    }
    
	private static String joinArray(List<String> pieces,String glue){
		String resultString = "";
		if(pieces.size() != 0){
			Iterator<String> piecesIter = pieces.iterator();
			while (piecesIter.hasNext()){
				resultString += piecesIter.next()+glue;
			}
			return resultString.substring(0,resultString.length()-glue.length());
		}
		return "";
	}
}