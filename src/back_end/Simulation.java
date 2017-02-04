package back_end;
import java.util.ArrayList;

import utilities.Grid;
import utilities.GridLocation;
public abstract class Simulation{
	
	private Cell[][] myGrid;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 */
	public abstract Grid updateGrid();
	
	
	/**
	 * 
	 * @param row the row of the current cell
	 * @param col the col of the current cell
	 * @return the Cell neighbors of the current cell
	 */
	protected abstract ArrayList<Cell> getNeighbors(int row, int col);
	
	/**
	 * generates a [row, column] pair such that newGrid[row][column] is empty for putting a new cell
	 * AND (row, column) is not its current position
	 * 
	 * simulates a cell moving to somewhere else
	 * @return int[]. 0 position is row,1 position is column
	 */
	//TODO rename move to findEmptySpot
	protected abstract GridLocation move(Cell[][] newGrid, int currentRow, int currentCol);
	
	
	
	
	/**
	 * getter method
	 * @return Grid containing cell info
	 */
	public Grid getGrid(){
		return new Grid(myGrid);
	}
	
	
	/**
	 * getter method
	 * @return myGrid
	 */
	protected Cell[][] getArrayGrid(){
		return myGrid;
	}
	
	/**
	 * setter method for myGrid
	 */
	protected void setArrayGrid(Cell[][] newGrid){
		myGrid=newGrid;
	}
	
	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract void setSimInfo(SimulationInfo newInfo);

	
	/**
	 * 
	 * @return number of rows in myGrid
	 */
	public int getNumRows(){
		return myGrid.length;
	}
	
	/**
	 * 
	 * @return number of columns in myGrid
	 */
	public int getNumCols(){
		return myGrid[0].length;
	}
	
	/**
	 * checks whether the position specified by (row, col) is valid i.e. won't cause OutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param row
	 * @param col
	 * @return true if the position is valid
	 */
	//added this util method. helpful for all subclass simulations as they need to check for boundaries
	protected boolean isValidPosition(int row, int col){
		return row < myGrid.length && row>=0 
				&& col<myGrid[0].length &&     col>=0;
	}
}
