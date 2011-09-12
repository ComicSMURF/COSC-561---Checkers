import board.Board;
import board.MoveChecker;
import ai.CoordConventionMapper;
import shell.Runner;
import shell.Shell;
import shell.aiMoveGetter;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Runner runner = new Runner(new Shell(new Board(), new aiMoveGetter(), new CoordConventionMapper(), new MoveChecker()));
		runner.run();
		
	}

}
