package board;

import java.util.ArrayList;
/**
 * Maintains all aspects of the board
 * 
 * @author Matthew Weitzel
 *
 */
public class Board {

	private int movesSoFar = 0;
	/**
	 * keeps track of the current state of the board
	 */
	private Character[][] grid = {{' ','b',' ','b',' ','b',' ','b'},
			 			  {'b',' ','b',' ','b',' ','b',' '},
			 			  {' ','b',' ','b',' ','b',' ','b'},
			 			  {' ',' ',' ',' ',' ',' ',' ',' '},
			 			  {' ',' ',' ',' ',' ',' ',' ',' '},
			 			  {'w',' ','w',' ','w',' ','w',' '},
			 			  {' ','w',' ','w',' ','w',' ','w'},
			 			  {'w',' ','w',' ','w',' ','w',' '}};

	/**
	 * Getter for the checker board
	 * 
	 * @return the current state of the board
	 */
	public Character[][] grid() {
		return grid ;
	}

	/**
	 * Looks at the board after a move so that it takes
	 * out whatever was jumped, kings whatever pieces are 
	 * needing a king status, switches the location of the piece
	 * that moved and whatever was there before
	 * 
	 * @param move the move that was made
	 */
	public void move(ArrayList<Integer[]> move) {
		takeOutAnythingJumped(move);
		kingIfPossible(move);
		switchFirstAndLast(move);
		movesSoFar++;
	}

	/**
	 * Kings all pieces in updating
	 * 
	 * @param move the move that was made
	 */
	private void kingIfPossible(ArrayList<Integer[]> move) {
		for (Integer[] integers : move) {
			if(integers[0] == 7 && grid[move.get(0)[0]][move.get(0)[1]] == 'b')
				grid[move.get(0)[0]][move.get(0)[1]] = 'B';
			else if(integers[0] == 0 && grid[move.get(0)[0]][move.get(0)[1]] == 'w')
				grid[move.get(0)[0]][move.get(0)[1]] = 'W';
	 	 }
	}

	/**
	 * Removes jumped pieces
	 * 
	 * @param move the move that was made
	 */
	private void takeOutAnythingJumped(ArrayList<Integer[]> move) {
		for (int i = 0; i < move.size()-1; i++) {
			int difference = move.get(i)[0] - move.get(i+1)[0];
			if(Math.abs(difference) == 2) {
				grid[getIntegerBetween(move.get(i)[0], move.get(i+1)[0])][getIntegerBetween(move.get(i)[1], move.get(i+1)[1])] = ' ';
			}
		}
		
	}

	/**
	 * Finds the board number of the piece that is being jumped
	 * 
	 * @param integer starting location
	 * @param integer2 destination location
	 * @return the integer between the two given integers
	 */
	private int getIntegerBetween(Integer integer, Integer integer2) {
		return Math.max(integer, integer2) - 1;
	}

	/**
	 * Switches the location destination with the original location
	 * on the board
	 * 
	 * @param move the move that was made
	 */
	private void switchFirstAndLast(ArrayList<Integer[]> move) {
		char temp = grid[move.get(0)[0]][move.get(0)[1]];
		grid[move.get(0)[0]][move.get(0)[1]] = grid[move.get(move.size()-1)[0]][move.get(move.size()-1)[1]];
		grid[move.get(move.size()-1)[0]][move.get(move.size()-1)[1]] = temp;
	}

	/**
	 * Getter for the number of moves
	 * 
	 * @return the number of moves made thus far
	 */
	public int movesSoFar() {
		return movesSoFar;
	}

	/**
	 * Simply returns the char array as a string
	 * 
	 * @return the grid as a string for printing
	 */
	public String gridAsString() {
		String stringGrid = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				stringGrid += grid[i][j];
			}
			stringGrid += "\n";
		}
		return stringGrid;
	}

}
