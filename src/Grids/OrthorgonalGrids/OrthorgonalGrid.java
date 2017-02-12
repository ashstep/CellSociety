/**
 * 
 */
package Grids.OrthorgonalGrids;

import java.util.Collection;

import Grids.Grid;
import back_end.Cell;
import utilities.GridLocation;

/**
 * @author Richard
 *
 */
public abstract class OrthorgonalGrid extends Grid {

	/**
	 * @param cellGrid
	 * @param instanceCell
	 */
	public OrthorgonalGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public OrthorgonalGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
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
