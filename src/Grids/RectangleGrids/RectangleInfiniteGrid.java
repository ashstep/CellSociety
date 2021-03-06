package Grids.RectangleGrids;

import java.util.ArrayList;
import java.util.Collection;
import back_end.Cell;
import utilities.GridLocation;

public class RectangleInfiniteGrid extends RectangularGrid {
	
	/**
	 * 
	 * @param cellGrid
	 * @param instanceCell
	 */
	public RectangleInfiniteGrid(Cell[][] cellGrid){
		super(cellGrid);
	}
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * @param instanceCell
	 */
	public RectangleInfiniteGrid(int numRows, int numCols) {
		super(numRows, numCols);
	}
	
	/**
	 * Overrides setCellAt in Grid which throws an error.
	 * Normally if(! isValidPosition(row, col) this throws ArrayIndexOutOfBoundsException. 
	 * Now resizes the grid
	 */
	@Override
	public void setCellAt(GridLocation abstractedLocation, Cell cell) throws IllegalArgumentException{
		int abstractedRow = abstractedLocation.getRow(), abstractedCol = abstractedLocation.getCol();
		if(cell.getClass().isInstance( isValidAbstractedPosition(abstractedLocation))){
			//TODO no need to cast?
			super.setCellAt(abstractedLocation, cell);
		} else if(! isValidAbstractedPosition(abstractedRow, abstractedCol)){
			super.resize(abstractedLocation);
			this.setCellAt(abstractedLocation, cell);
		} else {
			throw new IllegalArgumentException("Cell type is wrong: ");
		}
	}

	
	/**
	 * get the neighbors from the original grid.
	 */
	@Override
	public Collection<Cell> getNeighbors(GridLocation location, int flag) {
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		return getNeighbors(location, rowOffset, colOffset);
	}
	
	@Override
	public Collection<Cell> getNeighbors(GridLocation abstractedLocation, int[] rowOffset, int[] colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = abstractedLocation.getRow(), col = abstractedLocation.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
//			if (super.isValidAbstractedPosition(resultant_row, resultant_col)) {
				output.add(this.getCellAt(new GridLocation(resultant_row, resultant_col)));
//			} else {
//				super.resize(new GridLocation(resultant_row, resultant_col));
//				output.add(getCellAt(new GridLocation(resultant_row, resultant_col)));
//			}
		}
		return output;
	}

	
	
	/**
	 * returns the cell at the location specified by abstractedLocation
	 * @param abstractedLocation
	 * @return cell at location
	 */
	@Override
	public Cell getCellAt(GridLocation abstractedLocation){
		if(super.isValidAbstractedPosition(abstractedLocation)){
			return super.getCellAt(abstractedLocation);
		} else {
			super.resize(abstractedLocation);
			return super.getCellAt(abstractedLocation);
		}
	}

	@Override
	public Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag) {
		int row = location.getRow(), col = location.getCol();
		ArrayList<GridLocation> output = new ArrayList<GridLocation>();
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.getCellAt(new GridLocation(resultant_row, resultant_col)).getMyType() == neighborType) {//super.getGrid().isValidAbstractedPosition(resultant_row, resultant_col)
				output.add(new GridLocation(resultant_row, resultant_col));
			}
		}
		return output;
	}
}
