import java.awt.Color;

public class Eyeball implements MouseMotionListener, ActionListener {

	public Eyeball(GraphicsPanel p) {
		
	}
	/**
	 * React to the mouse moving
	 */
	public void mouseMoved(MouseEvent e) {
	}
	
	// You will modify the following method only if you
	// complete the animation Extension of this project.
	public void actionPerformed(ActionEvent arg0) {
	}

	//  Do nothing here
	public void mouseDragged(MouseEvent e) {
	}
	
	/**
	 * 
	 * @param 
	 * 
	 */
	public void lookAt (int x0, int y0) {
		Vector v1 = new Vector (x0 - 50, y0 - 75);
		v1.rescale(PUPIL_DISTANCE);
		
	}

}
