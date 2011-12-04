package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import board.Board;

public class Evaluator {

	/*****************************************
	 * Takes a board and evaluates the state
	 * of the board, and returns a numeric 
	 * value that reflects that state.
	 * 
	 * First Revision:
	 *   Simply adds all values of the pieces
	 *    - 5 for regular pieces
	 *    - 10 for kings
	 * @param thinger
	 * @return
	 */
	public int evaluate(Struct thinger) {
		int valBlackP = 0, valBlackK = 0, valWhiteP = 0, valWhiteK = 0;
		if(thinger != null){
			for(int i = 0; i < thinger.currBoard.grid.length; i++){
				for(int j = 0; j < thinger.currBoard.grid[0].length; j++){
					if(thinger.currBoard.grid[i][j] == 'B'){
						valBlackK += 2;
						if(i==0 || j == 7 || i == 7 || j == 0){
							valBlackK += 10;
						}else{
							if((thinger.currBoard.grid[i+1][j+1] == 'w' && thinger.currBoard.grid[i-1][j-1] == ' ' )|| 
								(thinger.currBoard.grid[i+1][j+1] == 'W' && thinger.currBoard.grid[i-1][j-1] == ' ') || 
									(thinger.currBoard.grid[i+1][j-1] == 'w' && thinger.currBoard.grid[i-1][j+1] == ' ') ||
									(thinger.currBoard.grid[i+1][j-1] == 'W' && thinger.currBoard.grid[i-1][j+1] == ' ') || 
									(thinger.currBoard.grid[i-1][j+1] == 'W' && thinger.currBoard.grid[i+1][j-1] == ' ') || 
									(thinger.currBoard.grid[i-1][j-1] == 'W' && thinger.currBoard.grid[i+1][j+1] == ' ')){
								valBlackK -=10;
							}
							else{
								valBlackK +=10;
							}
						}
					}
					else if(thinger.currBoard.grid[i][j] == 'b'){
						valBlackP += 1;
						if(i==0 || j == 7 || i == 7 || j == 0){
							valBlackP += 5;
						}else{
							if((thinger.currBoard.grid[i+1][j+1] == 'w' && thinger.currBoard.grid[i-1][j-1] == ' ' )|| 
								(thinger.currBoard.grid[i+1][j+1] == 'W' && thinger.currBoard.grid[i-1][j-1] == ' ') || 
									(thinger.currBoard.grid[i+1][j-1] == 'w' && thinger.currBoard.grid[i-1][j+1] == ' ') ||
									(thinger.currBoard.grid[i+1][j-1] == 'W' && thinger.currBoard.grid[i-1][j+1] == ' ') || 
									(thinger.currBoard.grid[i-1][j+1] == 'W' && thinger.currBoard.grid[i+1][j-1] == ' ') || 
									(thinger.currBoard.grid[i-1][j-1] == 'W' && thinger.currBoard.grid[i+1][j+1] == ' ')){
								valBlackP -= 5;
							}
							else{
								valBlackP +=5;
							}
						}
					}
					else if(thinger.currBoard.grid[i][j] == 'W'){
						valWhiteK += 2;
						if(i==0 || j == 7 || i == 7 || j == 0){
							valWhiteK += 10;
						}else{
							if((thinger.currBoard.grid[i-1][j-1] == 'w' && thinger.currBoard.grid[i+1][j+1] == ' ' )|| 
								(thinger.currBoard.grid[i-1][j-1] == 'W' && thinger.currBoard.grid[i+1][j+1] == ' ') || 
									(thinger.currBoard.grid[i-1][j+1] == 'w' && thinger.currBoard.grid[i+1][j-1] == ' ') ||
									(thinger.currBoard.grid[i-1][j+1] == 'W' && thinger.currBoard.grid[i+1][j-1] == ' ') || 
									(thinger.currBoard.grid[i+1][j-1] == 'W' && thinger.currBoard.grid[i-1][j+1] == ' ') || 
									(thinger.currBoard.grid[i+1][j+1] == 'W' && thinger.currBoard.grid[i-1][j-1] == ' ')){
								valWhiteK -= 10;
							}
							else{
								valWhiteK += 10;
							}
						}
					}
					else if(thinger.currBoard.grid[i][j] == 'w'){
						valWhiteP += 1;
						if(i==0 || j == 7 || i == 7 || j == 0){
							valWhiteP += 5;
						}else{
							if((thinger.currBoard.grid[i-1][j-1] == 'w' && thinger.currBoard.grid[i+1][j+1] == ' ' )|| 
								(thinger.currBoard.grid[i-1][j-1] == 'W' && thinger.currBoard.grid[i+1][j+1] == ' ') || 
									(thinger.currBoard.grid[i-1][j+1] == 'w' && thinger.currBoard.grid[i+1][j-1] == ' ') ||
									(thinger.currBoard.grid[i-1][j+1] == 'W' && thinger.currBoard.grid[i+1][j-1] == ' ') || 
									(thinger.currBoard.grid[i+1][j-1] == 'W' && thinger.currBoard.grid[i-1][j+1] == ' ') || 
									(thinger.currBoard.grid[i+1][j+1] == 'W' && thinger.currBoard.grid[i-1][j-1] == ' ')){
								valWhiteP -= 5;
							}
							else{
								valWhiteP +=5;
							}
						}
					}
					else{ }
				}
			}
		

		
			int value = 0, valBlack = 0, valWhite = 0;
			//if(valBlackP+(valBlackK)/2 > 5 || valWhiteP+(valWhiteK)/2 > 5){
				valBlack = (valBlackP+3*valBlackK)/2;
				valWhite = (valWhiteP+3*valWhiteK)/2;
        
				value += (valBlack-valWhite)*Math.pow(27, 2);
		//	}else{
				//Random r = new Random();
				//value = r.nextInt(100);
			//}
			return value;
        }
        return 0;
	}

	private boolean isJumpable() {
		// TODO Auto-generated method stub
		return false;
	}

}

class Struct{

	Board currBoard;
	ArrayList<Integer[]> move;
	Struct prev;
	
	public Struct(Board passedBoard, ArrayList<Integer[]> move, Struct parent) {
		this.currBoard = passedBoard;
		this.move= move;
		this.prev = parent;
	}
	
	
}