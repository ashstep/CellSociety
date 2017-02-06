package back_end.gameOfLifePack;
import java.util.ArrayList;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.ArrayLocation;
import utilities.Grid;
public class GameOfLifeSim extends Simulation{
	
	private final int[] ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	
	//repeated code for setting up Grid?
	public GameOfLifeSim(int[][] typeGrid){
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		GameOfLifeCell[][] cellGrid=new GameOfLifeCell[numRows][numCols];
		
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				cellGrid[row][col]=new GameOfLifeCell(typeGrid[row][col]);
			}
		}
		super.setArrayGrid(cellGrid);
	}
	
	
	/**
	 * updates and returns the grid. No cells move in position in this simulation
	 * @return the updated grid
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		Cell[][] newGrid=new Cell[numRows][numCols];
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				newGrid[row][col]=new GameOfLifeCell((GameOfLifeCell)getArrayGrid()[row][col]);
				newGrid[row][col].checkAndTakeAction(getNeighbors(row, col), null);
			}
		}
		setArrayGrid(newGrid);
		return new Grid(newGrid);
	}

	/**
	 * get the neighbors from the original grid
	 * top, down, left, right
	 * top-left, top-right
	 * bottom-left, bottom-right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output=new ArrayList<Cell>();	
		for(int i=0; i<ROW_OFFSET.length; i++){
			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
			if(isValidPosition(resultant_row, resultant_col)){
				output.add(super.getArrayGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
	}
	

	/**
	 * no general environment attribute in this simulation so no use
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		
	}


	@Override
	protected ArrayLocation findEmptySpots(Cell[][] grid, int currentRow, int currentCol) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SimulationInfo getSimInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
}