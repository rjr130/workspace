/**
 * This class represents a single square of the reversi board and can be empty, or have a black or white disk in it
 * 
 * @author Junrui Ruan(jruan@cs.wisc.edu) Qianwen Lu (qianwen)
 *
 */
public class Square {
	private int SquareState = ReversiGUI.EMPTY;

	
	public void setSquare (int num) {
		SquareState = num;
	}
	
	public int getSquare () {
		return SquareState;
	}	
}
