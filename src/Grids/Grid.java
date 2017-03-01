package Grids;

import java.util.Collection;
import back_end.Cell;
import javafx.scene.paint.Color;
import utilities.GridLocation;
/**
 * 
 * Abstraction of the grid object in Simulation
 * Has its own abstracted indices (only after it resizes), so that indices on this grid does not change  after resizing.
 * This feature is mainly for infinite grid, but we did not have enough time to incorporate infinite grid to our code. 
 *  
 *  This still works with normal and toroidal.
 *  
 * @author Ashka Stephen
 * @author Yuxiang He
 */

public abstract class Grid {
	private Cell[][] container;
	private int topLeftRowNum;
	private int topLeftColNum;
	private boolean hasLines;
	
	
	/**
	 * creates a grid object from a Cell[][]
	 * @param cellGrid
	 */
	public Grid(Cell[][] cellGrid){
		container=cellGrid;
		topLeftRowNum=0;
		topLeftColNum=0;
	}

	/**
	 * initializes a grid with numRows rows and numCols cols
	 * @param numRows
	 * @param numCols
	 */
	public Grid(int numRows, int numCols){
		container=new Cell[numRows][numCols];
		topLeftRowNum=0;
		topLeftColNum=0;
	}
	
	/**
	 * getter for the internal Cell[][]
	 * @return
	 */
	public Cell[][] getContainer(){
		return container;
	}

	/**
	 * 
	 * @return the top-left abstracted corner position
	 */
	public GridLocation getTLIndex(){
		return new GridLocation(topLeftRowNum, topLeftColNum);
	}

	/**
	 * @return number of rows in grid
	 */
	public int getNumRows(){
		return container.length;
	}

	/**
	 * @return number of columns in grid
	 */
	public int getNumCols(){
		return container[0].length;
	}

	/**
	 * @return total number of cells in this grid
	 */
	public int getNumCells(){
		return getNumRows()*getNumCols();
	}

	/**
	 * Set cell at abstractedLocation to be cell
	 * @param abstractedLocation
	 * @param cell
	 * @throws IllegalArgumentException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void setCellAt(GridLocation abstractedLocation, Cell cell) throws ArrayIndexOutOfBoundsException{
		GridLocation normalizedLocation=normalizeLocation(abstractedLocation);
		if(isValidAbstractedPosition(abstractedLocation)){
			container[normalizedLocation.getRow()][normalizedLocation.getCol()]=cell;
		} else if(! isValidAbstractedPosition(abstractedLocation)){
			throw new ArrayIndexOutOfBoundsException(
					String.format("Grid.setCellAt: row out of bounds: %b, Col out of bounds: %b", 
							abstractedRowOutOfBounds(abstractedLocation.getRow()), abstractedColOutOfBounds(abstractedLocation.getCol())
							)
					);}
		}
	

	/**
	 * returns the cell at the location specified by abstractedLocation
	 * @param abstractedLocation
	 * @return cell at location
	 */
	public Cell getCellAt(GridLocation abstractedLocation) throws ArrayIndexOutOfBoundsException{
		if(isValidAbstractedPosition(abstractedLocation)){
			GridLocation normalizedLocation=normalizeLocation(abstractedLocation);
			return container[normalizedLocation.getRow()][normalizedLocation.getCol()];
		} else {
			throw new ArrayIndexOutOfBoundsException(
					String.format("Grid.getCellAt: row %d out of bounds: %b, Col %d out of bounds: %b", abstractedLocation.getRow(),
							abstractedRowOutOfBounds(abstractedLocation.getRow()), abstractedLocation.getCol(),abstractedColOutOfBounds(abstractedLocation.getCol())
							));
		}	
	}

	/**
	 * checks whether the position specified by (row, col) is valid i.e. won't cause ArrayOutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param abstractedRow
	 * @param abstractedCol
	 * @return true if the position is valid
	 */
	public boolean isValidAbstractedPosition(int abstractedRow, int abstractedCol){
		return ! abstractedRowOutOfBounds(abstractedRow)
				&& ! abstractedColOutOfBounds(abstractedCol);
	}

