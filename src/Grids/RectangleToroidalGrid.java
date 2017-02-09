package Grids;

import java.util.ArrayList;
import java.util.Collection;

import back_end.Cell;

/**
 * 
 * @author Yuxiang He
 *
 */
public class RectangleToroidalGrid extends RectangleGrid {
	/**
	 * 
	 * @param cellGrid
	 * @param instanceCell an instance of the super class of all the cells in this simulation, used for error checking
	 */
	public RectangleToroidalGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}
	
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public RectangleToroidalGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}
	

	/**
	 * collects the neighbors surrounding the cell at location
	 * @param rowOffset
	 * @param colOffset
	 */
	@Override
	public Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[]colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = location.getRow(), col = location.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.rowOutOfBounds(resultant_row)) {
				resultant_row = resultant_row < 0 ? resultant_row + super.getNumRows(): super.getNumRows() - resultant_row;
			} if (super.colOutOfBounds(resultant_col)) {
				resultant_col = resultant_col < 0 ? resultant_col + super.getNumCols(): super.getNumCols() - resultant_col;
			}
			output.add(super.get(new GridLocation(resultant_row, resultant_col)));
		}
		return output;
	}

}
