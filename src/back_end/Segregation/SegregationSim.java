// This entire file is my masterpiece.
// Yuxiang He
package back_end.Segregation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Consumer;
import Grids.Grid;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;

/**
 * @author Yuxiang He
 * Simulation class for Segregation. Maintains and updates the cell grid.
 *
 */
public class SegregationSim extends Simulation {
	private final int TYPE_EMPTY = 0;
	private SegregationSimInfo myInfo;
	private final int NEIGHBOR_FLAG=0;
	private final int RANDOM_ITER_LIMIT = 2000;
	
	
	/**
	 * 
	 * @param typeGrid
	 *            int[][] that specifies the type of SegregationCell at the
	 *            corresponding position in myGrid
	 * @param threshold
	 *            satisfaction threshold for each cell, environment attribute
	 */
	public SegregationSim(int[][] typeGrid, int threshold, String boundsType, String shapeType) {
		myInfo = new SegregationSimInfo(threshold);
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SegregationCell[][] cellGrid = new SegregationCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				cellGrid[row][col] = new SegregationCell(typeGrid[row][col]);
			}
		}
		super.makeGrid(boundsType, shapeType, cellGrid);
	}


	/**
	 * makes a copy of the old grid, and do updates on that copy.
	 * After updating, set the grid to be the modified copy.
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		Grid copy = createCellGrid(super.deepCopyCellArray(super.getCellGrid().getContainer()));
		Grid oldGrid = super.getCellGrid();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				SegregationCell cell=new SegregationCell ((SegregationCell)(copy.getCellAt(new GridLocation(row, col))));
				if (cell.getMyType() == TYPE_EMPTY) {
					continue;
				}
				GridLocation location=new GridLocation(row, col);
				boolean isMoving = cell.checkAndTakeAction(copy.getNeighbors(location, NEIGHBOR_FLAG), myInfo).toMove();
				if (isMoving) {
					moveToNewSpot(oldGrid, row, col, cell);
				} else if (!isMoving && oldGrid.getCellAt(location).getMyType() == TYPE_EMPTY) {
					relocateToNewSpot(oldGrid, row, col, cell);
				} else {
					oldGrid.setCellAt(location, cell);
				}
			}
		}
		return oldGrid;
	}

	/**
	 * moves the cell to a new spot if the position [row, col] is already taken
	 * @param newGrid
	 * @param row
	 * @param col
	 * @param cell
	 */
	private void relocateToNewSpot(Grid newGrid, int row, int col, SegregationCell cell) {
		GridLocation newPos = findEmptySpot(newGrid, row, col);
		newGrid.setCellAt(newPos, new SegregationCell(cell.getMyType()));
	}

	/**
	 * given the current position of the cell, move it to a new position in newGrid
	 * 
	 * @param newGrid
	 * @param row current row
	 * @param col current column
	 * @param cell  the cell
	 */
	private void moveToNewSpot(Grid newGrid, int row, int col, SegregationCell cell) {
		GridLocation newPos = findEmptySpot(newGrid, row, col);
		newGrid.setCellAt(new GridLocation(row, col), new SegregationCell(TYPE_EMPTY));
		newGrid.setCellAt(newPos, new SegregationCell(cell.getMyType()));
	}


	/**
	 * generates a GridLocation where grid contains an empty type cell
	 * and GridLocation is not the same location as [currentRow, currentCol]
	 * @param grid
	 */
	protected GridLocation findEmptySpot(Grid grid, int currentRow, int currentCol) {
		int iter = 0;
		Random rn = new Random();
		ArrayList<GridLocation> emptySpaces = findPotentialEmptySpaces(grid);
		GridLocation location=emptySpaces.get(rn.nextInt(emptySpaces.size()));
		int row=location.getRow(), col=location.getCol();
		while (!(row != currentRow && col != currentCol) && iter < RANDOM_ITER_LIMIT) {
			iter++;
			location = emptySpaces.get(rn.nextInt(emptySpaces.size()));
			row=location.getRow();
			col=location.getCol();
		}
		if (!(row != currentRow && col != currentCol)) {
			for(GridLocation loopLocation : emptySpaces){
				if(!(grid.getCellAt(loopLocation).getMyType() == TYPE_EMPTY && loopLocation.getRow() != currentRow && loopLocation.getCol() != currentCol)){
					return loopLocation;
				}
			}
		}
		return new GridLocation(row, col);
	}

	/**
	 * finds all empty spaces in the current grid
	 * 
	 * @param grid
	 * @return an ArrayList of GridLocation specifying the locations
	 */
	private ArrayList<GridLocation> findPotentialEmptySpaces(Grid grid) {
		ArrayList<GridLocation> locations = new ArrayList<GridLocation>();
		int numRows = grid.getNumRows(), numCols = grid.getNumCols();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				GridLocation location=new GridLocation(row, col);
				if (grid.getCellAt(location).getMyType() == TYPE_EMPTY) {
					locations.add(location);
				}
			}
		}
		return locations;
	}

	/**
	 * updates the threshold for the cells to be satisfied
	 * 
	 * @param newInfo the new SegregationSimInfo object to be set to myInfo
	 * @throws
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		if(newInfo instanceof SegregationSimInfo){
			myInfo = (SegregationSimInfo) newInfo;
		} else {
			throw new Error("newInfo must be SegregationSimInfo");
		}
	}

	/**
	 * getter for myInfo
	 * @return mtInfo
	 */
	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}

	/**
	 * updates the threshold for the cells to be satisfied.
	 * 
	 * @param newThreshold
	 *            new value for threshold
	 */
	public void setThreshold(int newThreshold) {
		myInfo.setThreshold(newThreshold);
	}

	
	/**
	 * Please see the abstract class declaration for the following methods
	 * 
	 */
	@Override
	public Collection<String> getParameterList()
	{
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add("Threshold");
		return parameterList;
	}

	@Override
	public Consumer<Number> getChangeMethod(String x)
	{
		Consumer<Number> r = (Number n) -> {};
		if (x.equals("Threshold")) r = (Number n) -> {myInfo.setThreshold(n.intValue());};
		return r;
	}

	@Override
	public double getSliderLowerBound(String x)
	{
		if (x.equals("Threshold"))return 1;
		return 0;
	}

	@Override
	public double getSliderUpperBound(String x)
	{
		if (x.equals("Threshold")) return 100;
		return 0;
	}

	@Override
	public double getCurrentValue(String x)
	{
		if (x.equals("Threshold")) return myInfo.getThreshold();
		return 0;
	}
}