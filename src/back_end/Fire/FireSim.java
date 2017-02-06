package back_end.Fire;

import java.util.ArrayList;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.ArrayLocation;
import utilities.Grid;
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
	 * @param probablilty of the tree catching on fire and a int[][] that holds the location 
	 * of each fire cell
	 */
	public FireSim(int[][] typeGrid, double probCatch){
		myInfo = new FireSimInfo(probCatch);

		int numRows = typeGrid.length ;
		int numCols = typeGrid[0].length ;
		FireCell[][] cellGrid = new FireCell[numRows][numCols];
		//start at 1 ends at  numRows-1
		//typeGrid[row-1][col-1]
		for(int row = 0; row < numRows ; row++){
			for(int col = 0 ; col < numCols; col++){
				cellGrid[row][col]=new FireCell(typeGrid[row][col]);
			}
		}
		super.setArrayGrid(cellGrid);
	}


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
				System.out.println("update grid was called");
				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), myInfo);
			}
		}

//		
//		int totalRows = super.getNumRows();
//		int totalCol = super.getNumCols();
//		
//		Cell[][] gridWithoutBorders = new Cell[totalRows][totalCol];
//		for(int row=0; row<numRows; row++){
//			for(int col=0; col<numCols; col++){
//				FireCell add = new FireCell((FireCell) getArrayGrid()[row][col]);
//				newGrid[row][col] = add;
//				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), myInfo);
//			}
//		}
//
//		

		setArrayGrid(newGrid);
		return new Grid(newGrid);
	}
	
	
	//returns without the border from update grid
	public Grid withoutBorder() {
		return null;
	}
	
	

	/**
	 * Getting the neighbors 
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output = new ArrayList<Cell>();	
		for(int i = 0; i< ROW_OFFSET.length; i++){
			int finalRow = row + ROW_OFFSET[i];
			int finalCol = col+COL_OFFSET[i];
			if(super.isValidPosition(finalRow, finalCol)){
				output.add(super.getArrayGrid()[finalRow][finalCol]);
			}
		}
		return output;
	}


	@Override
	protected ArrayLocation findEmptySpots(Cell[][] newgrid, int currentRow, int currentCol) {
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		if(newInfo instanceof FireSimInfo){
			myInfo = (FireSimInfo) newInfo;
		} else {
			throw new Error("Information is not valid");
		}
	}

	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}


}
