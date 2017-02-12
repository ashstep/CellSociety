package Grids.HexagonalGrids;

import java.util.ArrayList;
import java.util.Collection;

import back_end.Cell;
import utilities.GridLocation;

public class HexFiniteGrid extends HexagonalGrid {

	public HexFiniteGrid(Cell[][] cellGrid, Cell instanceCell) {
		super(cellGrid, instanceCell);
	}

	public HexFiniteGrid(int numRows, int numCols, Cell instanceCell) {
		super(numRows, numCols, instanceCell);
	}

	@Override
	public Collection<Cell> getNeighbors(GridLocation abstractedLocation, int flag) {
		Collection<Cell> output = new ArrayList<Cell>();
		int row = abstractedLocation.getRow(), col = abstractedLocation.getCol();
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)) {
				output.add(super.getCellAt(new GridLocation(resultant_row, resultant_col)));
			}
		}
		return output;
	}

	@Override
	public Collection<GridLocation> getNeighborLocationByType(GridLocation location, int neighborType, int flag) {
		int row = location.getRow(), col = location.getCol();
		ArrayList<GridLocation> output = new ArrayList<GridLocation>();
		int[] rowOffset=super.getRowOffsetArray(flag), colOffset=super.getColOffsetArray(flag);
		for (int i = 0; i < rowOffset.length; i++) {
			int resultant_row = row + rowOffset[i], resultant_col = col + colOffset[i];
			if (super.isValidAbstractedPosition(resultant_row, resultant_col)
					&& super.getCellAt(new GridLocation(resultant_row, resultant_col)).getMyType() == neighborType){
				output.add(new GridLocation(resultant_row, resultant_col));
			}
		}
		return output;
	}

}
