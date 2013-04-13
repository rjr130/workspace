package conway;

import nip.*;

/**
 * Where the Conway's Game of Life simulation is ran. Has a main method that builds a nip and sets the tool to
 * the controller.
 * @author briandorne
 *
 */
public class Main {
	
	public static void main(String args[]) {
		NIP nip = new NIP(500, 500, 1);
		nip.setTool(new Controller(nip.getTargetPanel()));
	}
}
