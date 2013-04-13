/**A class for the windows' variables 
 * 
 * @author Junrui Ruan
 *
 */
public class Windows {
	// Windows' heights and width variables:
	private double winHeight = 0;
	private double winWidth = 0;
	
	// Getters for the window's height
	public double getWinHeight() {
		return this.winHeight;
	}
	
	// Getters for the window's width
	public double getWinWidth() {
		return this.winWidth;
	}
	
	// Set the value of window's height
	public void setWinHeight (double height) {
		this.winHeight = height;
	}
	
	// Set the value of window's width
	public void setWinWidth (double width) {
		this.winWidth = width;
	}
}
