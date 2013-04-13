/**A class for the Frames' variables
 * 
 * @author Junrui Ruan
 *
 */
public class Frames {
	// Frames' height and width variables:
	private double frameHeight = 0;
	private double frameWidth = 0;
		
	// The upper-left position for the frame
	private double frameX = 0;
	private double frameY = 0;
	
	// Getter for frameHeight
	public double getFrameHeight() {
		return this.frameHeight;
	}
	
	// Getter for frameWidth
	public double getFrameWidth() {
		return this.frameWidth;
	}
	
	// Getter for frame upper-left position's width value
	public double getFrameX() {
		return this.frameX;
	}
	
	// Getter for frame upper-left position's height value
	public double getFrameY() {
		return this.frameY;
	}
	
	// Set the value of frameHeight
	public void setFrameHeight(double height) {
		this.frameHeight = height;
	}
	
	// Set the value of frameWidth
	public void setFrameWidth(double width) {
		this.frameWidth = width;
	}
	
	// Set the value of upper-left position's width value
	public void setFrameX(double width) {
		this.frameX = width;
	}
	
	// Set the value of upper-left osition's height value
	public void setFrameY(double height) {
		this.frameY = height;
	}
}
