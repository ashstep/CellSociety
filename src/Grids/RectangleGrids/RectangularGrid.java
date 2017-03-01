package Grids.RectangleGrids;

import java.util.Arrays;
import Grids.Grid;
import back_end.Cell;

public abstract class RectangularGrid extends Grid {
	private final int[] RECTANGLE_ROW_OFFSET={0, 0, -1, 1, -1, -1, 1, 1};
	private final int[] RECTANGLE_COL_OFFSET ={-1, 1, 0, 0, -1, 1, -1, 1};
	private final int[] FLAG_TO_END_INDEX={8, 4};

	public RectangularGrid(Cell[][] cellGrid) {
		super(cellGrid);
		// TODO Auto-generated constructor stub
	}

	public RectangularGrid(int numRows, int numCols) {
		super(numRows, numCols);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param flag 0 if top, bottom, left and right; 1 if all 8 neighbors
	 * @return
	 */
	protected int[] getRowOffsetArray(int flag){
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(RECTANGLE_ROW_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}
	
	/**
	 * 
	 * @param flag 0 if top, bottom, left and right; 1 if all 8 neighbors
	 * @return
	 */
	protected int[] getColOffsetArray(int flag){
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(RECTANGLE_COL_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}
}
