package shell;

import java.util.Scanner;

import board.Board;

public class Runner {

	Scanner scan = new Scanner(System.in);
	Shell shell;
	
	public Runner(Shell shell) {
		this.shell = shell;
	}
	
	public void run() {
		System.out.println(shell.board.gridAsString());
		while(shell.getWinnerMessage().equals("none yet")) {
			tick();
		}
		System.out.println(shell.getWinnerMessage());
	}

	private void tick() {
		outputPlayerInstructions();
		String input = askPlayerForInput();
		String response = shell.tryMove(input);
		System.out.println(response);
		
	}

	private void outputPlayerInstructions() {
		if(shell.board.movesSoFar() % 2 == 0)
			System.out.println("Black's turn");
		else
			System.out.println("White's turn");

	}

	private String askPlayerForInput() {
		return scan.nextLine();
	}
	
}
