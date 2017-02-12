package Grids.TriangularGrid;

import java.util.ArrayList;
import java.util.Collection;

import back_end.Cell;
import utilities.GridLocation;

public class TriangularToroidalGrid extends TriangularGrid {

	public TriangularToroidalGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}

	public TriangularToroidalGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}

	/**
	 * collects the neighbors surrounding the cell at location
	 * @param flag
	 */
	@Override
	public Collection<Cell> getNeighbors(GridLocation abstractedLocation, int flag) {
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		return getNeighbors(abstractedLocation, rowOffset, colOffset);
	}
	
	@Override
	public Collection<Cell> getNeighbors(GridLocation abstractedLocation, int[] rowOffset, int[] colOffset) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = abstractedLocation.getRow(), col = abstractedLocation.getCol();
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.abstractedRowOutOfBounds(resultant_row)) {
				resultant_row = resultant_row < 0 ? resultant_row + super.getNumRows(): super.getNumRows() - resultant_row;
			} if (super.abstractedColOutOfBounds(resultant_col)) {
				resultant_col = resultant_col < 0 ? resultant_col + super.getNumCols(): super.getNumCols() - resultant_col;
			}
			output.add(super.getCellAt(new GridLocation(resultant_row, resultant_col)));
		}
		return output;
	}

	@Override
	public Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag) {
		Collection<GridLocation> output = new ArrayList<GridLocation>();
		int row = location.getRow(), col = location.getCol();
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.abstractedRowOutOfBounds(resultant_row)) {
				resultant_row = resultant_row < 0 ? resultant_row + super.getNumRows(): super.getNumRows() - resultant_row;
			} if (super.abstractedColOutOfBounds(resultant_col)) {
				resultant_col = resultant_col < 0 ? resultant_col + super.getNumCols(): super.getNumCols() - resultant_col;
			}
			if (super.getCellAt(new GridLocation(resultant_row, resultant_col)).getMyType() == neighborType){
				output.add(new GridLocation(resultant_row, resultant_col));
			}
		}
		return output;
	}

	

}
