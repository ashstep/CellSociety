package Grids.HexagonalGrids;

import java.util.Arrays;
import Grids.Grid;
import back_end.Cell;

public abstract class HexagonalGrid extends Grid {
	//Odd-r horizontal type
	private final int[] HEXAGON_ROW_OFFSET={-1, -1, 0, 0, 1, 1};
	private final int[] HEXAGON_EVENROW_COL_OFFSET ={-1, 0, -1, 1, -1, 0};
	private final int[] HEXAGON_ODDROW_COL_OFFSET ={0, 1, -1, 1, 0, 1};
	private final int[] FLAG_TO_END_INDEX={3, 12};
	
	public HexagonalGrid(Cell[][] cellGrid) {
		super(cellGrid);
	}

	public HexagonalGrid(int numRows, int numCols) {
		super(numRows, numCols);
	}

	@Override
	protected int[] getRowOffsetArray(int flag) {
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(HEXAGON_ROW_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}

	@Override
	protected int[] getColOffsetArray(int flag) {
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(HEXAGON_EVENROW_COL_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}
	

	protected int[] getColOffsetArrayOddRow(int flag) {
		if(flag<0 || flag>=FLAG_TO_END_INDEX.length){
			throw new IllegalArgumentException();
		}else {
			return Arrays.copyOfRange(HEXAGON_ODDROW_COL_OFFSET, 0, FLAG_TO_END_INDEX[flag]);
		}
	}

}
