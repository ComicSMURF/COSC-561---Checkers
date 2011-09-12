package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MoveChecker {

	public boolean isLegal(Character[][] grid, ArrayList<Integer[]> move, int turn) {
		if(itIsBlacksTurn(turn))
			return theMoveIsOneOfTheFoundLegalMoves(grid, move);
		else
			return theMoveIsOneOfTheFoundLegalMoves(flip(grid), flip(move));
	}

	public ArrayList<Integer[]> flip(ArrayList<Integer[]> move) {
		ArrayList<Integer[]> newMove = new ArrayList<Integer[]>();
		for(int i = 0; i < move.size(); i++)
			newMove.add(new Integer[] {7-move.get(i)[0], 7-move.get(i)[1]});
		return newMove;
	}

	public Character[][] flip(Character[][] grid) {
		Character[][] newGrid = new Character[grid.length][grid[0].length];
		for (int i = 0; i < newGrid.length; i++) {
			for (int j = 0; j < newGrid[0].length; j++) {
				newGrid[7-i][7-j] = grid[i][j]; 
			}
		}
		for (int i = 0; i < newGrid.length; i++) {
			for (int j = 0; j < newGrid[0].length; j++) {
				if(newGrid[i][j] == 'w')
					newGrid[i][j] = 'b';
				else if(newGrid[i][j] == 'W')
					newGrid[i][j] = 'B';
				else if(newGrid[i][j] == 'b')
					newGrid[i][j] = 'w';
				else if(newGrid[i][j] == 'B')
					newGrid[i][j] = 'W';
			}
		}
		return newGrid;
	}

	private boolean theMoveIsOneOfTheFoundLegalMoves(Character[][] grid, ArrayList<Integer[]> move) {
		Collection<ArrayList<Integer[]>> allPossibleMoves = getAllPossibleMovesForGivenGrid(grid);
		
		return contains(allPossibleMoves, move);
	}

	public Collection<ArrayList<Integer[]>> getAllPossibleMovesForGivenGrid(
			Character[][] grid) {
		Collection<ArrayList<Integer[]>> allPossibleMoves = new LinkedList<ArrayList<Integer[]>>();
		Collection<Integer[]> blackPieces = getAllBlackPieces(grid);

		for (Integer[] blackPiece : blackPieces) {
			ArrayList<Integer[]> jumpSoFar = new ArrayList<Integer[]>();
			jumpSoFar.add(blackPiece);
			allPossibleMoves.addAll(jumpMovesFor(blackPiece, grid, jumpSoFar));
		}
		
		if(allPossibleMoves.isEmpty())
			for (Integer[] blackPiece : blackPieces) {
				allPossibleMoves.addAll(normalMovesFor(blackPiece, grid));
			}
		return allPossibleMoves;
	}

	private boolean contains(Collection<ArrayList<Integer[]>> moves, ArrayList<Integer[]> move) {
		for (ArrayList<Integer[]> integers : moves) {
			if(isSameAs(integers, move))
				return true;
		}
		return false;
	}

	private boolean isSameAs(ArrayList<Integer[]> integers, ArrayList<Integer[]> move) {
		try{
			for(int i = 0; i < integers.size(); i++)
				for(int j = 0; j < integers.get(0).length; j++)
					if(integers.get(i)[j] != move.get(i)[j])
						return false;
			for(int i = 0; i < move.size(); i++)
				for(int j = 0; j < move.get(0).length; j++)
					if(integers.get(i)[j] != move.get(i)[j])
						return false;
		}catch(Exception e) {
			return false;
		}
		return true;
		
	}

	private Collection<ArrayList<Integer[]>> normalMovesFor(Integer[] blackPiece, Character[][] grid) {
		Collection<ArrayList<Integer[]>> moves = new LinkedList<ArrayList<Integer[]>>();
		Collection<Integer[]> potentials = new LinkedList<Integer[]>();
		if(isKing(blackPiece, grid)) {
			potentials.add(new Integer[] {blackPiece[0]-1, blackPiece[1]-1});
			potentials.add(new Integer[] {blackPiece[0]-1, blackPiece[1]+1});
		}
		potentials.add(new Integer[] {blackPiece[0]+1, blackPiece[1]+1});
		potentials.add(new Integer[] {blackPiece[0]+1, blackPiece[1]-1});
		
		for (Integer[] move : potentials) {
			try{
				char spot = grid[move[0]][move[1]]; 
				if(spot == ' ') {
					ArrayList<Integer[]> moveToOpen = new ArrayList<Integer[]>();
					moveToOpen.add(new Integer[]{blackPiece[0], blackPiece[1]});
					moveToOpen.add(new Integer[]{move[0], move[1]});
					moves.add(moveToOpen);
				}
			}catch(Exception e) {}
		}
		
		return moves;
	}

	private Collection<Integer[]> getAllBlackPieces(Character[][] grid) {
		LinkedList<Integer[]> blackPieces = new LinkedList<Integer[]>();
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == 'b' || grid[i][j] == 'B')
					blackPieces.add(new Integer[] {i, j});
			}
		return blackPieces;
	}

	private boolean itIsBlacksTurn(int turn) {
		return (turn % 2) == 0;
	}

	private Collection<ArrayList<Integer[]>> jumpMovesFor(Integer[] blackPiece, Character[][] grid, ArrayList<Integer[]> jumpSoFar) {
		Collection<ArrayList<Integer[]>> jumpMoves = new LinkedList<ArrayList<Integer[]>>();

		Integer[] white;
		Integer[] landing;

		white = new Integer[]   {blackPiece[0]-1,blackPiece[1]-1};
		landing = new Integer[] {blackPiece[0]-2,blackPiece[1]-2};
		if(isKing(blackPiece, grid) && isLegalAndWhite(white, grid) && isLegalAndOpen(landing, grid)) {
			jumpMoves.add(moveFrom(jumpSoFar, landing));
			jumpMoves.addAll(jumpMovesFor(landing, newGridFrom(grid, blackPiece, white, landing), moveFrom(jumpSoFar, landing)));
		}
		white = new Integer[]   {blackPiece[0]-1,blackPiece[1]+1};
		landing = new Integer[] {blackPiece[0]-2,blackPiece[1]+2};
		if(isKing(blackPiece, grid) && isLegalAndWhite(white, grid) && isLegalAndOpen(landing, grid)) {
			jumpMoves.add(moveFrom(jumpSoFar, landing));
			jumpMoves.addAll(jumpMovesFor(landing, newGridFrom(grid, blackPiece, white, landing), moveFrom(jumpSoFar, landing)));
		}
		white = new Integer[]   {blackPiece[0]+1,blackPiece[1]-1};
		landing = new Integer[] {blackPiece[0]+2,blackPiece[1]-2};
		if(isLegalAndWhite(white, grid) && isLegalAndOpen(landing, grid)) {
			jumpMoves.add(moveFrom(jumpSoFar, landing));
			jumpMoves.addAll(jumpMovesFor(landing, newGridFrom(grid, blackPiece, white, landing), moveFrom(jumpSoFar, landing)));
		}
		white = new Integer[]   {blackPiece[0]+1,blackPiece[1]+1};
		landing = new Integer[] {blackPiece[0]+2,blackPiece[1]+2};
		if(isLegalAndWhite(white, grid) && isLegalAndOpen(landing, grid)) {
			jumpMoves.add(moveFrom(jumpSoFar, landing));
			jumpMoves.addAll(jumpMovesFor(landing, newGridFrom(grid, blackPiece, white, landing), moveFrom(jumpSoFar, landing)));
		}
/*		isKing() && isWhite() && isOpen
		isWhite() && isOpen
		isWhite() && isOpen
		if(isKing(blackPiece, grid)) {
			potentials.add(new Integer[] {blackPiece[0]-1, blackPiece[1]-1});
			potentials.add(new Integer[] {blackPiece[0]-1, blackPiece[1]+1});
		}
		potentials.add(new Integer[] {blackPiece[0]+1, blackPiece[1]+1});
		potentials.add(new Integer[] {blackPiece[0]+1, blackPiece[1]-1});
	*/	
		
		/*
		 * 
		 * for every black piece
		 * check to see if it has diagonal white pieces
		 * 		for each diagonal white piece, jump it NOW add this jump, THEN
		 * 			then recursively check again, but on a new board with no white piece there 
		 * 										and the black one in the new spot ALSO check to maybe king it
		 * 
		 * 
		 */
		
		return jumpMoves;
	}

	private Character[][] newGridFrom(Character[][] grid, Integer[] blackPiece,
			Integer[] white, Integer[] landing) {
		
		Character[][] newGrid = new Character[grid.length][grid[0].length];
		for (int i = 0; i < newGrid.length; i++) {
			for (int j = 0; j < newGrid[0].length; j++) {
				newGrid[i][j] = grid[i][j]; 
			}
		}
		
		newGrid[landing[0]][landing[1]] = grid[blackPiece[0]][blackPiece[1]];
		newGrid[blackPiece[0]][blackPiece[1]] = ' ';
		newGrid[white[0]][white[1]] = ' ';
		if(landing[0] == 7)
			newGrid[landing[0]][landing[1]] = 'B';
		return newGrid;
	}

	private ArrayList<Integer[]> moveFrom(ArrayList<Integer[]> jumpSoFar,
			Integer[] landing) {
		ArrayList<Integer[]> newJump = (ArrayList<Integer[]>) jumpSoFar.clone();
		newJump.add(landing);
		return newJump;
	}

	private boolean isLegalAndOpen(Integer[] landing, Character[][] grid) {
		return (
				isWithinBounds(landing) &&
				(grid[landing[0]][landing[1]] == ' ')
		);
	}

	private boolean isLegalAndWhite(Integer[] white, Character[][] grid) {
		return (
				isWithinBounds(white) &&
				(grid[white[0]][white[1]] == 'w' || grid[white[0]][white[1]] == 'W')
		);
	}

	private boolean isWithinBounds(Integer[] spot) {
		return spot[0] >= 0 && spot[0] <=7 && spot[1] >= 0 && spot[1] <=7;
	}

	private boolean isKing(Integer[] blackPiece, Character[][] grid) {
		return grid[blackPiece[0]][blackPiece[1]] == 'B';
	}
}

