package shell;

import java.util.ArrayList;
import java.util.Collection;

import board.Board;
import board.MoveChecker;

public class aiMoveGetter {

	public ArrayList<Integer[]> getBestMove(Board board, MoveChecker moveChecker) {
		ArrayList<ArrayList<Integer[]>> allPossible = new ArrayList<ArrayList<Integer[]>>();
				
		if(itIsBlacksTurn(board)) {
			allPossible.addAll(moveChecker.getAllPossibleMovesForGivenGrid(board.grid()));
			return allPossible.get((int) (Math.random() % allPossible.size()));	
		}
		else {
			allPossible.addAll(moveChecker.getAllPossibleMovesForGivenGrid(moveChecker.flip(board.grid())));
			return moveChecker.flip((allPossible.get((int) (Math.random() % allPossible.size()))));
		}
		
	}

	private boolean itIsBlacksTurn(Board board) {
		return board.movesSoFar() % 2 == 0;
	}

}