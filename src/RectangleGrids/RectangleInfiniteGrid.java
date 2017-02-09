package RectangleGrids;

import java.util.ArrayList;
import java.util.Collection;

import Grids.Grid;
import back_end.Cell;
import utilities.GridLocation;

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
	 * Overrides setCellAt in Grid which throws an error.
	 * Normally if(! isValidPosition(row, col) this throws ArrayIndexOutOfBoundsException. 
	 * Now resizes the grid
	 */
	@Override
	public void setCellAt(GridLocation abstractedLocation, Cell cell) throws IllegalArgumentException{
		int row = abstractedLocation.getRow(),  col = abstractedLocation.getCol();
		if(cell.getClass().isInstance(super.getInstanceCell()) && isValidAbstractedPosition(abstractedLocation)){
			//TODO no need to cast?
			super.setCellAt(abstractedLocation, cell);
		} else if(! isValidAbstractedPosition(row, col)){
			super.resize(abstractedLocation);
			setCellAt(abstractedLocation, cell);
		} else {
			throw new IllegalArgumentException("Cell should be type: "+super.getInstanceCell().getClass().toString());
		}
	}

	
	/**
	 * get the neighbors from the original grid.
	 */
	@Override
	public Collection<Cell> getNeighbors(GridLocation location, int[] rowOffset, int[]colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = location.getRow(), col = location.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)) {
				output.add(getCellAt(new GridLocation(resultant_row, resultant_col)));
			} else {
				super.resize(new GridLocation(resultant_row, resultant_col));
				output.add(getCellAt(new GridLocation(resultant_row, resultant_col)));
			}
		}
		return output;
	}

	
	
	/**
	 * returns the cell at the location specified by abstractedLocation
	 * @param abstractedLocation
	 * @return cell at location
	 */
	@Override
	public Cell getCellAt(GridLocation abstractedLocation) throws ArrayIndexOutOfBoundsException{
		if(isValidAbstractedPosition(abstractedLocation)){
			return super.getCellAt(abstractedLocation);
		} else {
			super.resize(abstractedLocation);
			return getCellAt(abstractedLocation);
		}
	}
}
