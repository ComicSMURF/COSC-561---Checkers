package shell;

import org.junit.Before;
import org.junit.Test;

public class ShellTest {

	Shell shell;
	
	@Before
	public void setUp() {
		shell = new Shell();
	}
	
	@Test
	public void acceptsAString() {
		shell.move("some input");
	}
	
	@Test
	public void ifStringSentIsInvalidThenMessageIllegalMoveIsReturned() {
		shell.move("invalid Input");
	}
	
	
}