	/**
	 * checks whether the position specified by abstractedLocation is valid i.e. won't cause ArrayOutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param abstractedLocation
	 * @return true if the position is valid
	 */
	public boolean isValidAbstractedPosition(GridLocation abstractedLocation){
		return ! abstractedRowOutOfBounds(abstractedLocation.getRow())
				&& ! abstractedColOutOfBounds(abstractedLocation.getCol());
	}

	/**
	 * @param abstractedRow
	 * @return the true row in container represented by abstractedRow
	 */
	private int normalizeRow(int abstractedRow) {
		return abstractedRow-topLeftRowNum;}

	/**
	 * @param abstractedCol
	 * @return the true col in container represented by abstractedCol
	 */
	private int normalizeCol(int abstractedCol) {
		return abstractedCol-topLeftColNum;}

	/**
	 * @param abstractedLocation
	 * @return the GridLocation corresponding to the true internal GridLocation
	 */
	protected GridLocation normalizeLocation(GridLocation abstractedLocation){
		return new GridLocation(
				normalizeRow(abstractedLocation.getRow()), 
				normalizeCol(abstractedLocation.getCol()));
	}

	/**
	 * @param abstractedRow abstracted row
	 * @return true if row gets out of bounds
	 */
	protected boolean abstractedRowOutOfBounds(int abstractedRow){
		return normalizeRow(abstractedRow)>=getNumRows() || normalizeRow(abstractedRow)<0;
	}

	/**
	 * @param abstractedCol abstracted column
	 * @return true if col gets out of bounds
	 */
	protected boolean abstractedColOutOfBounds(int abstractedCol){
		return normalizeCol(abstractedCol)>=getNumCols() || normalizeCol(abstractedCol)<0;
	}

	/**
	 * resizes the container such that invalidAbstractedLocation becomes valid
	 * @param invalidAbstractedLocation
	 */
	protected void resize(GridLocation invalidAbstractedLocation){
		int newNumRows=getNumRows(), newNumCols=getNumCols();
		if(abstractedRowOutOfBounds(invalidAbstractedLocation.getRow())){
			int invalidInternalRow=normalizeRow(invalidAbstractedLocation.getRow());
			newNumRows=invalidInternalRow<0? getNumRows()-invalidInternalRow: invalidInternalRow+1;
		}	
		if(abstractedColOutOfBounds(invalidAbstractedLocation.getCol())){
			int invalidInternalCol=normalizeCol(invalidAbstractedLocation.getCol());
			newNumCols=invalidInternalCol<0? getNumCols()-invalidInternalCol: invalidInternalCol+1;
		}
		Cell[][] newContainer=new Cell[newNumRows][newNumCols];
		GridLocation newAbstractedTLCorner=calculateNewTopLeftCorner(invalidAbstractedLocation);
		fillInResizedContainer(newContainer, newAbstractedTLCorner);
		setNewTopLeftCornerLocation( invalidAbstractedLocation);
		container=newContainer;
	}

	/**
	 * fills the newContainer with the corresponding cells
	 * @param newContainer
	 * @param newAbstractedTLCorner
	 */
	private void fillInResizedContainer(Cell[][] newContainer, GridLocation newAbstractedTLCorner) {
		int newNumRows=newContainer.length, newNumCols=newContainer[0].length;

		int oldTLRowOffset=-newAbstractedTLCorner.getRow()+topLeftRowNum;
		int oldTLColOffset=-newAbstractedTLCorner.getCol()+topLeftColNum;
		for(int internalRow=0; internalRow<newNumRows; internalRow++){
			for(int internalCol=0; internalCol<newNumCols; internalCol++){
				if(internalRow-oldTLRowOffset>=0 &&  internalRow-oldTLRowOffset<container.length
						&& internalCol-oldTLColOffset>=0 && internalCol-oldTLColOffset<container[0].length){
					newContainer[internalRow][internalCol]=container[internalRow-oldTLRowOffset][internalCol-oldTLColOffset];
				} else {
					newContainer[internalRow][internalCol]=container[0][0].makeEmptyCell();
				}
			}
		}
	}

