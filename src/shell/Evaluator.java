package shell;

import java.util.ArrayList;
import java.util.Random;

import board.Board;

public class Evaluator {

	/*****************************************
	 * Takes a board and evaluates the state
	 * of the board, and returns a numeric 
	 * value that reflects that state.
	 * 
	 * First Revision:
	 *   Simply adds all values of the pieces
	 *    - 5 for regular pieces
	 *    - 10 for kings
	 * @param thinger
	 * @return
	 */
	public int evaluate(Struct thinger) {
		int valBlack = 0, valWhite = 0;
		if(thinger != null){
			for(int i = 0; i < thinger.currBoard.grid.length; i++){
				for(int j = 0; j < thinger.currBoard.grid[0].length; j++){
					if(thinger.currBoard.grid[i][j] == 'B')
						valBlack += 10;
					else if(thinger.currBoard.grid[i][j] == 'b')
						valBlack += 5;
					else if(thinger.currBoard.grid[i][j] == 'W')
						valWhite += 10;
					else if(thinger.currBoard.grid[i][j] == 'w')
						valWhite += 5;
					else{ }
				}
			}
		}
		if((thinger.currBoard.movesSoFar()%2) == 0)
			return (valBlack - valWhite);
		return (valWhite - valBlack);
	}

}

class Struct{

	Board currBoard;
	ArrayList<Integer[]> move;
	Struct prev;
	
	public Struct(Board passedBoard, ArrayList<Integer[]> move, Struct parent) {
		this.currBoard = passedBoard;
		this.move= move;
		this.prev = parent;
	}
	
	
}