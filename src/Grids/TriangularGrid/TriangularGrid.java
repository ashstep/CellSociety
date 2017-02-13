package Grids.TriangularGrid;

import java.util.Arrays;

import Grids.Grid;
import back_end.Cell;

public abstract class TriangularGrid extends Grid {
	private final int[] TRIANGLE_ROW_OFFSET={0, 0, 1, -1, -1, -1, 0, 0, 1, 1, 1, 1};
	private final int[] TRIANGLE_COL_OFFSET ={-1, 1, 0, -1, 0, 1, -2, -2, -2, -1, 1, 2};
	private final int[] FLAG_TO_END_INDEX={3, 12};
	
	public TriangularGrid(Cell[][] cellGrid) {
		super(cellGrid);
	}

	public TriangularGrid(int numRows, int numCols){// Cell instanceCell) {
		super(numRows, numCols);
	}
	
	
	/**
	 * 
	 * @param flag 0 if top, bottom, left and right; 1 if all 12 neighbors
	 * @return
	 */
	protected int[] getRowOffsetArray(int flag){
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(TRIANGLE_ROW_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
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
			return Arrays.copyOfRange(TRIANGLE_COL_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}

}
