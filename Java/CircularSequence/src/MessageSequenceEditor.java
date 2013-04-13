///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  
// File:             MessageSequenceEditor.java
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

public class MessageSequenceEditor {
    
	//A flag used to decide whether to input the command by user
	private static int choice = -1;
	
	//String which is used to hold new message buffer
	private static String messageBuffer = new String();
	
    // messages is a class data member; this allows all helper methods in this
    // class to be able to access it directly.
    private static CircularSequenceADT<String> messages = 
                                        new LinkedCircularSequence<String>();
    // NOTE: change the above line to:   new LinkedCircularSequence<String>();
    // after you have created and tested your LinkedCircularSequence class
    
    
    public static void main(String[] args) 
    		throws IOException, InterruptedException{
    	Scanner stdin = null;
    	
    	if (args.length > 1) {
    		System.out.println("Usage: java MessageSequenceEditor Filename");
    		System.exit(0);
    	}
    	else if (args.length == 1) {
	    	File file = new File(args[0]);
	    	if (!file.exists() || !file.canRead()) {
	    		System.out.println("Error: Cannot access input file");
	    		System.exit(0);
	    	}
	    	
			stdin = new Scanner(file); 
			choice = 0;
    	}
    	
    	else {
    		stdin = new Scanner(System.in);
    	}
    	
        //Scanner in = new Scanner(System.in);
        boolean again = true;

        while (again) {
            System.out.print("enter command (? for help): ");
            String input = stdin.nextLine();
            if (choice == 0) {
            	System.out.println(input);
            }

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) {
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                switch (choice) {
                case '?':
                    System.out.println("s (save)  l (load)      d (display)");
                    System.out.println("n (next)  p (previous)  j (jump)");
                    System.out.println("a (add after)    i (insert before)");
                    System.out.println("c (copy)  v (paste)     x (cut)");
                    System.out.println("f (find)  r (replace)   q (quit)");            
                    break;

                case 's':
                    if (remainder.length() > 0)
                        save(remainder);
                    else
                        System.out.println("invalid command");
                    break;

                case 'l':
                    if (remainder.length() > 0)
                        load(remainder);
                    else
                        System.out.println("invalid command");
                    break;

                case 'd': 
                    display();
                    break;

                case 'n':
                    //System.out.println("comand 'n' not implemented");
                	nextMessage();
                    break;

                case 'p':
                    //System.out.println("command 'p' not implemented");
                	previousMessage();
                    break;
                    
                case 'j':
                    //System.out.println("command 'j' not implemented");
                	int n = 0;
                	if (remainder.length() > 0) {
                		try{
							int jump = Integer.parseInt(remainder);//convert to
							//an integer
							jumpMessage(jump);
						}catch(NumberFormatException exception){//catches if
							//not an integer because it cannot be converted
							System.out.println("invalid command");
						}
                	}
                	else
                        System.out.println("invalid command");
                    break;        

                case 'a':
                    //System.out.println("command 'a' not implemented");
                	if (remainder.length() > 0)
                        addMessage(remainder);
                    else
                        System.out.println("invalid command");
                    break;
                    
                case 'i':
                	if (remainder.length() > 0)
                        insertMessage(remainder);
                    else
                        System.out.println("invalid command");
                    break;
                    
                case 'c':
                	copy();
                    break;
                    
                case 'v':
                   paste();
                    break;
                    
                case 'x':
                    cut();
                    break;
                    
                case 'f':
                	if (remainder.length() > 0)
                        find(remainder);
                    else
                        System.out.println("invalid command");
                    break;
                                        
                case 'r':
                	if (remainder.length() > 0)
                        replace(remainder);
                    else
                        System.out.println("invalid command");
                    break;
                    
                case 'q': 
                    System.out.println("quit");
                    again = false;
                    break;

                default:
                    System.out.println("invalid command");
                }
            }
        }
    }

    /**
     * If the message sequence is empty, display "no messages to save".  
     * Otherwise, save all the messages to a file named filename, one message
     * per line starting with the current message (and proceeding forward 
     * through the sequence).  If filename already exists, display "warning: 
     * file already exists, will be overwritten" before saving the messages.  
     * If filename cannot be written to, display "unable to save".
     *   
     * @param filename the name of the file to which to save the messages
     */
    private static void save(String filename) {
        if (messages.size() == 0) {
            System.out.println("no messages to save");
            return;
        }

        File file = new File(filename);
        
        // warn about overwriting existing file
        if (file.exists()) {
            System.out.print("warning: file already exists, ");
            System.out.println("will be overwritten");
        }
        
        // give message if not able to write to file
        if (file.exists() && !file.canWrite()) {
            System.out.println("unable to save");
            return;
        }
        
        // print each message to the file
        try {
            PrintStream outFile = new PrintStream(file);
            for (String msg : messages)
                outFile.println(msg);
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to save");
        }
    }

    /**
     * If a file named filename does not exists or cannot be read from, display
     * "unable to load".  Otherwise, load the messages from filename in the
     * order they are given before the current message.  The current message is
     * is not changed.
     * 
     * It is assumed that the input file contains one message per line, that
     * there are no blank lines, and that the file is not empty (i.e., it has
     * at least one line).
     * 
     * @param filename the name of the file from which to load the messages
     */
    private static void load(String filename) {
        File file = new File(filename);
        
        // check for existence and readability
        if (!file.exists() || !file.canRead()) {
            System.out.println("unable to load");
            return;
        }
        
        try {
            Scanner inFile = new Scanner(file);
            while (inFile.hasNext()) {
                // trim off any leading or trailing spaces before adding
                messages.insert(inFile.nextLine().trim());
                
                // since insert sets the current to the item just added,
                // move forward so the next item will get added after the one
                // that just got added
                messages.next();
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to load");
        }
    }

    /**
     * If the message sequence is empty, display "no message".  Otherwise, 
     * display all of the messages in the sequence, starting with the current 
     * message, one message per line (going forward through the entire 
     * sequence).
     */
    private static void display() {
        if (messages.size() == 0)
            System.out.println("no messages");
        
        else {
            for (String msg : messages) {
                System.out.println(msg);
            }
        }
    }
    
    /**
     * If the message sequence is empty, display "no messages". Otherwise, 
     * go forward to the next message in the sequence and display the current 
     * context (see note below).
     */
    private static void nextMessage() {
    	if (messages.size() == 0)
            System.out.println("no messages");
    	
    	else if (messages.size() == 2) {
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
    	
    	else {
    		System.out.println("    " + messages.getCurrent());
    		messages.next();
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
    }
    
    /**
     * If the message sequence is empty, display "no messages". Otherwise, 
     * go backwards to the previous message in the sequence and display the 
     * current context (see note below).
     */
    private static void previousMessage() {
    	if (messages.size() == 0)
            System.out.println("no messages");
    	
    	else if (messages.size() == 2) {
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
    	
    	else {
    		messages.previous();
    		messages.previous();
    		System.out.println("    " + messages.getCurrent());
    		messages.next();
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
    }
    
    /**
     * If the message sequence is empty, display "no messages". Otherwise, 
     * jump N messages in the sequence (forward if N > 0, backwards if N < 0) 
     * and display the current context (see note below).
     */
    private static void jumpMessage(int n) {
    	if (messages.size() == 0) {
            System.out.println("no messages");
            return;
    	}
    	if (n > 0) {
    		for (int i = 0; i < n; i++) {
    			messages.next();
    		}
    	}
    	
    	if (n < 0) {
    		n = 0 - n;
    		for (int i = 0; i < n; i++) {
    			messages.previous();
    		}
    	}
    	if (messages.size() == 2) {
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
		else {
			messages.previous();
    		System.out.println("    " + messages.getCurrent());
    		messages.next();
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		messages.next();
    		System.out.println("    " + messages.getCurrent());
    		messages.previous();
    	}
    }
    
    /**
     * If the message sequence is empty, add the new message to the sequence 
     * and make it the current message.  Otherwise, add the new message 
     * immediately after the current message and make the new message the new 
     * current message.  In either case, display the current context 
     * (see note below).
     */
    private static void addMessage(String msg) { 
    	if (messages.size() == 0) {
    		messages.insert(msg);
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		return;
    	}
    	
    	else {
    		String tmp = messages.getCurrent();
    		messages.insert(msg);
    		messages.insert(tmp);
    		messages.next();    		
    		messages.next();
    		messages.removeCurrent();
    		messages.previous();
    		if (messages.size() == 2) {
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.next();
        		System.out.println("    " + messages.getCurrent());
        		messages.previous();
        	}
    		else {
    			messages.previous();
        		System.out.println("    " + messages.getCurrent());
        		messages.next();
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.next();
        		System.out.println("    " + messages.getCurrent());
        		messages.previous();
        	}
    	}
    }
    
    /**
     * If the message sequence is empty, add the new message to the sequence 
     * and make it the current message.  Otherwise, insert the new message 
     * immediately before the current message and make the new message the new 
     * current message.  In either case, display the current context 
     * (see note below).
     */
    private static void insertMessage(String msg) {
    	if (messages.size() == 0) {
    		messages.insert(msg);
    		System.out.println(">>> " + messages.getCurrent() + " <<<");
    		return;
    	}
    	
    	else {
    		messages.insert(msg);
    		if (messages.size() == 2) {
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.next();
        		System.out.println("    " + messages.getCurrent());
        		messages.previous();
        	}
    		else {
    			messages.previous();
        		System.out.println("    " + messages.getCurrent());
        		messages.next();
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.next();
        		System.out.println("    " + messages.getCurrent());
        		messages.previous();
        	}
    	}
    }
    
    /**
     * If the message sequence is empty, display "no messages". Otherwise, copy
     * the current message to the message buffer (replacing any contents that 
     * are currently in the message buffer). The message sequence is not change
     */
    private static void copy() {
    	if (messages.size() == 0)
            System.out.println("no messages");
    	
    	else {
    		messageBuffer = messages.getCurrent();
    	}
    }
    
    /**
     * If there is nothing in the message buffer (because nothing has been cut 
     * or copied), display "nothing to paste". Otherwise, paste the contents 
     * message buffer by inserting it as a new message before the current 
     * message, make the new message the new current message, and display the 
     * current context (see note below).
     */
    private static void paste() {
    	if (messageBuffer.equals("")){ //check to see if the buffer is empty
			System.out.println("nothing to paste");
		}
		else {
			messages.insert(messageBuffer);
			if(messages.size() == 1){ //check to see if size is 1
				System.out.println(">>> " + messages.getCurrent() + " <<<");
			}
			else if(messages.size() == 2){ //check to see if size is 2
				System.out.println(">>> " + messages.getCurrent() + " <<<");
				messages.previous();
				System.out.println("    " + messages.getCurrent());
				messages.next();
			}
			else{
				messages.previous();
				System.out.println("    " + messages.getCurrent());
				messages.next();
				System.out.println(">>> " + messages.getCurrent() + " <<<");
				messages.next();
				System.out.println("    " + messages.getCurrent());
				messages.previous();
			}
		}
    }
    
    /**
     * If the message sequence is empty, display "no messages". Otherwise, cut 
     * the current message by copying it to the message buffer (replacing any 
     * contents currently in the message buffer) and removing it from the 
     * message sequence. If the message sequence becomes empty as a result of 
     * the removal, display "no messages". Otherwise, make the message after 
     * the removed message the current message and display the current context 
     * (see note below).
     */
    private static void cut() {
    	if (messages.size() == 0)
            System.out.println("no messages");
    	
    	else {
    		messageBuffer = messages.removeCurrent();
    		if (messages.size() == 0)
                System.out.println("no messages");
    		if (messages.size() == 1) {
    			System.out.println(">>> " + messages.getCurrent() + " <<<");
    		}
    		if (messages.size() == 2) {
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.previous();
        		System.out.println("    " + messages.getCurrent());
        		messages.next();
        	}
    		else {
    			messages.previous();
        		System.out.println("    " + messages.getCurrent());
        		messages.next();
        		System.out.println(">>> " + messages.getCurrent() + " <<<");
        		messages.next();
        		System.out.println("    " + messages.getCurrent());
        		messages.previous();
        	}
    	}
    }
    
    /**
     * If the message sequence is empty, display "no messages".  Otherwise, 
     * find (by searching forward in the message sequence starting with the 
     * message after the current message) the first message that contains the 
     * given string.  If no message containing string is found, display "not 
     * found"; otherwise, make that message the current message and display the
     * current context (see note below).
     */
    private static void find(String msg) {
    	//check whether the sequence is empty
    			if (messages.size() == 0){
    				System.out.println("no messages");
    			}
    			else {
    				int count = messages.size();
    				while(count>0){
    					messages.next();
    					//check whether the sequence is 1
    					if(messages.getCurrent().contains(msg)){
    						if (messages.size() == 1) {
    							System.out.println(">>> " + 
    									messages.getCurrent() + " <<<");
    							return;
    						}
    						//check whether the sequence is 2
    						if (messages.size() == 2) {
    							System.out.println(">>> " + 
    									messages.getCurrent() + " <<<");
    							messages.next();
    							System.out.println("    " + 
    									messages.getCurrent());
    							messages.previous();
    							return;
    						}
    						else{
    							messages.previous();
    							System.out.println("    " + 
    									messages.getCurrent());
    							messages.next();
    							System.out.println(">>> " + 
    									messages.getCurrent() + " <<<");
    							messages.next();
    							System.out.println("    " + 
    									messages.getCurrent());
    							messages.previous();
    							return;
    						}
    					}
    					count--;
    				}
    				System.out.println("not found");
    			}
    }
    
    /**
     * If the message sequence is empty, display "no messages".  Otherwise, 
     * replace the current message with the new message and display the current
     *  context (see note below).
     */
    private static void replace(String msg) {
    	if (messages.size() == 0)
            System.out.println("no messages");
    	
    	else {
    		if (messages.size() == 1) {
    			messages.insert(msg);
    			messages.next();
    			messages.removeCurrent();
    			System.out.println(">>> " + messages.getCurrent() + " <<<");
    		}
    		
    		else {
    			messages.insert(msg);
    			messages.next();
    			messages.removeCurrent();
    			messages.previous();
    			if (messages.size() == 2) {
	        		System.out.println(">>> " + 
	        				messages.getCurrent() + " <<<");
	        		messages.next();
	        		System.out.println("    " + 
	        				messages.getCurrent());
	        		messages.previous();
	        	}
	    		else {
	    			messages.previous();
	        		System.out.println("    " + 
	        				messages.getCurrent());
	        		messages.next();
	        		System.out.println(">>> " + 
	        				messages.getCurrent() + " <<<");
	        		messages.next();
	        		System.out.println("    " + 
	        				messages.getCurrent());
	        		messages.previous();
	        	}
    		}
    	}
    }
}
