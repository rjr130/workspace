package conway;

/**
 * A Model representation of a single cell in Conway's game of life. Has a current life status as well as a
 * life status for the next generation, which can both be set. Also has an evolve() method, which facilitates
 * the movement of the cell to next generation.
 * @author briandorne
 *
 */
public class Cell {
	
	private boolean alive;
	private boolean nextGenAlive;
	
	/**
	 * Constructs a living (true) or dead (false) cell based on what is specified.
	 * @param living - a boolean indicating whether the cell is alive (true) or dead (false)
	 */
	public Cell(boolean living) {
		alive = living;
		nextGenAlive = false;
	}
	
	/**
	 * A getter which returns whether the cell is alive or not.
	 * @return a boolean whether the cell is alive (true) or dead (false)
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * A setter which sets whether the cell is alive or not.
	 * @param alive - a boolean as to whether the cell is alive (true) or dead (false)
	 */
	public void setAlive(boolean alive) {
		this.alive=alive;
	}
	
	/**
	 * Sets the status of the cell in the next generation, specifically whether it will be living or dead
	 * @param alive - a boolean as to whether the cell will be alive (true) or dead (false)
	 */
	public void setNextGenAlive(boolean alive) {
		this.nextGenAlive=alive;
	}
	
	/**
	 * Moves the cell into the next generation, setting alive to whatever nextGenAlive has been set to,
	 * and setting nextGenAlive to false.
	 */
	public void evolve() {
		alive = nextGenAlive;
		nextGenAlive= false;
	}
	
	/**
	 * Tells us whether a current cell is alive or dead.
	 */
	public String toString() {
		if (alive == true)
		return "[X]";
		else
		return "[ ]";
	}
	
	
}
