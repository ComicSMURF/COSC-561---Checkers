package shell;

import java.util.ArrayList;
import board.Board;

public class Evaluator {

	
	public int evaluate(Struct thinger) {
		//thinger.currBoard.grid;
		return 5;
		//return (int) (Math.random() * 1000.0);
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