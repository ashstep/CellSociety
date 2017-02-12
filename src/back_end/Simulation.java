package back_end;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.Grid;
import utilities.GridLocation;

public abstract class Simulation{
	//TODO: implement changeToNextType();
	//made abstract
	private Grid myCellGrid;
	private Grid myGroundGrid;
	
	
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
	
	
	public Grid createGrid(Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myCellGrid.getClass().getConstructor(int.class, int.class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return constructor.newInstance(getNumRows(), getNumCols(), cellType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grid createGrid(Grid grid, Cell[][] cellArray, Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = grid.getClass().getConstructor(Cell[][].class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return constructor.newInstance(cellArray, cellType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Deprecated
	public Grid createGroundGrid(Cell[][] cellArray, Cell cellType)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myGroundGrid.getClass().getConstructor(Cell[][].class ,Cell.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return constructor.newInstance(cellArray, cellType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param oldArray
	 * @return copiedArray a copy of old array, with each cell in copiedArray a new copy of the corresponding cell
	 */
	protected Cell[][] deepCopyCellArray(Cell[][] oldArray) {
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		Cell[][] copiedArray = new Cell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				Constructor<? extends Cell> constructor = null;
				try {
					constructor = oldArray[0][0].getClass().getConstructor(oldArray[0][0].getClass());
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					copiedArray[row][col] = constructor.newInstance(oldArray[row][col]);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return copiedArray;
	}
	
	/**
	 * generates a GridLocation such that newGrid[GridLocation] is empty for putting a new cell
	 * AND (row, column) is not its current position
	 * 
	 * simulates a cell moving to somewhere else
	 * @return GridLocation
	 */
	protected abstract GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol);
	
	/**
	 * getter method
	 * @return Grid containing cell info
	 */
	public Grid getCellGrid(){
		return myCellGrid;
	}
	public Grid getGroundGrid(){
		return myGroundGrid;
	}
	
	
	/**
	 * setter method for myGrid and myGroundGrid
	 */
	protected void setGrid(Grid grid){
		myCellGrid=grid;
	}
	
	protected void setGroundGrid(Grid groundGrid){
		myGroundGrid = groundGrid;
	}
	
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
	 * same for both ground and regular grid
	 */
	public int getNumRows(){
		return myCellGrid.getNumRows();
	}
	
	public int getNumRowsGround(){
		return myGroundGrid.getNumRows();
	}
	
	/**
	 * 
	 * @return number of columns in myGrid
	 */
	public int getNumCols(){
		return myCellGrid.getNumCols();
	}
	public int getNumColsGround(){
		return myGroundGrid.getNumCols();
	}

	public abstract ArrayList<String> getParameterList();
	public abstract Consumer<Number> getChangeMethod(String x);
	public abstract double getSliderLowerBound(String x);
	public abstract double getSliderUpperBound(String x);
	public abstract double getCurrentValue(String x);
}