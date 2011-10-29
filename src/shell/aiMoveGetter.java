package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import board.Board;
import board.MoveChecker;

public class aiMoveGetter {


	public ArrayList<Integer[]> bestMoveFor(Board passedBoard, int depth){
		Collection<Struct> boardsParent = new ArrayList<Struct>();
		Collection<Struct> boardsChildren = new ArrayList<Struct>();
		
		boardsParent.add(new Struct(passedBoard, null, null));
		
		for(int i = 0; i < depth; i++) {
			for(Struct aPossibleBoard : boardsParent){				
				boardsChildren.addAll(everyPossibleMoveIn(aPossibleBoard));
			}
			if(!allChildrenAreNull(boardsChildren)){
				boardsParent = boardsChildren;
				boardsChildren = new ArrayList<Struct>();
			}
		}
		for(Struct s : boardsParent)
			boardsParent.remove(null);
		if((depth + passedBoard.movesSoFar())%2 == 0)
			return moveAsIfTryingToGetMax(boardsParent);
		else
			return moveAsIfTryingToGetMin(boardsParent);
		
	}

	private boolean allChildrenAreNull(Collection<Struct> boardsChildren) {
		for(Struct s : boardsChildren){
			if(!s.equals(null))
				return false;
		}
		return true;
	}

	private Collection<Struct> everyPossibleMoveIn(Struct aPossibleBoard) {
		MoveChecker moveChecker = new MoveChecker();
		
		
		
		Collection<ArrayList<Integer[]>> allMoves = new ArrayList<ArrayList<Integer[]>>();
		Collection<Struct> allNextMoves = new ArrayList<Struct>();
		
		if(itIsBlacksTurn(aPossibleBoard.currBoard)) {
			allMoves.addAll(moveChecker.getAllPossibleMovesForGivenGrid(aPossibleBoard.currBoard.grid()));
			for (ArrayList<Integer[]> move : allMoves) {
				//create a new board from the move being applied to the aPossibleBoard.currBoard
				//create a new struct with the move, the board, and pointing to aPossibleBoard
				Board boardFromMove = aPossibleBoard.currBoard.cloneDeep();
				boardFromMove.move(move);
				Struct structToAddToCollection = new Struct(boardFromMove, move, aPossibleBoard);
				
				allNextMoves.add(structToAddToCollection);
				
			}
		}
		else {
			allMoves.addAll(moveChecker.getAllPossibleMovesForGivenGrid(moveChecker.flip(aPossibleBoard.currBoard.grid())));
			for (ArrayList<Integer[]> move : allMoves) {
				//create a new board from the move being applied to the aPossibleBoard.currBoard
				//create a new struct with the move, the board, and pointing to aPossibleBoard
				Board boardFromMove = aPossibleBoard.currBoard.cloneDeep();
				boardFromMove.move(move);
				boardFromMove.grid = moveChecker.flip(boardFromMove.grid);
				Struct structToAddToCollection = new Struct(boardFromMove, moveChecker.flip(move), aPossibleBoard);
				
				allNextMoves.add(structToAddToCollection);
				
			}

		}
		
		
		
		
		
		//needs to return a collection of structs created from the boards, moves to the boards, and pointing to a parent)
		return allNextMoves;
	}
//		Collection<Struct> boards1 = new Arraylist<struct>
//		Collection<Struct> boards2 = new Arraylist<struct>
//		boards1.add(new Struct(passedBoard, null, null)//move to get there and parent are null)
//		for(depthAllowed){
//			
//			for(every boardx in boards1){
//				
//				for(every possible move in boardx){
//					boards2.add(new Struct(deepclonedBoardx.applyMove(move), move, boardx))
//
//				}
//			}
//			boards1 = boards2;
//			boards2 = new Arraylist();
//		}


	private ArrayList<Integer[]> moveAsIfTryingToGetMax(Collection<Struct> boardsParent) {
		Evaluator evaluator = new Evaluator();
		Struct currentBestSituation = null;
		
		for (Struct struct : boardsParent) {
			//System.out.println(evaluator.evaluate(struct) > evaluator.evaluate(currentBestSituation));
			if(currentBestSituation == null || evaluator.evaluate(struct) >= evaluator.evaluate(currentBestSituation))
				currentBestSituation = struct;			
		}
		return getFirstMoveToward(currentBestSituation);
		
	}
	
	private ArrayList<Integer[]> moveAsIfTryingToGetMin(Collection<Struct> boardsParent) {
		Evaluator evaluator = new Evaluator();
		Struct currentBestSituation = null;
		
		for (Struct struct : boardsParent) {
			if(currentBestSituation == null || evaluator.evaluate(struct) < evaluator.evaluate(currentBestSituation))
				currentBestSituation = struct;			
		}
		return getFirstMoveToward(currentBestSituation);
		
	}
	
	private ArrayList<Integer[]> getFirstMoveToward(Struct currentBestSituation) {
		Struct getParent = currentBestSituation;
		Struct childOfParent = null;
		
		while(getParent.prev != null){
			childOfParent = getParent;
			getParent = getParent.prev;
		}
		return childOfParent.move;
	}

	
	
	
	
	
	
	
	public ArrayList<Integer[]> getRandomLegalMove(Board board, MoveChecker moveChecker) {
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