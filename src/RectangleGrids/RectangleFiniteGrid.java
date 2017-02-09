package RectangleGrids;

import java.util.ArrayList;
import java.util.Collection;

import Grids.Grid;
import back_end.Cell;
import utilities.GridLocation;

public class RectangleFiniteGrid extends Grid {
		
	public RectangleFiniteGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public RectangleFiniteGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}

	/**
	 * 
	 */
	public Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[]colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = location.getRow(), col = location.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)) {
				output.add(super.getCellAt(new GridLocation(resultant_row, resultant_col)));
			}
		}
		return output;
	}

}
