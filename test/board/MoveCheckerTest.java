package board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MoveCheckerTest {
	
	Board board;
	MoveChecker checker;
	ArrayList<Integer[]> move;
	
	@Before
	public void setUp() {
		board = new Board();
		checker = new MoveChecker();
	}

	@Test
	public void allOpeningMovesAreLegal() {
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,0});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));

		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,1});
		move.add(new Integer[]{3,2});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,3});
		move.add(new Integer[]{3,2});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,3});
		move.add(new Integer[]{3,4});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,5});
		move.add(new Integer[]{3,4});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,5});
		move.add(new Integer[]{3,6});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,7});
		move.add(new Integer[]{3,6});
		assertTrue(checker.isLegal(board.grid(), move, board.movesSoFar()));
	}

	@Test
	public void whiteCantGoFirstOrOnAnyOfBlacksTurns() {
		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,0});
		move.add(new Integer[]{4,1});
		assertFalse(checker.isLegal(board.grid(), move, 0));
		assertTrue(checker.isLegal(board.grid(), move, 1));
		assertFalse(checker.isLegal(board.grid(), move, 2));
		assertFalse(checker.isLegal(board.grid(), move, 4));
	}

	@Test
	public void blackCanJumpWhite() {
		Character[][] grid ={{' ','b',' ','b',' ','b',' ','b'},
							 {'b',' ','b',' ','b',' ','b',' '},
							 {' ',' ',' ','b',' ','b',' ',' '},
							 {'w',' ','b',' ',' ',' ',' ',' '},
							 {' ','w',' ',' ',' ',' ',' ','B'},
							 {' ',' ',' ',' ','w',' ','w',' '},
							 {' ','w',' ','w',' ',' ',' ','w'},
							 {'w',' ',' ',' ','w',' ','w',' '}};

		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{3,2});
		move.add(new Integer[]{5,0});
		assertTrue(checker.isLegal(grid, move, 6));

		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{3,2});
		move.add(new Integer[]{5,0});
		move.add(new Integer[]{7,2});
		assertTrue(checker.isLegal(grid, move, 6));

		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{4,7});
		move.add(new Integer[]{6,5});
		move.add(new Integer[]{4,3});
		assertTrue(checker.isLegal(grid, move, 6));

	}
	
	@Test
	public void blackCanJumpWhiteTurnIntoAKingThenJumpBackwards() {
		Character[][] grid ={{' ','b',' ','b',' ','b',' ','b'},
							 {'b',' ','b',' ','b',' ','b',' '},
							 {' ',' ',' ','b',' ','b',' ',' '},
							 {'w',' ','b',' ',' ',' ',' ',' '},
							 {' ','w',' ',' ',' ',' ',' ','B'},
							 {' ',' ',' ',' ','b',' ','w',' '},
							 {' ','w',' ','w',' ',' ',' ','w'},
							 {'w',' ',' ',' ','w',' ','w',' '}};

		
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,4});
		move.add(new Integer[]{7,2});
		move.add(new Integer[]{5,0});
		assertTrue(checker.isLegal(grid, move, 6));

		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,4});
		move.add(new Integer[]{7,2});
		assertTrue(checker.isLegal(grid, move, 6));
	}
	
	@Test
	public void blackCantGoOnWhiteTurns() {
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{2,3});
		move.add(new Integer[]{3,2});
		assertFalse(checker.isLegal(board.grid(), move, 1));
		assertFalse(checker.isLegal(board.grid(), move, 3));
		assertFalse(checker.isLegal(board.grid(), move, 437));
	}
	
	@Test
	public void whiteCanGoOnSecondTurn() {
		move = new ArrayList<Integer[]>();
		move.add(new Integer[]{5,0});
		move.add(new Integer[]{4,1});
		assertTrue(checker.isLegal(board.grid(), move, 1));
	}
	
}

/*						     0   1   2   3   4   5   6   7
*	Character[][] grid = {0{' ','b',' ','b',' ','b',' ','b'},
			 			  1{'b',' ','b',' ','b',' ','b',' '},
			 			  2{' ','b',' ','b',' ','b',' ','b'},
			 			  3{' ',' ',' ',' ',' ',' ',' ',' '},
			 			  4{' ',' ',' ',' ',' ',' ',' ',' '},
			 			  5{'w',' ','w',' ','w',' ','w',' '},
			 			  6{' ','w',' ','w',' ','w',' ','w'},
			 			  7{'w',' ','w',' ','w',' ','w',' '}};
*
*/