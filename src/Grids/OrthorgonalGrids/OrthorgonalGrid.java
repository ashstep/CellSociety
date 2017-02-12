/**
 * 
 */
package Grids.OrthorgonalGrids;

import java.util.Collection;

import Grids.Grid;
import back_end.Cell;
import utilities.GridLocation;

/**
 * @author YuxiangHe
 *
 */
public abstract class OrthorgonalGrid extends Grid {

	/**
	 * @param cellGrid
	 * @param instanceCell
	 */
	public OrthorgonalGrid(Cell[][] cellGrid) {
		super(cellGrid);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public OrthorgonalGrid(int numRows, int numCols) {
		super(numRows, numCols);
	}
	
	@Override
	public Collection<Cell> getNeighbors(GridLocation abstractedLocation, int flag) {
		return null;
	}

	
	@Override
	public Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag) {
		return null;
	}

	
	@Override
	protected int[] getRowOffsetArray(int flag) {
		return null;
	}

	@Override
	protected int[] getColOffsetArray(int flag) {
		return null;
	}

}
