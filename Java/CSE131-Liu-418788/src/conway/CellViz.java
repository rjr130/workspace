package conway;

import java.awt.Color;

import nip.GraphicsPanel;
import nip.Rectangle;

/**
 * A View representation of a single cell in Conway's game of life. Has a current life status as well as a
 * knows the GraphicsPanel to which it is hooked to.
 * @author briandorne
 *
 */
@SuppressWarnings("serial")
public class CellViz extends Rectangle {

	private GraphicsPanel panel;
	private boolean alive;

	/**
	 * Default constructs a rectangle from the given Rectangle superclass and then visualizes the cell,
	 * indicating whether it is living (Black) or dead (White) on the board.
	 * @param panel - the given target panel from nip
	 * @param alive - the life status of the cell, whether it is alive (true, black) or dead (false, white)
	 * @param x - the x coordinate location of the rectangle's top-left corner
	 * @param y - the y coordinate location of the rectangle's top-left corner
	 * @param width - the width of the rectangle
	 * @param height - the height of the rectangle
	 */
	public CellViz(GraphicsPanel panel, boolean alive, int x, int y, int width, int height) {
		super(x, y, width, height, Color.WHITE, true); // always fill, start as dead cell
		
		this.panel = panel;
		this.alive = alive;
		
		// visual settings
		setLineColor(Color.GRAY);
		if (alive) {
			setFillColor(Color.BLACK);
		}
		else {
			setFillColor(Color.WHITE);
		}
		setFilled(true);
		this.panel.add(this);
	}
	
	/**
	 * Updates the visualization of CellViz, refreshing whether the cell is alive (BLACK) or dead (WHITE)
	 */
	public void updateGraphic() {
		if (alive)
			setFillColor(Color.BLACK);
		else
			setFillColor(Color.WHITE);
		panel.repaint();
	}
	
	/**
	 * A getter which returns whether the CellViz is alive or not
	 * @return a boolean indicating whether the cell is alive (true) or dead (false)
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Sets whether the CellViz should be alive or dead.
	 * @param lifeStatus - a boolean indicating whether the cell is alive (true) or dead (false)
	 */
	public void setAlive(boolean lifeStatus) {
		alive = lifeStatus;
	}
	
	/**
	 * Gives the BOARD/GRID location of the cell (useful for mousePressed)
	 */
	public String toString() {
		return "Cell at (" + getX()/(getWidth()-1) + ", " + getY()/(getHeight()-1) + ") ";
	}
	

}
