///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             DuplicateKeyException.java
// Semester:         Spring 2012
//
// Author:           Chew Wei Lai clai9@wisc.edu
// CS Login:         clai
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Junrui Ruan jruan@wisc.edu
// CS Login:         junrui
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;

public class ConcordanceGenerator {
	/**
	 * The main method generates a concordance as described in the program 
	 * write-up.  You will need to add to the code given here.
	 * 
	 * @param args the command-line arguments that determine how input and 
	 * output is done:
	 * <ul>
	 *   <li>if there is one command-line argument, then it is treated as the
	 *   name of the file from which to get input and output is sent to the
	 *   console</li>
	 *   <li>if there are two command-line arguments, then the first is the name
	 *   of the file from which to get the input and the second is the name of 
	 *   the file to which to sent the output</li>
	 * </ul>
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(""); // for input //Dummy scanner
		PrintStream out = null;    // for output, defaults to System.out
		BasicMapADT<String, Entry> concord = new BSTBasicMap<String, Entry>();  
		// the concordance

		//Check for length
		if(args.length < 1){
			System.out.println
			("Usage: java ConcordanceGenerator <FileInput> <FileOutput>");
			System.exit(0);
		}

		// Set up where to send input and output
		//Import file into
		try{
			in = new Scanner(new File(args[0]));
		}catch(Exception e){
			System.err.println("Problem with file: "+args[0]);
			System.exit(0);
		}
		switch (args.length) {
		case 1:
			out = System.out; //Set output to console
			break;
			
		case 3: //Extra credit
			Scanner inList = new Scanner(""); // for input //Dummy scanner
			PrintStream listOut = null;   // for output, defaults to System.out
			BasicMapADT<String, Entry> concordLinked = 
											new BSTBasicMap<String, Entry>();  
			//Import file into 2nd Input stream
			try{
				inList = new Scanner(new File(args[0]));
			}catch(Exception e){
				System.err.println("Problem with file: "+args[0]);
				System.exit(0);
			}
			//Set output stream
			try {
				listOut = new java.io.PrintStream(args[2]);//Set output to file
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			int i = 1;
			while (inList.hasNext()){
				String line = inList.nextLine();
				line = line.toLowerCase();
				List<String> wordList = parseLine(line);
				for(String word : wordList){
					Entry concordanceEntry = concordLinked.get(word);
					if(concordanceEntry == null){
						//Does not exist
						Entry tempEntry = new Entry(word); // Create new entry
						tempEntry.getLines().add(i);
						try{
							concordLinked.put(word, tempEntry);
						}catch(DuplicateException e){
							System.exit(1); //Ouch, this is not suppose 
											//to happen
						}
					}else{
						//Existed just change it
						concordanceEntry.getLines().add(i);
					}
				}
				i++;
			}
			// Print out the concordance
			Iterator<Entry> iter = concord.values().iterator();
	        while (iter.hasNext())
	            out.println(iter.next().toString());

	        // Print out the statistics
	        int keys = concord.size();
	        int pathLength = concord.totalPathLength();
	        out.print("# keys: " + keys + "  total path length: " + pathLength);
	        double avg = pathLength;
	        out.println("  avg path length: " + ((keys  == 0)? 0 :avg/keys));
	        
	        
	        
		case 2: 
			try {
				out = new java.io.PrintStream(args[1]); //Set output to file
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.err.println("Invalid command-line arguments");
			System.exit(0);
		}
		//Begin adding to concordance
		int i = 1;
		while (in.hasNext()){
			String line = in.nextLine();
			line = line.toLowerCase();
			List<String> wordList = parseLine(line);
			for(String word : wordList){
				Entry concordanceEntry = concord.get(word);
				if(concordanceEntry == null){
					//Does not exist
					Entry tempEntry = new Entry(word); // Create new entry
					tempEntry.getLines().add(i);
					try{
						concord.put(word, tempEntry);
					}catch(DuplicateException e){
						System.exit(1); //Ouch, this is not suppose to happen
					}
				}else{
					//Existed just change it
					concordanceEntry.getLines().add(i);
				}
			}
			i++;
		}
		// Print out the concordance
        Iterator<Entry> iter = concord.values().iterator();
        while (iter.hasNext())
            out.println(iter.next().toString());

      // Print out the statistics
      int keys = concord.size();
      int pathLength = concord.totalPathLength();
      out.print("# keys: " + keys + "  total path length: " + pathLength);
      double avg = pathLength;
      out.println("  avg path length: " + ((keys  == 0)? 0 :avg/keys));
	} 

	/**
	 * Parses the given line into an array of words.
	 * DO NOT CHANGE THIS METHOD.
	 */
	private static List<String> parseLine(String line) {
		String[] tokens = line.split("[ ]+");
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++) {  // for each word

			// find index of first digit/letter
			boolean done = false; 
			int first = 0;
			String word = tokens[i];
			while (first < word.length() && !done) {
				if (Character.isDigit(word.charAt(first)) ||
						Character.isLetter(word.charAt(first)))
					done = true;
				else first++;
			}

			// find index of last digit/letter
			int last = word.length()-1;
			done = false;
			while (last > first && !done) {
				if (Character.isDigit(word.charAt(last)) ||
						Character.isLetter(word.charAt(last)))
					done = true;
				else last--;
			}

			// trim from beginning and end of string so that is starts and
			// ends with a letter or digit
			word = word.substring(first, last+1);

			// make sure there is at least one letter in the word
			done = false;
			first = 0;
			while (first < word.length() && !done)
				if (Character.isLetter(word.charAt(first)))
					done = true;
				else first++;           
			if (done)
				words.add(word);
		}

		return words;
	}
}