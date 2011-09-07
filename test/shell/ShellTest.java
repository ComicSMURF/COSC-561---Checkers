package shell;

import org.junit.Test;

public class ShellTest {

	@Test
	public void acceptsAString() {
		Shell shell = new Shell();
		shell.move("some input");
	}
	
	
	
}
