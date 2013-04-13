package conway;

/**
 * The Model for Conway's Game of Life. Contains a board of cells which can either be default
 * constructed to 10x10 or defined to user preferences. Handles the movement of cells to the
 * next generation and whether they live or die in that next generation.
 * @author briandorne and ron cytron
 *
 */
public class Conway {

	private Cell[][] board; // two-dimensional array representation for each individual cell

	/**
	 * Default constructs an 10 by 10 board with all cells set to dead (false)
	 */
	public Conway() {
		this(10, 10);
	}

	/**
	 * Constructs a board with given length and height, with all cells set to dead (false)
	 * @param width - horizontal size of board in #cells
	 * @param height - vertical size of board in #cells
	 */
	public Conway(int width, int height) {
		board = new Cell[width][height];
		for (int x=0; x<width;x++){
			for (int y=0;y<height;y++)
				board[x][y]= new Cell(false);
		}
	}

	/**
	 * Finds the width of the Conway board.
	 * @return an int representation of the number of cells from left to right on the board
	 */
	public int getWidth() {
		return board.length;

	}

	/**
	 * Finds the height of the Conway board.
	 * @return an int representation of the number of cells from top to bottom on the board
	 */
	public int getHeight() {
		return board[0].length;
	}

	/**
	 * Returns a cell at the given (x, y) board location.
	 * @param x - the horizontal coordinate, with 0 being the leftmost column
	 * @param y - the vertical coordinate, with 0 being the topmost row
	 * @return a cell at the given board location
	 */
	public Cell getCell(int x, int y) {
		return board[x][y];
	}

	/**
	 * Checks to see if a given cell at location (x,y) is alive and returns whether it is or not living
	 * @param x - the horizontal coordinate, with 0 being the leftmost column
	 * @param y - the vertical coordinate, with 0 being the topmost row
	 * @return a boolean indicating whether the cell is alive or dead
	 */
	public boolean isAlive(int x, int y) {
		if (x<getWidth() && y<getHeight()&& x>=0 && y>=0)
			return board[x][y].isAlive();
		else
			return false;

	}

	/**
	 * Sets the current generation life status of a cell at the given (x, y) location on the board
	 * @param lifeStatus - a boolean representing whether a cell is alive (true) or dead (false)
	 * @param x - the horizontal coordinate, with 0 being the leftmost column
	 * @param y - the vertical coordinate, with 0 being the topmost row
	 */
	public void setLife(boolean lifeStatus, int x, int y) {
		if (x<getWidth() && y<getHeight()&& x>=0 && y>=0)
			board[x][y].setAlive(lifeStatus);
		else
			return;
	}

	/**
	 * Drives Conway's game based on these four rules:
	 * 
	 * 1. Any live cell with fewer than two live neighbors dies, because of under-population.
	 * 2. Any live cell with more than three live neighbors dies, because of overcrowding.
	 * 3. Any live cell with two or three live neighbors lives on to the next generation.
	 * 4. Any dead cell with exactly three live neighbors becomes a live cell.
	 * 
	 * Updates a cell's "nextGenAlive" parameter and then calls the method .evolve() on it
	 * in order to move to the next generation.
	 * 
	 * Hint: a private checkLivingNeighbors() method would help here
	 */
	private int checkLivingNeighbors(int x, int y){
		int N=0;
		if (isAlive(x-1,y))
			N=N+1;
		if (isAlive(x+1,y))
			N=N+1;
		if (isAlive(x,y-1))
			N=N+1;
		if (isAlive(x,y+1))
			N=N+1;
		if (isAlive(x-1,y-1))
			N=N+1;
		if (isAlive(x-1,y+1))
			N=N+1;
		if (isAlive(x+1,y-1))
			N=N+1;
		if (isAlive(x+1,y+1))
			N=N+1;
		return N;
	}

	public void step() {
		for (int x=0; x<getWidth();x++){
			for (int y=0;y<getHeight();y++){ 
				int n = checkLivingNeighbors(x, y);
				if (n<2 || n>3)
					board[x][y].setNextGenAlive(false);
				else if (n==2&&board[x][y].isAlive()==true)
					board[x][y].setNextGenAlive(true);
				else if (n==3)
					board[x][y].setNextGenAlive(true);
			}
		}
		for (int x=0; x<getWidth();x++){
			for (int y=0;y<getHeight();y++){ 
				board[x][y].evolve();
			}
		}
	}
	/**
	 * Visually display a text representation of each cell and whether it is alive or dead in the form
	 * of a two-dimensional grid containing a "Y" if the cell is living and a "N" if the cell is dead
	 * for each cell.
	 * @return a String as the text representation of the board
	 */
	public String toString() {
		String str="";
		for (int x=0; x<getHeight();x++){
			for (int y=0;y<getWidth();y++){ 
				str=str+board[x][y].toString();
			}
			str = str+"\n";
		}
		return str;
	}	
}