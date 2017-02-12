package back_end.gameOfLifePack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.*;
import Grids.RectangleGrids.*;
import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;

public class GameOfLifeSim extends Simulation {

	private final GameOfLifeCell TYPE_CELL = new GameOfLifeCell(1);
	private final int NEIGHBOR_FLAG = 1;

	/**
	 * 
	 * @param typeGrid
	 */
	public GameOfLifeSim(int[][] typeGrid, String gridType) {
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		GameOfLifeCell[][] cellGrid = new GameOfLifeCell[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				cellGrid[row][col] = new GameOfLifeCell(typeGrid[row][col]);
			}
		}
		// super.setGrid(new RectangleInfiniteGrid(cellGrid, TYPE_CELL));
		setGrid(gridType, cellGrid);
	}

	private void setGrid(String gridType, GameOfLifeCell[][] cellGrid) {
		if (gridType.equals("Toroidal"))
			super.setGrid(new RectangleToroidalGrid(cellGrid, TYPE_CELL));
		else if (gridType.equals("Finite"))
			super.setGrid(new RectangleFiniteGrid(cellGrid, TYPE_CELL));
		else if (gridType.equals("Infinite"))
			super.setGrid(new RectangleInfiniteGrid(cellGrid, TYPE_CELL));
		else
			throw new Error("Incorrect Grid Type");
	}

	/**
	 * updates and returns the grid. No cells move in position in this
	 * simulation
	 * 
	 * @return the updated grid
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		// TODO: how to switch the Grid object?
		Grid copy = createGrid(super.deepCopyCellArray(super.getGrid().getContainer()), TYPE_CELL);
		Grid oldGrid = super.getGrid();
		int x = 0;
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				GridLocation location = new GridLocation(row, col);
				oldGrid.setCellAt(location, new GameOfLifeCell((GameOfLifeCell) copy.getCellAt(location)));
				oldGrid.getCellAt(location).checkAndTakeAction(copy.getNeighbors(location, NEIGHBOR_FLAG), null);
			}
		}
		super.setGrid(oldGrid);
		return oldGrid;
	}



	/**
	 * no general environment attribute in this simulation so no use
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {

	}

	@Override
	protected GridLocation findEmptySpots(Grid grid, int currentRow, int currentCol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimulationInfo getSimInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getParameterList() {
		return new ArrayList<String>();
	}

	@Override
	public Consumer<Number> getChangeMethod(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getSliderLowerBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSliderUpperBound(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrentValue(String x) {
		// TODO Auto-generated method stub
		return 0;
	}

}