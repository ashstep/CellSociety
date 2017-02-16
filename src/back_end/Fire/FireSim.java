package back_end.Fire;

import java.util.ArrayList;
import java.util.function.Consumer;
import Grids.*;
import back_end.Simulation;
import back_end.SimulationInfo;
import utilities.GridLocation;
/**
 * Purpose: Class that implements the unique properties of the fire simulation.
 * Assumptions: Each cell will change (or not) based on its neighbors and current state
 * Dependencies: Extends simulation class and thus has base implemented methods which are assumed to be performed in each FireSom
 * Example of use: When a file is read this class is the main one that brings all the information together (all cells take on the 
 * properties in FireSimInfo and act according to properties in FireCell).
 * @author Ashka Stephen
 */

public class FireSim extends Simulation {
	private FireSimInfo myInfo;
	private final int NEIGHBOR_FLAG = 0;
	
	/**
	 * Constructor
	 * @param probCatch - probability of the tree catching on fire and a int[][] that holds the location 
	 * of each fire cell
	 * @param typeGrid - grid on which the cells will interact with each other 
	 * @param boundsType - infinite/finite
	 * @param shapeType - visual representation of this class
	 * @return object that takes in information and will be used to implement the changing display
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
		super.makeGrid(boundsType, shapeType, cellGrid);
	}


	/**
	 * Function: Creates a new grid which stores updated values of the cells based on interactions outlined in other classes.
	 */
	@Override
	public Grid updateGrid() {
		int numRows=super.getNumRows(), numCols=super.getNumCols();
		Grid copy = createCellGrid(super.deepCopyCellArray(super.getCellGrid().getContainer()));
		Grid oldGrid=super.getCellGrid();
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numCols; col++){		
				GridLocation location=new GridLocation(row, col);
				oldGrid.setCellAt(location, new FireCell((FireCell)copy.getCellAt(location)));
				oldGrid.getCellAt(location).checkAndTakeAction(copy.getNeighbors(location, NEIGHBOR_FLAG), myInfo);
			}
		}
		super.setCellGrid(oldGrid);
		return oldGrid;
	}
	
	@Override
	protected GridLocation findEmptySpot(Grid newgrid, int currentRow, int currentCol) {
		return null;
	}

	/**
	 * Function: check info passed
	 * @param newInfo = passes information relevant to this simulation (probaility that a tree catches on fire).
	 */
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
	public ArrayList<String> getParameterList(){
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add("probCatch");
		return parameterList;
	}


	@Override
	public Consumer<Number> getChangeMethod(String x){
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