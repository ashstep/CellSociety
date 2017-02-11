package back_end.gameOfLifePack;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;

import Grids.*;
import Grids.RectangleGrids.*;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;
public class GameOfLifeSim extends Simulation{
	
	private final GameOfLifeCell TYPE_CELL=new GameOfLifeCell(1);
	private final int NEIGHBOR_FLAG=1;
	
	/**
	 * 
	 * @param typeGrid
	 */
	public GameOfLifeSim (int[][] typeGrid, String gridType)
	{
		int numRows = typeGrid.length;
		int numCols = typeGrid[0].length;
		GameOfLifeCell[][] cellGrid=new GameOfLifeCell[numRows][numCols];
		
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				cellGrid[row][col]=new GameOfLifeCell(typeGrid[row][col]);
			}
		}
		super.setGrid(new RectangleInfiniteGrid(cellGrid, TYPE_CELL));
	}
	
	
	/**
	 * updates and returns the grid. No cells move in position in this simulation
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
		//TODO: how to switch the Grid object?
		Grid newGrid=new RectangleInfiniteGrid(numRows, numCols, TYPE_CELL);
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				Grid oldGrid=super.getGrid();
				GridLocation location=new GridLocation(row, col);
				newGrid.setCellAt(location, new GameOfLifeCell((GameOfLifeCell)oldGrid.getCellAt(location)));
				newGrid.getCellAt(location).checkAndTakeAction(oldGrid.getNeighbors(location, NEIGHBOR_FLAG), null);
				
			}
		}
		super.setGrid(newGrid);
		return newGrid;
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
	public ArrayList<String> getParameterList()
	{
		return new ArrayList<String>();
	}


	@Override
	public Consumer<Number> getChangeMethod(String x)
	{
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