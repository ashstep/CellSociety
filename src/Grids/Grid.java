package Grids;
import java.util.Collection;

import back_end.Cell;
import javafx.scene.paint.Color;
import utilities.GridLocation;
/**
 * 
 * @author Ashka Stephen
 * @author Yuxiang He
 */
public abstract class Grid {

	
	private Cell[][] container;
	private Cell myInstanceCell; //for error checking? If initialized as Grid of  FireCell then cannot add FishCell
	//make sure it is an empty type cell, useful for filling in the container after it resizes
	private int topLeftRowNum;
	private int topLeftColNum;
	
	/**
	 * keep this constructor?
	 * Bad: exposes the inner working of the class i.e. uses a Cell[][]?
	 * Good: no need for messy coordinate system
	 * @param cellGrid
	 */
	public Grid(Cell[][] cellGrid, Cell instanceCell){
		container=cellGrid;
		myInstanceCell=instanceCell;
		topLeftRowNum=0;
		topLeftColNum=0;
	}
	
	
	public Grid(int numRows, int numCols, Cell instanceCell){
		container=new Cell[numRows][numCols];
		myInstanceCell=instanceCell;
	}
	/**
	 * 
	 * @return container
	 */
	protected Cell[][] getContainer(){
		return container;
	}
	
	/**
	 * 
	 * @return myInstanceCell
	 */
	protected Cell getInstanceCell(){
		return myInstanceCell;
	}
	
	/**
	 * 
	 * @return number of rows in grid
	 */
	public int getNumRows(){
		return container.length;
	}

	/**
	 * 
	 * @return number of columns in grid
	 */
	public int getNumCols(){
		return container[0].length;
	}
	
	/**
	 * 
	 * @return total number of cells in this grid
	 */
	public int getNumCells(){
		return getNumRows()*getNumCols();
	}

	/**
	 * 
	 * @param abstractedLocation
	 * @param cell
	 * @throws IllegalArgumentException
	 */
	public void setCellAt(GridLocation abstractedLocation, Cell cell) throws IllegalArgumentException, ArrayIndexOutOfBoundsException{
		GridLocation normalizedLocation=normalizeLocation(abstractedLocation);
			if(cell.getClass().isInstance(myInstanceCell) && isValidPosition(normalizedLocation.getRow(), normalizedLocation.getCol())){
				//TODO no need to cast?
				container[normalizedLocation.getRow()][normalizedLocation.getCol()]=cell;
			} else if(! isValidPosition(abstractedLocation.getRow(), abstractedLocation.getCol())){
				throw new ArrayIndexOutOfBoundsException(
						String.format("Row out of bounds: %b, Col out of bounds: %b", 
								rowOutOfBounds(abstractedLocation.getRow()), colOutOfBounds(abstractedLocation.getCol())
								)
						);
			} else {
				throw new IllegalArgumentException("Cell should be type/brother of the type: "+myInstanceCell.getClass().toString());
			}
	}
	
	/**
	 * returns the cell at the location specified by abstractedLocation
	 * @param abstractedLocation
	 * @return cell at location
	 */
	public Cell getCellAt(GridLocation abstractedLocation){
		GridLocation transformedLocation=normalizeLocation(abstractedLocation);
		return container[transformedLocation.getRow()][transformedLocation.getCol()];
	}
	
	/**
	 * given the location specified by abstractedLocation, return the neighbors
	 * @return
	 */
	public abstract Collection<Cell> getNeighbors(GridLocation abstractedLocation, int[] rowOffset, int[]colOffset);
	
	/**
	 * checks whether the position specified by (row, col) is valid i.e. won't cause OutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param abstractedRow
	 * @param abstractedCol
	 * @return true if the position is valid
	 */
	protected boolean isValidPosition(int abstractedRow, int abstractedCol){
		return ! rowOutOfBounds(abstractedRow)
				&& ! colOutOfBounds(abstractedCol);
	}
	
	/**
	 * 
	 * @param abstractedRow
	 * @return the true row in container represented by abstractedRow
	 */
	private int normalizeRow(int abstractedRow) {
		return abstractedRow-topLeftRowNum;
	}
	
	/**
	 * 
	 * @param abstractedCol
	 * @return the true col in container represented by abstractedCol
	 */
	private int normalizeCol(int abstractedCol) {
		return abstractedCol-topLeftColNum;
	}
	
	/**
	 * 
	 * @param abstractedLocation
	 * @return the GridLocation corresponding to the true internal GridLocation
	 */
	private GridLocation normalizeLocation(GridLocation abstractedLocation){
		return new GridLocation(
				normalizeRow(abstractedLocation.getRow()), 
				normalizeCol(abstractedLocation.getCol())
				);
	}
	
	/**
	 * 
	 * @param abstractedRow abstracted row
	 * @return true if row gets out of bounds
	 */
	protected boolean rowOutOfBounds(int abstractedRow){
		return normalizeRow(abstractedRow)>=getNumRows() || normalizeRow(abstractedRow)<0;
	}
	
	/**
	 * 
	 * @param abstractedCol abstracted column
	 * @return true if col gets out of bounds
	 */
	protected boolean colOutOfBounds(int abstractedCol){
		return normalizeCol(abstractedCol)>=getNumCols() || normalizeCol(abstractedCol)<0;
	}

	
	/**
	 * resizes the container
	 * @param invalidAbstractedLocation
	 */

	protected void resize(GridLocation invalidAbstractedLocation){
		int newNumRows=getNumRows(), newNumCols=getNumCols();
		
		if(rowOutOfBounds(invalidAbstractedLocation.getRow())){
			int invalidInternalRow=normalizeRow(invalidAbstractedLocation.getRow());
			newNumRows=invalidInternalRow<0? getNumRows()-invalidInternalRow: invalidInternalRow;
		}
		
		if(colOutOfBounds(invalidAbstractedLocation.getCol())){
			int invalidInternalCol=normalizeCol(invalidAbstractedLocation.getCol());
			newNumCols=invalidInternalCol<0? getNumCols()-invalidInternalCol: invalidInternalCol;
		}
		Cell[][] newContainer=new Cell[newNumRows][newNumCols];
//		Disaster in terms of coordinate transformation!
		for(int row=0; row<newNumRows; row++){
			for(int col=0; col<newNumCols; col++){
				if(isValidPosition(row, col)){
					newContainer[row][col]=container[row][col];
				} else {
					newContainer[row][col]=myInstanceCell.makeEmptyCell();
				}
			}
		}
		System.out.printf("Grid line 160: resizing grid to %d rows and %d cols", newNumRows, newNumCols);
		container=newContainer;
		setNewTopLeftCornerLocation( invalidAbstractedLocation);
	}
	
	
	
	/**
	 * 
	 * @param invalidLocation
	 */
	private void setNewTopLeftCornerLocation(GridLocation invalidLocation){
		topLeftRowNum=topLeftRowNum>invalidLocation.getRow()? invalidLocation.getRow(): topLeftRowNum;
		topLeftColNum=topLeftColNum>invalidLocation.getCol()? invalidLocation.getCol(): topLeftColNum;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Color getColorAt(int x, int y){
		if(isValidPosition(x, y)){
			return container[x][y].getColor();
		} else {
			return Color.WHITE;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<String> getCellTypes()
	{
		return container[0][0].getTypeNames();
	}

	/**
	 * 
	 * @param x
	 * @return
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
}