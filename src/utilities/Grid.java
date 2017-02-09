package utilities;
import java.util.ArrayList;
import java.util.Collection;

import back_end.Cell;
import javafx.scene.paint.Color;
/**
 * 
 * @author Ashka Stephen
 * @author Yuxiang He
 */
public abstract class Grid {

	
	private Cell[][] container;
	private Cell myInstanceCell; //for error checking? If initialized as Grid of  FireCell then cannot add FishCell
	
	
	/**
	 * keep this constructor?
	 * Bad: exposes the inner working of the class i.e. uses a Cell[][]?
	 * Good: no need for 
	 * @param cellGrid
	 */
	public Grid(Cell[][] cellGrid, Cell instanceCell){
		container=cellGrid;
		myInstanceCell=instanceCell;
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
	 * @param location
	 * @param cell
	 * @throws IllegalArgumentException
	 */
	public void setCellAt(GridLocation location, Cell cell) throws IllegalArgumentException{
			if(cell.getClass().isInstance(myInstanceCell)){
				//TODO no need to cast?
				container[location.getRow()][location.getCol()]=cell;
			} else {
				throw new IllegalArgumentException("Cell should be type: "+myInstanceCell.getClass().toString());
			}
		
	}
	
	/**
	 * returns the cell at the location specified by loc
	 * @param location
	 * @return cell at location
	 */
	public Cell getCellAt(GridLocation location){
		return container[location.getRow()][location.getCol()];
	}
	
	/**
	 * given the location specified by GridLocation, return the neighbors
	 * @return
	 */
	public abstract Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[]colOffset);
	
	/**
	 * checks whether the position specified by (row, col) is valid i.e. won't cause OutOfBoundsException.
	 * Assumes each row of myGrid has the same number of columns
	 * @param row
	 * @param col
	 * @return true if the position is valid
	 */
	protected boolean isValidPosition(int row, int col){
		return ! rowOutOfBounds(row)
				&& ! colOutOfBounds(col);
	}
	
	
	protected boolean rowOutOfBounds(int row){
		return row>=container.length || row<0;
	}
	
	protected boolean colOutOfBounds(int col){
		return col>=container[0].length || col<0;
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Color getColorAt(int x, int y){
		if(x>=0 && x<getNumRows()
				&& y>=0 && y<getNumCols()){
			return container[x][y].getColor();
		} else {
			return Color.WHITE;
		}
	}
}
