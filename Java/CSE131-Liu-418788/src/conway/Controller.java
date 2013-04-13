package conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import nip.*;

/**
 * The Controller for the Model and View of Conway's Game of Life. Implements Tool from the nip package.
 * Detects user mouse presses and updates both the model and view according. Also contains the step method,
 * which respectively moves both the model and view into the next generation.
 * @author briandorne
 *
 */
public class Controller extends Tool {

	private static final int size = 20;
	private CellView view; // view
	private Conway conway; // model
	private Timer timer;

	/**
	 * Takes in the target panel from nip and initializes both the model (conway) and the view (view) for
	 * the cellular automation simulation. User can change the size of the board by changing the values
	 * in this constructor.
	 * @param panel - the given target panel in nip
	 */
	public Controller(GraphicsPanel panel) {
		conway = new Conway(size, size); // modify this line and the line below to change grid dimensions
		view = new CellView(panel, size, size);
		// ideal sizes for the board are 10x10, 20x20, and 50x50
	}

	/**
	 * Translates the coordinates received from a mouse event into the matrix coordinates for the
	 * identical cell on the model and view. Then, sets the life status of the cell to the opposite of what it
	 * previously was and updates the view accordingly using the updateViewAndModel method.
	 */
	public void mousePressed(MouseEvent e) {
		int x=e.getX()*size/view.getPanel().getWidth();
		int y=e.getY()*size/view.getPanel().getHeight();

		// updateViewAndModel(lifeStatus, x, y); // update view and model
	}

	/**
	 * Updates the view and model simultaneously.
	 * @param lifeStatus - whether the cell is alive (true) or dead (false)
	 * @param x - the horizontal coordinate, with 0 being the leftmost column
	 * @param y - the vertical coordinate, with 0 being the topmost row
	 */
	public void updateViewAndModel(boolean lifeStatus, int x, int y) {
		conway.setLife(lifeStatus, x, y);
		view.updateView(lifeStatus, x, y);
	}

