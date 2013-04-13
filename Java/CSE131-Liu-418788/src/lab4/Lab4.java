package lab4;
/**
 * Name:
 * Lab Section: 
 * Date:
 * Lab4.java
 * CSE 131 Lab 4
 */

import nip.NIP;

public class Lab4 {
	/**
	 * The main method of the project.
	 */
	public static void main(String[] args) {
		NIP nip = new NIP(300, 300, 2, "", "ken.jpg");
		nip.setTool(new EyeballTool(nip.getTargetPanel()));
		nip.addTool(new CloningTool());
	}
}

