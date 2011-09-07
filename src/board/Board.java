package board;

import java.util.ArrayList;

public class Board {

	private int movesSoFar = 0;
	private Character[][] grid = {{' ','b',' ','b',' ','b',' ','b'},
			 			  {'b',' ','b',' ','b',' ','b',' '},
			 			  {' ','b',' ','b',' ','b',' ','b'},
			 			  {' ',' ',' ',' ',' ',' ',' ',' '},
			 			  {' ',' ',' ',' ',' ',' ',' ',' '},
			 			  {'w',' ','w',' ','w',' ','w',' '},
			 			  {' ','w',' ','w',' ','w',' ','w'},
			 			  {'w',' ','w',' ','w',' ','w',' '}};

	public Character[][] grid() {
		return grid ;
	}

	public void move(ArrayList<Integer[]> move) {
		takeOutAnythingJumped(move);
		kingIfPossible(move);
		switchFirstAndLast(move);
		movesSoFar++;
	}

	private void kingIfPossible(ArrayList<Integer[]> move) {
		for (Integer[] integers : move) {
			if(integers[0] == 7 && grid[move.get(0)[0]][move.get(0)[1]] == 'b')
				grid[move.get(0)[0]][move.get(0)[1]] = 'B';
			else if(integers[0] == 0 && grid[move.get(0)[0]][move.get(0)[1]] == 'w')
				grid[move.get(0)[0]][move.get(0)[1]] = 'W';
	 	 }
	}

	private void takeOutAnythingJumped(ArrayList<Integer[]> move) {
		for (int i = 0; i < move.size()-1; i++) {
			int difference = move.get(i)[0] - move.get(i+1)[0];
			if(Math.abs(difference) == 2) {
				grid[mid(move.get(i)[0], move.get(i+1)[0])][mid(move.get(i)[1], move.get(i+1)[1])] = ' ';
			}
		}
		
	}

	private int mid(Integer integer, Integer integer2) {
		return Math.max(integer, integer2) - 1;
	}

	private void switchFirstAndLast(ArrayList<Integer[]> move) {
		char temp = grid[move.get(0)[0]][move.get(0)[1]];
		grid[move.get(0)[0]][move.get(0)[1]] = grid[move.get(move.size()-1)[0]][move.get(move.size()-1)[1]];
		grid[move.get(move.size()-1)[0]][move.get(move.size()-1)[1]] = temp;
	}

	public int movesSoFar() {
		return movesSoFar;
	}

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