	/**
	 * Runs a persistent version of the simulation. Creates a timer and starts it, which calls
	 * stepAndUpdateVisuals() for each tick that takes place. The timer will not be stopped until
	 * stopSimulation() is called by the user.
	 */
	public void startSimulation() {
		int delay = 100; //milliseconds, make smaller for faster ticks or larger for slower ticks
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				stepAndUpdateVisuals(); // run a single step each delay/1000.0 seconds
			}
		};
		timer = new Timer(delay, taskPerformer); // FIXME redundant
		timer.start();
		System.out.println("Simulation running at " + (delay / 1000.0) + 
				" seconds for each step. This will persist until stopped.");
	}

	/**
	 * Stops the persistent version of the simulation. Stops the timer that has been created.
	 */
	public void stopSimulation() {
		if (timer != null) timer.stop();
		System.out.println("Simulation has been stopped.");
	}

	/**
	 * Simulates a single step of the evolution of the cells to the next generation,
	 * and updates the view accordingly.
	 */
	public void stepAndUpdateVisuals() {
		conway.step();
		for (int x = 0; x < conway.getWidth(); x++) {
			for (int y = 0; y < conway.getHeight(); y++) {
				view.updateView(conway.isAlive(x, y), x, y);
			}
		}
	}

	/**
	 * Clears the living cells on the board.
	 */
	public void clear() {
		for (int x = 0; x < conway.getWidth(); x++) {
			for (int y = 0; y < conway.getHeight(); y++) {
				updateViewAndModel(false, x, y);
			}
		}
	}

	/**
	 * Extension: Extend the functionality of Conway to log and capture the currently living cells.
	 */
	public void logAndCapture() {
		// FIXME
	}

	/**
	 * Extension: Create your own design here!
	 */
	public void myDesignOne() {
		// FIXME
	}

	/**
	 * Extension: Create your own design here!
	 */
	public void myDesignTwo() {
		// FIXME
	}

	/**
	 * Extension: Create your own design here!
	 */
	public void myDesignThree() {
		// FIXME
	}

	/**
	 * Draws a basic blinker. Requires grid 3x3 and above.
	 */
	public void drawBlinker() {
		clear();
		if (conway.getWidth() < 3 || conway.getHeight() < 3) {
			System.out.println("Grid is too small for premade pattern Blinker. " +
					"Conway must be at least 3x3");
		}
		else {
			for (int i = 0; i < 3; i++) {
				updateViewAndModel(true, 1, i);
			}
		}
	}

	/**
	 * Begins as a 3x3 cell cluster, and ultimately forms 4 blinkers. Requires grid 9x9 and above.
	 */
	public void drawFourBlinkers() {
		clear();
		if (conway.getWidth() < 9 || conway.getHeight() < 9) {
			System.out.println("Grid is too small for premade pattern Four Blinkers. " +
					"Conway must be at least 9x9");
		}
		else {
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					updateViewAndModel(true, i, j);
				}
			}
		}
	}

	/**
	 * Draws a glider in top left corner. Requires grid 10x10 and above.
	 */
	public void drawGlider() {
		clear();
		if (conway.getWidth() < 10 || conway.getHeight() < 10) {
			System.out.println("Grid is too small for premade pattern Four Blinkers. " +
					"Conway must be at least 10x10");
		}
		else {
			updateViewAndModel(true, 1, 1);
			updateViewAndModel(true, 2, 2);
			updateViewAndModel(true, 2, 3);
			updateViewAndModel(true, 3, 2);
			updateViewAndModel(true, 3, 1);
		}
	}

	/**
	 * Draws a Gosper Glider Gun (a perpetual glider creator). Requires grid 50x50 and above.
	 */
	public void drawGosperGliderGun() {
		clear();
		if (conway.getWidth() < 50 || conway.getHeight() < 50) {
			System.out.println("Grid is too small for premade pattern Gosper Glider Gun. " +
					"Conway must be at least 50x50");
		}
		else {
			// first square
			updateViewAndModel(true, 3, 4);
			updateViewAndModel(true, 4, 4);
			updateViewAndModel(true, 3, 5);
			updateViewAndModel(true, 4, 5);

			// first ship
			updateViewAndModel(true, 13, 4);
			updateViewAndModel(true, 13, 5);
			updateViewAndModel(true, 13, 6);
			updateViewAndModel(true, 14, 3);
			updateViewAndModel(true, 14, 7);
			updateViewAndModel(true, 15, 2);
			updateViewAndModel(true, 16, 2);
			updateViewAndModel(true, 15, 8);
			updateViewAndModel(true, 16, 8);
			updateViewAndModel(true, 17, 5);
			updateViewAndModel(true, 18, 3);
			updateViewAndModel(true, 18, 7);
			updateViewAndModel(true, 19, 4);
			updateViewAndModel(true, 19, 5);
			updateViewAndModel(true, 19, 6);
			updateViewAndModel(true, 20, 5);

			// second ship
			updateViewAndModel(true, 23, 2);
			updateViewAndModel(true, 23, 3);
			updateViewAndModel(true, 23, 4);
			updateViewAndModel(true, 24, 2);
			updateViewAndModel(true, 24, 3);
			updateViewAndModel(true, 24, 4);
			updateViewAndModel(true, 25, 1);
			updateViewAndModel(true, 25, 5);
			updateViewAndModel(true, 27, 0);
			updateViewAndModel(true, 27, 1);
			updateViewAndModel(true, 27, 5);
			updateViewAndModel(true, 27, 6);

			// second square
			updateViewAndModel(true, 37, 2);
			updateViewAndModel(true, 37, 3);
			updateViewAndModel(true, 38, 2);
			updateViewAndModel(true, 38, 3);
		}
	}

	/**
	 * Name of the tool as it appears in the nip window.
	 */
	public String toString() {
		return "Controller";
	}

	/**
	 * Make sure you fill these in so the appropriate methods are called when an action is taken.
	 */
	public void actionNameCalled(String name) {
		if (name.equals("Start"))
			startSimulation();
		if (name.equals("Stop"))
			stopSimulation();
		if (name.equals("Step"))
			stepAndUpdateVisuals();
		if (name.equals("Clear"))
			clear();
		if (name.equals("Log and Capture"))
			logAndCapture();
		if (name.equals("One Blinker"))
			drawBlinker();
		if (name.equals("Four Blinkers"))
			drawFourBlinkers();
		if (name.equals("Glider"))
			drawGlider();
		if (name.equals("Gosper Glider Gun"))
			drawGosperGliderGun();
		if (name.equals("My Design One"))
			myDesignOne();
		if (name.equals("My Design Two"))
			myDesignTwo();
		if (name.equals("My Design Three"))
			myDesignThree();
	}

	/**
	 * Make sure you fill these in with the methods that you want to call when you run Main. 
	 */
	public String[] getEventNames() {
		String[] s = {"Start", "Stop", "Step", "Clear", "Log and Capture", 
				"One Blinker", "Four Blinkers", "Glider", "Gosper Glider Gun",
				"My Design One", "My Design Two", "My Design Three"};
		return s;
	}

}
