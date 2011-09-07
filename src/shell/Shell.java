package shell;

import java.util.ArrayList;
import java.util.StringTokenizer;

import ai.CoordConventionMapper;
import board.Board;

public class Shell {

	aiMoveGetter ai;
	Board board;
	CoordConventionMapper coordMapper;
	
	public Shell(Board board, aiMoveGetter aiMoveGetter, CoordConventionMapper coordMapper) {
		this.board = board;
		ai = aiMoveGetter;
		this.coordMapper = coordMapper;
	}

	public String tryMove(String playerInput) {
		try{
			ArrayList<Integer[]> bestMove = moveFromString(playerInput);
			board.move(bestMove);
			return board.gridAsString();
			
		}
		catch(Exception e) {
			return "not a valid move.. try again!";
		}
	}

	private ArrayList<Integer[]> moveFromString(String playerInput) throws Exception {
		ArrayList<Integer[]> bestMove;
		
		if(playerInput == "ai") {
			bestMove = ai.getBestMove(board);
			return bestMove;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(playerInput);
		bestMove = new ArrayList<Integer[]>();
		while(tokenizer.hasMoreTokens()) {
			bestMove.add(coordMapper.getCoordOf(Integer.parseInt(tokenizer.nextToken())));
		}
		return bestMove;
	}

}
