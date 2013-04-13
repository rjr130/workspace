package lab4;
/**
 * Name:Ruiqi,Liu	
 * Lab Section: A
 * Date:02/20/2012
 * Eyeball.java
 * CSE 131 Lab 4
 */


import java.awt.Color;

import lab4.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import nip.*;

public class Eyeball implements MouseMotionListener, ActionListener {
	/**
	 * the inner shape (I would like to do it this way)
	 */
	private Ellipse pupil;    
	private Ellipse outline;   //the outline around the shape
	
	
	
	public static final int PUPIL_SIZE = 25;        //Radius of the pupil
	public static final int OUTLINE_SIZE = 100;        //Radius of the outline
	public static final int PUPIL_DISTANCE = 37;   //maximum distance between the center of the pupil and the center of the eyeball
	
	/**
	 * The constructor creates the shapes, sets their colors, and puts them on
	 * the NIP target panel.
	 */
	public Eyeball (GraphicsPanel p, int x, int y) {
		//create a pupil at (50,75) that is 25 wide and 25 high 
		pupil = new Ellipse (50,75,25,25);
		pupil.setLineColor(Color.BLACK);     
		pupil.setFillColor(Color.BLACK);
		p.add(pupil);                   // put the pupil on the panel

		
		// Create a outline that is 100 wide and 100 high
		outline = new Ellipse(50,75,100,100); 
		outline.setLineColor(Color.BLACK);     
		outline.setFillColor(Color.WHITE);
		p.add(outline);  // put the outline on the panel 
		//
		// The line below is important. Without it, this object would not
	    //    listen to mouse events and respond in mouseMoved, below
	    //
		p.addMouseMotionListener(this);
	}
		

	/**
	 * Whenever the mouse moves inside the panel, move the shapes to
	 * the mouse location.
	 */
	// @Override
	public void mouseMoved(MouseEvent e) {
		lookAt(e.getX(), e.getY());
    }
	
	/**
	 * Sets the centers of the pupil and the outline to the given coordinates.
	 */
	public void moveTo(int x, int y) {
		pupil.setCenter(x, y);
		outline.setCenter(x, y);
	}	
	   	
	/**
	 * Make the pupil look toward the given point without letting it go outside the outline of the eyeball
	 */
    public void lookAt (int x1, int y1) {
    	  Vector v1 = new Vector (-(outline.getCenterX() - x1), -(outline.getCenterY() - y1));
  		  v1 = v1.rescale(PUPIL_DISTANCE);
  		  Vector v2 = v1;
  		  if (v2.magnitude() > 25) {
  			  int finalX = (int) (v2.getDeltaX()+outline.getCenterX());
  			  int finalY = (int) (v2.getDeltaY()+outline.getCenterY());
  			  pupil.setCenter(finalX,finalY);
  		  }
    }
	
  	// You will modify the following method only if you
	// complete the animation Extension of this project.
	public void actionPerformed(ActionEvent arg0) {
		 
	}

	//  Do nothing here
	public void mouseDragged (MouseEvent e) {
		 
	}

}
