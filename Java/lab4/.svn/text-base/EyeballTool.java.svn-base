package lab4;
/**
 * Name: Ruiqi,Liu
 * Lab Section: A
 * Date:02/20/2012
 * EyeballTool.java
 * CSE 131 Lab 4
 */

import java.awt.event.MouseEvent;

import nip.*;
import lab4.*;
public class EyeballTool extends Tool {
	
	private Eyeball eball;
	private GraphicsPanel p;
	
	public String toString() {
		return "Eyeballs";
	}
	/**
	 * The constructor creates the shapes, sets their colors, and puts them on
	 * the NIP target panel.
	 */
	public EyeballTool(GraphicsPanel p) {
		this.p = p;
	}
		
  	/**
  	 * When the user presses the mouse button, the tool creates an eyeball
  	 * centered at the mouse location.
  	 */
  	// @Override
	public void mousePressed (MouseEvent e) {
		eball = new Eyeball(p,e.getX(),e.getY());
		eball.moveTo(e.getX(), e.getY());  
	}
	/**
	 * When the user drags the mouse, the tool creates a newly eyeball moving around with 
	 * the mouse so the user can position it.
	 */
	public void mouseDragged (MouseEvent e) {		
		eball.moveTo(e.getX(),e.getY());
	}

		
	/**
	 * We won't be using the menu to do anything for this lab
	 */
	public void actionNameCalled(String name) {		
		
	}
	
	/**
	 * No menu items needed
	 */
	public String[] getEventNames() {
		return new String[0];
	}
	
	/**
     * You can run this test program to see the shapes appear and respond to
     * mouse motion.
     */
	public static void main(String args[]) {
		NIP nip = new NIP();
		nip.setTool(new EyeballTool(nip.getTargetPanel()));
	}
}


