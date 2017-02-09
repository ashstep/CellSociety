package utilities;

import java.util.ArrayList;
import java.util.Collection;

import back_end.Cell;

public class RectangleInfiniteGrid extends Grid {
	
	
	/**
	 * 
	 * @param cellGrid
	 * @param instanceCell
	 */
	public RectangleInfiniteGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public RectangleInfiniteGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}

	/**
	 * get the neighbors from the original grid. top, down, left, right
	 */
	@Override
	public Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[]colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = location.getRow(), col = location.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidPosition(resultant_row, resultant_col)) {
				output.add(super.getContainer()[resultant_row][resultant_col]);
			}
		}
		return output;
	}

}
