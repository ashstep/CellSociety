package back_end;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;
import utilities.Grid;
import utilities.GridLocation;

public abstract class Simulation{
	
	private final int[] RECTANGULAR_ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] RECTANGULAR_COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	private Grid myGrid;
	
	/**
	 * update the grid based on the cells' current state
	 * @return the updated myGrid
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
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
	
	
	protected int[] getRectangularColOffset(){
		return RECTANGULAR_COL_OFFSET;
	}
	
	
	protected int[] getRectangularRowOffset(){
		return RECTANGULAR_ROW_OFFSET;
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
	

	public abstract ArrayList<String> getParameterList();
	public abstract Consumer<Number> getChangeMethod(String x);
	public abstract double getSliderLowerBound(String x);
	public abstract double getSliderUpperBound(String x);
	public abstract double getCurrentValue(String x);


	public Grid createGrid(Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myGrid.getClass().getConstructor(int.class, int.class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return constructor.newInstance(getNumRows(), getNumCols(), cellType);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}