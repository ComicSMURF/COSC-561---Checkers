package shell;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ai.CoordConventionMapper;
import board.Board;
import board.MoveChecker;

public class ShellTest {

	Shell shell;
	aiMoveGetter mockedAI = mock(aiMoveGetter.class);
	Board mockedBoard = mock(Board.class);
	MoveChecker mockedChecker = mock(MoveChecker.class);
	CoordConventionMapper mockedMapper = mock(CoordConventionMapper.class);
	
	@Before
	public void setUp() {
		shell = new Shell(mockedBoard, mockedAI, mockedMapper, null);
	}
	
	@Test
	public void acceptsAString() {
		shell.tryMove("some input");
	}
	
	@Test
	public void ifStringSentIsInvalidThenMessageIllegalMoveIsReturned() {
		String response = shell.tryMove("invalid Input");
		assertEquals("not a valid move.. try again!", response);
	}
	
	@Test
	public void ifStringSentIsAIThenMakesCallToAIWIthCurrentBoardAndMovesCurrentBoard() {
		ArrayList<Integer[]> move = new ArrayList<Integer[]>();
		move.add(new Integer[] {2, 3});
		when(mockedAI.getRandomLegalMove(mockedBoard, null)).thenReturn(move);
		
		shell.tryMove("ai");

		verify(mockedAI).getRandomLegalMove(mockedBoard, null);
		verify(mockedBoard).move(move);
	}
	
	@Test
	public void ifAListOfIntegersIsGivenItWillCheckToSeeIfItIsLegalThenTellTheBoardToMoveThere() {
		String attemptedMove = "13 17";
		when(mockedChecker.isLegal((Character[][])anyObject(), (ArrayList<Integer[]>)anyObject(), anyInt()))
		.thenReturn(true);
//		when(mockedMapper.getCoordOf(13)).thenReturn(new Integer[] {3, 0});
//		when(mockedMapper.getCoordOf(17)).thenReturn(new Integer[] {4, 2});
		
/*		ArrayList<Integer[]> expectedMove = new ArrayList<Integer[]>();
		expectedMove.add(new Integer[] {3, 0});
		expectedMove.add(new Integer[] {4, 2});
	*/	
		shell.tryMove(attemptedMove);
		
		verify(mockedBoard).move((ArrayList<Integer[]>)anyObject());
		
	}
	
	
}