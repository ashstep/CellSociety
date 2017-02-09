package back_end.gameOfLifePack;
import java.util.ArrayList;
import java.util.function.Consumer;

import back_end.Cell;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.Grid;
import utilities.GridLocation;
import utilities.RectangleFiniteGrid;
import utilities.RectangleInfiniteGrid;
import utilities.RectangleToroidalGrid;
public class GameOfLifeSim extends Simulation{
	
	private final GameOfLifeCell TYPE_CELL=new GameOfLifeCell(1);
	private final int[] ROW_OFFSET={-1, -1, -1,  0, 0,   1, 1, 1};
	private final int[] COL_OFFSET ={-1,   0,  1, -1, 1, -1, 0, 1};
	
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
		
		setGrid(gridType, cellGrid);
	}


	private void setGrid(String gridType, GameOfLifeCell[][] cellGrid) {
		if (gridType.equals("Toroidal")) super.setGrid(new RectangleToroidalGrid(cellGrid, TYPE_CELL));
		else if (gridType.equals("Finite")) 
			{
				super.setGrid(new RectangleFiniteGrid(cellGrid, TYPE_CELL));
			}
		else if (gridType.equals("Infinite")) super.setGrid(new RectangleInfiniteGrid(cellGrid, TYPE_CELL));
		else throw new Error("Incorrect Grid Type");
	}
	
	
	/**
	 * updates and returns the grid. No cells move in position in this simulation
	 * @return the updated grid
	 */
	@Override
	public Grid updateGrid() {
		int numRows = super.getNumRows(), numCols = super.getNumCols();
		//TODO: how to switch the Grid object?
		Grid newGrid=new RectangleToroidalGrid(numRows, numCols, TYPE_CELL);
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){
				Grid oldGrid=super.getGrid();
				GridLocation location=new GridLocation(row, col);
				newGrid.setCellAt(location, new GameOfLifeCell((GameOfLifeCell)oldGrid.getCellAt(location)));
				newGrid.getCellAt(location).checkAndTakeAction(oldGrid.getNeighbors(location, ROW_OFFSET, COL_OFFSET), null);
			}
		}
		super.setGrid(newGrid);
		return newGrid;
	}

//	/**
//	 * get the neighbors from the original grid
//	 * top, down, left, right
//	 * top-left, top-right
//	 * bottom-left, bottom-right
//	 */
//	@Override
//	protected ArrayList<Cell> getNeighbors(int row, int col) {
//		ArrayList<Cell> output=new ArrayList<Cell>();	
//		for(int i=0; i<ROW_OFFSET.length; i++){
//			int resultant_row=row+ROW_OFFSET[i], resultant_col=col+COL_OFFSET[i];
//			if(isValidPosition(resultant_row, resultant_col)){
//				output.add(super.getArrayGrid()[resultant_row][resultant_col]);
//			}
//		}
//		return output;
//	}
	

	/**
	 * no general environment attribute in this simulation so no use
	 */
	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		
	}


	@Override
	protected GridLocation findEmptySpots(Cell[][] grid, int currentRow, int currentCol) {
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