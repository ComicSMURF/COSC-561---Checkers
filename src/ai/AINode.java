package ai;

import java.util.ArrayList;

import board.Board;

public class AINode {
	
	private Board board;
	private int evaluate;
	private ArrayList<AINode> nextList;
	
	public AINode(){
		
	}
	
	public AINode(Board b){
		this.board = b;
	}
	
	public AINode(int value, Board b, ArrayList a){
		this.board = b;
		this.evaluate = value;
		this.nextList = a;
	}
	
	public void changeEval(int i){
		this.evaluate = i;
	}
	
	
}
