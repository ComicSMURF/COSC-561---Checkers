package shell;

import java.util.ArrayList;

import board.Board;

public class Shell {

	aiMoveGetter ai;
	Board board;
	
	public Shell(Board board, aiMoveGetter aiMoveGetter) {
		this.board = board;
		ai = aiMoveGetter;
	}

	public String move(String string) {
		ArrayList<Integer[]> bestMove;
		if(string == "ai") {
			bestMove = ai.getBestMove(board.grid());
			board.move(bestMove);
			return board.gridAsString();
		}
		else
			return "not a valid move.. try again!";
	}

}
