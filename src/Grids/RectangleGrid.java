/**
 * 
 */
package Grids;

import back_end.Cell;

/**
 * @author Yuxiang He
 *
 */
public abstract class RectangleGrid extends Grid {

//	private final int[] ROW_OFFSET = { -1, -1, -1, 0, 0, 1, 1, 1 };
//	private final int[] COL_OFFSET = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	/**
	 * @param cellGrid
	 * @param instanceCell
	 */
	public RectangleGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}

	public RectangleGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}

}
