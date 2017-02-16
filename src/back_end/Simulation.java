package back_end;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.Grid;
import Grids.HexagonalGrids.*;
import Grids.RectangleGrids.*;
import Grids.TriangularGrid.*;
import utilities.GridLocation;
/**
 * Abstract class that captures the concept of a simulation. 
 * Contains the Grid of cells, and updates the cell when the API updateCells() is called. 
 * 
 * 
 * @author Yuxiang He
 *@author Ashka Stephen
 *@author Juan Philippe
 */
public abstract class Simulation
{
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

	
	/**
	 * creates a Grid object filled with the cell types specified by cellArray
	 * @param cellArray
	 * @return
	 */
	public Grid createCellGrid(Cell[][] cellArray)
	{
		Constructor<? extends Grid> constructor = null;
		try {
			constructor = myCellGrid.getClass().getConstructor(Cell[][].class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new Error("No such constructor");
		}
		try {
			return constructor.newInstance(cellArray);
		} catch (Exception e) {
			throw new Error("No such constructor");
		}
	}
	
	/**
	 * Creates a copy of a Cell[][] array, with each cell in the returned array also a copy of the corresponding original one. 
	 * Used for updateGrid in sub-classes. Since the new, updated grid needs information about its current state, 
	 * the array copied by this method provides a 'snapshot' of the previous-iteration state while the myGrid is being updated. 
	 * @param oldArray
	 * @return copiedArray a copy of old array
	 * @throws Error when there is no such constructor found for the cell. Not possible in our current code.
	 */
	protected Cell[][] deepCopyCellArray(Cell[][] oldArray){
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		Cell[][] copiedArray = new Cell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				Constructor<? extends Cell> constructor = null;
				try {
					constructor = oldArray[row][col].getClass().getConstructor(oldArray[row][col].getClass());
				} catch (NoSuchMethodException | SecurityException e) {
					throw new Error("No available constructor");
				}
				try {
					Cell oldLocation = oldArray[row][col];
					copiedArray[row][col] = constructor.newInstance(oldLocation);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new Error("No available constructor");
				}
			}
		}
		return copiedArray;
	}
	
	/**
	 * generates a GridLocation such that myGrid.getCellAt(GridLocation) is empty for putting a new cell
	 * AND GridLocation is not its current position
	 * simulates a cell moving to somewhere else
	 * @return GridLocation specifies a new empty location in myGrid
	 */
	protected abstract GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol);
	
	
	/**
	 * getter method
	 * @return Grid containing cell info
	 */
	public Grid getCellGrid(){
		return myCellGrid;
	}
	
	/**
	 * getter method
	 * @return Grid containing ground info
	 */
	public Grid getGroundGrid(){
		return myGroundGrid;
	}
	
	
	/**
	 * setter method for myCellGrid
	 */
	protected void setCellGrid(Grid grid){
		myCellGrid=grid;
	}

	/**
	 * setter method for  myGroundGrid
	 */
	protected void setGroundGrid(Grid groundGrid){
		myGroundGrid = groundGrid;
	}
	

    /**
	 * creates a new grid with the defined configuration (whose origin lies in simulation builder) in terms of bounds, shapes  and celltype
	 * @param gridBoundsType
	 * @param shapeType
	 * @param cellArray
	 */
	protected void makeGrid(String gridBoundsType, String shapeType, Cell[][] cellArray)
	{
		if (gridBoundsType.equals("Toroidal") && shapeType.equals("Rectangular"))
			setCellGrid(new RectangleToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Rectangular"))
			setCellGrid(new RectangleFiniteGrid(cellArray));
		else if (gridBoundsType.equals("Infinite") && shapeType.equals("Rectangular"))
			setCellGrid(new RectangleInfiniteGrid(cellArray));
		
		else if (gridBoundsType.equals("Toroidal") && shapeType.equals("Triangular"))
			setCellGrid(new TriangularToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Triangular"))
			setCellGrid(new TriangularFiniteGrid(cellArray));
		else if (gridBoundsType.equals("Toroidal") && shapeType.equals("Hexagonal"))
			setCellGrid(new HexToroidalGrid(cellArray));
		else if (gridBoundsType.equals("Finite") && shapeType.equals("Hexagonal"))
			setCellGrid(new HexFiniteGrid(cellArray));
		else
			throw new Error("Incorrect Grid Type");
	}
	

	/**
	 * setter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract void setSimInfo(SimulationInfo newInfo);
	
	/**
	 * getter method. Sets sim's myInfo to newInfo
	 * @param newInfo
	 */
	public abstract SimulationInfo getSimInfo();

	
	/**
	 * 
	 * @return number of rows in myCellGrid
	 */
	public int getNumRows(){
		return myCellGrid.getNumRows();
	}
	
	/**
	 * 
	 * @return number of rows in myGroundGrid
	 * used for ground and regular grid
	 */
	public int getNumRowsGround(){
		return myGroundGrid.getNumRows();
	}
	
	/**
	 * @return number of columns in myCellGrid
	 */
	public int getNumCols(){
		return myCellGrid.getNumCols();
	}
	
	/**
	 * @return number of columns in myGroundGrid
	 */
	public int getNumColsGround(){
		return myGroundGrid.getNumCols();
	}
	
	/**
	 * sets whether there are lines surronding the cells
	 * @param linesOn
	 */
	public void setLines(boolean linesOn)
	{
		myCellGrid.setLines(linesOn);
	}

	/**
	 * 
	 * @return
	 */
	public abstract ArrayList<String> getParameterList();
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public abstract Consumer<Number> getChangeMethod(String x);
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public abstract double getSliderLowerBound(String x);
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public abstract double getSliderUpperBound(String x);
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public abstract double getCurrentValue(String x);
}