package back_end.gameOfLifePack;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;

public class GameOfLifeSim extends Simulation{
	
	private final int[] ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	
	
	@Override
	public void updateGrid() {
		Cell[][] newGrid=new Cell[getGrid().length][getGrid()[0].length];
		for(int row=0; row<getGrid().length; row++){
			for(int col=0; col<getGrid()[0].length; col++){
				newGrid[row][col]=getGrid()[row][col];
				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), getSimInfo());
			}
		}
		setGrid(newGrid);
	}

	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(isValidPosition(resultant_row, resultant_col)){
				output.add(getGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
	}
	

	@Override
	protected int[] move(Cell[][] newGrid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		//TODO Auto-generated method stub
	}
	
}
