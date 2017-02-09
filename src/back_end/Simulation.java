package back_end;

import utilities.Grid;
import utilities.GridLocation;
public abstract class Simulation{
	
	private Grid myGrid;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 */
	public abstract Grid updateGrid();
	
	
//	/**
//	 * 
//	 * @param row the row of the current cell
//	 * @param col the col of the current cell
//	 * @return the Cell neighbors of the current cell
//	 */
//	protected abstract ArrayList<Cell> getNeighbors(int row, int col);
	
	/**
	 * generates a [row, column] pair such that newGrid[row][column] is empty for putting a new cell
	 * AND (row, column) is not its current position
	 * 
	 * simulates a cell moving to somewhere else
	 * @return int[]. 0 position is row,1 position is column
	 */
	protected abstract GridLocation findEmptySpots(Cell[][] grid, int currentRow, int currentCol);
	
	/**
	 * getter method
	 * @return Grid containing cell info
	 */
	public Grid getGrid(){
		return myGrid;
	}
	
	/**
	 * setter method for myGrid
	 */
	protected void setGrid(Grid grid){
		myGrid=grid;
	}
	
	
//	/**
//	 * getter method
//	 * @return myGrid
//	 */
//	protected Cell[][] getArrayGrid(){
//		return myGrid;
//	}
//	
//	/**
//	 * setter method for myGrid
//	 */
//	protected void setArrayGrid(Cell[][] newGrid){
//		myGrid=newGrid;
//	}
	

	
	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract void setSimInfo(SimulationInfo newInfo);
	
	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract SimulationInfo getSimInfo();

	
	/**
	 * 
	 * @return number of rows in myGrid
	 */
	public int getNumRows(){
		return myGrid.getNumRows();
	}
	
	/**
	 * 
	 * @return number of columns in myGrid
	 */
	public int getNumCols(){
		return myGrid.getNumCols();
	}
	
	
	
	
	
}
