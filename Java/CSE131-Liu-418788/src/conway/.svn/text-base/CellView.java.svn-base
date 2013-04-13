package conway;

import nip.GraphicsPanel;

/**
 * The view of the cellular automation simulation. Visualizes and updates the changes that take place in the
 * model, as told by the controller.
 * @author briandorne
 *
 */
public class CellView {
	private GraphicsPanel panel;
	private CellViz[][] grid;
	
	/**
	 * Constructs empty "dead" cells on the panel in the form of rectangles.
	 * @param panel - the graphics panel from nip
	 * @param width - an int representing the number of cells to create for the width
	 * @param height - an int representing the number of cells to create for the height
	 */
	public CellView(GraphicsPanel panel, int width, int height) {
		this.panel = panel;
		grid = new CellViz[width][height];
		drawDeadCells(width, height);
	}
	
	/**
	 * A private method which actually draws the rectangles on the graphics panel, and also adds them
	 * to the grid to keep track of their states. Announces when finished.
	 * @param numCellsWide - the number of cells for the width
	 * @param numCellsTall - the number of cells for the height
	 */
	private void drawDeadCells(int numCellsWide, int numCellsTall) {
		int pixelsWide = panel.getWidth();
		int pixelsTall = panel.getHeight();
		int widthCell = pixelsWide/numCellsWide; // the width per cell based on the num cells in the row
		int heightCell = pixelsTall/numCellsTall; // the height per cell based on the num cells in the column
		for (int i = 0; i < pixelsWide; i += widthCell) {
			for (int j = 0; j < pixelsTall; j += heightCell) {
				grid[i/widthCell][j/heightCell] = new CellViz(panel, false, i, j, widthCell, heightCell);
			}
		}
		System.out.println("CellView constructed with... Width: " + pixelsWide + " Height: "
				+ pixelsTall + " widthCell: " + widthCell + " heightCell: " + heightCell);
	}
	
	/**
	 * A getter for the graphics panel given to the cell view.
	 * @return the target panel from nip in which the simulation is taking place
	 */
	public GraphicsPanel getPanel() {
		return panel;
	}

	/**
	 * Updates a specific CellViz based on a given life status and the coordinates where that cell is located.
	 * Then calls the updateGraphic() method for CellViz to reflect the changes on the board.
	 * @param lifeStatus - whether the CellViz should be displayed as alive (true, black) or dead (false, white)
	 * @param x - the x coordinate in grid[x][y]
	 * @param y - the y coordinate in grid[x][y]
	 */
	public void updateView(boolean lifeStatus, int x, int y) {
		grid[x][y].setAlive(lifeStatus);
		grid[x][y].updateGraphic();
	}
	
	/**
	 * Gives a text representation of the matrix location of a cell. Called from the mousePressed event listener.
	 * @param x - the x coordinate in grid[x][y]
	 * @param y - the y coordinate in grid[x][y]
	 * @return a String representing the (x,y)  coordinate location of a cell on the grid/board
	 */
	public String getCellMatrixLocation(int x, int y) {
		return grid[x][y].toString();
	}
	
}
