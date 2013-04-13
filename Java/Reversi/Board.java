/**
 * This class is designed for the board of the game.
 * 
 * @author Junrui Ruan(jruan@wisc.edu), Qianwen Lu (qianwen)
 *
 */
public class Board {
	/**
	 *	Rows of the game board 
	 */
	private int rows = 8;
	/**
	 *  Columns of the game board
	 */
	private int cols = 8;
	/**
	 *  The 2D array to save the state
	 */
	private Square[][] grid;
	
	/**
	 * Get the rows and the cols of the board
	 * 
	 * @param rows Number of rows on the board
	 * @param cols Number of columns on the board
	 */
	public Board(int rows, int cols){
		this.cols = cols;
		this.rows = rows;
		grid = new Square [rows][cols];	
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new Square();
				grid[i][j].setSquare(ReversiGUI.EMPTY);
			}
		}		
		grid[rows / 2 - 1][cols / 2 - 1].setSquare(ReversiGUI.BLACK);
		grid[rows / 2 - 1][cols / 2].setSquare(ReversiGUI.WHITE);
		grid[rows / 2][cols / 2].setSquare(ReversiGUI.BLACK);
		grid[rows / 2][cols / 2 - 1].setSquare(ReversiGUI.WHITE);
	}
	
	/**
	 * Assign the 2D array
	 * 
	 * @return The initial 2D array
	 */
	public int[][] toIntGrid() {
		int [][] grid = new int [rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = this.grid[i][j].getSquare();
			}
		}
		return grid;
	}
	
	public Square getGrid(int rows, int cols) {
		return grid[rows][cols];
	}
	
	
	
	
}
