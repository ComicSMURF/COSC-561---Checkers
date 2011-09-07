package board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	Board board;

	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void boardHas8x8Grid() {
		assertEquals(board.grid().length, 8);
		assertEquals(board.grid()[0].length, 8);
	}
	
	@Test
	public void boardsGridInitializesToCheckers() {
		Character[][] grid = {{' ','b',' ','b',' ','b',' ','b'},
							  {'b',' ','b',' ','b',' ','b',' '},
							  {' ','b',' ','b',' ','b',' ','b'},
							  {' ',' ',' ',' ',' ',' ',' ',' '},
							  {' ',' ',' ',' ',' ',' ',' ',' '},
							  {'w',' ','w',' ','w',' ','w',' '},
							  {' ','w',' ','w',' ','w',' ','w'},
							  {'w',' ','w',' ','w',' ','w',' '}};
		assertEquals(board.grid(), grid);
	}
	
	@Test
	public void knowsHowManyMovesHaveBeenMade() {
		ArrayList<Integer[]> move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,0});
		
	
		assertEquals(board.movesSoFar(), 0);
		board.move(move);
		assertEquals(board.movesSoFar(), 1);
		
	}
	
	@Test
	public void boardIsDifferentWhenDone() {
		ArrayList<Integer[]> move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,0});
		
		board.move(move);
		Character[][] expectedGrid = {{' ','b',' ','b',' ','b',' ','b'},
									  {'b',' ','b',' ','b',' ','b',' '},
									  {' ',' ',' ','b',' ','b',' ','b'},
									  {'b',' ',' ',' ',' ',' ',' ',' '},
									  {' ',' ',' ',' ',' ',' ',' ',' '},
									  {'w',' ','w',' ','w',' ','w',' '},
									  {' ','w',' ','w',' ','w',' ','w'},
									  {'w',' ','w',' ','w',' ','w',' '}};

		for (int i = 0; i < board.grid().length; i++) {
			for (int j = 0; j < board.grid()[0].length; j++) {
				assertEquals(expectedGrid[i][j], board.grid()[i][j]);
			}
		}
	}
	
	@Test
	public void boardIsDifferentWhenDoneJumping() {
		ArrayList<Integer[]> move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,0});
		board.move(move);
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,2});
		move.add(new Integer[]{4,1});
		board.move(move);
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{3,0});
		move.add(new Integer[]{5,2});
		board.move(move);

		Character[][] expectedGrid = {{' ','b',' ','b',' ','b',' ','b'},
									  {'b',' ','b',' ','b',' ','b',' '},
									  {' ',' ',' ','b',' ','b',' ','b'},
									  {' ',' ',' ',' ',' ',' ',' ',' '},
									  {' ',' ',' ',' ',' ',' ',' ',' '},
									  {'w',' ','b',' ','w',' ','w',' '},
									  {' ','w',' ','w',' ','w',' ','w'},
									  {'w',' ','w',' ','w',' ','w',' '}};

	}	
	
	@Test
	public void boardIsDifferentAndPiecesAreKingedWhenDoneDoubleJumpingBackwards() {
		ArrayList<Integer[]> move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,0});
		board.move(move);
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,2});
		move.add(new Integer[]{4,1});
		board.move(move);
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{3,0});
		move.add(new Integer[]{5,2});
		board.move(move);

		Character[][] expectedGrid = {{' ','b',' ','b',' ','b',' ','b'},
									  {'b',' ','b',' ','b',' ','b',' '},
									  {' ',' ',' ','b',' ','b',' ','b'},
									  {' ',' ',' ',' ',' ',' ',' ',' '},
									  {' ',' ',' ',' ',' ',' ',' ',' '},
									  {'w',' ','b',' ','w',' ','w',' '},
									  {' ','w',' ','w',' ','w',' ','w'},
									  {'w',' ','w',' ','w',' ','w',' '}};

		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,4});
		move.add(new Integer[]{4,3});
		board.move(move);
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,6});
		move.add(new Integer[]{4,5});
		board.move(move);
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{6,5});
		move.add(new Integer[]{5,4});
		board.move(move);
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{7,4});
		move.add(new Integer[]{6,5});
		board.move(move);
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,2});
		move.add(new Integer[]{7,4});
		move.add(new Integer[]{5,6});
		board.move(move);
		
		expectedGrid = new Character[][]{{' ','b',' ','b',' ','b',' ','b'},
				  {'b',' ','b',' ','b',' ','b',' '},
				  {' ',' ',' ','b',' ','b',' ','b'},
				  {' ',' ',' ',' ',' ',' ',' ',' '},
				  {' ',' ',' ','w',' ','w',' ',' '},
				  {'w',' ',' ',' ','w',' ','B',' '},
				  {' ','w',' ',' ',' ',' ',' ','w'},
				  {'w',' ','w',' ',' ',' ','w',' '}};

	}

	private void printBoard(Character[][] expectedGrid) {
		for (int i = 0; i < board.grid().length; i++) {
			for (int j = 0; j < board.grid()[0].length; j++) {
				System.out.print(" i, j "+i+" "+j);
				assertEquals(expectedGrid[i][j], board.grid()[i][j]);
			}
		}
	}
}
