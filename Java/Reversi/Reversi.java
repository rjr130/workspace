import java.io.*;
import java.util.*;

/**
 * This class represents the Reversi game.
 * 
 * @author Junrui Ruan (jruan@wisc.edu)  Qianwen Lu (qianwen)
 */

public class Reversi {
	/**
	 * The Rows of the board. You can just set it before compile.
	 */
	public static final int ROWS = 8;
	/**
	 * The Columns of the board. You can just set it before compile.
	 */
	public static final int COLS = 8;
	/**
	 * The counter of the steps left
	 */
	private int count = this.ROWS * this.COLS - 4;
	/**
	 * The 2D array of location that next step you can put disk on.
	 */
	private boolean[][] okForNext = new boolean[ROWS][COLS];
	/**
	 * The board for the Game.
	 */
	private Board board = new Board(Reversi.ROWS, Reversi.COLS);
	/**
	 * The Object of GUI, call ReversiGUI method.
	 */
	ReversiGUI gui = new ReversiGUI("Riversi", Reversi.ROWS, Reversi.COLS);

	/**
	 * Creates a new <tt>Reversi</tt> object with the specified properties
	 * ReversiGUI.BLACK = -3 ReversiGUI.WHITE = -2 ReversiGUI.EMPTY = -1 (Just a
	 * reminder)
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	public Reversi() {
		gui.update(board.toIntGrid(), false, ReversiGUI.WHITE, false);

		// The loop of the Game. It is very IMPORTANT!!!
		ReversiAction action = gui.getMouseInput();
		while (action.ACTION_TYPE != ReversiAction.QUIT) { // If the game does
															// not end
			if (action.ACTION_TYPE == ReversiAction.NEW_GAME
					|| action.ACTION_TYPE == ReversiAction.PLAYER_CHANGED) {
				this.reset();
				gui.update(board.toIntGrid(), false, ReversiGUI.EMPTY, false);
			}

			if (action.ACTION_TYPE == ReversiAction.SAVE_GAME) { // If choose to
																	// save
				if (gui.getSaveFile() != null) {
					try {
						PrintWriter out = new PrintWriter(gui.getSaveFile());
						out.println(ROWS + " " + COLS + " " + checkTurn()); // print
																			// the
																			// first
																			// line
						for (int i = 0; i < ROWS; i++) { // print the board
							for (int j = 0; j < COLS; j++) {
								if (board.getGrid(i, j).getSquare() == ReversiGUI.EMPTY) {
									out.print("0");
								} else if (board.getGrid(i, j).getSquare() == ReversiGUI.BLACK) {
									out.print("B");
								} else {
									out.print("W");
								}
							}
							out.println();
						}
						out.close();
					} catch (FileNotFoundException exception) {
						System.out.println("File has not been saved");
					}
				}
			}

			if (action.ACTION_TYPE == ReversiAction.LOAD_GAME) { // If choose to
																	// load game
				if (gui.getLoadFile() != null) {
					try {
						Scanner in = new Scanner(gui.getLoadFile());
						if (in.nextLine().charAt(4) == "black".charAt(0)) { // To
																			// determine
																			// the
																			// color
																			// of
																			// current
																			// player
							gui.setCurrentPlayer(ReversiGUI.BLACK);
						} else {
							gui.setCurrentPlayer(ReversiGUI.WHITE);
						}

						for (int i = 0; i < ROWS; i++) { // Make the board using
															// saved file
							String line = in.nextLine();
							for (int j = 0; j < COLS; j++) {
								if (line.charAt(j) == "WHITE".charAt(0)) {
									board.getGrid(i, j).setSquare(
											ReversiGUI.WHITE);
								} else if (line.charAt(j) == "BLACK".charAt(0)) {
									board.getGrid(i, j).setSquare(
											ReversiGUI.BLACK);
								} else {
									board.getGrid(i, j).setSquare(
											ReversiGUI.EMPTY);
								}
							}
						}

						gui.update(board.toIntGrid(), false, ReversiGUI.EMPTY,
								false);
						in.close();
					}

					catch (Exception v) { // deal with input file errors
						gui.displayError("Error Loading File"
								+ gui.getLoadFile()
								+ "Are you sure it is a valid file?");
					}

				}
			}

			if (action.ACTION_TYPE == ReversiAction.STEP) {
				if(!(gui.getCurrentPlayer() instanceof HumanPlayer))
				 {
					int[] ab=gui.getCurrentPlayer().calcMove(  //assign an array to the calcmove since it change every time
							board.toIntGrid());
					int nonhumancolor;
					if (gui.getCurrentPlayer().getColor()=="black"){
						nonhumancolor=-3;
					}
					else nonhumancolor=-2;
					if (gui.getCurrentPlayer() instanceof RandomPlayer) {
						board.getGrid(ab[0],ab[1]).setSquare(nonhumancolor);

					} else if (gui.getCurrentPlayer() instanceof CompPlayer) {

						board.getGrid(ab[0],ab[1]).setSquare(nonhumancolor);
					}
					if (updateGrid(nonhumancolor, ab[0], ab[1])) { // Whether
																					// there
																					// is
																					// disk
																					// flip
						gui.update(board.toIntGrid(), false, ReversiGUI.EMPTY,
								true);
						count--;
					}

					if (this.checkOver() == 1) { // Whether the game is over
						gui.update(board.toIntGrid(), true, ReversiGUI.WHITE,
								false);
						break;
					} else if (this.checkOver() == -1) {
						gui.update(board.toIntGrid(), true, ReversiGUI.BLACK,
								false);
						break;
					}
				}
			}

			if (action.ACTION_TYPE != ReversiAction.LEFT_CLICK) {
				System.out.println(action.ROW + "," + action.COLUMN);
			}
			if (action.ACTION_TYPE == ReversiAction.LEFT_CLICK) {

				System.out
						.println("(" + action.ROW + "," + action.COLUMN + ")");
				if (board.getGrid(action.ROW, action.COLUMN).getSquare() == ReversiGUI.EMPTY) {
					if (count % 2 == 0) { // Enter to the BLACK player's time
						this.okForNextStep(ReversiGUI.BLACK);
						if (!this.checkNext()) {														
							int white = 0;
							int black = 0;
							for (int i = 0; i < ROWS; i++) {
								for (int j = 0; j < COLS; j++) {
									if (board.getGrid(i, j).getSquare() == ReversiGUI.WHITE) {
										white++;
									}
									else if (board.getGrid(i, j).getSquare() == ReversiGUI.BLACK) {
										black++;
									}
								}
							}
							
							if (black + white == ROWS * COLS || black == 0 || white == 0) {
								if (white > black || black == 0) {
									gui.update(board.toIntGrid(), true, ReversiGUI.WHITE, false);
								}
								else if (white < black || white == 0) {
									gui.update(board.toIntGrid(), true, ReversiGUI.BLACK, false);
								}
								else {
									gui.update(board.toIntGrid(), true, ReversiGUI.EMPTY, false);
								}
							}
							continue;
						}
						System.out
								.println(okForNext[action.ROW][action.COLUMN]);
						if (okForNext[action.ROW][action.COLUMN]) { // OK for
																	// placing a
																	// disk

							board.getGrid(action.ROW, action.COLUMN).setSquare(
									ReversiGUI.BLACK);
						}

						if (updateGrid(ReversiGUI.BLACK, action.ROW,
								action.COLUMN)) { // Whether there is disk
													// flip
							gui.update(board.toIntGrid(), false,
									ReversiGUI.EMPTY, true);
							count--;
						}

						if (this.checkOver() == 1) { // Whether the game is over
							gui.update(board.toIntGrid(), true,
									ReversiGUI.WHITE, false);
							break;
						} else if (this.checkOver() == -1) {
							gui.update(board.toIntGrid(), true,
									ReversiGUI.BLACK, false);
							break;
						}
					} else { // Enter to the WHITE player's time
						this.okForNextStep(ReversiGUI.WHITE);
						if (!this.checkNext()) {														
							int white = 0;
							int black = 0;
							for (int i = 0; i < ROWS; i++) {
								for (int j = 0; j < COLS; j++) {
									if (board.getGrid(i, j).getSquare() == ReversiGUI.WHITE) {
										white++;
									}
									else if (board.getGrid(i, j).getSquare() == ReversiGUI.BLACK) {
										black++;
									}
								}
							}
							
							if (black + white == ROWS * COLS || black == 0 || white == 0) {
								if (white > black || black == 0) {
									gui.update(board.toIntGrid(), true, ReversiGUI.WHITE, false);
								}
								else if (white < black || white == 0) {
									gui.update(board.toIntGrid(), true, ReversiGUI.BLACK, false);
								}
								else {
									gui.update(board.toIntGrid(), true, ReversiGUI.EMPTY, false);
								}
							}
							continue;
						}
						System.out
								.println(okForNext[action.ROW][action.COLUMN]);
						if (okForNext[action.ROW][action.COLUMN]) {
							board.getGrid(action.ROW, action.COLUMN).setSquare(
									ReversiGUI.WHITE);
							if (updateGrid(ReversiGUI.WHITE, action.ROW,
									action.COLUMN)) {
								gui.update(board.toIntGrid(), false,
										ReversiGUI.EMPTY, true);
								count--;
							}
						}
						if (this.checkOver() == 1) {
							gui.update(board.toIntGrid(), true,
									ReversiGUI.WHITE, false);
						} else if (this.checkOver() == -1) {
							gui.update(board.toIntGrid(), true,
									ReversiGUI.BLACK, false);
						}
					}
				}
			}
			if (gui.isAutoPlaying()) { 
				while (!(gui.getCurrentPlayer() instanceof HumanPlayer)) {
					int nonhumancolor;   //convert the string color to int
					if (gui.getCurrentPlayer().getColor() == "black") {
						nonhumancolor = -3;
					}
					else nonhumancolor = -2;
					this.okForNextStep(nonhumancolor);
					if (this.checkNext()) {
						int[] ab=gui.getCurrentPlayer().calcMove(board.toIntGrid());
						if (gui.getCurrentPlayer() instanceof RandomPlayer) {
							board.getGrid(ab[0],ab[1]).setSquare(nonhumancolor);
					} 
					else if (gui.getCurrentPlayer() instanceof CompPlayer) {
						board.getGrid(ab[0],ab[1]).setSquare(nonhumancolor);
					}
					if (updateGrid(nonhumancolor, ab[0], ab[1])) {                  // Whether
																					// there
																					// is
																					// disk
																					// flip
						gui.update(board.toIntGrid(), false, ReversiGUI.EMPTY, true);
						count--;
						}
					}
					if (this.checkOver() == 1) { // Whether the game is over
						gui.update(board.toIntGrid(), true, ReversiGUI.WHITE, false);
						break;
					} 
					else if (this.checkOver() == -1) {
						gui.update(board.toIntGrid(), true, ReversiGUI.BLACK, false);
						break;
					}
					if (!this.checkNext()) {														
						int white = 0;
						int black = 0;
						for (int i = 0; i < ROWS; i++) {
							for (int j = 0; j < COLS; j++) {
								if (board.getGrid(i, j).getSquare() == ReversiGUI.WHITE) {
									white++;
								}
								else if (board.getGrid(i, j).getSquare() == ReversiGUI.BLACK) {
									black++;
								}
							}
						}
						
						if (black + white == ROWS * COLS || black == 0 || white == 0) {
							if (white > black || black == 0) {
								gui.update(board.toIntGrid(), true, ReversiGUI.WHITE, false);
							}
							else if (white < black || white == 0) {
								gui.update(board.toIntGrid(), true, ReversiGUI.BLACK, false);
							}
							else {
								gui.update(board.toIntGrid(), true, ReversiGUI.BLACK, false);
							}
						}
						continue;
					}
				}
			}
			action = gui.getMouseInput();
		}
		gui.quit();
	}

	/**
	 * Update the location where you can put the next disk in the next step.
	 * 
	 * @param color
	 */
	public void okForNextStep(int color) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				okForNext[i][j] = false;
				if (color == ReversiGUI.WHITE) {
					if (check(i, j, ReversiGUI.WHITE) > 0) {
						okForNext[i][j] = true;
					}
				}  
				else if (color == ReversiGUI.BLACK) {
					if (check(i, j, ReversiGUI.BLACK) > 0) {
						okForNext[i][j] = true;
					}
				}
			}
		}
	}

	public boolean checkNext() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (okForNext[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check whose turn is it.
	 */
	public String checkTurn () {
		if (count % 2 == 0) {
			return "black";

		} 
		else {
			return "white";
		}
	}

	/**
	 * Check whether the current location can put a disk whose color is color.
	 * 
	 * @param i
	 *            used as counter of rows
	 * @param j
	 *            used as counter of cols
	 * @param color
	 *            the color of the current disk
	 * @param flag
	 *            used to return the value
	 * @return
	 */
	public int check (int i, int j, int color) {

		int flag = 0;
		if (board.getGrid(i, j).getSquare() != ReversiGUI.EMPTY) {
			return 0;
		}
		if (j != 0) {
			if (color != board.getGrid(i, j - 1).getSquare()
					&& -1 != board.getGrid(i, j - 1).getSquare()) {
				int m = j - 1;
				while (board.getGrid(i, j - 1).getSquare() == board.getGrid(i,
						m).getSquare()
						&& m > 0) {
					m--;
				}
				if (board.getGrid(i, m).getSquare() == color) {
					flag = flag + (j - 1 - m);

				}
			}
		}
		if (j != COLS - 1) {
			if (color != board.getGrid(i, j + 1).getSquare()
					&& -1 != board.getGrid(i, j + 1).getSquare()) {
				int m = j + 1;
				while (board.getGrid(i, j + 1).getSquare() == board.getGrid(i,
						m).getSquare()
						&& m < COLS - 1) {
					m++;
				}
				if (board.getGrid(i, m).getSquare() == color) {
					flag = flag + (m - j - 1);
				}
			}
		}
		if (i != 0) {
			if (color != board.getGrid(i - 1, j).getSquare()
					&& -1 != board.getGrid(i - 1, j).getSquare()) {
				int m = i - 1;
				while (board.getGrid(i - 1, j).getSquare() == board.getGrid(m,
						j).getSquare()
						&& m > 0) {
					m--;
				}
				if (board.getGrid(m, j).getSquare() == color) {
					flag = flag + (i - 1 - m);
				}
			}
		}
		if (i != ROWS - 1) {
			if (color != board.getGrid(i + 1, j).getSquare()
					&& -1 != board.getGrid(i + 1, j).getSquare()) {
				int m = i + 1;
				while (board.getGrid(i + 1, j).getSquare() == board.getGrid(m,
						j).getSquare()
						&& m < ROWS - 1) {
					m++;
				}
				if (board.getGrid(m, j).getSquare() == color) {
					flag = flag + (m - i - 1);
				}
			}
		}
		if (j != 0 && i != 0) {
			if (color != board.getGrid(i - 1, j - 1).getSquare()
					&& -1 != board.getGrid(i - 1, j - 1).getSquare()) {
				int m = i - 1;
				int n = j - 1;
				while (board.getGrid(i - 1, j - 1).getSquare() == board
						.getGrid(m, n).getSquare() && m > 0 && n > 0) {
					m--;
					n--;
				}
				if (board.getGrid(m, n).getSquare() == color) {
					flag = flag + (i - 1 - m);
				}
			}
		}
		if (j != COLS - 1 && i != ROWS - 1) {
			if (color != board.getGrid(i + 1, j + 1).getSquare()
					&& -1 != board.getGrid(i + 1, j + 1).getSquare()) {
				int m = i + 1;
				int n = j + 1;
				while (board.getGrid(i + 1, j + 1).getSquare() == board
						.getGrid(m, n).getSquare() && m < ROWS - 1 && n < COLS - 1) {
					m++;
					n++;
				}
				if (board.getGrid(m, n).getSquare() == color) {
					flag = flag + (m - i - 1);
				}
			}
		}
		if (j != COLS - 1 && i != 0) {
			if (color != board.getGrid(i - 1, j + 1).getSquare()
					&& -1 != board.getGrid(i - 1, j + 1).getSquare()) {
				int m = i - 1;
				int n = j + 1;
				while (board.getGrid(i - 1, j + 1).getSquare() == board
						.getGrid(m, n).getSquare() && m > 0 && n < COLS - 1) {
					m--;
					n++;
				}
				if (board.getGrid(m, n).getSquare() == color) {
					flag = flag + (i - 1 - m);

				}
			}
		}
		if (j != 0 && i != ROWS - 1) {
			if (color != board.getGrid(i + 1, j - 1).getSquare()
					&& -1 != board.getGrid(i + 1, j - 1).getSquare()) {
				int m = i + 1;
				int n = j - 1;
				while (board.getGrid(i + 1, j - 1).getSquare() == board
						.getGrid(m, n).getSquare() && m < ROWS - 1 && n > 0) {
					m++;
					n--;
				}
				if (board.getGrid(m, n).getSquare() == color) {
					flag = flag + (j - 1 - n);
				}
			}
		}

		return flag;

	}

	/**
	 * Whether the Grid need to be update(Whether there are disk filps happened)
	 * 
	 * @param player
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean updateGrid(int player, int i, int j) {
		boolean flag = false;

		if (board.getGrid(i, j).getSquare() == -1) {
			flag = false;
		} else {
			if (j != 0) {
				if (player != board.getGrid(i, j - 1).getSquare()
						&& -1 != board.getGrid(i, j - 1).getSquare()) {
					int m = j - 1;
					while (board.getGrid(i, j - 1).getSquare() == board
							.getGrid(i, m).getSquare() && m > 0) {
						m--;
					}
					if (board.getGrid(i, m).getSquare() == player) {
						flag = true;
						for (int n = j; n > m; n--) {
							board.getGrid(i, n).setSquare(player);
						}
					}
				}
			}
			if (j != COLS - 1) { 
				if (player != board.getGrid(i, j + 1).getSquare()
						&& -1 != board.getGrid(i, j + 1).getSquare()) {
					int m = j + 1;
					while (board.getGrid(i, j + 1).getSquare() == board
							.getGrid(i, m).getSquare() && m < ROWS - 1) {
						m++;
					}
					if (board.getGrid(i, m).getSquare() == player) {
						flag = true;
						for (int n = j; n < m; n++) {
							board.getGrid(i, n).setSquare(player);
						}
					}

				}
			}
			if (i != 0) {
				if (player != board.getGrid(i - 1, j).getSquare()
						&& -1 != board.getGrid(i - 1, j).getSquare()) {
					int m = i - 1;
					while (board.getGrid(i - 1, j).getSquare() == board
							.getGrid(m, j).getSquare() && m > 0) {
						m--;
					}
					if (board.getGrid(m, j).getSquare() == player) {
						flag = true;
						for (int n = i; n > m; n--) {
							board.getGrid(n, j).setSquare(player);
						}
					}
				}
			}
			if (i != ROWS - 1) {
				if (player != board.getGrid(i + 1, j).getSquare()
						&& -1 != board.getGrid(i + 1, j).getSquare()) {
					int m = i + 1;
					while (board.getGrid(i + 1, j).getSquare() == board
							.getGrid(m, j).getSquare() && m < ROWS - 1) {
						m++;
					}
					if (board.getGrid(m, j).getSquare() == player) {
						flag = true;
						for (int n = i; n < m; n++) {
							board.getGrid(n, j).setSquare(player);
						}
					}
				}
			}
			if (j != 0 && i != 0) {
				if (player != board.getGrid(i - 1, j - 1).getSquare()
						&& -1 != board.getGrid(i - 1, j - 1).getSquare()) {
					int m = i - 1;
					int n = j - 1;
					while (board.getGrid(i - 1, j - 1).getSquare() == board
							.getGrid(m, n).getSquare() && m > 0 && n > 0) {
						m--;
						n--;
					}
					if (board.getGrid(m, n).getSquare() == player) {
						flag = true;
						for (int x = i, y = j; x > m; x--, y--) {
							board.getGrid(x, y).setSquare(player);
						}
					}
				}
			}
			if (j != COLS - 1 && i != ROWS - 1) {
				if (player != board.getGrid(i + 1, j + 1).getSquare()
						&& -1 != board.getGrid(i + 1, j + 1).getSquare()) {
					int m = i + 1;
					int n = j + 1;
					while (board.getGrid(i + 1, j + 1).getSquare() == board
							.getGrid(m, n).getSquare() && m < ROWS - 1 && n < COLS - 1) {
						m++;
						n++;
					}
					if (board.getGrid(m, n).getSquare() == player) {
						flag = true;
						for (int x = i, y = j; x < m; x++, y++) {
							board.getGrid(x, y).setSquare(player);
						}
					}
				}
			}
			if (j != 0 && i != ROWS - 1) {
				if (player != board.getGrid(i + 1, j - 1).getSquare()
						&& -1 != board.getGrid(i + 1, j - 1).getSquare()) {
					int m = i + 1;
					int n = j - 1;
					while (board.getGrid(i + 1, j - 1).getSquare() == board
							.getGrid(m, n).getSquare() && m < COLS - 1 && n > 0) {
						m++;
						n--;
					}
					if (board.getGrid(m, n).getSquare() == player) {
						flag = true;
						for (int x = i, y = j; x < m; x++, y--) {
							board.getGrid(x, y).setSquare(player);
						}
					}
				}
			}
			if (j != COLS - 1 && i != 0) {
				if (player != board.getGrid(i - 1, j + 1).getSquare()
						&& -1 != board.getGrid(i - 1, j + 1).getSquare()) {
					int m = i - 1;
					int n = j + 1;
					while (board.getGrid(i - 1, j + 1).getSquare() == board
							.getGrid(m, n).getSquare() && m > 0 && n < COLS - 1) {
						m--;
						n++;
					}
					if (board.getGrid(m, n).getSquare() == player) {
						flag = true;
						for (int x = i, y = j; x > m; x--, y++) {
							board.getGrid(x, y).setSquare(player);
						}
					}
				}
			}
		}
		System.out.println();
		return flag;
	}

	public int checkOver () {
		int white = 0;
		int black = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board.getGrid(i, j).getSquare() == ReversiGUI.WHITE) {
					white++;
				} 
				else if (board.getGrid(i, j).getSquare() == ReversiGUI.BLACK) {
					black++;
				}
			}
		}

		if (black + white == ROWS * COLS || black == 0 || white == 0) {
			if (white > black || black == 0) {
				return 1; 
			} 
			else if (white < black || white == 0) {
				return -1; 
			} 
			else {
				return 0; 
			}
		} 
		else {
			return 2; 
		}
	}
	
	/**
	 * Reset the Game by calling this method
	 */
	public void reset() {
		count = ROWS * COLS - 4;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < ROWS; j++) {
				board.getGrid(i, j).setSquare(ReversiGUI.EMPTY);
				okForNext[i][j] = false;
			}
		}
		board = new Board(Reversi.ROWS, Reversi.COLS);
		
		gui.setCurrentPlayer(ReversiGUI.BLACK);
	}
}