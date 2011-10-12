package shell;

import java.util.ArrayList;
import java.util.Collection;

import board.Board;
import board.MoveChecker;

public class Evaluator {

	
	
	public Integer[] bestMoveFor(Board passedBoard, int depth){
		MoveChecker moving = new MoveChecker();
		Collection<Struct> boardsParent = new ArrayList<Struct>();
		Collection<Struct> boardsChildren = new ArrayList<Struct>();
		
		boardsParent.add(new Struct(passedBoard, null, null));
		
		for(Struct b : boardsParent){
			for( ArrayList<Integer[]> k : moving.getAllPossibleMovesForGivenGrid(b.currBoard.grid())){
				System.out.println(k);
				for(Integer[] r : k){
					System.out.println(r);
					Board copied = new Board(b.currBoard);
					copied.move(k);
					boardsChildren.add(new Struct(copied, r, b));
				}
			}
			boardsParent = boardsChildren;
			boardsChildren = new ArrayList<Struct>();
		}
		
		for(Struct board : boardsParent){
			//board.setVal(evaluate(board.currBoard));
		}
		if(depth%2==0)
			max(boardsParent);
		else
			min(boardsParent);
		
		return new Integer[] {2, 4};
		
	}

	private Struct min(Collection<Struct> boardsParent) {
		Struct temp = null;
		for(Struct board : boardsParent)
			if(temp == null || temp.getVal() > board.getVal())
				temp = board;
		return temp;
	}

	private Struct max(Collection<Struct> boardsParent) {
		Struct temp = null;
		for(Struct board : boardsParent)
			if(temp == null || temp.getVal() < board.getVal())
				temp = board;
		return temp;
	}
}

class Struct{

	Board currBoard;
	Integer[] move;
	Struct prev;
	private int value;
	
	public Struct(Board passedBoard, Integer[] move, Struct parent) {
		this.currBoard = passedBoard;
		this.move= move;
		this.prev = parent;
		this.value = 0;
	}
	
	public void setVal(int val){
		this.value = val;
	}
	public int getVal(){
		return this.value;
	}
	
}