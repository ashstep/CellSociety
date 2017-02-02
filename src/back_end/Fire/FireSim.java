package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
/**
 * Class that implements the unique properties of the fire simulation
 * @author Ashka Stephen
 *
 */

public class FireSim extends Simulation {

	private final int[] ROW_OFFSET = {-1, 1, 0, 0};
	private final int[] COL_OFFSET = {0, 0,-1, 1};
	private FireSimInfo myInfo;
	
	/**
	 * Constructor
	 */
	public FireSim(int[][] typeGrid){
		int numRows = typeGrid.length + 2;
		int numCols = typeGrid[0].length + 2;
		FireCell[][] cellGrid = new FireCell[numRows][numCols];
		for(int row = 0; row<numRows; row++){
			for(int col = 0; col<numCols; col++){
				cellGrid[row][col] = new FireCell(typeGrid[row][col]);
			}
		}
		super.setGrid(cellGrid);
	}

	/**
	 * Update the grid by creating copies of existing cells
	 */
	@Override
	public Cell[][] updateGrid() {
		Cell[][] newGrid=new Cell[getGrid().length][getGrid()[0].length];
		for(int row=0; row<getGrid().length; row++){
			for(int col=0; col<getGrid()[0].length; col++){
				FireCell add = new FireCell((FireCell) getGrid()[row][col]);
				newGrid[row][col] = add;
				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), myInfo);
			}
		}
		setGrid(newGrid);
		return newGrid;
	}
	
	/**
	 * Getting the neighbors 
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i = 0; i<ROW_OFFSET.length; i++){
			int resultant_row = row+ROW_OFFSET[i], resultant_col = col+COL_OFFSET[i];
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
		// TODO Auto-generated method stub
	}

}