	/**
	 * 
	 * @param invalidLocation
	 */
	private void setNewTopLeftCornerLocation(GridLocation invalidLocation){
		GridLocation newAbstractedTLCorner=calculateNewTopLeftCorner(invalidLocation);
		topLeftRowNum=newAbstractedTLCorner.getRow();
		topLeftColNum=newAbstractedTLCorner.getCol();
	}

	
	/**
	 * calculates the new top-left corner indices should the container gets resized when it needs to incorporate invalidLocation
	 * @param invalidLocation
	 * @return GridLocation specifying the new top-left corner
	 */
	private GridLocation calculateNewTopLeftCorner(GridLocation invalidLocation){
		int newTopLeftRow=topLeftRowNum>invalidLocation.getRow()? invalidLocation.getRow(): topLeftRowNum;
		int newtopLeftCol=topLeftColNum>invalidLocation.getCol()? invalidLocation.getCol(): topLeftColNum;
		return new GridLocation(newTopLeftRow, newtopLeftCol);
	}

	
	/**
	 * set the cell at location to be the next state cell.
	 * Helps front end for user setting state of cell
	 * @param location
	 */
	public void nextState(GridLocation location){
		Cell cell=getCellAt(location);
		setCellAt(location, cell.makeNextStateCell());
	}

	/**
	 * given the location specified by abstractedLocation, return the neighbors
	 * @param flag signals which neighbors (cardinal, or all) to be collected
	 * @return Collection of neighbors
	 */
	public abstract Collection<Cell> getNeighbors(GridLocation abstractedLocation, int flag);
	
	/**
	 * given the location specified by abstractedLocation, return the neighbors
	 * Overloads getNeighbors(). Now can customize which neighbors to collect though rowOffset and colOffset array
	 * 
	 * @param int[] rowOffset
	 * @param int[] colOffset
	 * @return Collection of neighbors
	 */
	public abstract Collection<Cell> getNeighbors(GridLocation abstractedLocation, int[] rowOffset, int[] colOffset);
	
	
	/**
	 * given the location specified by abstractedLocation, return the neighbors of a specified type
	 * @param flag signals which neighbors (cardinal, or all) to be collected
	 * @return Collection of neighbors' locations
	 */
	public abstract Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag);
	
	/**
	 * 
	 * @param flag cardinal or all neighbors
	 * @return rowOffsetArray for cardinal or all neighbors, depending on flag
	 */
	protected abstract int[] getRowOffsetArray(int flag);
	
	/**
	 * 
	 * @param flag cardinal or all neighbors
	 * @return colOffsetArray for cardinal or all neighbors, depending on flag
	 */
	protected abstract int[] getColOffsetArray(int flag);

	/**
	 * 
	 * @param x
	 * @param y
	 * @return color of the cell at position [x, y]
	 */
	public Color getColorAt(int x, int y){
		if(isValidAbstractedPosition(x, y)){
			return container[x][y].getColor();
		} else {
			return Color.WHITE;
		}
	}


	/**
	 * 
	 * @return collection of string each specifies each tupe of cell in this grid
	 */
	public Collection<String> getCellTypes()
	{
		return container[0][0].getTypeNames();
	}

	/**
	 * 
	 * @param x specifies the type of the cell
	 * @return total number of the cell in this grid
	 */
	public Number getCountByType(String x)
	{
		int counter = 0;
		for (int i = 0; i < getNumRows(); i++)
		{
			for (int j = 0; j < getNumCols(); j++)
			{
				if (container[i][j].getTypeName().equals(x))
				{
					counter++;
				}
			}
		}
		return counter;
	}
	
	
	/**
	 * sets whether the cells on the front end has border lines
	 * @param inLines true if  the cells on the front end has border lines
	 */
	public void setLines(boolean inLines)
	{
		hasLines = inLines;
	}
	
	
	/**
	 * 
	 * @return true if  the cells on the front end has border lines
	 */
	public boolean hasLines()
	{
		return hasLines;
	}
}