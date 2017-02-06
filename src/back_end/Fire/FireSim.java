package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.Grid;
/**
 * Class that implements the unique properties of the fire simulation
 * @author Ashka Stephen
 *
 */

public class FireSim extends Simulation {

	private final int emptyCell = 0;
	private final int[] ROW_OFFSET = {-1, 1, 0, 0};
	private final int[] COL_OFFSET = {0, 0,-1, 1};
	private FireSimInfo myInfo;

	/**
	 * Constructor
	 * @param probablilty of the tree catching on fire and a int[][] that holds the location 
	 * of each fire cell
	 */
	public FireSim(int[][] typeGrid, double probCatch){
		myInfo = new FireSimInfo(probCatch);

		int numRows = typeGrid.length + 2 ;
		int numCols = typeGrid[0].length + 2 ;
		FireCell[][] cellGrid = new FireCell[numRows][numCols];
		
		for(int row = 1; row < numRows-1 ; row++){
			for(int col = 1 ; col < numCols-1 ; col++){
				cellGrid[row][col]=new FireCell(typeGrid[row][col]);
			}
		}
		
		super.setGrid(cellGrid);

	}
	
	//////NEW STUFF ADDEDED fix

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		myInfo=(SegregationSimInfo) newInfo;
		if(newInfo instanceof SegregationSimInfo){
			myInfo = (SegregationSimInfo) newInfo;
		} else {
			throw new Error("newInfo must be SegregationSimInfo");
		}
	}

	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}
	////////////////////////////////////////////


	/**
	 * Creates a new grid which stores updated values of the cells based on interactions
	 */
	@Override
	public Grid updateGrid() {
		int numRows=super.getNumRows(), numCols=super.getNumRows();
		Cell[][] newGrid=new Cell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				FireCell add = new FireCell((FireCell) getArrayGrid()[row][col]);
				newGrid[row][col] = add;
				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), myInfo);
			}
		}
		setArrayGrid(newGrid);
		return new Grid(newGrid);
	}

	/**
	 * Getting the neighbors 
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output = new ArrayList<Cell>();	
		for(int i = 0; i< ROW_OFFSET.length; i++){
			int finalRow = row + ROW_OFFSET[i], finalCol = col+COL_OFFSET[i];
			
			if(super.isValidPosition(finalRow, finalCol)){
				output.add(super.getGrid()[finalRow][finalCol]);
			}
		}
		return output;
	}

	@Override
	protected int[] move(Cell[][] newGrid, int oldRow, int oldCol,FireCell cell) {
		newGrid[row][col] = new FireCell(emptyCell);
		newGrid[newPos.getRow()][newPos.getCol()] = new SegregationCell(cell.getMyType());
	}
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		// TODO Auto-generated method stub
	}

}
