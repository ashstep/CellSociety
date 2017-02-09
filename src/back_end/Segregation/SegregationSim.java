package back_end.Segregation;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import Grids.Grid;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;

public class SegregationSim extends Simulation {
	private final int TYPE_EMPTY = 0;
	private SegregationSimInfo myInfo;
	private final int RANDOM_ITER_LIMIT = 2000;

	/**
	 * 
	 * @param typeGrid
	 *            int[][] that specifies the type of SegregationCell at the
	 *            corresponding position in myGrid
	 * @param threshold
	 *            satisfaction threshold for each cell, environment attribute
	 */
	// repeated code for setting up Grid?
	public SegregationSim(int[][] typeGrid, int threshold) {
		myInfo = new SegregationSimInfo(threshold);

		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		SegregationCell[][] cellGrid = new SegregationCell[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				cellGrid[row][col] = new SegregationCell(typeGrid[row][col]);
			}
		}
		super.setArrayGrid(cellGrid);
	}


	/**
	 * makes a copy of the old grid, and do updates on that copy.
	 * After updating, set the grid to be the modified copy.
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		SegregationCell[][] oldGridCopy = (SegregationCell[][]) copyArray(super.getArrayGrid());
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if ((super.getArrayGrid()[row][col]).getMyType() == TYPE_EMPTY) {
					continue;
				}
				SegregationCell cell = new SegregationCell(oldGridCopy[row][col]);
				boolean isMoving = cell.checkAndTakeAction(getNeighbors(row, col), myInfo).toMove();
				if (isMoving) {
					moveToNewSpot(oldGridCopy, row, col, cell);
				} else if (!isMoving && oldGridCopy[row][col].getMyType() == TYPE_EMPTY) {
					relocateToNewSpot(oldGridCopy, row, col, cell);
				} else {
					oldGridCopy[row][col] = cell;
				}
			}
		}
		super.setArrayGrid(oldGridCopy);
		return new Grid(oldGridCopy);
	}

	private void relocateToNewSpot(SegregationCell[][] newGrid, int row, int col, SegregationCell cell) {
		GridLocation newPos = findEmptySpots(newGrid, row, col);
		newGrid[newPos.getRow()][newPos.getCol()] = new SegregationCell(cell);
	}

	/**
	 * given the current position of the cell, move it to a new position in
	 * newGrid
	 * 
	 * @param newGrid
	 * @param row current row
	 * @param col current column
	 * @param cell  the cell
	 */
	private void moveToNewSpot(SegregationCell[][] newGrid, int row, int col, SegregationCell cell) {
		GridLocation newPos = findEmptySpots(newGrid, row, col);
		newGrid[row][col] = new SegregationCell(TYPE_EMPTY);
		newGrid[newPos.getRow()][newPos.getCol()] = new SegregationCell(cell.getMyType());
	}

	
	/**
	 * generates a position where newGrid contains an empty type cell
	 */
	protected GridLocation findEmptySpots(Cell[][] grid, int currentRow, int currentCol) {
		int iter = 0;
		Random rn = new Random();
		ArrayList<GridLocation> emptySpaces = findEmptySpaces(grid);
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
				if(!(grid[loopLocation.getRow()][loopLocation.getCol()].getMyType() == TYPE_EMPTY && loopLocation.getRow() != currentRow && loopLocation.getCol() != currentCol)){
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
	private ArrayList<GridLocation> findEmptySpaces(Cell[][] grid) {
		ArrayList<GridLocation> locations = new ArrayList<GridLocation>();
		int numRows = grid.length, numCols = grid[0].length;
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (grid[row][col].getMyType() == TYPE_EMPTY) {
					locations.add(new GridLocation(row, col));
				}
			}
		}
		return locations;
	}

	/**
	 * get the neighbors from the original grid top, down, left, right top-left,
	 * top-right bottom-left, bottom-right
	 */
	@Override
	protected ArrayList<Cell> getNeighbors(int row, int col) {
		ArrayList<Cell> output = new ArrayList<Cell>();
		for (int i = 0; i < ROW_OFFSET.length; i++) {
			int resultant_row = row + ROW_OFFSET[i], resultant_col = col + COL_OFFSET[i];
			if (super.isValidPosition(resultant_row, resultant_col)) {
				output.add(super.getArrayGrid()[resultant_row][resultant_col]);
			}
		}
		return output;
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
	 * makes a copy of an old array. Each element is also points to a new copy
	 * 
	 * @param oldArray
	 * @return newArray
	 */
	protected Cell[][] copyArray(Cell[][] oldArray) {
		int numRows = oldArray.length;
		int numCols = oldArray[0].length;
		SegregationCell[][] copiedArray = new SegregationCell[numRows][numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				copiedArray[row][col] = new SegregationCell((SegregationCell) oldArray[row][col]);
			}
		}
		return copiedArray;
	}


	@Override
	public ArrayList<String> getParameterList()
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