package back_end.Fire;

import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.*;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;
/**
 * Class that implements the unique properties of the fire simulation
 * @author Ashka Stephen
 *
 */

public class FireSim extends Simulation {
	private FireSimInfo myInfo;
	private final FireCell TYPE_CELL=new FireCell(1);
	private final int NEIGHBOR_FLAG=0;
	/**
	 * Constructor
	 * @param probablilty of the tree catching on fire and a int[][] that holds the location 
	 * of each fire cell
	 */
	public FireSim(int[][] typeGrid, double probCatch, String boundsType, String shapeType)
	{
		myInfo = new FireSimInfo(probCatch);

		int numRows = typeGrid.length ;
		int numCols = typeGrid[0].length ;
		FireCell[][] cellGrid = new FireCell[numRows][numCols];
		for(int row = 0; row < numRows ; row++){
			for(int col = 0 ; col < numCols; col++){
				cellGrid[row][col]=new FireCell(typeGrid[row][col]);
			}
		}
		super.makeGrid(boundsType, shapeType, cellGrid, TYPE_CELL);
	}


	/**
	 * Creates a new grid which stores updated values of the cells based on interactions
	 */
	@Override
	public Grid updateGrid() {
		int numRows=super.getNumRows(), numCols=super.getNumRows();
		Grid copy = createGrid(super.getCellGrid(), super.deepCopyCellArray(super.getCellGrid().getContainer()), TYPE_CELL);
		Grid oldGrid=super.getCellGrid();
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){		
				GridLocation location=new GridLocation(row, col);
				oldGrid.setCellAt(location, new FireCell((FireCell)copy.getCellAt(location)));
				oldGrid.getCellAt(location).checkAndTakeAction(copy.getNeighbors(location, NEIGHBOR_FLAG), myInfo);
			}
		}
		super.setGrid(oldGrid);
		return oldGrid;
	}
	


	@Override
	protected GridLocation findEmptySpots(Grid newgrid, int currentRow, int currentCol) {
		return null;
	}

	@Override
	public void setSimInfo(SimulationInfo newInfo) {
		if(newInfo instanceof FireSimInfo){
			myInfo = (FireSimInfo) newInfo;
		} else {
			throw new Error("Information is not valid");
		}
	}

	@Override
	public SimulationInfo getSimInfo() {
		return myInfo;
	}


	@Override
	public ArrayList<String> getParameterList()
	{
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add("probCatch");
		return parameterList;
	}


	@Override
	public Consumer<Number> getChangeMethod(String x)
	{
		Consumer<Number> r = (Number n) -> { };
		
		if (x.equals("probCatch")) r = (Number n) -> {myInfo.setProbCatch(n.doubleValue());};

		return r;
	}


	@Override
	public double getSliderLowerBound(String x) {
		if (x.equals("probCatch")) return 0;
		return 0;
	}


	@Override
	public double getSliderUpperBound(String x) {
		if (x.equals("probCatch")) return 1.0;
		return 0;
	}


	@Override
	public double getCurrentValue(String x) {
		if (x.equals("probCatch")) return myInfo.getProbCatch();
		return 0;
	}


}
