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
			{
				board.move(bestMove);
			}
			else{
				throw new Exception(" asdf"+  bestMove.toString() + "\n" + board.gridAsString());
			}			
			return board.gridAsString();
			
		}
		catch(Exception e) {
			return e.getMessage() + " is not a valid move.. try again!";
		}
	}

	private ArrayList<Integer[]> moveFromString(String playerInput) throws Exception {
		ArrayList<Integer[]> bestMove;
		
		if(playerInput.equals("ai")) {
			bestMove = ai.bestMoveFor(board, 6);//getRandomLegalMove(board, moveChecker);
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
			throw new Exception("something " + bestMove.toString());
		return bestMove;
	}

	/*******************************
	 * Determines the winner and message
	 * to be displayed for a draw and
	 * win/lose. To determine a 
	 * @return
	 */
	private int movesSinceLastJump = 0;
	public Object getWinnerMessage() {
		movesSinceLastJump++;
		//tell if stale mate
		if(hasDoneTooManyMovesWithNoJumps()){

			System.out.println();
			return "\t\tThis game is a draw\n\n" + statsFromGame();
		}
		
		else if(bothSideStillHasPieces(board.grid)){
			return "none yet";	
		}
		else{
			String color = "";
			if(lastValOfBlack > 0)
				color = "Black";
			else
				color = "White";
			return "\t\t" + color +" Wins!!\n\n" + statsFromGame();
		}
		
	}
	/********************************
	 * Returns the stats
	 * @return
	 */
	private String statsFromGame() {
		return "\tNumber of moves:   "+ board.movesSoFar()+"\n" +
				"\tNumber of remaining white pieces:   " + lastValOfWhite+"\n" +
				"\tNumber of remaining black pieces:   " + lastValOfBlack+"\n";
	}

	private boolean hasDoneTooManyMovesWithNoJumps() {
		return (movesSinceLastJump>40);
	}
	
	private int lastValOfBlack = 0, lastValOfWhite = 0;
	
	private boolean bothSideStillHasPieces(Character[][] grid) {
		int numWhite = 0, numBlack = 0;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(grid[row][col] == 'b' || grid[row][col] == 'B')
					numBlack++;
				else if(grid[row][col] == 'w' || grid[row][col] == 'W')
					numWhite++;
			}
		}
		if(lastValOfBlack != numBlack || lastValOfWhite != numWhite)
			movesSinceLastJump = 0;
		lastValOfBlack = numBlack;
		lastValOfWhite = numWhite;
		return (numWhite>0 && numBlack>0);
	}

	
}