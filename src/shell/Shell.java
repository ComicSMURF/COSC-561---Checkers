package shell;

import java.util.ArrayList;
import java.util.StringTokenizer;

import ai.CoordConventionMapper;
import board.Board;
import board.MoveChecker;

public class Shell {

	aiMoveGetter ai;
	Board board;
	CoordConventionMapper coordMapper;
	private MoveChecker moveChecker;
	
	public Shell(Board board, aiMoveGetter aiMoveGetter, CoordConventionMapper coordMapper, MoveChecker moveChecker) {
		this.board = board;
		ai = aiMoveGetter;
		this.coordMapper = coordMapper;
		this.moveChecker = moveChecker;
	}

	public String tryMove(String playerInput) {
		try{
			ArrayList<Integer[]> bestMove = moveFromString(playerInput);
			
			if(moveChecker.isLegal(board.grid(), bestMove, board.movesSoFar()))
				board.move(bestMove);
			else
				throw new Exception();
			
			return board.gridAsString();
			
		}
		catch(Exception e) {
			return "not a valid move.. try again!";
		}
	}

	private ArrayList<Integer[]> moveFromString(String playerInput) throws Exception {
		ArrayList<Integer[]> bestMove;
		
		if(playerInput.equals("ai")) {
			bestMove = ai.getBestMove(board, moveChecker);
			for(int i = 0; i < bestMove.size(); i++) {
				Integer[] move = bestMove.get(i);
				
				
				System.out.print(coordMapper.returnCoord(move[0], move[1])+ " ");
				
			
			}
			System.out.println();
//			System.out.println(bestMove);
			return bestMove;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(playerInput);
		bestMove = new ArrayList<Integer[]>();
		while(tokenizer.hasMoreTokens()) {
			bestMove.add(coordMapper.getCoordOf(Integer.parseInt(tokenizer.nextToken())));
		}
		if(bestMove.size() == 0 || bestMove.size() == 1)
			throw new Exception();
		return bestMove;
	}

	public Object getWinnerMessage() {
		return "none yet";
	}

}
